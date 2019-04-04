package com.example.trnhxunnam.pshopmyclone.Presenter;

import android.util.Log;

import com.example.trnhxunnam.pshopmyclone.Base.BasePresenter;
import com.example.trnhxunnam.pshopmyclone.Contract.LoginContract;
import com.example.trnhxunnam.pshopmyclone.Model.User;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.APIUntilsLogin;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter extends BasePresenter<LoginContract.LGView> implements LoginContract.LGPresenetr {

    @Override
    public void login(String mobile, String pass) {
        checkUser(mobile, pass);
    }

    private void checkUser(final String mobile, final String pass) {
        DataClient dataClient = APIUntilsLogin.getdata();
        Call<String> callback = dataClient.checkuser(mobile,pass);

        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                String result = response.body();

                if (result.equals("khongtontaitaikhoan")) {
                    connectUser(mobile,pass);
                }else if(result.equals("matkhaukhongdung")){
                    getView().loginFailPass();
                }else if(result.equals("matkhaudung")){
                    connectUser(mobile,pass);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e("Error", "" + t.getMessage());
            }
        });
    }

    private void connectUser(String taikhoan, String matkhau) {
        DataClient dataClient = APIUntilsLogin.getdata();
        Call<List<User>> listCall = dataClient.login(taikhoan,matkhau);

        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                ArrayList<User> manguser = new ArrayList<User>();
                manguser.addAll(response.body());
                getView().loginSuccess(manguser);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.e("ERRORLOADDATA", "" + t.getMessage());
                //presenter.ldfail();
                getView().loginFail();
            }
        });
    }
}
