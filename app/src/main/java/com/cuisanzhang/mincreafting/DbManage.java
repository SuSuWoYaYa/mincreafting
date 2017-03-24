package com.cuisanzhang.mincreafting;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DbManage {
    private static String SAVE_FILE = "save.txt";
    private static String HASDADABASE = "hasDatabase";

    private static final String TAG = "DbManage";
    private Context context;
    private static MyDatabaseHelper db = null;
    private static SQLiteDatabase dbRead = null;
    private static SQLiteDatabase dbWrite = null;
    private static SharedPreferences preferences = null;

    private static String[] josns = {
            "josns/building.josn",    //建筑 building
            "josns/daily.josn",       //日常 daily
            "josns/decoration.josn",  //装饰 decoration
            "josns/dye.josn",         //染料类 dye
            "josns/food.josn",        //食物 food
            "josns/lighting.josn",    //照明 lighting
            "josns/ore.josn",         //矿石 ore
            "josns/redstone.josn",    //红石 redstone
            "josns/tannsport.josn",   //运输 tannsport
            "josns/tools.josn",       //工具 tools
            "josns/weapon.josn",      //武器 weapon
            "josns/others.josn"       //杂项类 others
    };


    public DbManage(Context c) {
        // TODO Auto-generated constructor stub
        context = c;

        if (db == null) {
            db = new MyDatabaseHelper(c);
            dbRead = db.getReadableDatabase();
            dbWrite = db.getWritableDatabase();
        }

        if (preferences == null) {
            preferences = context.getSharedPreferences(SAVE_FILE,
                    Context.MODE_APPEND);
        }

        boolean hasDatabase = preferences.getBoolean(HASDADABASE, false);
        System.out.println("hasDatabase = " + hasDatabase);
        if (hasDatabase == false) {
            initDatabase();
        }
    }

    public void initDatabase() {

        System.out.println("Start dbManage.insertDataToTable");
        for (int i = 0; i < josns.length; i++) {
            insertDataToTable(MyDatabaseHelper.TABLE_NAMES[i],
                    josns[i]);
        }
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(HASDADABASE, true);
        editor.apply();

    }

    public void insertDataToTable(String TableName, String JosnfileName) {
        List<Block> list = null;
        list = ReadJosnData.ReadBlockformJosnFile(context, JosnfileName);
        for (int i = 0; i < list.size(); i++) {

            Block item = list.get(i);

            String file_name = item.getFileName();
            String name = item.getName();
            String material = item.getMaterial();
            String use = item.getUse();
            String detail = item.getDetail();
            boolean isgif = item.isgif();

            ContentValues values = new ContentValues();
//            values.put(Block.RES_ID, res_id);
            values.put(Block.FILE_NAME, file_name);
            values.put(Block.NAME, name);
            values.put(Block.MATERIAL, material);
            values.put(Block.USE, use);
            values.put(Block.DETAIL, detail);
            values.put(Block.ISGIF, isgif);
            Log.e(TAG, "insertDataToTable " + name);
            dbWrite.insert(TableName, null, values);
        }


    }

    public List<Block> getDatasFormTable(String TableName) {
        List<Block> list = new ArrayList<Block>();

        Cursor cursor = dbRead.query(TableName, null, null, null, null, null, null);
//		int count = cursor.getCount();
        if (cursor.moveToFirst() == false) {
            Log.e(TAG, "getDatasFormTable " + TableName + " return cursor getcount = " + cursor.getCount());
            return list;
        }
        Log.e(TAG, "getDatasFormTable cursor getcount = " + cursor.getCount());

        for (int i = 0; i < cursor.getCount(); i++) {


//            int res_id = cursor.getInt((cursor.getColumnIndex(Block.RES_ID)));
            String file_name = cursor.getString((cursor.getColumnIndex(Block.FILE_NAME)));
            String name = cursor.getString((cursor.getColumnIndex(Block.NAME)));
            String material = cursor.getString((cursor.getColumnIndex(Block.MATERIAL)));
            String use = cursor.getString((cursor.getColumnIndex(Block.USE)));
            String detail = cursor.getString((cursor.getColumnIndex(Block.DETAIL)));
            int isgif = cursor.getInt((cursor.getColumnIndex(Block.ISGIF)));

            boolean boolIsGif;
            if (isgif == 0)
                boolIsGif = false;
            else {
                boolIsGif = true;
            }
            ;

//			Log.e(TAG , "getDatasFormTable " + name);
            Block block = new Block(file_name, name, material, use, detail, boolIsGif);

            list.add(block);
            cursor.moveToNext();
        }

        cursor.close();

        return list;
    }

    public  void  closeDatabase(){
        if (db != null){
            dbWrite.close();
            dbRead.close();
            db.close();
            dbWrite = null;
            dbRead = null;
            db = null;
        }
    }

    public List<Block> SeachString(String name) {
        List<Block> list = null;

        return list;
    }


}
