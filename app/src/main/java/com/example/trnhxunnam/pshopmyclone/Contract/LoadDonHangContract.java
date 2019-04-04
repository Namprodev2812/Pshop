package com.example.trnhxunnam.pshopmyclone.Contract;

import android.content.Context;

import com.example.trnhxunnam.pshopmyclone.Base.BaseView;
import com.example.trnhxunnam.pshopmyclone.Model.DonHang;
import com.example.trnhxunnam.pshopmyclone.Model.Timesave;

import java.util.ArrayList;

public interface LoadDonHangContract {
    interface LDHView extends BaseView {

        void loadProcess(ArrayList<DonHang> arrayList);
        void loadFail();
        void getShareperenceProcess(Timesave timesave);
    }

    interface  LDHPresenter{

        void loadMonth(String first,String thesecond);
        void loadAll();
        void loadWeek(String first,String thesecond);
        void loadBeforeWeek(String first,String thesecond);
        void setShareperence(Context context, Timesave timesave);
        void getShareperence(Context context);
    }
}
