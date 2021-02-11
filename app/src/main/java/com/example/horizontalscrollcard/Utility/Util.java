package com.example.horizontalscrollcard.Utility;

import android.app.Activity;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class Util {
    public static void showSnackBar(Activity activity, String message) {
        View rootView = activity.getWindow().getDecorView().findViewById(android.R.id.content);
        Snackbar.make(rootView, message, Snackbar.LENGTH_SHORT).show();
    }
}
