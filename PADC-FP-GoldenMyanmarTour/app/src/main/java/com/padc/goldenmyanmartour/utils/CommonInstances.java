package com.padc.goldenmyanmartour.utils;

import com.google.gson.Gson;

/**
 * Created by WT on 9/22/2016.
 */
public class CommonInstances {
    private static Gson gson = new Gson();

    public static Gson getInstance() {
        return gson;
    }
}
