package com.j4f.wallpaper.Helpers.Commons;

import com.j4f.wallpaper.Client.ListWallPaperProxy;
import com.j4f.wallpaper.Client.ListMenuProxy;
import com.j4f.wallpaper.Client.ListAlbumProxy;

/**
 * Created by pham on 7/13/2015.
 */
public class ShareDataHelper {
    private static ShareDataHelper instance = null;
    private ListMenuProxy listMenuProxy;
    private ListAlbumProxy listWallPaperProxy;

    private ListWallPaperProxy listItemWallPaperProxy;

    private ShareDataHelper() {
        listMenuProxy = new ListMenuProxy();
        listWallPaperProxy = new ListAlbumProxy();
        listItemWallPaperProxy = new ListWallPaperProxy();
    }

    public ListMenuProxy getListMenuProxy() {
        return listMenuProxy;
    }

    public ListAlbumProxy getListWallPaperProxy() {
        return listWallPaperProxy;
    }

    public ListWallPaperProxy getListItemWallPaperProxy() {
        return listItemWallPaperProxy;
    }

    public static ShareDataHelper getInstance() {
        if (instance == null) {
            instance = new ShareDataHelper();
        }
        return instance;
    }
}
