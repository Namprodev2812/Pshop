package com.example.trnhxunnam.pshopmyclone.Contract;

import android.content.Context;

import com.example.trnhxunnam.pshopmyclone.Base.BaseView;
import com.example.trnhxunnam.pshopmyclone.Model.Infor;
import com.example.trnhxunnam.pshopmyclone.Model.Product;

import java.util.ArrayList;

public interface LoadRSSContract {

    interface LRSSView extends BaseView {

        void loadProcess(ArrayList<Infor> arrayList);
        void loadFail(ArrayList<Infor> arrayList);
    }

    interface  LRSSPresenter{

        void load(String kind, Context context);
        void closeRealm();
    }
}
