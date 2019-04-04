package com.example.trnhxunnam.pshopmyclone.Retrofit2;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    public  static Retrofit retrofitv = null ;

    public static  Retrofit getclient(String baseurl){
        OkHttpClient builder= new OkHttpClient.Builder()
                .readTimeout(5000,TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000,TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true).build();

        Gson gson = new GsonBuilder().setLenient().create();
        retrofitv =  new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(builder)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofitv;
    }



}
