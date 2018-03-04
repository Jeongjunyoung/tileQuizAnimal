package tq.apps.obg.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.List;

import tq.apps.obg.R;
import tq.apps.obg.animation.Rotate3DAnimation;
import tq.apps.obg.databinding.Level0FragmentBinding;
import tq.apps.obg.domain.EmblemVO;
import tq.apps.obg.domain.PersonVO;
import tq.apps.obg.domain.TileVO;
import tq.apps.obg.service.UserApplication;
import tq.apps.obg.service.UserService;
import tq.apps.obg.service.UserServiceInterface;


public class Level0Fragment extends Fragment implements View.OnTouchListener{
    private Level0FragmentBinding mBinding;
    private UserServiceInterface userServiceInterface = UserApplication.getInstance().getServiceInterface();
    private boolean isFront = true;
    private int isEnd = 0;
    private int isEndIndex = 1;
    private int DURATION = 130;
    private float centerX;
    private float centerY;
    Handler mHandler = new Handler();
    public Level0Fragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.level_0_fragment, container, false);
        setViewData();
        return mBinding.getRoot();
    }

    private void setViewData() {
        List<Integer> list = userServiceInterface.getTileImageList(2);
        if (userServiceInterface.getIsPlayerQuiz()) {
            PersonVO vo = userServiceInterface.getPersonList();
            mBinding.backQuizImage.setBackgroundResource(vo.getP_res_id());
        } else {
            EmblemVO vo = userServiceInterface.getmEmblemImageList();
            mBinding.backQuizImage.setBackgroundResource(vo.getE_res_id());
        }
        mBinding.imageBack11.setImageResource(list.get(0));
        mBinding.imageBack12.setImageResource(list.get(1));
        mBinding.imageBack21.setImageResource(list.get(2));
        mBinding.imageBack22.setImageResource(list.get(3));
        mBinding.tile11.setOnTouchListener(this);
        mBinding.tile12.setOnTouchListener(this);
        mBinding.tile21.setOnTouchListener(this);
        mBinding.tile22.setOnTouchListener(this);
        mBinding.testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               //applyRotation(0f,90f,180f,0f);
                viewHintTile1();
            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (view.getId()) {
            case R.id.tile_11:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile11Layout, true);
                break;
            case R.id.tile_12:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile12Layout, true);
                break;
            case R.id.tile_21:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile21Layout, true);
                break;
            case R.id.tile_22:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile22Layout, true);
                break;
        }
        return false;
    }
    public void applyRotation(float start, float mid, float end, float depth, FrameLayout frameLayout) {
        centerX = frameLayout.getWidth() / 2.0f;
        centerY = frameLayout.getHeight() / 2.0f;
        Rotate3DAnimation rot = new Rotate3DAnimation(start, mid, centerX, centerY, depth, true);
        rot.setDuration(DURATION);
        rot.setAnimationListener(new DisplayNextView(mid, end, depth, frameLayout));
        frameLayout.startAnimation(rot);
    }
    public class DisplayNextView implements Animation.AnimationListener{
        private float mid;
        private float end;
        private float depth;
        private FrameLayout mFrameLayout;

        public DisplayNextView(float mid, float end, float depth, FrameLayout frameLayout) {
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
                    if (isFront) {
                        mFrameLayout.getChildAt(0).setVisibility(View.GONE);
                        mFrameLayout.getChildAt(1).setVisibility(View.VISIBLE);
                        mFrameLayout.setEnabled(false);
                        isFront = false;
                    } else {
                        mFrameLayout.setEnabled(true);
                        mFrameLayout.getChildAt(0).setVisibility(View.VISIBLE);
                        mFrameLayout.getChildAt(1).setVisibility(View.GONE);
                        isFront = true;
                    }
                    Rotate3DAnimation rot = new Rotate3DAnimation(mid, end, centerX, centerY, depth, false);
                    rot.setDuration(DURATION);
                    rot.setInterpolator(new AccelerateInterpolator());
                    mFrameLayout.startAnimation(rot);
                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (isEnd == 0) {
                                applyRotation(180f,270f,360f,0f, mFrameLayout);
                                System.out.println("isEnddddd");
                                isEnd++;
                            } else if (isEnd == 1) {
                                isEnd = 0;
                                isEndIndex++;
                                if (isEndIndex < 5) {
                                    viewHindTile(isEndIndex);
                                } else {
                                    isEndIndex = 1;
                                }

                            }
                        }
                    },170);
                    //isEnd++;
                }
            });

        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
    private void viewHindTile(int i) {
        if (i == 2) {
            viewHintTile2();
        } else if (i == 3) {
            viewHintTile3();
        } else if (i == 4) {
            viewHintTile4();
        }
    }
    private void viewHintTile1() {
        applyRotation(0f,90f,180f,0f, mBinding.tile11);
    }
    private void viewHintTile2() {
        applyRotation(0f,90f,180f,0f, mBinding.tile12);
    }
    private void viewHintTile3() {
        applyRotation(0f,90f,180f,0f, mBinding.tile21);
    }
    private void viewHintTile4() {
        applyRotation(0f,90f,180f,0f, mBinding.tile22);
    }
}
