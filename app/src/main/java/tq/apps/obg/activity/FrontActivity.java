package tq.apps.obg.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import java.util.List;

import tq.apps.obg.R;
import tq.apps.obg.databinding.ActivityFrontBinding;
import tq.apps.obg.db.DBHelper;
import tq.apps.obg.domain.EmblemDBList;
import tq.apps.obg.domain.EmblemVO;
import tq.apps.obg.domain.PersonDBList;
import tq.apps.obg.domain.PersonVO;
import tq.apps.obg.domain.TileDBList;
import tq.apps.obg.domain.TileVO;

public class FrontActivity extends AppCompatActivity implements View.OnClickListener{
    ActivityFrontBinding mBinding;
    public static Context mContext;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_front);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
    }
    private void initView() {
        mContext = this;
        dbHelper = new DBHelper(this);
        dbHelper.open();
        mBinding.playerQuiz.setOnClickListener(this);
        mBinding.teamQuiz.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(FrontActivity.this, TQActivity.class);
        switch (view.getId()) {
            case R.id.player_quiz:
                intent.putExtra("quizKinds", "player");
                startActivity(intent);
                break;
            case R.id.team_quiz:
                intent.putExtra("quizKinds", "team");
                startActivity(intent);
                break;
        }
    }
    public List<TileVO> getTileList() {
        TileDBList tileDBList = new TileDBList();
        return tileDBList.getDBTileList();
    }

    public List<PersonVO> getPersonList() {
        PersonDBList personDBList = new PersonDBList();
        return personDBList.getDBPersonList();
    }

    public List<EmblemVO> getEmblemList() {
        EmblemDBList emblemDBList = new EmblemDBList();
        return emblemDBList.getDBPersonList();
    }
    public int getId(String imageName) {
        return getResources().getIdentifier("tq.apps.obg:drawable/" + imageName, null, null);
    }
}
