package com.j4f.wallpaper.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import com.j4f.wallpaper.Helpers.Commons.Constants;
import com.j4f.wallpaper.R;

import java.io.File;

/**
 * Created by pham on 7/29/2015.
 */
public class SplashActivity extends Activity{
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //createFolder();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    public void createFolder(){
        File root = Environment.getExternalStorageDirectory();
        File newFolder= new File(root.getAbsolutePath()+ Constants.PATH_FOLDER);
        newFolder.mkdir();
    }
}
