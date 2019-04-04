package com.example.trnhxunnam.pshopmyclone.Contract;

import android.content.Context;

import com.example.trnhxunnam.pshopmyclone.Base.BaseView;

public interface MyProductContract {
    interface LSView extends BaseView {
        void getSumCartProcess(int value);
    }

    interface  LSPresenter{
        void getSumCart();
        void clearAllCart();
    }
}
