package com.cuisanzhang.mincreafting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabaseHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "DataBase.sqlite";
	public static final int DB_VERSION = 9;


    //每个分类数据库的表名,用来传递给分类详情页使用
	public static final String TABLE_BUILDING    =   "table_building";	//建筑 building
	public static final String TABLE_DECORATION  =   "table_decoration";	//装饰 decoration
	public static final String TABLE_DYE         =   "table_dye";		//染料类 dye
	public static final String TABLE_FOOD        =   "table_food";		//食物 food
	public static final String TABLE_LIGHTING    =   "table_lighting";	//照明 lighting
	public static final String TABLE_ORE         =   "table_ore";		//矿石 ore
	public static final String TABLE_REDSTONE    =   "table_redstone";	//红石 redstone
	public static final String TABLE_TANNSPORT   =   "table_tannsport";	//运输 tannsport
	public static final String TABLE_TOOLS       =   "table_tools";		//工具 tools
	public static final String TABLE_WEAPON      =   "table_weapon";		//武器 weapon
	public static final String TABLE_OTHERS      =   "table_others";		//杂项类 others
	public static final String TABLE_SMELTING    =   "table_smelting";		//烧炼类 smelting
	public static final String TABLE_BREWING     =   "table_brewing";		//药水类 brewing
	public static final String TABLE_ENCHANT     =   "table_enchant";		//附魔类 enchant

	public static final String TABLE_MOBS_BOSS	    =	"table_mobs_boss"; 			//Boss boss
	public static final String TABLE_MOBS_HOSTILE	=	"table_mobs_hostile";       // 攻击型
	public static final String TABLE_MOBS_NEUTRAL	=	"table_mobs_neutral";       // 中立型
	public static final String TABLE_MOBS_PASSIVE	=	"table_mobs_passive"; 		//被动型
	public static final String TABLE_MOBS_TAMEABLE	=	"table_mobs_tameable";      // 可驯服
	public static final String TABLE_MOBS_UTILITY	=	"table_mobs_utility";        // 效用型
	public static final String TABLE_MOBS_UNUSE		=	"table_mobs_unuse";      	// 其他的生物


    public static final String TABLE_ITEM_BLOCK_NATURAL		=	"table_item_block_natural";     //自然生成
    public static final String TABLE_ITEM_BLOCK_STRUCTURES	=	"table_item_block_structures";  //结构方块
    public static final String TABLE_ITEM_BLOCK_COMMANDS	=	"table_item_block_commands";    //命令类
    public static final String TABLE_ITEM_BLOCK_FOOD	    =	"table_item_block_food";      	//食物类
    public static final String TABLE_ITEM_BLOCK_OTHERS	    =	"table_item_block_others";      //其他
    public static final String TABLE_ITEM_BLOCK_MATERIALS	=	"table_item_block_materials";   //材料类
    public static final String TABLE_ITEM_BLOCK_PLANTS		=	"table_item_block_plants";      //植物类



	public static void initLanguageMessage (Context context){

		DATA_BASE_CATEGORYS[0] = context.getString(R.string.yaoshuilei);
		DATA_BASE_CATEGORYS[1] = context.getString(R.string.fumolei);
		DATA_BASE_CATEGORYS[2] = context.getString(R.string.rangliaoleihecheng);
		DATA_BASE_CATEGORYS[3] = context.getString(R.string.zhuangshileihecheng);
		DATA_BASE_CATEGORYS[4] = context.getString(R.string.wuqileihecheng);

		DATA_BASE_CATEGORYS[5] = context.getString(R.string.yunshuleihecheng);
		DATA_BASE_CATEGORYS[6] = context.getString(R.string.gongjuleihecheng);
		DATA_BASE_CATEGORYS[7] = context.getString(R.string.jianzhuleihecheng);
		DATA_BASE_CATEGORYS[8] = context.getString(R.string.redstonehezhuanzhileihechen);

		DATA_BASE_CATEGORYS[9] = context.getString(R.string.shiwuleihecheng);
		DATA_BASE_CATEGORYS[10] = context.getString(R.string.zhaomingleihecheng);
		DATA_BASE_CATEGORYS[11] = context.getString(R.string.kuangshileihecheng);
		DATA_BASE_CATEGORYS[12] = context.getString(R.string.qitaleihecheng);
		DATA_BASE_CATEGORYS[13] = context.getString(R.string.shaolianlei);

		DATA_BASE_CATEGORYS[14] = context.getString(R.string.zhirangshengchengfankuai);
		DATA_BASE_CATEGORYS[15] = context.getString(R.string.jiegoufangkuai);
		DATA_BASE_CATEGORYS[16] = context.getString(R.string.minglingleifankuai);
		DATA_BASE_CATEGORYS[17] = context.getString(R.string.shiwuleiwuping);
		DATA_BASE_CATEGORYS[18] = context.getString(R.string.qitawuping);
		DATA_BASE_CATEGORYS[19] = context.getString(R.string.cailiaoleiwuping);
		DATA_BASE_CATEGORYS[20] = context.getString(R.string.zhiwuleiwuping);

		DATA_BASE_CATEGORYS[21] = context.getString(R.string.boss);
		DATA_BASE_CATEGORYS[22] = context.getString(R.string.gongjixingshengwu);
		DATA_BASE_CATEGORYS[23] = context.getString(R.string.zhonglixingshengwu);
		DATA_BASE_CATEGORYS[24] = context.getString(R.string.beidongxingshengwu);
		DATA_BASE_CATEGORYS[25] = context.getString(R.string.kexunfushengwu);
		DATA_BASE_CATEGORYS[26] = context.getString(R.string.xiaoyongxingshengwu);
		DATA_BASE_CATEGORYS[27] = context.getString(R.string.qitashengwu);
	}
	//排序影响搜索结果,搜索从后搜到前
    //每个分类数据的类别,初始化数据库提示用
    public static final String[] DATA_BASE_CATEGORYS = {

			"药水类",
			"附魔类",
			"染料类合成",
			"装饰类合成",
			"武器类合成",

			"运输类合成",
			"工具类合成",
            "建筑类合成",
			"红石类合成",
//            "日常类",
            "食物类合成",
            "照明类合成",
            "矿石类合成",
            "其他类合成",
            "烧炼类",

			"自然生成方块",
			"结构方块",
			"命令类方块",
			"食物类物品",
			"其他物品",
			"材料类物品",
			"植物类物品",

            "Boss生物",
            "攻击型生物",
            "中立型生物",
            "被动型生物",
            "可驯服生物",
            "效用型生物",
            "其他生物",

    };

//	public static String jsonEnchant = "jsons/creating/enchant.json";

    //json文件路径
	//排序影响搜索结果
	public static final String[] jsons = {

			"jsons/creating/brewing.json",       //药水类 brewing
			"jsons/creating/enchant.json",		//附魔类 enchant
			"jsons/creating/dye.json",         //染料类 dye
			"jsons/creating/decoration.json",  //装饰 decoration
			"jsons/creating/weapon.json",      //武器 weapon
			"jsons/creating/tannsport.json",   //运输 tannsport
			"jsons/creating/tools.json",       //工具 tools
			"jsons/creating/building.json",    //建筑 building
			"jsons/creating/redstone.json",    //红石 redstone
//            "jsons/daily.json",       //日常 daily
			"jsons/creating/food.json",        //食物 food
			"jsons/creating/lighting.json",    //照明 lighting
			"jsons/creating/ore.json",         //矿石 ore
			"jsons/creating/others.json",      //杂项类 others
			"jsons/creating/smelting.json",      //烧炼类 others

            //物品和方块
            "jsons/item_block/item_block_natural.json",     //自然生成
            "jsons/item_block/item_block_structures.json",  //结构方块
            "jsons/item_block/item_block_commands.json",    //命令类
            "jsons/item_block/item_block_food.json",        //食物类
            "jsons/item_block/item_block_others.json",      //其他
            "jsons/item_block/item_block_materials.json",   //材料类
            "jsons/item_block/item_block_plants.json",      //植物类

			"jsons/mobs/mobs_boss.json",         //Boss boss
			"jsons/mobs/mobs_hostile.json",         // 攻击型生物
			"jsons/mobs/mobs_neutral.json",         // 中立型生物
			"jsons/mobs/mobs_passive.json",         // 被动型生物
			"jsons/mobs/mobs_tameable.json",         // 可驯服生物
			"jsons/mobs/mobs_utility.json",         // 效用型生物
			"jsons/mobs/mobs_unuse.json",         // 其他的生物

	};

    //每个分类数据库的表名, 便于循环使用数据库
	//排序影响搜索结果
	public static final String[] TABLE_NAMES = {

			"table_brewing",		//药水类 brewing
			"table_enchant",		//附魔类 enchant
			"table_dye",		//染料类 dye
			"table_decoration",	//装饰 decoration
			"table_weapon",		//武器 weapon
			"table_tannsport",	//运输 tannsport
			"table_tools",		//工具 tools
			"table_building", 	//建筑 building
			"table_redstone",	//红石 redstone
//			"table_daily",		//日常 daily
			"table_food",		//食物 food
			"table_lighting",	//照明 lighting
			"table_ore",		//矿石 ore
			"table_others",		//杂项类 others
			"table_smelting",		//烧炼类 weapon

            //物品和方块
            "table_item_block_natural",     //自然生成
            "table_item_block_structures",  //结构方块
            "table_item_block_commands",    //命令类
            "table_item_block_food",        //食物类
            "table_item_block_others",      //其他
            "table_item_block_materials",   //材料类
            "table_item_block_plants",      //植物类

			"table_mobs_boss",         //Boss boss
			"table_mobs_hostile",         // 攻击型生物
			"table_mobs_neutral",         // 中立型生物
			"table_mobs_passive",         // 被动型生物
			"table_mobs_tameable",         // 可驯服生物
			"table_mobs_utility",         // 效用型生物
			"table_mobs_unuse",         // 其他的生物


	};



	public MyDatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DB_VERSION);
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
		//附魔取消单独一个表
//		db.execSQL("CREATE TABLE " + TABLE_ENCHANT
//				+ " (_id INTEGER PRIMARY KEY AUTOINCREMENT,"
////					+ " res_id INTEGER,"
//				+ " name TEXT,"
//				+ " high_level TEXT,"
//				+ " main_file_name TEXT,"
//				+ " sub_file_name TEXT,"
////				+ " material TEXT,"
//				+ " use TEXT,"
//				+ " detail TEXT)");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		for (int i = 0; i < TABLE_NAMES.length; i++) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMES[i]);
		}
		//附魔取消单独一个表
		//db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENCHANT);
		onCreate(db);


	}

}
