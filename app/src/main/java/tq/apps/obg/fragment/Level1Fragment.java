package tq.apps.obg.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import tq.apps.obg.R;
import tq.apps.obg.databinding.Level1FragmentBinding;
import tq.apps.obg.domain.EmblemVO;
import tq.apps.obg.domain.PersonVO;
import tq.apps.obg.service.UserApplication;
import tq.apps.obg.service.UserServiceInterface;


public class Level1Fragment extends Fragment implements View.OnTouchListener{
    private Level1FragmentBinding mBinding;
    private List<FrameLayout> frameLayoutList = new ArrayList<>();
    private UserServiceInterface userServiceInterface = UserApplication.getInstance().getServiceInterface();
    public Level1Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.level_1_fragment, container, false);
        setViewData();
        return mBinding.getRoot();
    }

    private void setViewData() {
        List<Integer> list = UserApplication.getInstance().getServiceInterface().getTileImageList(3);
        if (userServiceInterface.getIsPlayerQuiz()) {
            PersonVO vo = userServiceInterface.getPersonList();
            mBinding.backQuizImage.setBackgroundResource(vo.getP_res_id());
        } else {
            EmblemVO vo = userServiceInterface.getmEmblemImageList();
            mBinding.backQuizImage.setBackgroundResource(vo.getE_res_id());
        }
        frameLayoutList.add(mBinding.tile11);
        frameLayoutList.add(mBinding.tile12);
        frameLayoutList.add(mBinding.tile21);
        frameLayoutList.add(mBinding.tile22);
        frameLayoutList.add(mBinding.tile31);
        frameLayoutList.add(mBinding.tile32);
        for(int i=0; i<frameLayoutList.size();i++) {
            ImageView iv = (ImageView) frameLayoutList.get(i).getChildAt(1);
            frameLayoutList.get(i).setOnTouchListener(this);
            iv.setImageResource(list.get(i));
        }
        mBinding.testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userServiceInterface.viewHindListener(frameLayoutList);
            }
        });
        userServiceInterface.startQuizGoneHint(frameLayoutList);
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
            case R.id.tile_31:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile31Layout, true);
                break;
            case R.id.tile_32:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile32Layout, true);
                break;
        }
        return false;
    }
}
