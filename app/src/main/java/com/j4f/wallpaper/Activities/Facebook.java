package com.j4f.wallpaper.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.j4f.wallpaper.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by pham on 21/8/2015.
 */
public class Facebook extends Activity {
    private LoginButton button;
    private TextView textView;
    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.facebook);
        button = (LoginButton) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        button.setReadPermissions("user_friends");
        LoginManager.getInstance().logOut();
        button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                AccessToken accessToken = loginResult.getAccessToken();
                Profile profile = Profile.getCurrentProfile();
                if (profile != null) {
                    textView.setText("WellCome: " + profile.getName());
                }
            }

            @Override
            public void onCancel() {
                textView.setText("Login attempt canceled.");
            }

            @Override
            public void onError(FacebookException e) {
                textView.setText("Login attempt failed.");
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    //=============================== get Key Hash ===============================
    private void printKeyHash() {
        try {
            PackageInfo info = getPackageManager().getPackageInfo("com.j4f.wallpaper", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("KeyHash:", e.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("KeyHash:", e.toString());
        }
    }
}
