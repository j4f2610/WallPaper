package com.j4f.wallpaper.Client;

import android.app.Activity;

import com.j4f.wallpaper.Helpers.Commons.Common;
import com.j4f.wallpaper.Helpers.Commons.Constants;
import com.j4f.wallpaper.Interfaces.AsyncTaskCompleteListener;
import com.j4f.wallpaper.Model.WallPaper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pham on 7/30/2015.
 */
public class ListWallPaperProxy extends BaseProxy {
    private AsyncTaskCompleteListener<ArrayList<WallPaper>> handleBaseDataCompleteListener = null;
    private ArrayList<WallPaper> wallPapers;
    private static final String TAG_FEED = "feed",
                                TAG_ENTRY = "entry",
                                TAG_MEDIA_GROUP = "media$group",
                                TAG_MEDIA_CONTENT = "media$content",
                                TAG_IMG_URL = "url",
                                TAG_MEDIA_THUMB="media$thumbnail",
                                TAG_WIDTH="width",
                                TAG_HEIGHT="width";
    public void getItemWallPaper(Activity activity, String code, AsyncTaskCompleteListener<ArrayList<WallPaper>> handleBaseDataCompleteListener) {
        this.handleBaseDataCompleteListener = handleBaseDataCompleteListener;
        String url = Constants.HOST_URL + Constants.HOST_USER + Constants.HOST_ALBUMID + code + "?" + Constants.HOST_ALT;
        super.callApi(activity, url, stringAsyncTaskCompleteListener);
    }

    AsyncTaskCompleteListener<JSONObject> stringAsyncTaskCompleteListener = new AsyncTaskCompleteListener<JSONObject>() {
        @Override
        public void onTaskCompelete(JSONObject result) {
            wallPapers=new ArrayList<>();
            try {
                JSONArray entry= result.getJSONObject(TAG_FEED).getJSONArray(TAG_ENTRY);
                for(int i=0;i<entry.length();i++){
                    String urlImage=null,urlImageMini = null;
                    JSONObject photoObj = (JSONObject) entry.get(i);
                    JSONArray mediacontentArry = photoObj.getJSONObject(TAG_MEDIA_GROUP).getJSONArray(TAG_MEDIA_CONTENT);
                    if(mediacontentArry.length()>0){
                        JSONObject mediaObj = (JSONObject) mediacontentArry
                                .get(0);
                        urlImage=  Common.insertSize(mediaObj.getString(TAG_IMG_URL));
                    }
                    JSONArray mediaThumbnailArry= photoObj.getJSONObject(TAG_MEDIA_GROUP).getJSONArray(TAG_MEDIA_THUMB);
                    if(mediaThumbnailArry.length()>0){
                        JSONObject mediaThumbObj = (JSONObject) mediaThumbnailArry
                                .get(2);
                        urlImageMini=mediaThumbObj.getString(TAG_IMG_URL);
                    }
                    WallPaper wallPaper= new WallPaper(urlImage,urlImageMini);
                    wallPapers.add(wallPaper);
                }
                handleBaseDataCompleteListener.onTaskCompelete(wallPapers);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(int ErrorCode) {

        }

    };
}
