package com.cuisanzhang.mincreafting;


public class SearchResult {

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public String getFileName() {
        return file_name;
    }

    public String getMaterial() {
        return material;
    }

    private String category;
    private String file_name;
    private String name;
    private String material;

    public SearchResult(String category, String file_name, String name, String material) {
        this.category = category;
        this.file_name = file_name;
        this.name = name;
        this.material = material;

    }


}