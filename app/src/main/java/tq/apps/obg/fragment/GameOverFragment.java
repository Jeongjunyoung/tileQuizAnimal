package tq.apps.obg.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.refactor.lib.colordialog.ColorDialog;
import tq.apps.obg.R;
import tq.apps.obg.activity.FrontActivity;
import tq.apps.obg.databinding.GameoverFragmentBinding;
import tq.apps.obg.service.UserApplication;
import tq.apps.obg.service.UserServiceInterface;

/**
 * Created by OBG on 2018-03-07.
 */

public class GameOverFragment extends Fragment implements View.OnClickListener{
    private GameoverFragmentBinding mBinding;
    private static final UserServiceInterface userServiceInterface = UserApplication.getInstance().getServiceInterface();
    private AnimationDrawable drawable;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.gameover_fragment, container, false);
        setViewData();
        return mBinding.getRoot();
    }
    private void setViewData() {
        mBinding.quizReStart.setOnClickListener(this);
        mBinding.quizOut.setOnClickListener(this);
        mBinding.quizReStart.setBackgroundResource(R.drawable.retry_btn_anim);
        drawable = (AnimationDrawable) mBinding.quizReStart.getBackground();
        drawable.start();
        if (userServiceInterface.getNewScore()) {
            mBinding.newRecordImage.setImageResource(R.drawable.new_record_1);
        } else {
            mBinding.newRecordImage.setImageResource(R.drawable.gameover_white);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.quiz_re_start:
                userServiceInterface.quizRestart();
                break;
            case R.id.quiz_out:
                Intent intent = new Intent(getActivity(), FrontActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
        }
    }
}
