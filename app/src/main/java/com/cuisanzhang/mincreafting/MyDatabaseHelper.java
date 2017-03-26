package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "DataBase.sqlite";

	public static final String TABLE_BUILDING =  "table_building";	//建筑 building
	public static final String TABLE_DAILY =  "table_daily";		//日常 daily
	public static final String TABLE_DECORATION =  "table_decoration";	//装饰 decoration
	public static final String TABLE_DYE =  "table_dye";		//染料类 dye
	public static final String TABLE_FOOD =  "table_food";		//食物 food
	public static final String TABLE_LIGHTING =  "table_lighting";	//照明 lighting
	public static final String TABLE_ORE =  "table_ore";		//矿石 ore
	public static final String TABLE_REDSTONE =  "table_redstone";	//红石 redstone
	public static final String TABLE_TANNSPORT =  "table_tannsport";	//运输 tannsport
	public static final String TABLE_TOOLS =  "table_tools";		//工具 tools
	public static final String TABLE_WEAPON =  "table_weapon";		//武器 weapon
	public static final String TABLE_OTHERS =  "table_others";		//杂项类 others
	public static final String TABLE_BREWING =  "table_brewing";		//药水类 brewing
	public static final String TABLE_ENCHANT =  "table_enchant";		//附魔类 enchant

	public static final String TABLE_NAMES []= {
			"table_building", 	//建筑 building
			"table_daily",		//日常 daily
			"table_decoration",	//装饰 decoration
			"table_dye",		//染料类 dye
			"table_food",		//食物 food
			"table_lighting",	//照明 lighting
			"table_ore",		//矿石 ore
			"table_redstone",	//红石 redstone
			"table_tannsport",	//运输 tannsport
			"table_tools",		//工具 tools
			"table_weapon",		//武器 weapon
			"table_others",		//杂项类 others
			"table_brewing",		//杂项类 others

	};
	
	
	public MyDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		for (int i = 0; i < TABLE_NAMES.length; i++) {
			db.execSQL("CREATE TABLE " + TABLE_NAMES[i]
					+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
//					+ " res_id INTEGER,"
					+ " file_name TEXT,"
					+ " name TEXT,"
					+ " material TEXT,"
					+ " use TEXT,"
					+ " detail TEXT)");
//					+ " isgif INTEGER)");
		}
		//附魔单独一个表
		db.execSQL("CREATE TABLE " + TABLE_ENCHANT
				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
//					+ " res_id INTEGER,"
				+ " name TEXT,"
				+ " high_level TEXT,"
				+ " main_file_name TEXT,"
				+ " sub_file_name TEXT,"
//				+ " material TEXT,"
				+ " use TEXT,"
				+ " detail TEXT)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

}
