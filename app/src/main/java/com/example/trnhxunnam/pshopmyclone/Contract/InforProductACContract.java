package com.example.trnhxunnam.pshopmyclone.Contract;

import com.example.trnhxunnam.pshopmyclone.Base.BaseView;
import com.example.trnhxunnam.pshopmyclone.Model.Product;

import java.util.ArrayList;

public interface InforProductACContract {
    interface LSView extends BaseView {

    }

    interface  LSPresenter{
        void addToCart(int add, Product product);
    }
}
