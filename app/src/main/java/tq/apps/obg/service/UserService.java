package tq.apps.obg.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.Games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import tq.apps.obg.R;
import tq.apps.obg.activity.FrontActivity;
import tq.apps.obg.animation.Rotate3DAnimation;
import tq.apps.obg.db.DBHelper;
import tq.apps.obg.domain.EmblemVO;
import tq.apps.obg.domain.PersonVO;
import tq.apps.obg.domain.TileVO;

/**
 * Created by d1jun on 2018-02-23.
 */

public class UserService extends Service {
    private final IBinder mBinder = new UserServiceBinder();
    private List<FrameLayout> quizFrameLayouts;
    private int clickedNum = 0;
    private LinearLayout firstView, secondView;
    private Bitmap firstResId, secondResId;
    private int DURATION = 100;
    private float centerX;
    private float centerY;
    private DBHelper dbHelper;
    private List<TileVO> mTileImageList;
    private List<PersonVO> mPersonImageList;
    private List<EmblemVO> mEmblemImageList;
    private List<FrameLayout> mFindLayout = new ArrayList<>();
    private PersonVO mAnswerVO;
    private EmblemVO mAnswerMVO;
    private int quizIndex = 0;
    private int quizLevel = 0;
    private int quizScore = 0;
    private int levelCount = 0;
    private int quizButtonLevel, quizButtonLevelIndex;
    private boolean isPlayerQuiz;
    private static GoogleApiClient apiClient;
    Handler mHandler = new Handler();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public class UserServiceBinder extends Binder {
        public UserService getService() {
            return UserService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = DBHelper.getInstance(getApplicationContext());
        System.out.println("UserService");
        setData();
    }

    private void setData() {
        /*apiClient = new GoogleApiClient.Builder(this)
                .addApi(Games.API)
                .addScope(Games.SCOPE_GAMES)
                .enableAutoManage((FragmentActivity) FrontActivity.mContext, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        System.out.println("Failllllllllll");
                    }
                }).build();*/
        mTileImageList = dbHelper.selectTielData();
        mPersonImageList = dbHelper.selectPersonData();
        mEmblemImageList = dbHelper.selectEmblemData();
        setmTileImageList();
        setmPersonImageList();
        setmEmblemImageList();
    }

    public void updateScore(long score) {
       // Games.Leaderboards.submitScore(apiClient, getString(R.string.leaderboard_score), score);
    }

    public void viewLeaderBoardScore() {
        //(Games.Leaderboards.getLeaderboardIntent(apiClient, getString(R.string.leaderboard_score)));
    }

    public GoogleApiClient getApiClient() {
        return apiClient;
    }
    public List<TileVO> getTileImages() {
        List<TileVO> list = dbHelper.selectTielData();
        long seed = System.nanoTime();
        Collections.shuffle(list, new Random(seed));
        return list;
    }

    public void checkedSameImage(LinearLayout view, Bitmap resId) {
        clickedNum++;
        if (clickedNum == 1) {
            firstView = view;
            firstResId = resId;
        } else if (clickedNum == 2) {
            setFrameLayoutsEnable(false);
            secondView = view;
            secondResId = resId;
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (isSameResId(firstResId, secondResId)) {
                        //같음
                        quizButtonLevelIndex += 1;
                        firstView.setBackgroundColor(Color.parseColor("#00000000"));
                        firstView.setEnabled(false);
                        secondView.setBackgroundColor(Color.parseColor("#00000000"));
                        secondView.setEnabled(false);
                        firstView.getChildAt(0).setVisibility(View.GONE);
                        secondView.getChildAt(0).setVisibility(View.GONE);
                        mFindLayout.add((FrameLayout) firstView.getChildAt(0));
                        mFindLayout.add((FrameLayout) secondView.getChildAt(0));
                        System.out.println(mFindLayout.get(0).getId() + " ::::: " + mFindLayout.get(1).getId());
                        if (quizButtonLevelIndex == quizButtonLevel) {
                            sendBroadcast(new Intent(BroadcastActions.BUTTON_VISIABLE));
                        }
                        quizScore += 15;
                    } else {
                        //다름
                        applyRotation(0f, 90f, 180f, 0f, firstView, false);
                        applyRotation(0f, 90f, 180f, 0f, secondView, false);
                    }
                    setFrameLayoutsEnable(true);
                }
            }, 290);
            clickedNum = 0;

        }
    }

    public boolean isSameResId(Bitmap num1, Bitmap num2) {
        if (num1.equals(num2)) {
            return true;
        }
        return false;
    }

    public Bitmap getBitMap(ImageView iv) {
        Drawable drawable = iv.getDrawable();
        return ((BitmapDrawable) drawable).getBitmap();
    }

    public void applyRotation(float start, float mid, float end, float depth, LinearLayout layout, boolean isFront) {
        FrameLayout frameLayout = (FrameLayout) layout.getChildAt(0);
        centerX = frameLayout.getWidth() / 2.0f;
        centerY = frameLayout.getHeight() / 2.0f;
        Rotate3DAnimation rot = new Rotate3DAnimation(start, mid, centerX, centerY, depth, true);
        rot.setDuration(DURATION);
        rot.setAnimationListener(new UserService.DisplayNextView(mid, end, depth, layout, isFront));
        frameLayout.startAnimation(rot);
    }

    public class DisplayNextView implements Animation.AnimationListener {
        private float mid;
        private float end;
        private float depth;
        private LinearLayout mLayout;
        private FrameLayout mFrameLayout;
        private boolean isFront;

        public DisplayNextView(float mid, float end, float depth, LinearLayout layout, boolean b) {
            this.mid = mid;
            this.end = end;
            this.depth = depth;
            this.mLayout = layout;
            this.mFrameLayout = (FrameLayout) layout.getChildAt(0);
            this.isFront = b;
        }

        @Override
        public void onAnimationStart(Animation animation) {
            mFrameLayout.post(new Runnable() {
                @Override
                public void run() {
                    if (isFront) {
                        /*clickedNum++;
                        if (clickedNum == 2) {
                            setFrameLayoutsEnable(false);
                        }*/
                        //setFrameLayoutsEnable(true);
                        checkedSameImage(mLayout, getBitMap((ImageView) mFrameLayout.getChildAt(1)));
                        mFrameLayout.setEnabled(false);
                        mFrameLayout.getChildAt(0).setVisibility(View.GONE);
                        mFrameLayout.getChildAt(1).setVisibility(View.VISIBLE);
                    } else {
                        mFrameLayout.setEnabled(true);
                        mFrameLayout.getChildAt(0).setVisibility(View.VISIBLE);
                        mFrameLayout.getChildAt(1).setVisibility(View.GONE);
                    }
                    Rotate3DAnimation rot = new Rotate3DAnimation(mid, end, centerX, centerY, depth, false);
                    rot.setDuration(DURATION);
                    rot.setInterpolator(new AccelerateInterpolator());
                    mFrameLayout.startAnimation(rot);
                }
            });

        }

        @Override
        public void onAnimationEnd(Animation animation) {
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    public List<String> getEmblemContentsArr() {
        List<String> strArr = new ArrayList<>();
        setmEmblemVO();
        List<EmblemVO> vo = dbHelper.selectEmblemContents(mAnswerMVO.getE_league());
        Collections.shuffle(vo, new Random(getSeed()));
        strArr.add(mAnswerMVO.getE_name());
        boolean isOverlap;
        int voIndex = 0;
        EmblemVO pVo = null;
        for (int i = 1; i < 4; i++) {
            pVo = vo.get(voIndex++);
            isOverlap = false;
            for (int j = 0; j < strArr.size(); j++) {
                if (strArr.get(j).trim().equals(pVo.getE_name().trim())) {
                    i--;
                    isOverlap = true;
                }
            }
            if (!isOverlap) {
                strArr.add(pVo.getE_name());
            }
        }
        Collections.shuffle(strArr, new Random(getSeed()));
        return strArr;
    }

    public List<String> getContentsArr() {
        List<String> strArr = new ArrayList<>();
        setmPersonVO();
        List<PersonVO> vo = dbHelper.selectContentsData(mAnswerVO.getP_job());
        Collections.shuffle(vo, new Random(getSeed()));
        strArr.add(mAnswerVO.getP_name());
        boolean isOverlap;
        int voIndex = 0;
        PersonVO pVo = null;
        for (int i = 1; i < 4; i++) {
            pVo = vo.get(voIndex++);
            isOverlap = false;
            for (int j = 0; j < strArr.size(); j++) {
                if (strArr.get(j).trim().equals(pVo.getP_name().trim())) {
                    i--;
                    isOverlap = true;
                }
            }
            if (!isOverlap) {
                strArr.add(pVo.getP_name());
            }
        }
        Collections.shuffle(strArr, new Random(getSeed()));
        return strArr;
    }

    public void setmTileImageList() {
        Collections.shuffle(mTileImageList, new Random(getSeed()));
    }

    public void setmPersonImageList() {
        Collections.shuffle(mPersonImageList, new Random(getSeed()));
    }

    public void setmEmblemImageList() {
        Collections.shuffle(mEmblemImageList, new Random(getSeed()));
    }

    public List<FrameLayout> getmFindLayout(List<FrameLayout> list) {
        List<FrameLayout> getList = list;
        for(Iterator<FrameLayout> it = getList.iterator(); it.hasNext();) {
            FrameLayout fl = it.next();
            for(int i=0; i<mFindLayout.size(); i++) {
                if (fl.getId() == mFindLayout.get(i).getId()) {
                    it.remove();
                }
            }
        }
        return getList;
    }
    //Quiz 첫 시작
    public List<Integer> getmTileImageList(int level) {
        quizButtonLevel = level / 2;
        mFindLayout.clear();
        System.out.println(quizButtonLevel + "::AA:");
        quizButtonLevelIndex = 0;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < level; i++) {
            list.add(mTileImageList.get(i).gettile_res_id());
            list.add(mTileImageList.get(i).gettile_res_id());
        }
        Collections.shuffle(list, new Random(getSeed()));
        setmTileImageList();
        setQuizLevel();
        return list;
    }

    public void quizRestart() {
        Intent intent = new Intent(BroadcastActions.QUIZ_RESTART);
        intent.putExtra("quizRe","quizRe");
        sendBroadcast(intent);
    }
    public void setmPersonVO() {
        mAnswerVO = mPersonImageList.get(quizIndex);
    }

    public void setmEmblemVO() {
        mAnswerMVO = mEmblemImageList.get(quizIndex);
    }

    public PersonVO getmPersonVO() {
        return mAnswerVO;
    }

    public PersonVO getmPersonImageList() {
        quizIndex++;
        return mAnswerVO;
    }

    public EmblemVO getmEmblemImageList() {
        quizIndex++;
        return mAnswerMVO;
    }

    public int getQiuzIndex() {
        return quizIndex;
    }

    public long getSeed() {
        return System.nanoTime();
    }

    public int getQuizLevel() {
        return quizLevel;
    }

    public void setQuizLevel() {
        if (levelCount == 0) {
            quizLevel++;
            setLevelCount();
        } else if (levelCount == 8) {
            quizLevel = 5;
        } else {
            levelCount--;
        }
        System.out.println("[QuizLevel :: "+quizLevel+"], [levelCount :: "+levelCount+"]");
    }
    public void setLevelCount() {
        if (quizLevel == 0) {
            levelCount = 2;
        } else if (quizLevel == 1) {
            levelCount = 3;
        } else if (quizLevel == 2) {
            levelCount = 4;
        } else if (quizLevel == 3) {
            levelCount = 5;
        } else if (quizLevel == 4) {
            levelCount = 7;
        } else if (quizLevel == 5) {
            levelCount = 8;
        }
    }

    public boolean isAnswer(String answer, boolean isPlayerQuiz) {
        if (isPlayerQuiz) {
            if (answer.equals(mAnswerVO.getP_name())) {
                setQuizScore(150);
                return true;
            }
        } else {
            if (answer.equals(mAnswerMVO.getE_name())) {
                setQuizScore(150);
                return true;
            }
        }
        return false;
    }

    public void setQuizScore(int score) {
        int quizLevelScore = 0;
        if (quizLevel == 0) {
            quizLevelScore = 1;
        } else {
            quizLevelScore = quizLevel;
        }
        if (score == 0) {
            quizScore = 0;
        } else {
            quizScore += (score * quizLevelScore);
        }
    }

    public int getQuizScore() {
        return quizScore;
    }

    public void setIsPlayerQuiz(boolean quiz) {
        if (quiz) {
            isPlayerQuiz = true;
        } else {
            isPlayerQuiz = false;
        }
        quizLevel = 0;
        levelCount = 1;
    }

    public boolean getIsPlayerQuiz() {
        return isPlayerQuiz;
    }


    //Tile Hint
    public void applyRotationHint(float start, float mid, float end, float depth, FrameLayout frameLayout) {
        centerX = frameLayout.getWidth() / 2.0f;
        centerY = frameLayout.getHeight() / 2.0f;
        Rotate3DAnimation rot = new Rotate3DAnimation(start, mid, centerX, centerY, depth, true);
        rot.setDuration(DURATION);
        rot.setAnimationListener(new DisplayNextViewHint(mid, end, depth, frameLayout));
        frameLayout.startAnimation(rot);
    }

    public class DisplayNextViewHint implements Animation.AnimationListener {
        private float mid;
        private float end;
        private float depth;
        private FrameLayout mFrameLayout;
        private boolean isFrontHint;
        private int isEnd;

        public DisplayNextViewHint(float mid, float end, float depth, FrameLayout frameLayout) {
            this.mid = mid;
            this.end = end;
            this.depth = depth;
            this.mFrameLayout = frameLayout;
            this.isFrontHint = true;
            this.isEnd = 0;
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mFrameLayout.post(new Runnable() {
                @Override
                public void run() {
                    mFrameLayout.getChildAt(0).setVisibility(View.GONE);
                    mFrameLayout.getChildAt(1).setVisibility(View.VISIBLE);
                    mFrameLayout.setEnabled(false);
                    Rotate3DAnimation rot = new Rotate3DAnimation(mid, end, centerX, centerY, depth, false);
                    rot.setDuration(DURATION);
                    rot.setInterpolator(new AccelerateInterpolator());
                    mFrameLayout.startAnimation(rot);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            applyRotationHintBack(180f, 270f, 360f, 0f, mFrameLayout);
                        }
                    }, 750);
                }
            });

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
    public void applyRotationHintBack(float start, float mid, float end, float depth, FrameLayout frameLayout) {
        centerX = frameLayout.getWidth() / 2.0f;
        centerY = frameLayout.getHeight() / 2.0f;
        Rotate3DAnimation rot = new Rotate3DAnimation(start, mid, centerX, centerY, depth, true);
        rot.setDuration(DURATION);
        rot.setAnimationListener(new DisplayNextViewHintBack(mid, end, depth, frameLayout));
        frameLayout.startAnimation(rot);
    }

    public class DisplayNextViewHintBack implements Animation.AnimationListener {
        private float mid;
        private float end;
        private float depth;
        private FrameLayout mFrameLayout;

        public DisplayNextViewHintBack(float mid, float end, float depth, FrameLayout frameLayout) {
            this.mid = mid;
            this.end = end;
            this.depth = depth;
            this.mFrameLayout = frameLayout;
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            mFrameLayout.post(new Runnable() {
                @Override
                public void run() {
                    mFrameLayout.getChildAt(0).setVisibility(View.VISIBLE);
                    mFrameLayout.getChildAt(1).setVisibility(View.GONE);
                    mFrameLayout.setEnabled(true);
                    Rotate3DAnimation rot = new Rotate3DAnimation(mid, end, centerX, centerY, depth, false);
                    rot.setDuration(DURATION);
                    rot.setInterpolator(new AccelerateInterpolator());
                    mFrameLayout.startAnimation(rot);
                }
            });

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }

    public void viewHindListener(List<FrameLayout> frameLayouts) {
        List<FrameLayout> list = getmFindLayout(frameLayouts);
        for (FrameLayout fl : list) {
            if (fl != null) {
                applyRotationHint(0f, 90f, 180f, 0f, fl);
            }
        }
    }
    public void viewHindBackListener(List<FrameLayout> frameLayouts) {
        List<FrameLayout> list = getmFindLayout(frameLayouts);
        for (FrameLayout fl : list) {
            if (fl != null) {
                applyRotationHintBack(180f, 270f, 360f, 0f, fl);
            }
        }
    }

    public void startQuizGoneHint(final List<FrameLayout> frameLayouts) {
        quizFrameLayouts = frameLayouts;
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewHindBackListener(frameLayouts);
            }
        }, 700);
    }

    public void setFrameLayoutsEnable(boolean isTrue) {
        for (FrameLayout fl : quizFrameLayouts) {
            fl.setEnabled(isTrue);
        }
    }

    public void setBackQuizImages(ImageView imageView) {
        if (getIsPlayerQuiz()) {
            PersonVO vo = getmPersonImageList();
            imageView.setBackgroundResource(vo.getP_res_id());
        } else {
            EmblemVO vo = getmEmblemImageList();
            imageView.setBackgroundResource(vo.getE_res_id());
        }
    }
}