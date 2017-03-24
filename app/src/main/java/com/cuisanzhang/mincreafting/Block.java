package com.cuisanzhang.mincreafting;

public class Block {

	
//	public static String RES_ID = "res_id";
	public static String FILE_NAME = "file_name";
	public static String NAME = "name";
	public static String MATERIAL = "material";
	public static String USE = "use";
	public static String DETAIL = "detail";
	public static String ISGIF = "isgif";
	
//	private int res_id = 0;
	private String file_name = null;
	private String name = null;
	private String material = null;
	private String use = null;
	private String detail = null;
	private Boolean isgif = false;

	public Block(String file_name, String name, String material,
			String use, String detail, Boolean isgif) {
		// TODO Auto-generated constructor stub
//		this.res_id = res_id;
		this.file_name = file_name;
		this.name = name;
		this.material = material;
		this.use = use;
		this.detail = detail;
		this.isgif = isgif;
	}

//	public int getResId() {
//		return res_id;
//	}

	public String getFileName() {
		return file_name;
	}

	public String getName() {
		return name;
	}

	public String getMaterial() {
		return material;
	}

	public String getUse() {
		return use;
	}

	public String getDetail() {
		return detail;
	}

	public Boolean isgif() {
		return isgif;
	}
	
	
	
}
