package com.iesvirgendelcarmen.rvargas.touchremote.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

import static com.google.gson.internal.UnsafeAllocator.create;

public final class ApiClient {
    public static final String URL = "192.168.1.15";
    public static final String PORT = "8080";
    private static RestAdapter restAdapter = null;

    public static RestAdapter getAdapter () {
        if (restAdapter == null) {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd")
                    .create();

            restAdapter = new RestAdapter.Builder()
                    .setEndpoint("http://" + URL + ":" + PORT + "/rest")
                    .setConverter(new GsonConverter(gson))
                    .build();
        }
        return restAdapter;
    }

    private ApiClient () {}
}