package com.example.trnhxunnam.pshopmyclone.Retrofit2;

import com.example.trnhxunnam.pshopmyclone.Model.DonHang;
import com.example.trnhxunnam.pshopmyclone.Model.DonHangfull;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Model.User;
import com.example.trnhxunnam.pshopmyclone.Model.UserFull;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface DataClient {


    @Multipart
    @POST("upload_image.php")
    Call<String> uploadphoto(@Part MultipartBody.Part photo);

    @FormUrlEncoded
    @POST("insertgiohang.php")
    Call<String> InsertGiohang(@Field("image")  String image,
                            @Field("name")   String name,
                            @Field("price")  Double price,
                            @Field("sum")    String sum
                            );

    @FormUrlEncoded
    @POST("insertthanhtoansnack.php")
    Call<String> InsertDonHang(@Field("madonhang")     String madonhang,
                               @Field("user")          String image,
                               @Field("mobile")        String moile,
                               @Field("index")         Integer index,
                               @Field("sumprice")      Double sumprice,
                               @Field("time")          String time
    );
    @FormUrlEncoded
    @POST("insertthanhtoan.php")
    Call<String> InsertDonhangfull(@Field("madonhang")  String madonhang,
                                   @Field("image")      String image,
                                    @Field("name")      String name,
                                    @Field("price")     Double price,
                                   @Field("indexsum")   Integer indexsum,
                                    @Field("sumprice")  Double sumprice
            );

    @FormUrlEncoded
    @POST("updateuser.php")
    Call<String> updateuser(        @Field("taikhoan")  String taikhoan,
                                    @Field("name")   String name,
                                    @Field("diachi")    String diachi
    );

    @FormUrlEncoded
    @POST("updategiohang.php")
    Call<String> UpdateGiohang(@Field("sum") String sum,@Field("name") String name);

    @GET("deleteallgiohang.php")
    Call<String> deleteallgh(@Query("id") String id, @Query("hinhanh") String hinhanh);

    @GET("deleteoneingiohang.php")
    Call<String> deleteoneingh(@Query("id") String id, @Query("hinhanh") String hinhanh);

    @FormUrlEncoded
    @POST("loginprofile.php")
    Call<List<User>> login(@Field("taikhoan") String taikhoan, @Field("matkhau") String matkhau);

    @FormUrlEncoded
    @POST("checkuser.php")
    Call<String> checkuser(@Field("taikhoan") String taikhoan, @Field("matkhau") String matkhau);

    @FormUrlEncoded
    @POST("loaduser.php")
    Call<List<UserFull>> loadFullUser(@Field("taikhoan") String taikhoan);

    @FormUrlEncoded
    @POST("loadproduct.php")
    Call<List<Product>> loadp(@Field("kind") String kind);

    @FormUrlEncoded
    @POST("loadsearch.php")
    Call<List<Product>> loadSearch(@Field("kind") String kind);



    @FormUrlEncoded
    @POST("loadthanhtoanmonth.php")
    Call<List<DonHang>> loaddonhangmonth(@Field("timefirst") String time,@Field("timesecond") String second);

    @FormUrlEncoded
    @POST("loadthanhtoanallfullinfor.php")
    Call<List<DonHangfull>> loaddonhangfullinfor(@Field("madonhang") String madonhang);

    @FormUrlEncoded
    @POST("loadthanhtoanbeforeweek.php")
    Call<List<DonHang>> loaddonhangbeforeweek(@Field("timefirst") String time,@Field("timesecond") String second);

    @FormUrlEncoded
    @POST("loadthanhtoanweek.php")
    Call<List<DonHang>> loaddonhangweek(@Field("timefirst") String time,@Field("timesecond") String second);

    @FormUrlEncoded
    @POST("loadthanhtoanall.php")
    Call<List<DonHang>> loaddonhangall(@Field("time") String time);

    @FormUrlEncoded
    @POST("loadcalluser.php")
    Call<List<Product>> loadcl(@Field("taikhoan") String taikhoan);

    @GET("delete.php")
    Call<String> deletedata(@Query("id") String id, @Query("hinhanh") String hinhanh);
}
