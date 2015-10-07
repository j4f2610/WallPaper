package com.j4f.wallpaper.Client;

import android.app.Activity;

import com.j4f.wallpaper.Helpers.Commons.Constants;
import com.j4f.wallpaper.Interfaces.AsyncTaskCompleteListener;
import com.j4f.wallpaper.Model.Album;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pham on 7/26/2015.
 */
public class ListAlbumProxy extends BaseProxy {
    private final static String METHOD_WALLPAPER_LIST = "getWallPaperName";
    private final static String METHOD_RESULT_TAG = "image";
    private ArrayList<Album> wallPapers;
    private AsyncTaskCompleteListener<ArrayList<Album>> handleBaseDataCompleteListener = null;
    private final static String TAG_ID = "Id";
    private final static String TAG_NAME = "Title";
    private final static String TAG_URLICON = "IconUrl";
    private final static String TAG_TYPE = "Code";
    public ListAlbumProxy(){
        wallPapers = new ArrayList<>();
    }
    public ArrayList<Album> getWallPapers(){
        return wallPapers;
    }
    public void getListWallPaper(Activity activity, AsyncTaskCompleteListener<ArrayList<Album>> handleBaseDataCompleteListener) {
        this.handleBaseDataCompleteListener = handleBaseDataCompleteListener;
        super.callApi(activity, Constants.makeWebUrl(METHOD_WALLPAPER_LIST), stringAsyncTaskCompleteListener);
    }

    AsyncTaskCompleteListener<JSONObject> stringAsyncTaskCompleteListener = new AsyncTaskCompleteListener<JSONObject>() {
        @Override
        public void onTaskCompelete(JSONObject result) {
            try {
                JSONArray jsonArray = result.getJSONArray(METHOD_RESULT_TAG);
                Album wallPaper;
                if (jsonArray.length() != 0) {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        wallPaper = new Album(
                                jsonObject.getString(TAG_ID),
                                jsonObject.getString(TAG_NAME),
                                jsonObject.getString(TAG_URLICON),
                                jsonObject.getString(TAG_TYPE));
                        wallPapers.add(wallPaper);
                    }
                    handleBaseDataCompleteListener.onTaskCompelete(wallPapers);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onError(int ErrorCode) {
           handleBaseDataCompleteListener.onError(ErrorCode);
        }

    };
}
