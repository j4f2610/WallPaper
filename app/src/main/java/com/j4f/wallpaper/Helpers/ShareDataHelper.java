package com.j4f.wallpaper.Helpers;

import com.j4f.wallpaper.Client.ListMenuProxy;

/**
 * Created by pham on 7/13/2015.
 */
public class ShareDataHelper {
    private static ShareDataHelper instance = null;
    private ListMenuProxy listMenuProxy;

    private ShareDataHelper() {
        listMenuProxy= new ListMenuProxy();
    }

    public ListMenuProxy getListMenuProxy() {
        return listMenuProxy;
    }

    public static ShareDataHelper getInstance() {
        if (instance == null) {
            instance = new ShareDataHelper();
        }
        return instance;
    }
}
