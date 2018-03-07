package tq.apps.obg.fragment;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tq.apps.obg.R;
import tq.apps.obg.databinding.GameoverFragmentBinding;
import tq.apps.obg.service.UserApplication;
import tq.apps.obg.service.UserServiceInterface;

/**
 * Created by OBG on 2018-03-07.
 */

public class GameOverFragment extends Fragment implements View.OnClickListener{
    private GameoverFragmentBinding mBinding;
    private static final UserServiceInterface userServiceInterface = UserApplication.getInstance().getServiceInterface();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.gameover_fragment, container, false);
        setViewData();
        return mBinding.getRoot();
    }
    private void setViewData() {
        mBinding.text3.setText("Game Over");
        mBinding.quizReStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.quiz_re_start:
                userServiceInterface.quizRestart();
                break;
        }
    }
}
