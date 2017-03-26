package com.cuisanzhang.mincreafting;

public class Enchant {


//	public static String RES_ID = "res_id";
	public static String NAME = "name";
	public static String HIGH_LEVEL = "high_level";
	public static String MAIN_FILE_NAME = "main_file_name";
	public static String SUB_FILE_NAME = "sub_file_name";
//	public static String MATERIAL = "material";
	public static String USE = "use";
	public static String DETAIL = "detail";

//	private int res_id = 0;
	private String name = null;
	private String high_level = null;
	private String main_file_name = null;
	private String sub_file_name = null;
	private String material = null;
	private String use = null;
	private String detail = null;

	public Enchant(String name, String high_level, String main_file_name,String sub_file_name,
				   String use, String detail) {
		// TODO Auto-generated constructor stub
//		this.res_id = res_id;
		this.main_file_name = main_file_name;
		this.sub_file_name = sub_file_name;
		this.name = name;
		this.high_level = high_level;
//		this.material = material;
		this.use = use;
		this.detail = detail;
	}


	public String getMainFileName() {
		return main_file_name;
	}
	public String getSubFileName() {
		return sub_file_name;
	}

	public String getName() {
		return name;
	}
	public String getHighLevel() {
		return high_level;
	}

//	public String getMaterial() {
//		return material;
//	}

	public String getUse() {
		return use;
	}

	public String getDetail() {
		return detail;
	}


	
	
}
