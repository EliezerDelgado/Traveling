package com.travel_world.traveling.utils;

import android.graphics.drawable.Drawable;

import com.travel_world.traveling.io.MyExecuterService;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class UtilsPictures {
    public Drawable loadImageFromWebOperations(String url) {
        Drawable srcName;
        try {
            srcName = MyExecuterService.executorService.submit(() -> {
                try {
                    InputStream is = (InputStream) new URL(url).getContent();
                    Drawable d = Drawable.createFromStream(is, "src name");
                    return d;
                } catch (IOException ignored) {
                    return null;
                }
            }).get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return srcName;
    }
}
