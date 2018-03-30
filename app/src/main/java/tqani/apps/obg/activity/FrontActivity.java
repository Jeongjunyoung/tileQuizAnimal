package tqani.apps.obg.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import cn.refactor.lib.colordialog.ColorDialog;
import tqani.apps.obg.R;
import tqani.apps.obg.databinding.ActivityFrontBinding;
import tqani.apps.obg.service.UserApplication;
import tqani.apps.obg.service.UserServiceInterface;

public class FrontActivity extends AppCompatActivity implements View.OnClickListener, RewardedVideoAdListener{
    private ActivityFrontBinding mBinding;
    public static Context mContext;
    private AnimationDrawable drawable;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private RewardedVideoAd mRewarded;
    //Test
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
        AdView mAdView = mBinding.frontAdview;
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
        mBinding.playerQuiz.setOnClickListener(this);
        mBinding.teamQuiz.setOnClickListener(this);
        mBinding.addHintBtn.setOnClickListener(this);
        mBinding.frontLogo.setBackgroundResource(R.drawable.front_logo_anim);
        drawable = (AnimationDrawable) mBinding.frontLogo.getBackground();
        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        myRef = database.getReference("saving-data/user/"+mUser.getUid());
        final int hintNum = mUserService.getHintNum();
        mBinding.frontHintNum.setText(String.valueOf(hintNum));
        mRewarded = MobileAds.getRewardedVideoAdInstance(this);
        mRewarded.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(FrontActivity.this, TQActivity.class);
        switch (view.getId()) {
            case R.id.player_quiz:
                intent.putExtra("quizKinds", "player");
                startActivity(intent);
                finish();
                break;
            case R.id.team_quiz:
                intent.putExtra("quizKinds", "team");
                startActivity(intent);
                finish();
                break;
            case R.id.add_hint_btn:
                loadRewardedVideoAd();
                ColorDialog dialog = new ColorDialog(this);
                dialog.setAnimationEnable(true);
                dialog.setColor("#427158");
                dialog.setContentImage(R.drawable.hint_image);
                dialog.setContentTextColor("#000000");
                dialog.setPositiveListener("Ok", new ColorDialog.OnPositiveListener() {
                    @Override
                    public void onClick(ColorDialog colorDialog) {
                        //동영상 광고 재생
                        if (mRewarded.isLoaded()) {
                            mRewarded.show();
                        } else {
                            Toast.makeText(getApplicationContext(),"Ads. is not Ready..",Toast.LENGTH_SHORT).show();
                        }
                        colorDialog.dismiss();
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
    public void onResume() {
        mRewarded.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mRewarded.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mRewarded.destroy(this);
        super.onDestroy();
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
    private void loadRewardedVideoAd() {
        mRewarded.loadAd(getResources().getString(R.string.reward_ads_unit_id),
                new AdRequest.Builder()
                        //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                        .build());
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        int hint_num = mUserService.getHintNum() + rewardItem.getAmount();
        mUserService.setmHintNum(hint_num);
        myRef.child("hint_num").setValue(hint_num);
        mBinding.frontHintNum.setText(String.valueOf(hint_num));
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }
}
