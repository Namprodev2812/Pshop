package com.example.trnhxunnam.pshopmyclone.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.trnhxunnam.pshopmyclone.Base.BasePresenter;
import com.example.trnhxunnam.pshopmyclone.Contract.LoadDonhangFull;
import com.example.trnhxunnam.pshopmyclone.Model.DonHangfull;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.APIUntilsDonHang;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadDonhangFullPresenter extends BasePresenter<LoadDonhangFull.LDHFView> implements LoadDonhangFull.LDHFPresenter {


    private void loadDonHangNow(String madonhang) {

        DataClient dataClient = APIUntilsDonHang.getdata();
        Call<List<DonHangfull>> listCall = dataClient.loaddonhangfullinfor(madonhang);
        listCall.enqueue(new Callback<List<DonHangfull>>() {
            @Override
            public void onResponse(Call<List<DonHangfull>> call, Response<List<DonHangfull>> response) {

                ArrayList<DonHangfull> donHangArrayList = new ArrayList<DonHangfull>();
                donHangArrayList.addAll(response.body());
                Log.e("DonHangfullreceiver", "" + donHangArrayList.size());
                getView().loadDonHangProcess(donHangArrayList);
            }

            @Override
            public void onFailure(Call<List<DonHangfull>> call, Throwable t) {
                Log.e("ERRORLOADDATA", "" + t.getMessage());
            }
        });
    }

    @Override
    public void loadDonHang(String maDonHang) {
        loadDonHangNow(maDonHang);
    }
}
