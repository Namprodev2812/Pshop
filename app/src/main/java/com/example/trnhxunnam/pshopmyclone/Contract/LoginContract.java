package com.example.trnhxunnam.pshopmyclone.Contract;

import com.example.trnhxunnam.pshopmyclone.Base.BaseView;
import com.example.trnhxunnam.pshopmyclone.Model.User;

import java.util.ArrayList;

public interface LoginContract {

    interface LGView extends BaseView {
        void loginSuccess(ArrayList<User> arrayList);
        void loginFailPass();
        void loginFail();
    }

    interface LGPresenetr{
        void login(String mobile,String pass);
    }
}
