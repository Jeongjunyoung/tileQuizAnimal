package tq.apps.obg.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import tq.apps.obg.R;
import tq.apps.obg.databinding.ActivityLoadingBinding;
import tq.apps.obg.db.DBHelper;
import tq.apps.obg.domain.EmblemDBList;
import tq.apps.obg.domain.EmblemVO;
import tq.apps.obg.domain.PersonDBList;
import tq.apps.obg.domain.PersonVO;
import tq.apps.obg.domain.TileDBList;
import tq.apps.obg.domain.TileVO;

public class LoadingActivity extends AppCompatActivity {
    private ActivityLoadingBinding mBinding;
    public static Context mContext;
    private DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        startLoading();
    }

    public void startLoading() {
        mContext = this;
        dbHelper = new DBHelper(this);
        dbHelper.open();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getBaseContext(), FrontActivity.class);
                startActivity(intent);
                finish();
            }
        }, 1200);
    }

    @Override
    public void finish() {
        overridePendingTransition(R.anim.loading_activity_anim_in, R.anim.loading_activity_anim_out);
        super.finish();
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
