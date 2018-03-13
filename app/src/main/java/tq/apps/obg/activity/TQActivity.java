package tq.apps.obg.activity;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import tq.apps.obg.R;
import tq.apps.obg.databinding.ActivityTqBinding;
import tq.apps.obg.db.DBHelper;
import tq.apps.obg.domain.EmblemDBList;
import tq.apps.obg.domain.EmblemVO;
import tq.apps.obg.domain.PersonDBList;
import tq.apps.obg.domain.PersonVO;
import tq.apps.obg.domain.TileDBList;
import tq.apps.obg.domain.TileVO;
import tq.apps.obg.fragment.GameOverFragment;
import tq.apps.obg.fragment.Level0Fragment;
import tq.apps.obg.fragment.Level1Fragment;
import tq.apps.obg.fragment.Level2Fragment;
import tq.apps.obg.fragment.Level3Fragment;
import tq.apps.obg.fragment.Level4Fragment;
import tq.apps.obg.fragment.Level5Fragment;
import tq.apps.obg.service.BroadcastActions;
import tq.apps.obg.service.UserApplication;
import tq.apps.obg.service.UserServiceInterface;

public class TQActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityTqBinding mBinding;
    private int levelNum;
    private DBHelper dbHelper;
    private int quizLife = 3;
    private UserServiceInterface mServiceInterface;
    private boolean isPlayerQuiz;
    private CountDownTimer mCountDown;
    private Handler mProgressHandler;
    private float quizCount;
    private RoundCornerProgressBar quizProg;
    private Fragment fragment = null;
    private Timer timer = null;
    private TimerTask timerTask = null;
    private long mQuizScore;
    private GoogleApiClient apiClient;
    private AnimationDrawable aDrawable;
    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getStringExtra("quizRe") != null) {
                quizReadyListener();
            } else {
                buttonVisible();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_tq);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();

    }

    @SuppressLint("HandlerLeak")
    private void initView() {
        mServiceInterface = UserApplication.getInstance().getServiceInterface();
        apiClient = mServiceInterface.getApiClient();
        /*apiClient = new GoogleApiClient.Builder(this)
                .addApi(Games.API)
                .addScope(Games.SCOPE_GAMES)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        System.out.println("failll");
                    }
                }).build();*/
        mBinding.startBtn.setBackgroundResource(R.drawable.start_btn_anim);
        aDrawable = (AnimationDrawable) mBinding.startBtn.getBackground();
        dbHelper = DBHelper.getInstance(this);
        Intent intent = getIntent();
        String str = intent.getStringExtra("quizKinds");
        if (str.equals("player")) {
            isPlayerQuiz = true;
        } else {
            isPlayerQuiz = false;
        }
        quizCount = 150;
        quizProg = mBinding.quizTimer;
        quizProg.setProgressColor(Color.parseColor("#b7e4b6"));
        quizProg.setProgressBackgroundColor(Color.parseColor("#3e483d"));
        mBinding.contents1.setOnClickListener(this);
        mBinding.contents2.setOnClickListener(this);
        mBinding.contents3.setOnClickListener(this);
        mBinding.contents4.setOnClickListener(this);
        mBinding.startBtn.setOnClickListener(this);
        quizReadyListener();
        registerBroadcast();
        mProgressHandler = new Handler(){
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (quizCount <= 0) {
                    quizGameOverListener();
                } else {
                    quizCount -= 0.5;
                    //quizProg.incrementProgressBy(-1);
                    quizProg.setProgress(quizCount);
                    mProgressHandler.sendEmptyMessageDelayed(0, 100);
                }
            }
        };
    }


    private void setNextLevelFragment() {
        if (levelNum != 9) {
            startTimerThread();
            levelNum = mServiceInterface.getQuizLevel();
        }
        mBinding.buttonLayout.setVisibility(View.GONE);
        if (levelNum == 1) {
            fragment = new Level1Fragment();
        } else if (levelNum ==2) {
            fragment = new Level2Fragment();
        } else if (levelNum == 3) {
            fragment = new Level3Fragment();
        } else if (levelNum == 0) {
            fragment = new Level0Fragment();
        } else if (levelNum == 4) {
            fragment = new Level4Fragment();
        } else if (levelNum == 5) {
            fragment = new Level5Fragment();
        } else if (levelNum == 9) {
            fragment = new GameOverFragment();
        }
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.level_fragment, fragment).commit();
        setBtnContents();
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.contents1:
                checkAnswer(mBinding.contents1.getText().toString());
                break;
            case R.id.contents2:
                checkAnswer(mBinding.contents2.getText().toString());
                break;
            case R.id.contents3:
                checkAnswer(mBinding.contents3.getText().toString());
                break;
            case R.id.contents4:
                checkAnswer(mBinding.contents4.getText().toString());
                break;
            case R.id.start_btn:
                setNextLevelFragment();
                mBinding.levelFragment.setVisibility(View.VISIBLE);
                mBinding.startBtnLayout.setVisibility(View.GONE);
                break;
        }
    }

    public void setBtnContents() {
        List<String> strList = null;
        if (isPlayerQuiz) {
            strList = mServiceInterface.getContentsArr();
        } else {
            strList = mServiceInterface.getEmblemContentsArr();
        }
        mBinding.contents1.setText(strList.get(0).toString());
        mBinding.contents2.setText(strList.get(1).toString());
        mBinding.contents3.setText(strList.get(2).toString());
        mBinding.contents4.setText(strList.get(3).toString());
    }

    public void checkAnswer(String answerStr) {
        if(mServiceInterface.isAnswer(answerStr, mServiceInterface.getIsPlayerQuiz())){
            setNextLevelFragment();
        } else {
            Toast.makeText(getApplicationContext(), "땡!!!!!!!!!", Toast.LENGTH_SHORT).show();
            setQuizLife();

        }
        mQuizScore = (long) mServiceInterface.getQuizScore();
        mBinding.quizScoreText.setText(String.valueOf(mQuizScore));
    }
    public void setQuizLife() {
        quizLife--;
        if (quizLife == 2) {
            mBinding.quizLife1.setVisibility(View.GONE);
            setNextLevelFragment();
        } else if (quizLife == 1) {
            mBinding.quizLife2.setVisibility(View.GONE);
            setNextLevelFragment();
        } else {
            mBinding.quizLife3.setVisibility(View.GONE);
            quizGameOverListener();
        }
    }
    public void registerBroadcast(){
        IntentFilter filter = new IntentFilter();
        filter.addAction(BroadcastActions.BUTTON_VISIABLE);
        filter.addAction(BroadcastActions.QUIZ_RESTART);
        registerReceiver(mBroadcastReceiver, filter);
    }

    private void buttonVisible() {

        mBinding.buttonLayout.setVisibility(View.VISIBLE);

    }

    private void startTimerThread() {
        mProgressHandler.removeMessages(0);
        quizCount = 150;
        quizProg.setMax(quizCount);
        quizProg.setProgress(quizCount);
        mProgressHandler.sendEmptyMessage(0);

    }

    /*private void decreaseBar() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                quizCount = (int) quizProg.getProgress();
                if (quizCount > 0) {
                    quizCount-=1;
                } else if (quizCount == 0) {
                    timer.cancel();
                    Thread.interrupted();
                }
                quizProg.setProgress(quizCount);
            }
        });
    }*/
    private void quizGameOverListener() {
        System.out.println("Game Over");
        mProgressHandler.removeMessages(0);
        levelNum = 9;
        setNextLevelFragment();
        mBinding.tqTopLayout.setVisibility(View.GONE);
        mBinding.tqBottomLayout.setVisibility(View.GONE);
        Games.Leaderboards.submitScore(apiClient, getString(R.string.leaderboard_score), mQuizScore);
        startActivityForResult(Games.Leaderboards.getLeaderboardIntent(apiClient,
                getString(R.string.leaderboard_score)),0);
    }

    private void quizReadyListener() {
        levelNum = 0;
        quizCount = 150;
        quizLife = 3;
        quizProg.setProgress(quizCount);
        mBinding.quizLife1.setVisibility(View.VISIBLE);
        mBinding.quizLife2.setVisibility(View.VISIBLE);
        mBinding.quizLife3.setVisibility(View.VISIBLE);
        mServiceInterface.setQuizScore(0);
        mBinding.quizScoreText.setText("0");
        mBinding.tqTopLayout.setVisibility(View.VISIBLE);
        mBinding.tqBottomLayout.setVisibility(View.VISIBLE);
        mServiceInterface.setTileImageList();
        mServiceInterface.setIsPlayerQuiz(isPlayerQuiz);
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (fragment != null) {
            fragmentTransaction.remove(fragment).commit();
        }
        //setNextLevelFragment();
    }
    public void unregisterBroadcast(){
        unregisterReceiver(mBroadcastReceiver);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mProgressHandler.removeMessages(0);
        unregisterBroadcast();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            aDrawable.start();
        } else {
            aDrawable.stop();
        }
    }
}
