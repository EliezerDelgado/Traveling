package com.travel_world.traveling.io;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecuterService {
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService executorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
}
