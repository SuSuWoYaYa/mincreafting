package com.cuisanzhang.mincreafting;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.array;
import android.R.color;
import android.content.Context;
import android.util.Log;

import com.cuisanzhang.mincreafting.Block;

public class ReadJosnData {

	private static Context context;

	public static List<Block> ReadBlockformJosnFile(Context c, String fileName) {

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
			string = builder.toString();
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
			Log.e("ReadJosnData", "read josn file "+  fileName + " error");
		}

		System.out.println(blocks.size());

		return blocks;
	}

	public static List<Enchant> ReadEnchantformJosnFile(Context c, String fileName) {

		context = c;
		List<Enchant> enchants = new ArrayList<Enchant>() {
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

//			Log.e("ReadJosnData", "read josn builder.toString "+  builder.toString());
			reader.close();
			isr.close();
			is.close();

			String string = null;
			string = builder.toString();
			System.out.println(builder.toString());

			JSONObject root = new JSONObject(string);
			String category = root.getString("category");
			JSONArray arrays = root.getJSONArray("items");
//
//			Log.e("ReadJosnData", "start read josn  " + fileName );
			for (int i = 0; i < arrays.length(); i++) {

				JSONObject item = arrays.getJSONObject(i);
				String name = item.getString(Enchant.NAME);
//				Log.e("ReadJosnData", "read josn Enchant NAME"+  name );
				String high_level = item.getString(Enchant.HIGH_LEVEL);
//				Log.e("ReadJosnData", "read josn Enchant HIGH_LEVEL"+  high_level );
				String main_file_name = item.getString(Enchant.MAIN_FILE_NAME);
//				Log.e("ReadJosnData", "read josn Enchant MAIN_FILE_NAME"+  main_file_name );

				String sub_file_name = item.getString(Enchant.SUB_FILE_NAME);
//				Log.e("ReadJosnData", "read josn Enchant SUB_FILE_NAME"+  sub_file_name );

				String use = item.getString(Enchant.USE);
				String detail = item.getString(Enchant.DETAIL);
				Enchant enchant = new Enchant(name, high_level, main_file_name, sub_file_name, use,
						detail);
				enchants.add(enchant);
//				Log.e("ReadJosnData", "read josn name "+  name );
			}

		} catch (Exception e) {
			e.printStackTrace();
			Log.e("ReadJosnData", "read josn file "+  fileName + " error");
		}

		System.out.println(enchants.size());

		return enchants;
	}
//
//	private static int getResourceId(String file_name) {
//		// Context ctx=context.getBaseContext();
//		int resId = context.getResources().getIdentifier(file_name, "drawable",
//				context.getPackageName());
//		return resId;
//	}

}
