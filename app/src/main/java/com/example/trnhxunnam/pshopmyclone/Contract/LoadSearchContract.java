package com.example.trnhxunnam.pshopmyclone.Contract;

import android.content.Context;

import com.example.trnhxunnam.pshopmyclone.Base.BaseView;
import com.example.trnhxunnam.pshopmyclone.Model.Product;

import java.util.ArrayList;

public interface LoadSearchContract {
    interface LSView extends BaseView {

        void loadProcess(ArrayList<Product> arrayList);
        void loadFail();
        void getSumCartProcess(int value);
    }

    interface  LSPresenter{

        void load();
        void getSumCart();
        void addToCart(int add,Product product);
    }
}
