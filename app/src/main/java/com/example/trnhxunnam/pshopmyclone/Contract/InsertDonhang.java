package com.example.trnhxunnam.pshopmyclone.Contract;

import android.content.Context;

import com.example.trnhxunnam.pshopmyclone.Base.BaseView;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Model.UserFull;

import java.util.ArrayList;

public interface InsertDonhang {

    interface InsDHView extends BaseView {

        void insertProcessNow();
        void insertAndLoadFail();
        void loadDaTaUserProcessNow(ArrayList<UserFull> arrayList);
        void getSumCartProcess(int value);
        void getSumPriceCartProcess(double value);
        void getTaiKhoanOfShareperenceProcess(String taikhoan);
        void getArrayCartProcess(ArrayList<Product> arrayList);
        void getValueOfProductProcess(int value);
    }

    interface  InsDHPresenter{
        void insertDonHang(String madonhang,String user, String name,int index,double sumprice,String time);
        void insertDonHangFull(String madonhang,String image, String name ,double price,int indexsum,double sumprice);
        void loadFullUser(String taikhoan);
        void getSumCart();
        void getSumPriceCart();
        void getTaiKhoanOfShareperence(Context context);
        void getArrayCart();
        void getValueOfProduct(Product product);
        void clearAllCart();
    }
}
