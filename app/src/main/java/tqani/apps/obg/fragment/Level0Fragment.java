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
import tqani.apps.obg.databinding.Level0FragmentBinding;
import tqani.apps.obg.domain.EmblemVO;
import tqani.apps.obg.domain.PersonVO;
import tqani.apps.obg.service.UserApplication;
import tqani.apps.obg.service.UserServiceInterface;


public class Level0Fragment extends Fragment implements View.OnTouchListener{
    private Level0FragmentBinding mBinding;
    private List<FrameLayout> frameLayoutList = new ArrayList<>();
    private UserServiceInterface userServiceInterface = UserApplication.getInstance().getServiceInterface();
    public Level0Fragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.level_0_fragment, container, false);
        setViewData();
        return mBinding.getRoot();
    }

    private void setViewData() {
        final List<Integer> list = userServiceInterface.getTileImageList(2);
        setBackQuizImages(userServiceInterface.getIsPlayerQuiz());
        frameLayoutList.add(mBinding.tile11);
        frameLayoutList.add(mBinding.tile12);
        frameLayoutList.add(mBinding.tile21);
        frameLayoutList.add(mBinding.tile22);
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
            case R.id.tile_21:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile21Layout, true);
                break;
            case R.id.tile_22:
                UserApplication.getInstance().getServiceInterface().applyRotation(0f, 90f, 180f, 0f, mBinding.tile22Layout, true);
                break;
        }
        return false;
    }

    private void setBackQuizImages(boolean isPlayerQuiz) {
        if (isPlayerQuiz) {
            PersonVO vo = userServiceInterface.getPersonList();
            mBinding.backQuizImage.setBackgroundResource(vo.getP_res_id());
        } else {
            EmblemVO vo = userServiceInterface.getmEmblemImageList();
            mBinding.backQuizImage.setBackgroundResource(vo.getE_res_id());
        }
    }
}
