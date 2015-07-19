package com.j4f.wallpaper.Helpers;

/**
 * Created by pham on 7/13/2015.
 */
public class Constants {
    public final static String WEB_URL = "http://manageadmin.bl.ee/WallPapaer/";
    public final static String WEB_TAG = ".php";

    public final static String makeUrl(String methodName) {
        String url = WEB_URL + methodName + WEB_TAG;
        return url;
    }
}
