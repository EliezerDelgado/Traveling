package com.travel_world.traveling.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;

public class Intents {
    private static Uri cameraImagenReturn;

    public static Intent openCamera(Context context) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
        cameraImagenReturn = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraImagenReturn);
        return intent;
    }
    public static Uri getCameraImagenReturn()
    {
        Uri u = cameraImagenReturn;
        cameraImagenReturn = null;
        return u;
    }
    public static Intent openPage(String page)
    {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(page));
        return intent;
    }
}
