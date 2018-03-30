package tqani.apps.obg.activity;

import android.annotation.SuppressLint;
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

import java.util.List;

import tqani.apps.obg.R;
import tqani.apps.obg.databinding.ActivityLoadingBinding;
import tqani.apps.obg.db.DBHelper;
import tqani.apps.obg.domain.EmblemDBList;
import tqani.apps.obg.domain.EmblemVO;
import tqani.apps.obg.domain.PersonDBList;
import tqani.apps.obg.domain.PersonVO;
import tqani.apps.obg.domain.TileDBList;
import tqani.apps.obg.domain.TileVO;
import tqani.apps.obg.domain.UserVO;
import tqani.apps.obg.service.UserApplication;

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
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("saving-data/user");
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
                .requestServerAuthCode(getString(R.string.default_web_client_id))
                .build();
        //startSignInIntent();
        //startLoading();
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
        }, 200);
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
    @SuppressLint("RestrictedApi")
    private boolean isSignedIn() {
        return GoogleSignIn.getLastSignedInAccount(this) != null;
    }
    @SuppressLint("RestrictedApi")
    private void signInSilently() {
        GoogleSignInClient signInClient = GoogleSignIn.getClient(this, gso);
        signInClient.silentSignIn().addOnCompleteListener(this,
                new OnCompleteListener<GoogleSignInAccount>() {
                    @Override
                    public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                        if (task.isSuccessful()) {
                            GoogleSignInAccount signedInAccount = task.getResult();
                            firebaseAuthWithPlayGames(signedInAccount);
                        } else {
                            startSignInIntent();
                        }
                    }
                });
    }


    @Override
    protected void onResume() {
        super.onResume();
        signInSilently();
    }

    @SuppressLint("RestrictedApi")
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
                firebaseAuthWithPlayGames(signedInAccount);

            } else {
                String message = result.getStatus().getStatusMessage();
                if (message == null || message.isEmpty()) {
                    //startLoading();
                }
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
                            mUser = mAuth.getCurrentUser();
                            getUser(mUser.getUid());

                        } else {
                            //System.out.println("signInWithCredential:fail"+ task.getException());
                        }
                    }
                });
    }

    private void getUser(@NonNull final String uid) {
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild(uid)) {
                    UserVO vo = new UserVO("10", "0", "0");
                    myRef.child(uid).setValue(vo);
                    startLoading();
                } else {
                    startLoading();
                }
                UserApplication.getInstance().getServiceInterface().setHintNum();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
