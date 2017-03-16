package com.cuisanzhang.mincreafting;

import java.util.ArrayList;
import java.util.List;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


public class DbManage {

	private static final String TAG = "DbManage";
	private Context context;
	private static MyDatabaseHelper db = null;
	private static SQLiteDatabase dbRead = null;
	private static SQLiteDatabase dbWrite = null;
	
	
	public DbManage(Context c) {
		// TODO Auto-generated constructor stub
		context = c;
		
		if (db == null) {
			db = new MyDatabaseHelper(c);
			dbRead = db.getReadableDatabase();
			dbWrite = db.getWritableDatabase();
		}
	}
	
	public void insertDataToTable(String TableName, String JosnfileName) {
		List<Block> list =null;
		list = ReadJosnData.ReadDataformJosnFile(context, JosnfileName);
		for (int i = 0; i < list.size(); i++) {
			
			Block item = list.get(i);
			
			int res_id = item.getResId();
			String file_name = item.getFileName();
			String name = item.getName();
			String material = item.getMaterial();
			String use = item.getUse();
			String detail = item.getDetail();
			boolean isgif = item.isgif();
			
			ContentValues values = new ContentValues();
			values.put(Block.RES_ID, res_id);
			values.put(Block.FILE_NAME, file_name);
			values.put(Block.NAME, name);
			values.put(Block.MATERIAL, material);
			values.put(Block.USE, use);
			values.put(Block.DETAIL, detail);
			values.put(Block.ISGIF, isgif);
			Log.e(TAG , "insertDataToTable " + name);
			dbWrite.insert(TableName, null, values);
		}
		
		
	}
	
	public List<Block>  getDatasFormTable(String TableName) {
		List<Block>  list = new ArrayList<Block>();
		
		Cursor cursor = dbRead.query(TableName, null, null, null, null, null, null);
//		int count = cursor.getCount();
		if (cursor.moveToFirst() == false) {
			Log.e(TAG , "getDatasFormTable return cursor getcount = " + cursor.getCount());
		return list;	
		}
		Log.e(TAG , "getDatasFormTable cursor getcount = " + cursor.getCount());
		
		for (int i = 0; i < cursor.getCount(); i++) {
			
		
			int res_id  = cursor.getInt((cursor.getColumnIndex(Block.RES_ID)));
			String file_name  = cursor.getString((cursor.getColumnIndex(Block.FILE_NAME)));
			String name = cursor.getString((cursor.getColumnIndex(Block.NAME)));
			String material = cursor.getString((cursor.getColumnIndex(Block.MATERIAL)));
			String use = cursor.getString((cursor.getColumnIndex(Block.USE)));
			String detail = cursor.getString((cursor.getColumnIndex(Block.DETAIL)));
			int isgif = cursor.getInt((cursor.getColumnIndex(Block.ISGIF)));
			
			boolean boolIsGif;
			if (isgif == 0 )
				 boolIsGif =false;
			else {
				 boolIsGif =true;
			};
			
//			Log.e(TAG , "getDatasFormTable " + name);
			Block block = new Block(res_id, file_name, name, material, use, detail, boolIsGif);
			
			list.add(block);
			cursor.moveToNext();
		}
		
				
			
		return list;
	}
	
	public List<Block>   SeachString(String name){
		List<Block> list =null;
		
		return list;
	}
	
}
