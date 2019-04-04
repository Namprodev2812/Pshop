package com.example.trnhxunnam.pshopmyclone.Contract;

import android.content.Context;

import com.example.trnhxunnam.pshopmyclone.Base.BaseView;
import com.example.trnhxunnam.pshopmyclone.Model.Product;

import java.util.ArrayList;

public interface LoadProductContract {

    interface LPView extends BaseView {

        void loadProcess(ArrayList<Product> arrayList);
        void loadFail();
        void getSumCartProcess(int value);
    }

    interface  LPPresenter{

        void load(String kind);
        void addToCart(int add,Product product);
        void getSumCart();
    }
}
