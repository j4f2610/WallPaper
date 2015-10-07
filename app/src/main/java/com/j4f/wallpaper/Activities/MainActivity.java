package com.j4f.wallpaper.Activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.FacebookSdk;
import com.j4f.wallpaper.Fragments.FragmentListAlbum;
import com.j4f.wallpaper.Fragments.FragmentMain;
import com.j4f.wallpaper.Fragments.FragmentWallPaper;
import com.j4f.wallpaper.Helpers.Commons.ShareDataHelper;
import com.j4f.wallpaper.Model.Album;
import com.j4f.wallpaper.R;


public class MainActivity extends ActionBarActivity implements FragmentListAlbum.FragmentListWallPaperListener {
    public Toolbar toolbar;
    public DrawerLayout Drawer;
    private ActionBarDrawerToggle mDrawerToggle;
    public static boolean enableBackNavigation = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(this, Drawer, toolbar, R.string.app_name, R.string.app_name) {
            @Override
            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                invalidateOptionsMenu();
            }
        };
        Drawer.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        FragmentMain fragmentMain = new FragmentMain();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frm_main, fragmentMain).addToBackStack(null).commit();
        getSupportFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                enableBackNavigation = !enableBackNavigation;
                if (enableBackNavigation) {
                    toolbar.setNavigationIcon(R.mipmap.ic_arrow_back);
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            onBackPressed();
                            toolbar.setTitle(R.string.app_name);
                        }
                    });
                } else {
                    toolbar.setNavigationIcon(R.mipmap.ic_menu);
                    mDrawerToggle.syncState();
                    toolbar.setNavigationOnClickListener(new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            if (Drawer
                                    .isDrawerOpen(GravityCompat.START))
                                Drawer
                                        .closeDrawer(GravityCompat.START);
                            else
                                Drawer
                                        .openDrawer(GravityCompat.START);
                        }
                    });
                }
            }
        });
    }

    public void replaceFragment(Fragment fragment, String name) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frm_main, fragment).addToBackStack(name).commit();
    }
    public void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frm_main, fragment).addToBackStack(null).commit();
    }
    @Override
    public void onSelected(int position) {
        Album code=ShareDataHelper.getInstance().getListWallPaperProxy().getWallPapers().get(position);
        replaceFragment(FragmentWallPaper.newInstance(code),code.getName());
        toolbar.setTitle(code.getName());
    }
}


