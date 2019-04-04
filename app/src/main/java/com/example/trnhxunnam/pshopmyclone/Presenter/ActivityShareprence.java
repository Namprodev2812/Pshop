package com.example.trnhxunnam.pshopmyclone.Presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.trnhxunnam.pshopmyclone.Base.BasePresenter;
import com.example.trnhxunnam.pshopmyclone.Contract.ActivityShareprenceContract;
import com.example.trnhxunnam.pshopmyclone.Model.Cart;
import com.example.trnhxunnam.pshopmyclone.Model.Product;

import static android.content.Context.MODE_PRIVATE;

public class ActivityShareprence extends BasePresenter<ActivityShareprenceContract.LSView> implements ActivityShareprenceContract.LSPresenter {

    final static  String OFFAPP = "offapp";
    final static  String ITEM_OFFAPP = "booblean";
    final static  String LOGIN = "login";
    final static  String ITEM_LOGIN_FELL = "feel";
    @Override
    public void getValueLogin(Context context) {
        getView().getValueLoginProcess(getValueLoginNow(context));
    }

    @Override
    public void getSoLuongGioHang() {
        getView().getSoLuongGioHangProcess(getSumCart());
    }

    @Override
    public void getOffApp(Context context) {
        getView().getOffAppProcess(getOffAppNow(context));
    }

    @Override
    public void setOffApp(Context context, int value) {
        setOffAppNow(context,value);
    }

    private int getSumCart(){
        int sum =0;
        for(Product product: Cart.getInstance().getCart().keySet()){

            int value = Cart.getInstance().getCart().get(product);
            sum =  sum+value;
        }
        return sum;
    }


    private int getOffAppNow(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(OFFAPP, MODE_PRIVATE);
        return sharedPreferences.getInt(ITEM_OFFAPP, 0);
    }
    private void setOffAppNow(Context context,int off) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(OFFAPP, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(ITEM_OFFAPP, off);
        editor.apply();
    }
    private int getValueLoginNow(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(ITEM_LOGIN_FELL, 0);
    }
}
