package com.travel_world.traveling.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.appcompat.app.AppCompatActivity;

public class Intents {
    private static Uri cameraImagenReturn;

    public static Uri getCameraImagenReturn()
    {
        Uri u = cameraImagenReturn;
        cameraImagenReturn = null;
        return u;
    }

    public static Intent openCamera(Context context) {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From Camera");
        cameraImagenReturn = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, cameraImagenReturn);
        return intent;
    }
    public static Intent openPage(String page)
    {
        return new Intent(Intent.ACTION_VIEW, Uri.parse(page));
    }

    public static <T> Intent intentActivity(Context context, Class<T> activity)
    {
        return new Intent(context,activity);
    }

    public static <T> Intent intentActivityWithExtras(Context context,  Class<T> activity, Bundle bundle)
    {
        return new Intent(context,activity.getClass()).putExtras(bundle);
    }
}
