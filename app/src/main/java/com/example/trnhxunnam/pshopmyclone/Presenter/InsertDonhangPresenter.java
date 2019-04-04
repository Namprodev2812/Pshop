package com.example.trnhxunnam.pshopmyclone.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.trnhxunnam.pshopmyclone.Base.BasePresenter;
import com.example.trnhxunnam.pshopmyclone.Contract.InsertDonhang;
import com.example.trnhxunnam.pshopmyclone.Model.Cart;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Model.UserFull;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.APIUntilsDonHang;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.APIUntilsLogin;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertDonhangPresenter extends BasePresenter<InsertDonhang.InsDHView> implements InsertDonhang.InsDHPresenter {


    final static  String LOGIN = "login";
    final static  String ITEM_LOGIN_TAIKHOAN = "logintaikhoan";
    @Override
    public void insertDonHang(String maDonHang,String user, String name, int index, double sumPrice,String time) {
        insertDonHangNow(maDonHang,user, name, index, sumPrice,time);
    }

    @Override
    public void insertDonHangFull(String maDonHang, String image, String name, double price, int indexSum, double sumPrice) {
        insertDonHangNowFull(maDonHang,image,name,price,indexSum,sumPrice);
    }

    @Override
    public void loadFullUser(String taikhoan) {
        loadFullUserNow(taikhoan);
    }

    @Override
    public void getSumCart() {
        getView().getSumCartProcess(getSumCartNow());
    }

    @Override
    public void getSumPriceCart() {
        getView().getSumPriceCartProcess(getSumPriceCartNow());
    }

    @Override
    public void getTaiKhoanOfShareperence(Context context) {
        getView().getTaiKhoanOfShareperenceProcess(getTaiKhoanOfShareperenceNow(context));
    }

    @Override
    public void getArrayCart() {
            getView().getArrayCartProcess(getArrayCartNow());
    }

    @Override
    public void getValueOfProduct(Product product) {
        getView().getValueOfProductProcess(getValueOfProductNow(product));
    }

    @Override
    public void clearAllCart() {
        clearAllCartNow();
    }

    private void insertDonHangNow(String maDonHang,String user, String name , int index, double sumPrice,String time) {
        DataClient dataClient1 = APIUntilsDonHang.getdata();
        retrofit2.Call<String> callback1 = ((DataClient) dataClient1).InsertDonHang(maDonHang,user, name,index, sumPrice,time);
        callback1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                String result = response.body();
                Log.e("resultdonhang",""+result);
                if (result.equals("Success")) {
                    getView().insertProcessNow();
                } else if (result.equals("Fail")) {
                    getView().insertAndLoadFail();
                } else {
                    getView().insertAndLoadFail();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<String> call, Throwable t) {
                getView().insertAndLoadFail();
            }
        });
    }
    private void insertDonHangNowFull(String maDonHang,String image, String name ,double price,int indexSum,double sumPrice){
        DataClient dataClient1 = APIUntilsDonHang.getdata();
        retrofit2.Call<String> callback1 = ((DataClient) dataClient1).InsertDonhangfull(maDonHang,image,name,price,indexSum,sumPrice);
        callback1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                String result = response.body();
                Log.e("resultdonhang",""+result);
                if (result.equals("Success")) {

                } else if (result.equals("Fail")) {

                } else {

                }
            }

            @Override
            public void onFailure(retrofit2.Call<String> call, Throwable t) {

            }
        });
    }
    private void loadFullUserNow(String taikhoan) {

        DataClient dataClient = APIUntilsLogin.getdata();
        Call<List<UserFull>> listCall = dataClient.loadFullUser(taikhoan);

        listCall.enqueue(new Callback<List<UserFull>>()
        {
            @Override
            public void onResponse(Call<List<UserFull>> call, Response<List<UserFull>> response) {
                ArrayList<UserFull> fulluser = new ArrayList<UserFull>();
                fulluser.addAll(response.body());
                Log.e("onResLoaddata", "" + response.body().size());
                getView().loadDaTaUserProcessNow(fulluser);
            }

            @Override
            public void onFailure(Call<List<UserFull>> call, Throwable t) {
                Log.e("ERRORLOADDATA", "" + t.getMessage());
                getView().insertAndLoadFail();
            }
        });
    }
    private int getSumCartNow(){
        int sum =0;
        for(Product product: Cart.getInstance().getCart().keySet()){

            int value = Cart.getInstance().getCart().get(product);
            sum =  sum+value;
        }
        return sum;
    }
    private double getSumPriceCartNow() {
        double sum = 0;
        for (Product product : Cart.getInstance().getCart().keySet()) {

            double price = product.getPrice();
            int value = Cart.getInstance().getCart().get(product);
            double sum1 = price * value;
            sum = sum1 + sum;
        }
        return sum;
    }
    private String getTaiKhoanOfShareperenceNow(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ITEM_LOGIN_TAIKHOAN, "");
    }
    private ArrayList<Product> getArrayCartNow() {
        ArrayList<Product> arrayList = new ArrayList<>();
        for (Product product : Cart.getInstance().getCart().keySet()) {
            String name = product.getName();
            arrayList.add(product);
        }
        return arrayList;
    }
    private int getValueOfProductNow(Product product) {
        return Cart.getInstance().getCart().get(product);
    }
    private void clearAllCartNow(){
        Cart.getInstance().getCart().clear();
    }
}
