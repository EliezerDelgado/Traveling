package com.travel_world.traveling.utils;

import android.app.Activity;
import android.app.AlertDialog;

public class AlertDialogs {
    public static AlertDialog createSimpleInformativeDialog(Activity activity, String title, String confirmButtonText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title)
                .setPositiveButton(confirmButtonText, (dialog, which) -> {
                    dialog.dismiss();
                });
        return builder.create();
    }
}
