package com.example.trnhxunnam.pshopmyclone.Presenter;

import com.example.trnhxunnam.pshopmyclone.Base.BaseFragment;
import com.example.trnhxunnam.pshopmyclone.Base.BasePresenter;

import com.example.trnhxunnam.pshopmyclone.Contract.MyProductContract;
import com.example.trnhxunnam.pshopmyclone.Model.Cart;
import com.example.trnhxunnam.pshopmyclone.Model.Product;

public class MyProductPresenter  extends BasePresenter<MyProductContract.LSView> implements MyProductContract.LSPresenter {

    private int getSumCartNow(){
        int sum =0;
        for(Product product: Cart.getInstance().getCart().keySet()){

            int value = Cart.getInstance().getCart().get(product);
            sum =  sum+value;
        }
        return sum;
    }
    private void clearallCartnow(){
        Cart.getInstance().getCart().clear();
    }

    @Override
    public void getSumCart() {
        getView().getSumCartProcess(getSumCartNow());
    }

    @Override
    public void clearAllCart() {
        clearallCartnow();
    }
}
