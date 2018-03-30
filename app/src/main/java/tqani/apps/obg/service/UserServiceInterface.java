package tqani.apps.obg.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.List;

import tqani.apps.obg.domain.EmblemVO;
import tqani.apps.obg.domain.PersonVO;

/**
 * Created by d1jun on 2018-02-23.
 */

public class UserServiceInterface {
    private ServiceConnection mServiceConnection;
    private UserService mService;

    public UserServiceInterface(Context context) {
        mServiceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder service) {
                mService = ((UserService.UserServiceBinder) service).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mServiceConnection = null;
                mService = null;
            }
        };
        context.bindService(new Intent(context, UserService.class)
                .setPackage(context.getPackageName()), mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    /*public void checkedSameImage(LinearLayout layout, Bitmap resId) {
        if (mService != null) {
            mService.checkedSameImage(layout, resId);
        }
    }
    public boolean isSameResId(Bitmap num1, Bitmap num2) {
        if (mService != null) {
            return mService.isSameResId(num1, num2);
        }
        return false;
    }
    public Bitmap getBitMap(ImageView iv) {
        if (mService != null) {
            return mService.getBitMap(iv);
        }
        return null;
    }
*/
    public void applyRotation(float start, float mid, float end, float depth, LinearLayout layout, boolean b) {
        if (mService != null) {
            mService.applyRotation(start, mid, end, depth, layout, b);
        }
    }

    /*public List<TileVO> getTileImages() {
        if (mService != null) {
            return mService.getTileImages();
        }
        return null;
    }*/
    public void setTileImageList() {
        if (mService != null) {
            mService.setmTileImageList();
        }
    }
    public List<Integer> getTileImageList(int level) {
        if (mService != null) {
            return mService.getmTileImageList(level);
        }
        return null;
    }

    public PersonVO getPersonList() {
        if (mService != null) {
            return mService.getmPersonImageList();
        }
        return null;
    }
    /*public int getQuizIndex() {
        if (mService != null) {
            return mService.getQiuzIndex();
        }
        return 0;
    }*/

    public List<String> getContentsArr() {
        if (mService != null) {
            return mService.getContentsArr();
        }
        return null;
    }

    /*public void setmPersonVO() {
        if (mService != null) {
            mService.setmPersonVO();
        }
    }

    public PersonVO getmPersonVO() {
        if (mService != null) {
           return mService.getmPersonVO();
        }
        return null;
    }
*/
    public int getQuizLevel() {
        if (mService != null) {
            return mService.getQuizLevel();
        }
        return 0;
    }

    public boolean isAnswer(String answer, boolean isPlayerQuiz) {
        if (mService != null) {
            return mService.isAnswer(answer, isPlayerQuiz);
        }
        return false;
    }

    public int getQuizScore() {
        if (mService != null) {
            return mService.getQuizScore();
        }
        return 0;
    }

    public void setQuizScore(int score) {
        if (mService != null) {
            mService.setQuizScore(score);
        }
    }
    public List<String> getEmblemContentsArr() {
        if (mService != null) {
            return mService.getEmblemContentsArr();
        }
        return null;
    }
    public void setIsPlayerQuiz(boolean quiz) {
        if (mService != null) {
            mService.setIsPlayerQuiz(quiz);
        }
    }
    public EmblemVO getmEmblemImageList() {
        if (mService != null) {
            return mService.getmEmblemImageList();
        }
        return null;
    }

    public boolean getIsPlayerQuiz() {
        if (mService != null) {
            return mService.getIsPlayerQuiz();
        }
        return false;
    }
    /*public List<FrameLayout> getmFindLayout(List<FrameLayout> list) {
        if (mService != null) {
            return mService.getmFindLayout(list);
        }
        return null;
    }*/
    /*public void applyRotationHint(float start, float mid, float end, float depth, FrameLayout frameLayout) {
        if (mService != null) {
            mService.applyRotationHint(start, mid, end, depth, frameLayout);
        }
    }*/
    public void viewHintListener(List<FrameLayout> frameLayouts) {
        if (mService != null) {
            mService.viewHintListener(frameLayouts);
        }
    }
    /*public void viewHindBackListener(List<FrameLayout> frameLayouts) {
        if (mService != null) {
            mService.viewHindBackListener(frameLayouts);
        }
    }*/

    public void startQuizGoneHint(List<FrameLayout> frameLayouts) {
        if (mService != null) {
            mService.startQuizGoneHint(frameLayouts);
        }
    }

    public void quizRestart() {
        if (mService != null) {
            mService.quizRestart();
        }
    }

    /*public void updateScore(long score) {
        if (mService != null) {
            mService.updateScore(score);
        }
    }

    public void viewLeaderBoardScore() {
        if (mService != null) {
            mService.viewLeaderBoardScore();
        }
    }*/

    public void setHintNum() {
        if (mService != null) {
            mService.setHintNum();
        }
    }

    public void setmHintNum(int num) {
        if (mService != null) {
            mService.setmHintNum(num);
        }
    }
    public int getHintNum() {
        if (mService != null) {
            return mService.getHintNum();
        }
        return 0;
    }

    public int getFrontAdsCount() {
        if (mService != null) {
            return mService.getFrontAdsCount();
        }
        return 0;
    }

    public void setFrontAdsCount(int num) {
        if (mService != null) {
            mService.setFrontAdsCount(num);
        }
    }

    public boolean getNewScore() {
        if (mService != null) {
            return mService.getNewScore();
        }
        return false;
    }

    public void setNewScore(boolean b) {
        if (mService != null) {
            mService.setNewScore(b);
        }
    }
}