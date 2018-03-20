package tq.apps.obg.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.refactor.lib.colordialog.ColorDialog;
import cn.refactor.lib.colordialog.PromptDialog;
import tq.apps.obg.R;
import tq.apps.obg.databinding.ActivityFrontBinding;
import tq.apps.obg.db.DBHelper;
import tq.apps.obg.domain.EmblemDBList;
import tq.apps.obg.domain.EmblemVO;
import tq.apps.obg.domain.PersonDBList;
import tq.apps.obg.domain.PersonVO;
import tq.apps.obg.domain.TileDBList;
import tq.apps.obg.domain.TileVO;
import tq.apps.obg.service.UserApplication;
import tq.apps.obg.service.UserService;
import tq.apps.obg.service.UserServiceInterface;

public class FrontActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityFrontBinding mBinding;
    public static Context mContext;
    private AnimationDrawable drawable;
    private UserServiceInterface mUserService = UserApplication.getInstance().getServiceInterface();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_front);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
    }
    private void initView() {
        mContext = this;
        /*dbHelper = new DBHelper(this);
        dbHelper.open();*/
        mBinding.playerQuiz.setOnClickListener(this);
        mBinding.teamQuiz.setOnClickListener(this);
        mBinding.addHintBtn.setOnClickListener(this);
        mBinding.frontLogo.setBackgroundResource(R.drawable.front_logo_anim);
        drawable = (AnimationDrawable) mBinding.frontLogo.getBackground();
        /*myRef = database.getReference("ggg/ggg");
        Map<String, String> map = new HashMap<>();
        //map.put("e_mail", user.getEmail());
        map.put("ggg", mUser.getEmail());
        myRef.child(mUser.getUid()).setValue(map);*/
        //userRef.child(mUser.getUid()).child
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(FrontActivity.this, TQActivity.class);
        switch (view.getId()) {
            case R.id.player_quiz:
                intent.putExtra("quizKinds", "player");
                startActivity(intent);
                break;
            case R.id.team_quiz:
                intent.putExtra("quizKinds", "team");
                startActivity(intent);
                break;
            case R.id.add_hint_btn:
                /*new PromptDialog(this)
                        .setDialogType(PromptDialog.DIALOG_TYPE_SUCCESS)
                        .setAnimationEnable(true)
                        .setTitleText(R.string.dialog_title)
                        .setContentText(R.string.dialog_content)
                        .setPositiveListener("OK", new PromptDialog.OnPositiveListener() {
                            @Override
                            public void onClick(PromptDialog promptDialog) {
                                promptDialog.dismiss();

                            }
                        }).show();*/
                ColorDialog dialog = new ColorDialog(this);
                dialog.setAnimationEnable(true);
                //dialog.setTitle(R.string.dialog_title);
                dialog.setColor("#427158");
                dialog.setContentImage(R.drawable.club_barca);
                dialog.setContentText(R.string.dialog_content);
                dialog.setContentTextColor("#000000");
                dialog.setPositiveListener("Ok", new ColorDialog.OnPositiveListener() {
                    @Override
                    public void onClick(ColorDialog colorDialog) {

                    }
                })
                .setNegativeListener("Cancel", new ColorDialog.OnNegativeListener() {
                    @Override
                    public void onClick(ColorDialog colorDialog) {
                        colorDialog.dismiss();
                    }
                }).show();
                break;
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            drawable.start();
        } else {
            drawable.stop();
        }
    }
}
