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
        mBinding.goAddHintBtn.setOnClickListener(this);
        mBinding.quizReStart.setBackgroundResource(R.drawable.retry_btn_anim);
        drawable = (AnimationDrawable) mBinding.quizReStart.getBackground();
        int hintNum = userServiceInterface.getHintNum();
        mBinding.goHintNum.setText(String.valueOf(hintNum));
        drawable.start();
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
            case R.id.go_add_hint_btn:
                ColorDialog dialog = new ColorDialog(getActivity());
                dialog.setAnimationEnable(true);
                dialog.setColor("#427158");
                dialog.setContentImage(R.drawable.hint_image);
                dialog.setContentTextColor("#000000");
                dialog.setPositiveListener("Ok", new ColorDialog.OnPositiveListener() {
                    @Override
                    public void onClick(ColorDialog colorDialog) {
                        //동영상 광고 재생

                    }
                })
                        .setNegativeListener("Cancel", new ColorDialog.OnNegativeListener() {
                            @Override
                            public void onClick(ColorDialog colorDialog) {
                                colorDialog.dismiss();
                            }
                        }).show();
                break;
        }
    }

}
