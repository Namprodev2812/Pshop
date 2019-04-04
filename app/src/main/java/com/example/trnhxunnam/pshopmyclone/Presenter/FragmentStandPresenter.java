package com.example.trnhxunnam.pshopmyclone.Presenter;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.trnhxunnam.pshopmyclone.Base.BasePresenter;
import com.example.trnhxunnam.pshopmyclone.Contract.FragmentStandContract;
import com.example.trnhxunnam.pshopmyclone.Model.Cart;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Model.UserinShareperence;

import java.util.ArrayList;

public class FragmentStandPresenter extends BasePresenter<FragmentStandContract.LSView> implements FragmentStandContract.LSPresenter {

    final static  String LOGIN = "login";
    final static  String ITEM_LOGIN_FELL = "feel";
    final static  String ITEM_LOGIN_TAIKHOAN = "logintaikhoan";
    final static  String ITEM_LOGIN_EMAIL = "email";
    final static  String ITEM_LOGIN_URLIMAGE = "loginurl";

    private double getSumPriceCartNow() {
        double sum = 0;
        for (Product product : Cart.getInstance().getCart().keySet()) {

            double price = product.getPrice();
            int value = Cart.getInstance().getCart().get(product);
            double sum1 = price * value;
            sum = sum1 + sum;
        }
        return sum;
    }
    private void addOneToCartNow(Product product) {

        if (Cart.getInstance().getCart().get(product) != null) {
            int oldvalue = Cart.getInstance().getCart().get(product);
            Cart.getInstance().addProduct(product, oldvalue + 1);
        }
    }
    private void removeOneInCartNow(Product product) {

        if (Cart.getInstance().getCart().get(product) >= 1) {
            int oldvalue = Cart.getInstance().getCart().get(product);
            Cart.getInstance().addProduct(product, oldvalue - 1);
        }
    }
    private int getValueOfProductNow(Product product) {
        return Cart.getInstance().getCart().get(product);
    }
    private void deleteOneInCartNow(Product product) {
        if (Cart.getInstance().getCart().get(product) == 0) {
            Cart.getInstance().getCart().remove(product);
        }
    }
    private void deleteOneInCartSwiperNow(Product product) {
        Cart.getInstance().getCart().remove(product);
    }

    private ArrayList<Product> getArrayCartNow() {
        ArrayList<Product> arrayList = new ArrayList<>();
        for (Product product : Cart.getInstance().getCart().keySet()) {
            String name = product.getName();
            arrayList.add(product);
        }
        return arrayList;
    }

    private int getValueLoginNow(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(ITEM_LOGIN_FELL, 0);
    }

    private UserinShareperence getTaiKhoanOfShareperenceNow(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(LOGIN, Context.MODE_PRIVATE);
        UserinShareperence userinShareperence  = new UserinShareperence(
                sharedPreferences.getString(ITEM_LOGIN_TAIKHOAN, ""),
                sharedPreferences.getString(ITEM_LOGIN_EMAIL, ""),
                sharedPreferences.getString(ITEM_LOGIN_URLIMAGE, ""));
        return  userinShareperence;
    }


    @Override
    public void getSumPriceCart() {
        getView().getSumPriceProcess(getSumPriceCartNow());
    }

    @Override
    public void addOneToCart(Product product) {
        addOneToCartNow(product);
    }

    @Override
    public void removeInCart(Product product) {
        removeOneInCartNow(product);
    }

    @Override
    public void getValueOfProduct(Product product) {
        getView().getValueOfProduct(getValueOfProductNow(product));
    }

    @Override
    public void deleteOneInCart(Product product) {
        deleteOneInCartNow(product);
    }

    @Override
    public void deleteOneInCartSwiper(Product product) {
        deleteOneInCartSwiperNow(product);
    }

    @Override
    public void getArrayCart() {
        getView().getArrayCartProcess(getArrayCartNow());
    }

    @Override
    public void getValueLogin(Context context) {
        getView().getValueLoginProcess1(getValueLoginNow(context));
    }

    @Override
    public void getTaiKhoanOfShareperence(Context context) {
        getView().getTaiKhoanOfShareperenceProcess(getTaiKhoanOfShareperenceNow(context));
    }
}
