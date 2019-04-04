package com.example.trnhxunnam.pshopmyclone.Presenter;

import com.example.trnhxunnam.pshopmyclone.Base.BasePresenter;
import com.example.trnhxunnam.pshopmyclone.Contract.InforProductACContract;
import com.example.trnhxunnam.pshopmyclone.Model.Cart;
import com.example.trnhxunnam.pshopmyclone.Model.Product;

import java.util.ArrayList;

public class InforProductACPresenter extends BasePresenter<InforProductACContract.LSView> implements InforProductACContract.LSPresenter {


    private void addToCartNow( int add, Product product) {
        int oldvalue = 0;
        if(Cart.getInstance().getCart().get(product) == null){
            Cart.getInstance().addProduct(product,0);
        }else {
            oldvalue = Cart.getInstance().getCart().get(product);
        }
        Cart.getInstance().addProduct(product,add+oldvalue);

    }

    @Override
    public void addToCart( int add, Product product) {
        addToCartNow(add,product);
    }
}
