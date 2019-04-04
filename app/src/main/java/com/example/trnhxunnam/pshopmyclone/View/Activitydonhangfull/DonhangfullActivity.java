package com.example.trnhxunnam.pshopmyclone.View.Activitydonhangfull;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerViewDonhangfull;
import com.example.trnhxunnam.pshopmyclone.Base.BaseActivity;
import com.example.trnhxunnam.pshopmyclone.Contract.LoadDonhangFull;
import com.example.trnhxunnam.pshopmyclone.Model.DonHangfull;
import com.example.trnhxunnam.pshopmyclone.Presenter.LoadDonhangFullPresenter;
import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.APIUntilsDonHang;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.DataClient;
import com.example.trnhxunnam.pshopmyclone.View.ActivityMain.MainActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonhangfullActivity extends BaseActivity<LoadDonhangFullPresenter> implements LoadDonhangFull.LDHFView {

    int i;
    @BindView(R.id.btn_show_product_donhangfull) ImageButton image_show;
    Animation animationdown;
    Animation animationup;
    @BindView(R.id.sumallprice_nearshop_donhangfull) TextView text_sumprice_nearshop;
    @BindView(R.id.sumallprice_donhangfull_tttt) TextView text_sum_tttt;
    @BindView(R.id.sumall_donhangfull_tttt) TextView text_sumprice_tttt;
    @BindView(R.id.menu_donhangfull_left) ImageView image_back;
    String madonhang;
    @BindView(R.id.recyclerview_donhangfull) RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    ArrayList<DonHangfull> arrayList;
    AdapterRecyclerViewDonhangfull AdapterRecyclerViewDonhangfull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donhang_full);

        ButterKnife.bind(this);
        animation();
        getDataInPayFragment();
    }
    public void animation(){
        animationdown = AnimationUtils.loadAnimation(this, R.anim.slide_down);
        animationup = AnimationUtils.loadAnimation(this, R.anim.slide_up);
    }

    @OnClick(R.id.btn_show_product_donhangfull)
    public void setClickToShow() {
        if (i == 0) {
            image_show.setImageResource(R.drawable.ic_down);
            setClickHide();
        } else {
            image_show.setImageResource(R.drawable.ic_up);
            setClickShow();
        }
    }

    public void setClickShow(){
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.startAnimation(animationdown);
        i = 0;
    }

    public void setClickHide(){
        recyclerView.setVisibility(View.GONE);
        i = 1;
    }

    public void getDataInPayFragment(){
        Intent intent  = getIntent();
        madonhang =  intent.getStringExtra("madonhang");
        getPresenter().loadDonHang(madonhang);
    }


    @OnClick(R.id.menu_donhangfull_left)
    public void setBack(){
        Intent intent = new Intent(DonhangfullActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void setRecyclerView(ArrayList<DonHangfull> arrayListback) {
        linearLayoutManager = new LinearLayoutManager(this);
        arrayList = arrayListback;
        AdapterRecyclerViewDonhangfull = new AdapterRecyclerViewDonhangfull(this, arrayList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(AdapterRecyclerViewDonhangfull);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(DonhangfullActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void setData(ArrayList<DonHangfull> arrayList){

        text_sumprice_nearshop.setText("đ "+formatMoney(caculator(arrayList)));
        text_sum_tttt.setText("đ "+formatMoney(caculator(arrayList)));
        text_sumprice_tttt.setText("đ "+formatMoney(caculator(arrayList)));
    }

    public double caculator(ArrayList<DonHangfull> arrayList){
        double sum = 0;
        for(int i =0;i<arrayList.size();i++){
            double sum1 = arrayList.get(i).getSumprice();
            sum = sum + sum1;
        }
        return sum;
    }
    public String formatMoney(Double money) {
        NumberFormat format = new DecimalFormat("#,###");
        String abc = format.format(money);
        return abc;
    }

    @Override
    public void loadDonHangProcess(ArrayList<DonHangfull> arrayList) {
        setRecyclerView(arrayList);
        setData(arrayList);
    }
}
