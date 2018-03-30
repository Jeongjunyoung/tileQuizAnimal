package tqani.apps.obg.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import tqani.apps.obg.R;
import tqani.apps.obg.databinding.Level3FragmentBinding;
import tqani.apps.obg.domain.EmblemVO;
import tqani.apps.obg.domain.PersonVO;
import tqani.apps.obg.service.UserApplication;
import tqani.apps.obg.service.UserServiceInterface;

/**
 * Created by d1jun on 2018-02-23.
 */

public class Level3Fragment extends Fragment implements View.OnTouchListener{
    private Level3FragmentBinding mBinding;
    private List<FrameLayout> frameLayoutList = new ArrayList<>();
    private UserServiceInterface userServiceInterface = UserApplication.getInstance().getServiceInterface();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.level_3_fragment, container, false);
        setViewData();
        return mBinding.getRoot();
    }
    private void setViewData() {
        List<Integer> list = UserApplication.getInstance().getServiceInterface().getTileImageList(8);
        if (userServiceInterface.getIsPlayerQuiz()) {
            PersonVO vo = userServiceInterface.getPersonList();
            mBinding.backQuizImage.setBackgroundResource(vo.getP_res_id());
        } else {
            EmblemVO vo = userServiceInterface.getmEmblemImageList();
            mBinding.backQuizImage.setBackgroundResource(vo.getE_res_id());
        }
        frameLayoutList.add(mBinding.tile11);
        frameLayoutList.add(mBinding.tile12);
        frameLayoutList.add(mBinding.tile13);
        frameLayoutList.add(mBinding.tile14);
        frameLayoutList.add(mBinding.tile21);
        frameLayoutList.add(mBinding.tile22);
        frameLayoutList.add(mBinding.tile23);
        frameLayoutList.add(mBinding.tile24);
        frameLayoutList.add(mBinding.tile31);
        frameLayoutList.add(mBinding.tile32);
        frameLayoutList.add(mBinding.tile33);
        frameLayoutList.add(mBinding.tile34);
        frameLayoutList.add(mBinding.tile41);
        frameLayoutList.add(mBinding.tile42);
        frameLayoutList.add(mBinding.tile43);
        frameLayoutList.add(mBinding.tile44);
        for(int i=0; i<frameLayoutList.size();i++) {
            ImageView iv = (ImageView) frameLayoutList.get(i).getChildAt(1);
            frameLayoutList.get(i).setOnTouchListener(this);
            iv.setImageResource(list.get(i));
        }
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
            case R.id.tile_13:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile13Layout, true);
                break;
            case R.id.tile_14:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile14Layout, true);
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
            case R.id.tile_24:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile24Layout, true);
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
            case R.id.tile_34:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile34Layout, true);
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
            case R.id.tile_44:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile44Layout, true);
                break;
        }
        return false;
    }
}
