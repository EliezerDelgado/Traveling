package com.travel_world.traveling.utils;

import static android.content.DialogInterface.BUTTON_POSITIVE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class AlertDialogs {
    public static AlertDialog createSimpleInformativeDialog(Activity activity, String title, String confirmButtonText) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title)
                .setPositiveButton(confirmButtonText, (dialog, which) -> {
                    dialog.dismiss();
                })
                .setCancelable(false);
        return builder.create();
    }
    public static AlertDialog createSimpleInformativeDialogWithOnCLickListener(Activity activity, String title, String confirmButtonText,final DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title)
                .setPositiveButton(confirmButtonText, (dialog, which) -> {
                    dialog.dismiss();
                    listener.onClick(dialog,which);
                })
                .setCancelable(false);
        return builder.create();
    }
}
