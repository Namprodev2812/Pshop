package com.example.trnhxunnam.pshopmyclone.Contract;

import android.content.Context;

import com.example.trnhxunnam.pshopmyclone.Base.BaseView;
import com.example.trnhxunnam.pshopmyclone.Model.UserFull;

import java.util.ArrayList;

public interface LoadUserContract {

    interface LUView extends BaseView {

        void loadProcess(ArrayList<UserFull> arrayList);
        void loadFail();
        void updateUserProcess();
        void getValueLoginProcess(int value);
        void getTaiKhoanOfShareperenceProcess(String taikhoan);
    }

    interface LUPresenter{
        void loadUser(String taikhoan);
        void updateUser(String taikhoan, String name, String diachi);
        void getValueLogin(Context context);
        void setValueLogin(Context context,int fell);
        void setValueLoginFull(Context context,UserFull userFull);
        void getTaiKhoanOfShareperence(Context context);

    }

}
