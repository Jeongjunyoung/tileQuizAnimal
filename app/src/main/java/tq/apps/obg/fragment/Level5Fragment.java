package tq.apps.obg.fragment;

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

import tq.apps.obg.R;
import tq.apps.obg.databinding.Level3FragmentBinding;
import tq.apps.obg.databinding.Level4FragmentBinding;
import tq.apps.obg.databinding.Level5FragmentBinding;
import tq.apps.obg.domain.EmblemVO;
import tq.apps.obg.domain.PersonVO;
import tq.apps.obg.service.UserApplication;
import tq.apps.obg.service.UserServiceInterface;

/**
 * Created by d1jun on 2018-02-23.
 */

public class Level5Fragment extends Fragment implements View.OnTouchListener{
    private Level5FragmentBinding mBinding;
    private List<FrameLayout> frameLayoutList = new ArrayList<>();
    private UserServiceInterface userServiceInterface = UserApplication.getInstance().getServiceInterface();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.level_5_fragment, container, false);
        setViewData();
        return mBinding.getRoot();
    }
    private void setViewData() {
        List<Integer> list = UserApplication.getInstance().getServiceInterface().getTileImageList(10);
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
        frameLayoutList.add(mBinding.tile15);
        frameLayoutList.add(mBinding.tile21);
        frameLayoutList.add(mBinding.tile22);
        frameLayoutList.add(mBinding.tile23);
        frameLayoutList.add(mBinding.tile24);
        frameLayoutList.add(mBinding.tile25);
        frameLayoutList.add(mBinding.tile31);
        frameLayoutList.add(mBinding.tile32);
        frameLayoutList.add(mBinding.tile33);
        frameLayoutList.add(mBinding.tile34);
        frameLayoutList.add(mBinding.tile35);
        frameLayoutList.add(mBinding.tile41);
        frameLayoutList.add(mBinding.tile42);
        frameLayoutList.add(mBinding.tile43);
        frameLayoutList.add(mBinding.tile44);
        frameLayoutList.add(mBinding.tile45);
        frameLayoutList.add(mBinding.tile51);
        frameLayoutList.add(mBinding.tile52);
        frameLayoutList.add(mBinding.tile53);
        frameLayoutList.add(mBinding.tile54);
        frameLayoutList.add(mBinding.tile55);
        frameLayoutList.add(mBinding.tile61);
        frameLayoutList.add(mBinding.tile62);
        frameLayoutList.add(mBinding.tile63);
        frameLayoutList.add(mBinding.tile64);
        frameLayoutList.add(mBinding.tile65);
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
            case R.id.tile_15:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile15Layout, true);
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
            case R.id.tile_25:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile25Layout, true);
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
            case R.id.tile_35:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile35Layout, true);
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
            case R.id.tile_45:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile45Layout, true);
                break;
            case R.id.tile_51:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile51Layout, true);
                break;
            case R.id.tile_52:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile52Layout, true);
                break;
            case R.id.tile_53:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile53Layout, true);
                break;
            case R.id.tile_54:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile54Layout, true);
                break;
            case R.id.tile_55:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile55Layout, true);
                break;
            case R.id.tile_61:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile61Layout, true);
                break;
            case R.id.tile_62:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile62Layout, true);
                break;
            case R.id.tile_63:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile63Layout, true);
                break;
            case R.id.tile_64:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile64Layout, true);
                break;
            case R.id.tile_65:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile65Layout, true);
                break;
        }
        return false;
    }
}
