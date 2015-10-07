package com.j4f.wallpaper.Fragments;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.j4f.wallpaper.Activities.MainActivity;
import com.j4f.wallpaper.Adapters.MenuAdapter;
import com.j4f.wallpaper.Helpers.Commons.Common;
import com.j4f.wallpaper.Helpers.Commons.ShareDataHelper;
import com.j4f.wallpaper.Interfaces.AsyncTaskCompleteListener;
import com.j4f.wallpaper.Model.BaseModel;
import com.j4f.wallpaper.R;
import com.j4f.wallpaper.Shimmer.Shimmer;
import com.j4f.wallpaper.Shimmer.ShimmerTextView;

import java.util.ArrayList;

/**
 * Created by pham on 7/28/2015.
 */
public class FragmentMenuLeft extends Fragment {
    private View view;
    private Button btnRefresh;
    private ListView lstMenu;
    private ShimmerTextView txtLoading;
    private Activity mContext;
    private View includeLayout;
    private LinearLayout layoutUser;
    private Button loginButton;
    private static CallbackManager callbackManager;
    private static TextView txtUserName;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();
        view = inflater.inflate(R.layout.fragment_menu_left, container, false);
        includeLayout = view.findViewById(R.id.include_layout);
        layoutUser = (LinearLayout) includeLayout.findViewById(R.id.layout_user);
        loginButton = (Button) includeLayout.findViewById(R.id.btnLogin);
        txtUserName = (TextView) includeLayout.findViewById(R.id.txtUserName);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogLogin(getActivity());
            }
        });
       /* loginButton.setVisibility(View.GONE);
        layoutUser.setVisibility(View.VISIBLE);*/

        btnRefresh = (Button) view.findViewById(R.id.btnRefreshMenu);
        lstMenu = (ListView) view.findViewById(R.id.lstMenu);
        loadData();
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();
            }
        });
        lstMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        FragmentMain fragmentMain = (FragmentMain) getActivity().getSupportFragmentManager().findFragmentById(R.id.frm_main);
                        fragmentMain.viewPager.setCurrentItem(1);
                        break;
                    case 1:
                        fragmentMain = (FragmentMain) getActivity().getSupportFragmentManager().findFragmentById(R.id.frm_main);
                        fragmentMain.viewPager.setCurrentItem(1);
                        break;
                    case 2:
                        fragmentMain = (FragmentMain) getActivity().getSupportFragmentManager().findFragmentById(R.id.frm_main);
                        fragmentMain.viewPager.setCurrentItem(1);
                        break;
                    case 3:

                        ((MainActivity) getActivity()).addFragment(FragmentAboutUs.newInstance());
                        break;

                    case 4:
                        Intent i = new Intent(Intent.ACTION_SEND);
                        i.setType("imag e/jpeg");
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getActivity().startActivity(Intent.createChooser(i, getResources().getString(R.string.ShareVia)));

                        break;
                }
                ((MainActivity) getActivity()).Drawer.closeDrawers();
            }
        });
        return view;
    }

    private void loadData() {
        if (Common.isNetworkAvailable(getActivity())) {

            txtLoading = (ShimmerTextView) view.findViewById(R.id.txtLoading);
            new Shimmer().start(txtLoading);
            txtLoading.setVisibility(View.VISIBLE);
            btnRefresh.setVisibility(View.GONE);
            ShareDataHelper.getInstance().getListMenuProxy().getListMenu(getActivity(), new AsyncTaskCompleteListener<ArrayList<BaseModel>>() {
                @Override
                public void onTaskCompelete(ArrayList<BaseModel> result) {
                    MenuAdapter adapter = new MenuAdapter(getActivity(), 0, result);
                    lstMenu.setAdapter(adapter);
                    txtLoading.setVisibility(View.GONE);
                    lstMenu.setVisibility(View.VISIBLE);
                }

                @Override
                public void onError(int ErrorCode) {
                    btnRefresh.setVisibility(View.VISIBLE);
                    txtLoading.setVisibility(View.GONE);
                    Common.showToast(getActivity(), getResources().getString(R.string.NoData));
                }
            });
        } else {
            Common.showToast(getActivity(), getResources().getString(R.string.NoNetwork));
            btnRefresh.setVisibility(View.VISIBLE);
        }
    }

    public static void showDialogLogin(Context context) {
        Dialog dialogFragment;
        dialogFragment = new Dialog(context);
        dialogFragment.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogFragment.setContentView(R.layout.fragment_login);
        LoginButton fbLogin = (LoginButton) dialogFragment.findViewById(R.id.btnLogin);
        fbLogin.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Profile profile = Profile.getCurrentProfile();
                if (profile != null) {
                    txtUserName.setText("WellCome: " + profile.getName());
                }
                txtUserName.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancel() {
                txtUserName.setText("Cancel");
                txtUserName.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(FacebookException e) {
                txtUserName.setText("Error");
                txtUserName.setVisibility(View.VISIBLE);
            }
        });
        dialogFragment.show();
    }
}
