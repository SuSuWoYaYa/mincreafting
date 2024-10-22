package com.cuisanzhang.mincreafting;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.luhuiguo.chinese.ChineseUtils;

public class ReadJsonData {

	private static Context context;

	public static List<Block> ReadBlockformJsonFile(Context c, String fileName, boolean is_simplified_chinese) {

		context = c;


		List<Block> blocks = new ArrayList<Block>() {
		};
		try {

			InputStream is = context.getAssets().open(fileName);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader reader = new BufferedReader(isr);

			StringBuilder builder = new StringBuilder();
			String line = null;

			while ((line = reader.readLine()) != null) {
				builder.append(line);
			}

			reader.close();
			isr.close();
			is.close();
			
			String string = null;
            if (is_simplified_chinese){
                string = builder.toString();
            }else {
                string = ChineseUtils.toTraditional(builder.toString());
            }
//			 System.out.println(builder.toString());

			JSONObject root = new JSONObject(string);
			String category = root.getString("category");
			JSONArray arrays = root.getJSONArray("blocks");
			for (int i = 0; i < arrays.length(); i++) {

				JSONObject item = arrays.getJSONObject(i);
				String file_name = item.getString(Block.FILE_NAME);
				String name = item.getString(Block.NAME);
				String material = item.getString(Block.MATERIAL);
				String use = item.getString(Block.USE);
				String detail = item.getString(Block.DETAIL);
//				boolean isgif = item.getBoolean(Block.ISGIF);
//				int res_id = getResourceId(file_name);
				Block block = new Block( file_name, name, material, use,
						detail);
				blocks.add(block);
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.e("ReadJsonData", "read json file "+  fileName + " error");
		}

//		System.out.println(blocks.size());

		return blocks;
	}


	public static List<Tutorial> ReadTutorialsformJsonString(Context c, String jsonString) {

        context = c;
        List<Tutorial> tutorials = new ArrayList<Tutorial>() {
        };
        try {

            JSONObject root = new JSONObject(jsonString);
            String category = root.getString("category");
            JSONArray arrays = root.getJSONArray("tutorials");
            for (int i = 0; i < arrays.length(); i++) {

                JSONObject item = arrays.getJSONObject(i);
                String tutorial_name = item.getString(Tutorial.TUTORIAL_NAME);
                String tutorial_url = item.getString(Tutorial.TUTORIAL_URL);
                Tutorial tutorial = new Tutorial( tutorial_name, tutorial_url);
                tutorials.add(tutorial);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("ReadJsonData", "read online json string error");
        }

        System.out.println(tutorials.size());

        return tutorials;

	}
//	public static List<Enchant> ReadEnchantformJsonFile(Context c, String fileName) {
//
//		context = c;
//		List<Enchant> enchants = new ArrayList<Enchant>() {
//		};
//		try {
//
//			InputStream is = context.getAssets().open(fileName);
//			InputStreamReader isr = new InputStreamReader(is);
//			BufferedReader reader = new BufferedReader(isr);
//
//			StringBuilder builder = new StringBuilder();
//			String line = null;
//
//			while ((line = reader.readLine()) != null) {
//				builder.append(line);
//			}
//
////			Log.e("ReadJsonData", "read josn builder.toString "+  builder.toString());
//			reader.close();
//			isr.close();
//			is.close();
//
//			String string = null;
//			string = builder.toString();
//			System.out.println(builder.toString());
//
//			JSONObject root = new JSONObject(string);
//			String category = root.getString("category");
//			JSONArray arrays = root.getJSONArray("items");
////
////			Log.e("ReadJsonData", "start read josn  " + fileName );
//			for (int i = 0; i < arrays.length(); i++) {
//
//				JSONObject item = arrays.getJSONObject(i);
//				String name = item.getString(Enchant.NAME);
////				Log.e("ReadJsonData", "read josn Enchant NAME"+  name );
//				String high_level = item.getString(Enchant.HIGH_LEVEL);
////				Log.e("ReadJsonData", "read josn Enchant HIGH_LEVEL"+  high_level );
//				String main_file_name = item.getString(Enchant.MAIN_FILE_NAME);
////				Log.e("ReadJsonData", "read josn Enchant MAIN_FILE_NAME"+  main_file_name );
//
//				String sub_file_name = item.getString(Enchant.SUB_FILE_NAME);
////				Log.e("ReadJsonData", "read josn Enchant SUB_FILE_NAME"+  sub_file_name );
//
//				String use = item.getString(Enchant.USE);
//				String detail = item.getString(Enchant.DETAIL);
//				Enchant enchant = new Enchant(name, high_level, main_file_name, sub_file_name, use,
//						detail);
//				enchants.add(enchant);
////				Log.e("ReadJsonData", "read josn name "+  name );
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			Log.e("ReadJsonData", "read json file "+  fileName + " error");
//		}
//
//		System.out.println(enchants.size());
//
//		return enchants;
//	}
//
//	private static int getResourceId(String file_name) {
//		// Context ctx=context.getBaseContext();
//		int resId = context.getResources().getIdentifier(file_name, "drawable",
//				context.getPackageName());
//		return resId;
//	}

}
