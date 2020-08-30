package com.appdevgenie.practiceproject.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static Retrofit retrofit = null;
    private static String GET_BASE_URL = "https://gadsapi.herokuapp.com/";
    private static String POST_BASE_URL = " https://docs.google.com/forms/d/e/";

    public static LearnerDataInterface getServiceInterface(){

        //if(retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(GET_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

       //}
        return retrofit.create(LearnerDataInterface.class);
    }

    public static LearnerDataInterface postServiceInterface(){
        Gson gson = new GsonBuilder().setLenient().create();
        //if(retrofit == null){
            retrofit = new Retrofit
                    .Builder()
                    .baseUrl(POST_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

       // }
        return retrofit.create(LearnerDataInterface.class);
    }
}
