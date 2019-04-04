package com.example.trnhxunnam.pshopmyclone.Contract;

import android.content.Context;

import com.example.trnhxunnam.pshopmyclone.Base.BaseView;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Model.UserinShareperence;

import java.util.ArrayList;

public interface FragmentStandContract {
    interface LSView extends BaseView {
       void getSumPriceProcess(double sumprice);
       void getValueOfProduct(int value);
       void getArrayCartProcess(ArrayList<Product> arrayList);
       void getValueLoginProcess1(int value);
       void getTaiKhoanOfShareperenceProcess(UserinShareperence userinShareperence);
    }

    interface  LSPresenter{
        void getSumPriceCart();
        void addOneToCart(Product product);
        void removeInCart(Product product);
        void getValueOfProduct(Product product);
        void deleteOneInCart(Product product);
        void deleteOneInCartSwiper(Product product);
        void getArrayCart();
        void getValueLogin(Context context);
        void getTaiKhoanOfShareperence(Context context);
    }
}
