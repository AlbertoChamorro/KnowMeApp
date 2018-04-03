package com.knowme.knowme.api;

import retrofit2.Retrofit;

/**
 * Created by coredeveloper on 4/3/18.
 */

public class RetrofitManager {

    public static RetrofitManager sharedInstance = new RetrofitManager();
    public Retrofit retrofit;

    public RetrofitManager(){
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .build();
    }
}
