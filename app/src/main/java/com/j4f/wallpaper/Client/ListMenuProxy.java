package com.j4f.wallpaper.Client;

import android.app.Activity;
import android.content.Context;

import com.j4f.wallpaper.Helpers.Commons.Common;
import com.j4f.wallpaper.Helpers.Commons.Constants;
import com.j4f.wallpaper.Interfaces.AsyncTaskCompleteListener;
import com.j4f.wallpaper.Model.BaseModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by pham on 7/13/2015.
 */
public class ListMenuProxy extends BaseProxy {
    private final static String METHOD_MENU_LIST = "getWallPaperMenu";
    private final static String METHOD_RESULT_TAG = "image";
    private final static String TAG_ID = "MenuID";
    private final static String TAG_NAME = "MenuTitle";
    private final static String TAG_URLICON = "MenuIcon";
    private AsyncTaskCompleteListener<ArrayList<BaseModel>> handleBaseDataCompleteListener = null;
    private ArrayList<BaseModel> baseModels;
    public void getListMenu(Activity activity, AsyncTaskCompleteListener<ArrayList<BaseModel>> handleBaseDataCompleteListener) {
        this.handleBaseDataCompleteListener = handleBaseDataCompleteListener;
        super.callApi(activity,Constants.makeWebUrl(METHOD_MENU_LIST), stringAsyncTaskCompleteListener);
    }

    AsyncTaskCompleteListener<JSONObject> stringAsyncTaskCompleteListener = new AsyncTaskCompleteListener<JSONObject>() {
        @Override
        public void onTaskCompelete(JSONObject result) {
            try {
                JSONArray jsonArray= result.getJSONArray(METHOD_RESULT_TAG);
                BaseModel baseModel;
                if(jsonArray.length()!=0){
                    baseModels= new ArrayList<>();
                    for(int i=0;i<jsonArray.length();i++) {
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        baseModel = new BaseModel(
                               jsonObject.getString(TAG_ID),
                                jsonObject.getString(TAG_NAME),
                                jsonObject.getString(TAG_URLICON));
                        baseModels.add(baseModel);
                    }
                    handleBaseDataCompleteListener.onTaskCompelete(baseModels);
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
