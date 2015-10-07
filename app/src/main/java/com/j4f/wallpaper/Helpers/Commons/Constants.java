package com.j4f.wallpaper.Helpers.Commons;

/**
 * Created by pham on 7/13/2015.
 */
public class Constants {
    public final static String WEB_URL = "http://manageadmin.bl.ee/WallPapaer/";
    public final static String WEB_TAG = ".php";
    public final static String HOST_URL = "https://picasaweb.google.com/data/feed/api/user/";
    public final static String HOST_USER = "knight.halloween";
    public final static String HOST_TAG = "?kind=album&alt=json";
    public final static String HOST_ALT = "alt=json";
    public final static String HOST_ALBUMID = "/albumid/";
    public final static String PATH_FOLDER = "/Pictures/WallPaper";
    public final static String SIZE = "/s1000";
    public final static String[] tabs = {"Album","WallPaper"};

    public final static String makeWebUrl(String methodName) {
        String url = WEB_URL + methodName + WEB_TAG;
        return url;
    }
    public final static String makeHostUrl() {
        String url = HOST_URL + HOST_USER + HOST_TAG;
        return url;
    }
}
