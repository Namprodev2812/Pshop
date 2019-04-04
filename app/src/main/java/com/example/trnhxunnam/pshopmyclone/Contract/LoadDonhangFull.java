package com.example.trnhxunnam.pshopmyclone.Contract;

import android.content.Context;

import com.example.trnhxunnam.pshopmyclone.Base.BaseView;
import com.example.trnhxunnam.pshopmyclone.Model.DonHangfull;

import java.util.ArrayList;


public interface LoadDonhangFull {
    interface LDHFView extends BaseView {
        void loadDonHangProcess(ArrayList<DonHangfull> arrayList);
    }

    interface  LDHFPresenter{
        void loadDonHang(String madonhang);
    }
}
