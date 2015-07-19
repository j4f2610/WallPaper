package com.j4f.wallpaper.Helpers;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Window;
import android.widget.Toast;

import com.j4f.wallpaper.R;
import com.j4f.wallpaper.Shimmer.Shimmer;
import com.j4f.wallpaper.Shimmer.ShimmerTextView;

/**
 * Created by pham on 7/15/2015.
 */
public class Common {
    public static Dialog dialogFragment;

    /**
     * show loading
     */
    public static void showDialog(Context context) {
        dialogFragment = new Dialog(context);
        dialogFragment.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogFragment.setContentView(R.layout.layout_dialog_loading);
        ShimmerTextView textView = (ShimmerTextView) dialogFragment.findViewById(R.id.te);
        new Shimmer().start(textView);
        dialogFragment.setCancelable(false);
        dialogFragment.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogFragment.show();
    }

    /**
     * dismiss loading
     */
    public static void dismissDialog(Context context) {
        dialogFragment.dismiss();
    }

    /**
     * check device internet connection
     */
    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED
                            || info[i].getState() == NetworkInfo.State.CONNECTING) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * check device internet connection
     */

    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
