package tq.apps.obg.db;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import tq.apps.obg.activity.FrontActivity;
import tq.apps.obg.activity.LoadingActivity;
import tq.apps.obg.activity.TQActivity;
import tq.apps.obg.domain.EmblemVO;
import tq.apps.obg.domain.PersonVO;
import tq.apps.obg.domain.TileVO;

/**
 * Created by d1jun on 2018-02-20.
 */

public class DBHelper {
    private static final String DATABASE_NAME = "tile_quiz.db";
    private static final String TILE_TABLE = "tile_data";
    private static final String PERSON_TABLE = "person_data";
    private static final String EMBLEM_TABLE = "emblem_data";
    private static final int DATABASE_VERSION = 1;
    private static final String C_TILE_NAME = "tile_name";
    private static final String C_TILE_RES_ID = "tile_res_id";
    /*private static final String C_EPOCH = "epoch";
    private static final String C_RES_ID = "res_id";
    private static final String C_QUIZ_NUM = "quiz_num";*/
    public static SQLiteDatabase db;
    private DatabaseHelper mDBHelper;
    private Context mCtx;
    private static DBHelper instance = null;

    public DBHelper() {
    }

    public DBHelper(Context context) {
        this.mCtx = context;
    }

    public static DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper(context.getApplicationContext());
        }
        return instance;
    }

    private class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL("create table tile_data(_id integer primary key AUTOINCREMENT, tile_name text, tile_res_id integer)");
            sqLiteDatabase.execSQL("create table person_data(_id integer primary key AUTOINCREMENT, p_name text, p_res_id integer, p_job text, p_kr_name text)");
            sqLiteDatabase.execSQL("create table emblem_data(_id integer primary key AUTOINCREMENT, e_name text, e_res_id integer, e_league text, e_kr_name text)");
            LoadingActivity tq = ((LoadingActivity) LoadingActivity.mContext);
            insertAllData(tq.getTileList(), sqLiteDatabase);
            insertPersonData(tq.getPersonList(), sqLiteDatabase);
            insertEmblemData(tq.getEmblemList(), sqLiteDatabase);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }

    public DBHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mCtx, DATABASE_NAME, null, DATABASE_VERSION);
        db = mDBHelper.getWritableDatabase();
        return this;
    }

    public List<TileVO> selectTielData() {
        List<TileVO> list = new ArrayList<>();
        TileVO vo = null;
        String sql = "select _id, tile_name, tile_res_id from tile_data";
        Cursor cursor =  db.rawQuery(sql, null);
        for(int i = 0;i<cursor.getCount(); i++) {
            cursor.moveToNext();
            vo = new TileVO();
            vo.set_id(cursor.getInt(0));
            vo.setTile_name(cursor.getString(1));
            vo.setTile_res_id(cursor.getInt(2));
            list.add(vo);
        }
        cursor.close();
        return list;
    }
    public List<PersonVO> selectPersonData() {
        List<PersonVO> list = new ArrayList<>();
        PersonVO vo = null;
        String sql = "select _id, p_name, p_res_id, p_job, p_kr_name from person_data";
        Cursor cursor =  db.rawQuery(sql, null);
        for(int i = 0;i<cursor.getCount(); i++) {
            cursor.moveToNext();
            vo = new PersonVO();
            vo.set_id(cursor.getInt(0));
            vo.setP_name(cursor.getString(1));
            vo.setP_res_id(cursor.getInt(2));
            vo.setP_job(cursor.getString(3));
            list.add(vo);
        }
        cursor.close();
        return list;
    }
    public List<EmblemVO> selectEmblemData() {
        List<EmblemVO> list = new ArrayList<>();
        EmblemVO vo = null;
        String sql = "select _id, e_name, e_res_id, e_league, e_kr_name from emblem_data";
        Cursor cursor =  db.rawQuery(sql, null);
        for(int i = 0;i<cursor.getCount(); i++) {
            cursor.moveToNext();
            vo = new EmblemVO();
            vo.set_id(cursor.getInt(0));
            vo.setE_name(cursor.getString(1));
            vo.setE_res_id(cursor.getInt(2));
            vo.setE_league(cursor.getString(3));
            vo.setE_kr_name(cursor.getString(4));
            list.add(vo);
        }
        cursor.close();
        return list;
    }
    public List<PersonVO> selectContentsData(String kinds) {
        List<PersonVO> list = new ArrayList<>();
        PersonVO vo = null;
        String sql = "";
        String[] params = {kinds};
        if (kinds.equals("fb")) {
            sql = "select _id, p_name, p_res_id, p_job, p_kr_name from person_data where p_job = ?";
        }
        Cursor cursor =  db.rawQuery(sql, params);
        for(int i = 0;i<cursor.getCount(); i++) {
            cursor.moveToNext();
            vo = new PersonVO();
            vo.set_id(cursor.getInt(0));
            vo.setP_name(cursor.getString(1));
            vo.setP_res_id(cursor.getInt(2));
            vo.setP_job(cursor.getString(3));
            list.add(vo);
        }
        return list;
    }
    public List<EmblemVO> selectEmblemContents(String kinds) {
        List<EmblemVO> list = new ArrayList<>();
        EmblemVO vo = null;
        String sql = "";
        String[] params = {kinds};
        sql = "select _id, e_name, e_res_id, e_league, e_kr_name from emblem_data where e_league = ?";
        Cursor cursor =  db.rawQuery(sql, params);
        for(int i = 0;i<cursor.getCount(); i++) {
            cursor.moveToNext();
            vo = new EmblemVO();
            vo.set_id(cursor.getInt(0));
            vo.setE_name(cursor.getString(1));
            vo.setE_res_id(cursor.getInt(2));
            vo.setE_league(cursor.getString(3));
            vo.setE_kr_name(cursor.getString(4));
            list.add(vo);
        }
        return list;
    }
    private void insertAllData(List<TileVO> list, SQLiteDatabase sDB) {
        sDB.beginTransaction();
        try {
            for (int i = 0; i < list.size(); i++) {
                ContentValues cv = new ContentValues();
                cv.put(C_TILE_NAME, list.get(i).getTile_name());
                cv.put(C_TILE_RES_ID, list.get(i).gettile_res_id());
                sDB.insert(TILE_TABLE, null, cv);
            }
            sDB.setTransactionSuccessful();
        }finally {
            sDB.endTransaction();
            System.out.println("DB Insert Success~");
        }
    }
    private void insertPersonData(List<PersonVO> list, SQLiteDatabase sDB) {
        sDB.beginTransaction();
        try {
            for (int i = 0; i < list.size(); i++) {
                ContentValues cv = new ContentValues();
                cv.put("p_name", list.get(i).getP_name());
                cv.put("p_res_id", list.get(i).getP_res_id());
                cv.put("p_job", list.get(i).getP_job());
                sDB.insert(PERSON_TABLE, null, cv);
            }
            sDB.setTransactionSuccessful();
        }finally {
            sDB.endTransaction();
            System.out.println("DB Person Insert Success~");
        }
    }
    private void insertEmblemData(List<EmblemVO> list, SQLiteDatabase sDB) {
        sDB.beginTransaction();
        try {
            for (int i = 0; i < list.size(); i++) {
                ContentValues cv = new ContentValues();
                cv.put("e_name", list.get(i).getE_name());
                cv.put("e_res_id", list.get(i).getE_res_id());
                cv.put("e_league", list.get(i).getE_league());
                cv.put("e_kr_name", list.get(i).getE_kr_name());
                sDB.insert(EMBLEM_TABLE, null, cv);
            }
            sDB.setTransactionSuccessful();
        }finally {
            sDB.endTransaction();
            System.out.println("DB Emblem Insert Success~");
        }
    }
}
