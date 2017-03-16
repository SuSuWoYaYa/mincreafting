package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "DataBase.db";
	public static final String TABLE_NAME_JIANZHULEI = "table_jianzhulei";
	public static final String TABLE_NAME_ZHUANGSHIXIGN = "table_zhuangshixing";
	public static final String TABLE_NAME_ZHUANGZHI_REDSHONE = "table_zhuanzhi_redstone";
	public static final String TABLE_NAME_FOOD = "table_food";
	public static final String TABLE_NAME_YUNSHU = "table_yunshu";
	
	
	public MyDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE " + TABLE_NAME_JIANZHULEI 
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT," 
				+ " res_id INTEGER,"
				+ " file_name TEXT,"
				+ " name TEXT,"
				+ " material TEXT,"
				+ " use TEXT,"
				+ " detail TEXT,"
				+ " isgif INTEGER)");
		
		db.execSQL("CREATE TABLE " + TABLE_NAME_ZHUANGSHIXIGN
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT," 
				+ " res_id INTEGER,"
				+ " file_name TEXT,"
				+ " name TEXT,"
				+ " material TEXT,"
				+ " use TEXT,"
				+ " detail TEXT,"
				+ " isgif INTEGER)");
		
		db.execSQL("CREATE TABLE " + TABLE_NAME_ZHUANGZHI_REDSHONE 
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT," 
				+ " res_id INTEGER,"
				+ " file_name TEXT,"
				+ " name TEXT,"
				+ " material TEXT,"
				+ " use TEXT,"
				+ " detail TEXT,"
				+ " isgif INTEGER)");
		
		db.execSQL("CREATE TABLE " + TABLE_NAME_FOOD 
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT," 
				+ " res_id INTEGER,"
				+ " file_name TEXT,"
				+ " name TEXT,"
				+ " material TEXT,"
				+ " use TEXT,"
				+ " detail TEXT,"
				+ " isgif INTEGER)");
		
		db.execSQL("CREATE TABLE " + TABLE_NAME_YUNSHU
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT," 
				+ " res_id INTEGER,"
				+ " file_name TEXT,"
				+ " name TEXT,"
				+ " material TEXT,"
				+ " use TEXT,"
				+ " detail TEXT,"
				+ " isgif INTEGER)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
