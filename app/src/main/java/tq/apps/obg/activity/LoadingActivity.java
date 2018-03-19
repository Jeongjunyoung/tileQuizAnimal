package tq.apps.obg.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PlayGamesAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tq.apps.obg.R;
import tq.apps.obg.databinding.ActivityLoadingBinding;
import tq.apps.obg.db.DBHelper;
import tq.apps.obg.domain.EmblemDBList;
import tq.apps.obg.domain.EmblemVO;
import tq.apps.obg.domain.PersonDBList;
import tq.apps.obg.domain.PersonVO;
import tq.apps.obg.domain.TileDBList;
import tq.apps.obg.domain.TileVO;

public class LoadingActivity extends AppCompatActivity {
    private ActivityLoadingBinding mBinding;
    public static Context mContext;
    private DBHelper dbHelper;
    private static final int RC_SIGN_IN = 9001;
    private FirebaseAuth mAuth;
    private GoogleSignInOptions gso;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        mContext = this;
        dbHelper = new DBHelper(this);
        dbHelper.open();
        /*mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("saving-data/user");*/

        //userRef.child(mUser.getUid()).child("user").setValue(user);
        /*gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                .requestServerAuthCode(getString(R.string.default_web_client_id))
                .build();
        startSignInIntent();*/
        startLoading();
    }

    public void startLoading() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), FrontActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1200);
    }

    @Override
    public void finish() {
        overridePendingTransition(R.anim.loading_activity_anim_in, R.anim.loading_activity_anim_out);
        super.finish();
    }

    public List<TileVO> getTileList() {
        TileDBList tileDBList = new TileDBList();
        return tileDBList.getDBTileList();
    }

    public List<PersonVO> getPersonList() {
        PersonDBList personDBList = new PersonDBList();
        return personDBList.getDBPersonList();
    }

    public List<EmblemVO> getEmblemList() {
        EmblemDBList emblemDBList = new EmblemDBList();
        return emblemDBList.getDBPersonList();
    }
    public int getId(String imageName) {
        return getResources().getIdentifier("tq.apps.obg:drawable/" + imageName, null, null);
    }
    private boolean isSignedIn() {
        return GoogleSignIn.getLastSignedInAccount(this) != null;
    }
    private void signInSilently() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this, gso);
        signInClient.silentSignIn().addOnCompleteListener(this,
                new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        if (task.isSuccessful()) {
                            // The signed in account is stored in the task's result.
                            GoogleSignInAccount signedInAccount = task.getResult();
                            System.out.println("InSilently success");
                            firebaseAuthWithPlayGames(signedInAccount);
                        } else {
                            // Player will need to sign-in explicitly using via UI
                            System.out.println("fail");
                            startSignInIntent();
                        }
                    }
                });
    }


    @Override
    protected void onResume() {
        super.onResume();
        //signInSilently();
    }

    private void startSignInIntent() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this, gso);
        Intent intent = signInClient.getSignInIntent();
        startActivityForResult(intent, RC_SIGN_IN);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // The signed in account is stored in the result.
                GoogleSignInAccount signedInAccount = result.getSignInAccount();
                //startLoading();
                firebaseAuthWithPlayGames(signedInAccount);
                System.out.println("success");

            } else {
                String message = result.getStatus().getStatusMessage();
                if (message == null || message.isEmpty()) {
                    System.out.println("error");
                }
                //startLoading();
            }
        }
    }
    private void firebaseAuthWithPlayGames(GoogleSignInAccount acct) {
        AuthCredential credential = PlayGamesAuthProvider.getCredential(acct.getServerAuthCode());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            System.out.println("signInWithCredential:success");
                            mUser = mAuth.getCurrentUser();
                            getUser(mAuth.getUid());
                        } else {
                            // If sign in fails, display a message to the user.
                            System.out.println("signInWithCredential:fail"+ task.getException());
                        }

                        // ...
                    }
                });
    }

    private void getUser(@NonNull final String uid) {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(uid)) {
                    Map<String, String> map = new HashMap<>();
                    map.put("hint_num", "10");
                    myRef.child(uid).setValue(map);
                    startLoading();
                } else {
                    startLoading();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
