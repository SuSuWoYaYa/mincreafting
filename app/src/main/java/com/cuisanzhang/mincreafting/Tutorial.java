package com.cuisanzhang.mincreafting;

/**
 * Created by hesuxiang on 18/2/8.
 */

public class Tutorial {
    private String tutorial_name = "tutorial_name";
    private String tutorial_url = "tutorial_url";

    public static String TUTORIAL_NAME = "tutorial_name";
    public static String TUTORIAL_URL = "tutorial_url";

    public Tutorial(String tutorialName, String tutorialUrl){
        tutorial_name = tutorialName;
        tutorial_url = tutorialUrl;
    }


    public String getTutorial_name() {
        return tutorial_name;
    }

    public String getTutorial_url() {
        return tutorial_url;
    }

}
