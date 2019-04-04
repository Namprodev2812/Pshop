package com.example.trnhxunnam.pshopmyclone.Retrofit2;

public class APIUntils {
    public static final String Base_url = "http://192.168.2.40:8888/Quanlisanpham/product/";
    //public static final String Base_url = "http://192.168.2.106:8888/Quanlisanpham/product/";
    public static  DataClient getdata(){

        return RetrofitClient.getclient(Base_url).create(DataClient.class);
        //return RetrofitClient.getclient(Base_url);
    }
}
