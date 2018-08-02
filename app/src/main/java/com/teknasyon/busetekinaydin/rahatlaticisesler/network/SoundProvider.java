package com.teknasyon.busetekinaydin.rahatlaticisesler.network;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.teknasyon.busetekinaydin.rahatlaticisesler.core.Util;

import java.util.Properties;

import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public final class SoundProvider {

    private static final String URL_KEY = "http://iyilikgazetesi.com/teknasyon/";

    private static Retrofit retrofit;

    private SoundProvider() {

    }

    public static Retrofit getClient(Context context) {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(URL_KEY)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
