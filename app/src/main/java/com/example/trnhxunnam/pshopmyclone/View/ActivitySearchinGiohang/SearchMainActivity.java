package com.example.trnhxunnam.pshopmyclone.View.ActivitySearchinGiohang;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterSearchView;
import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterSearchView2;
import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerGridMain;
import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerHistoryPay;
import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerStandard;
import com.example.trnhxunnam.pshopmyclone.Adapter.ItemClickmove;
import com.example.trnhxunnam.pshopmyclone.Base.BaseActivity;
import com.example.trnhxunnam.pshopmyclone.Contract.LoadSearchContract;
import com.example.trnhxunnam.pshopmyclone.Model.DonHang;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Presenter.LoadSearchPresenter;
import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.View.ActivityGiohang.MyProductActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchMainActivity extends BaseActivity<LoadSearchPresenter> implements LoadSearchContract.LSView,ItemClickmove,SwipeRefreshLayout.OnRefreshListener{

    @BindView(R.id.swipe_fragment_searchactivity) SwipeRefreshLayout swipe;
    ItemClickmove itemClickmove;
    @BindView(R.id.serach_child) android.support.v7.widget.SearchView searchView;
    @BindView(R.id.toolbar_child) Toolbar toolbar;
    @BindView(R.id.recyclerview_search) RecyclerView recyclerView;
    AdapterSearchView2 adapterSearchView;
    ArrayList<Product> arrayList;
    @BindView(R.id.back_searchmain_left) ImageView image_left;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_main);

        itemClickmove = this;
        ButterKnife.bind(this);
        setToolbar();
        arrayList = new ArrayList<>();
        getPresenter().load();
        setSwipe();
        adapterSearchView = new AdapterSearchView2(itemClickmove,this,arrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterSearchView);

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapterSearchView.getFilter().filter(s);
                return false;
            }
        });

    }

    public void setSwipe(){
        swipe.setColorSchemeColors(Color.BLUE);
        swipe.setOnRefreshListener(this);
    }

    public void setToolbar(){
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
    }
    @OnClick(R.id.back_searchmain_left)
    public void setBackMenu(){
        Intent intent = new Intent(SearchMainActivity.this,MyProductActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void loadProcess(ArrayList<Product> arrayList) {
        this.arrayList = arrayList;
        adapterSearchView = new AdapterSearchView2(itemClickmove,this, this.arrayList);
        recyclerView.setAdapter(adapterSearchView);
        adapterSearchView.notifyDataSetChanged();

        swipe.setRefreshing(false);
    }

    @Override
    public void loadFail() {

    }

    @Override
    public void getSumCartProcess(int value) {

    }

    @Override
    public void onBackPressed() {

        //super.onBackPressed();
        Intent intent = new Intent(SearchMainActivity.this,MyProductActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public String formatMoney(Double money){
        NumberFormat format = new DecimalFormat("#,###");
        String abc =  format.format(money);
        return  abc;
    }

    @Override
    public void click(int i, AdapterRecyclerGridMain.ViewHolderPay viewHolder, ImageView image_move) {

    }

    @Override
    public void click1(AdapterRecyclerHistoryPay.ViewHolderHistoryPay viewHolder, ArrayList<DonHang> arrayList) {

    }

    @Override
    public void click2(AdapterSearchView.ViewHolder viewHolder, ImageView imageView_move) {

    }

    @Override
    public void click3(AdapterRecyclerStandard.ViewHolder01 viewHolder, ArrayList<Product> arrayList, TextView textView3, TextView textView4) {

    }

    @Override
    public void onRefresh() {

                swipe.setRefreshing(true);
                getPresenter().load();
    }
}
