package com.j4f.wallpaper.Activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.j4f.wallpaper.Adapters.MenuAdapter;
import com.j4f.wallpaper.Helpers.Common;
import com.j4f.wallpaper.Helpers.ShareDataHelper;
import com.j4f.wallpaper.Interfaces.AsyncTaskCompleteListener;
import com.j4f.wallpaper.Model.BaseModel;
import com.j4f.wallpaper.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    private Toolbar toolbar;
    private DrawerLayout Drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView lstMenu1;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstMenu1 = (ListView) findViewById(R.id.lstMenu);

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);


        Drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

        };
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        bt = (Button) findViewById(R.id.bt);
        if (Common.isNetworkAvailable(getApplicationContext())) {
            Common.showDialog(MainActivity.this);
            ShareDataHelper.getInstance().getListMenuProxy().getListMenu(getApplicationContext(), new AsyncTaskCompleteListener<ArrayList<BaseModel>>() {
                @Override
                public void onTaskCompelete(ArrayList<BaseModel> result) {
                    MenuAdapter adapter = new MenuAdapter(getApplicationContext(), 0, result);
                    lstMenu1.setAdapter(adapter);
                    Common.dismissDialog(getApplicationContext());
                }
                @Override
                public void onError(int ErrorCode) {

                }
            });
        } else
            Common.showToast(getApplicationContext(), getString(R.string.NoNetwork));
    }

}


