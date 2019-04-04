package com.example.trnhxunnam.pshopmyclone.Presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.trnhxunnam.pshopmyclone.Base.BasePresenter;
import com.example.trnhxunnam.pshopmyclone.Contract.LoadUserContract;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Model.UserFull;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.APIUntils;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.APIUntilsLogin;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class LoadUserPresenter extends BasePresenter<LoadUserContract.LUView> implements LoadUserContract.LUPresenter {

    final static  String LOGIN = "login";
    final static  String ITEM_LOGIN_FELL = "feel";
    final static  String ITEM_LOGIN_TAIKHOAN = "logintaikhoan";
    final static  String ITEM_LOGIN_EMAIL = "email";
    final static  String ITEM_LOGIN_URLIMAGE = "loginurl";
    final static  String ITEM_LOGIN_NAME = "name";
    final static  String ITEM_LOGIN_ADDRESS = "diachi";


    @Override
    public void loadUser(String taikhoan) {
        loadFullUser(taikhoan);
    }

    @Override
    public void updateUser(String taikhoan, String name, String diachi) {
        updateUserNow(taikhoan, name, diachi);
    }

    @Override
    public void getValueLogin(Context context) {

        getValueLoginNow(context);
    }

    @Override
    public void setValueLogin(Context context,int fell) {
        setValueLoginNow(context,fell);
    }

    @Override
    public void setValueLoginFull(Context context, UserFull userFull) {
        setValueLoginFullNow(context,userFull);
    }

    @Override
    public void getTaiKhoanOfShareperence(Context context) {
        getTaiKhoanOfShareperenceNow(context);
    }

    private void loadFullUser(String taikhoan) {

        DataClient dataClient = APIUntilsLogin.getdata();
        Call<List<UserFull>> listCall = dataClient.loadFullUser(taikhoan);

        listCall.enqueue(new Callback<List<UserFull>>() {
            @Override
            public void onResponse(Call<List<UserFull>> call, Response<List<UserFull>> response) {

                ArrayList<UserFull> fulluser = new ArrayList<UserFull>();
                fulluser.addAll(response.body());
                Log.e("onResLoaddata", "" + response.body().size());
                getView().loadProcess(fulluser);
            }

            @Override
            public void onFailure(Call<List<UserFull>> call, Throwable t) {
                Log.e("ERRORLOADDATA", "" + t.getMessage());
                //presenter.ldfail();
                getView().loadFail();
            }
        });
    }
    private void updateUserNow(String taikhoan, String name, String diachi) {
        Log.e("senddata", "" + taikhoan + "---" + name + "---" + diachi);
        DataClient dataClient1 = APIUntilsLogin.getdata();
        retrofit2.Call<String> callback1 = ((DataClient) dataClient1).updateuser(taikhoan, name, diachi);
        callback1.enqueue(new Callback<String>() {
            @Override
            public void onResponse(retrofit2.Call<String> call, Response<String> response) {
                String result = response.body();
                Log.e("resultdonhang", "" + result);
                if (result.equals("Success")) {
                    getView().updateUserProcess();
                } else if (result.equals("Fail")) {
                    getView().loadFail();
                } else {
                    getView().loadFail();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<String> call, Throwable t) {
                Log.e("loiroi", "" + t.getMessage());
                getView().loadFail();
            }
        });
    }
    private void getValueLoginNow(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN, MODE_PRIVATE);
        getView().getValueLoginProcess(sharedPreferences.getInt(ITEM_LOGIN_FELL, 0));
    }
    private void setValueLoginNow(Context context,int feel) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(ITEM_LOGIN_FELL, feel);
        editor.apply();
    }
    private void setValueLoginFullNow(Context context,UserFull userfull) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ITEM_LOGIN_TAIKHOAN,userfull.getTaikhoan());
        editor.putString(ITEM_LOGIN_URLIMAGE,userfull.getHinhanh());
        editor.putString(ITEM_LOGIN_EMAIL,userfull.getEmail());
        editor.putString(ITEM_LOGIN_NAME,userfull.getName());
        editor.putString(ITEM_LOGIN_ADDRESS,userfull.getDiachi());
        editor.apply();
    }
    private String getTaiKhoanOfShareperenceNow(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ITEM_LOGIN_TAIKHOAN, "");
    }
}
