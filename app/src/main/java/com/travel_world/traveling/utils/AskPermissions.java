package com.travel_world.traveling.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.travel_world.traveling.data.constants.MyPermission;

public class AskPermissions {
    public static void askPermissionCamera(Activity activity)
    {
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.CAMERA}, MyPermission.CAMERA_PERMISSION_CODE);

        }
    }

    public static boolean isPermissionCameraOn(Context context)
    {
        return ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }
}
