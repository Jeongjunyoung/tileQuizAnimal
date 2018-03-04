package tq.apps.obg.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import tq.apps.obg.R;
import tq.apps.obg.databinding.Level2FragmentBinding;
import tq.apps.obg.domain.EmblemVO;
import tq.apps.obg.domain.PersonVO;
import tq.apps.obg.service.UserApplication;
import tq.apps.obg.service.UserServiceInterface;

/**
 * Created by d1jun on 2018-02-23.
 */

public class Level2Fragment extends Fragment implements View.OnTouchListener{
    private Level2FragmentBinding mBinding;
    private UserServiceInterface userServiceInterface = UserApplication.getInstance().getServiceInterface();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.level_2_fragment, container, false);
        setViewData();
        return mBinding.getRoot();
    }
    private void setViewData() {
        List<Integer> list = UserApplication.getInstance().getServiceInterface().getTileImageList(6);
        if (userServiceInterface.getIsPlayerQuiz()) {
            PersonVO vo = userServiceInterface.getPersonList();
            mBinding.backQuizImage.setBackgroundResource(vo.getP_res_id());
        } else {
            EmblemVO vo = userServiceInterface.getmEmblemImageList();
            mBinding.backQuizImage.setBackgroundResource(vo.getE_res_id());
        }
        mBinding.imageBack11.setImageResource(list.get(0));
        mBinding.imageBack12.setImageResource(list.get(1));
        mBinding.imageBack13.setImageResource(list.get(2));
        mBinding.imageBack21.setImageResource(list.get(3));
        mBinding.imageBack22.setImageResource(list.get(4));
        mBinding.imageBack23.setImageResource(list.get(5));
        mBinding.imageBack31.setImageResource(list.get(6));
        mBinding.imageBack32.setImageResource(list.get(7));
        mBinding.imageBack33.setImageResource(list.get(8));
        mBinding.imageBack41.setImageResource(list.get(9));
        mBinding.imageBack42.setImageResource(list.get(10));
        mBinding.imageBack43.setImageResource(list.get(11));
        mBinding.tile11.setOnTouchListener(this);
        mBinding.tile12.setOnTouchListener(this);
        mBinding.tile13.setOnTouchListener(this);
        mBinding.tile21.setOnTouchListener(this);
        mBinding.tile22.setOnTouchListener(this);
        mBinding.tile23.setOnTouchListener(this);
        mBinding.tile31.setOnTouchListener(this);
        mBinding.tile32.setOnTouchListener(this);
        mBinding.tile33.setOnTouchListener(this);
        mBinding.tile41.setOnTouchListener(this);
        mBinding.tile42.setOnTouchListener(this);
        mBinding.tile43.setOnTouchListener(this);
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
            case R.id.tile_13:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile13Layout, true);
                break;
            case R.id.tile_21:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile21Layout, true);
                break;
            case R.id.tile_22:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile22Layout, true);
                break;
            case R.id.tile_23:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile23Layout, true);
                break;
            case R.id.tile_31:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile31Layout, true);
                break;
            case R.id.tile_32:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile32Layout, true);
                break;
            case R.id.tile_33:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile33Layout, true);
                break;
            case R.id.tile_41:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile41Layout, true);
                break;
            case R.id.tile_42:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile42Layout, true);
                break;
            case R.id.tile_43:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile43Layout, true);
                break;
        }
        return false;
    }
}
