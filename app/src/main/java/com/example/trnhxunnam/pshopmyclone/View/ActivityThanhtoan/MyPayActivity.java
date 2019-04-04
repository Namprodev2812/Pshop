package com.example.trnhxunnam.pshopmyclone.View.ActivityThanhtoan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerPay;
import com.example.trnhxunnam.pshopmyclone.Base.BaseActivity;
import com.example.trnhxunnam.pshopmyclone.Contract.InsertDonhang;
import com.example.trnhxunnam.pshopmyclone.Model.Cart;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Model.UserFull;
import com.example.trnhxunnam.pshopmyclone.Presenter.InsertDonhangPresenter;
import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.View.ActivityGiohang.MyProductActivity;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyPayActivity extends BaseActivity<InsertDonhangPresenter> implements InsertDonhang.InsDHView {


    @BindView(R.id.toolbar_pay) Toolbar toolbar;
    @BindView(R.id.recyclerview_pay) RecyclerView recyclerView;
    ArrayList<Product> arrayList,getArrayCart;
    LinearLayoutManager linearLayoutManager;
    AdapterRecyclerPay AdapterRecyclerPay;
    @BindView(R.id.text_sumcash_mypay) TextView text_sumcash_mypay;
    @BindView(R.id.text_sumall_mypay) TextView text_sumall_mypay;
    @BindView(R.id.text_cashmove_mypay) TextView text_cashmove_mypay;
    @BindView(R.id.edit_user_mypay) TextView text_edit_user_mypay;
    @BindView(R.id.nameuser_mobile_mypay) TextView text_nameuser_mypay;
    @BindView(R.id.dccuthe_user_mypay) TextView text_diachicuthe_mypay;
    @BindView(R.id.menu_mypay_left) ImageView back;
    @BindView(R.id.btn_dathang_Mypay) Button btn_dathang_Mypay;
    Calendar c;
    int today,month,year,getsumcart,getvalueofproduct;
    double getsumpricecart;
    String nameuserdonhang,sodienthoaiuser,gettaikhoanofshareperence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypay);

        ButterKnife.bind(this);
        loadDataUser();
        setToolbar();

        setRecyclerView();
        loadCart();
    }

    @OnClick(R.id.edit_user_mypay)
    public void setClickEditUser(){
        Intent intent = new Intent(MyPayActivity.this,EditUserActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.menu_mypay_left)
    public void backToMyProduct(){

        Intent intent1 = new Intent(MyPayActivity.this, MyProductActivity.class);
        startActivity(intent1);

    }

    public void setText_sumcash_mypay() {

        Double sumall = 0.0;
        double cash_move = 0;
        try {
            cash_move = Double.parseDouble(text_cashmove_mypay.getText().toString());
        }catch (Exception e){

        }
        getPresenter().getSumPriceCart();
        sumall = getsumpricecart;
        text_sumcash_mypay.setText(""+formatMoney(sumall));
        sumall = sumall + cash_move;
        text_sumall_mypay.setText("" +formatMoney(sumall));

    }

    public void setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    public void setRecyclerView() {

        linearLayoutManager = new LinearLayoutManager(this);
        arrayList = new ArrayList<>();
        AdapterRecyclerPay = new AdapterRecyclerPay(this, arrayList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(AdapterRecyclerPay);
    }

    public void setClickToDatHang(final ArrayList<Product> arraydonhang) {
            getPresenter().getSumCart();
            final int index       = getsumcart;
            getPresenter().getSumPriceCart();
            final double sumprice = getsumpricecart;
            final String time = getTime();

            btn_dathang_Mypay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String madonhang = ""+sodienthoaiuser+nameuserdonhang+index;
                    // them vao don hang
                    getPresenter().insertDonHang(madonhang,nameuserdonhang,sodienthoaiuser,index,sumprice,time);

                    // them vao chi tiet don hang
                    for(int i = 0;i<arraydonhang.size();i++) {
                        final String image = arraydonhang.get(i).getImage();
                        final String name = arraydonhang.get(i).getName();
                        final double price = arraydonhang.get(i).getPrice();
                        getPresenter().getValueOfProduct(arraydonhang.get(i));
                        final int index = getvalueofproduct;
                        final double sumpriceone = price*index;
                        getPresenter().insertDonHangFull(madonhang,image,name,price,index,sumpriceone);
                    }
                    getPresenter().clearAllCart();
                }
            });

    }

    @Override
    public void insertProcessNow() {
        //Toast.makeText(this, "Đơn hàng được thêm thành công.Quý khách vui lòng đợi cuộc gọi từ bộ phận chăm sóc khách hàng", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(MyPayActivity.this,MyThanhtoanActivity.class);
        startActivity(intent);
    }

    @Override
    public void insertAndLoadFail() {
        Toast.makeText(this, "insert fail and load user fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadDaTaUserProcessNow(ArrayList<UserFull> fulluser) {
        text_nameuser_mypay.setText(""+fulluser.get(0).getName()+" (+84)"+fulluser.get(0).getTaikhoan());
        text_diachicuthe_mypay.setText(""+fulluser.get(0).getDiachi());
        nameuserdonhang = fulluser.get(0).getName();
        sodienthoaiuser = fulluser.get(0).getTaikhoan();
    }

    @Override
    public void getSumCartProcess(int value) {
        getsumcart =  value;
    }

    @Override
    public void getSumPriceCartProcess(double value) {
        getsumpricecart = value;
    }

    @Override
    public void getTaiKhoanOfShareperenceProcess(String taikhoan) {
        gettaikhoanofshareperence = taikhoan;
    }

    @Override
    public void getArrayCartProcess(ArrayList<Product> arrayList) {
        getArrayCart = arrayList;
    }

    @Override
    public void getValueOfProductProcess(int value) {
        getvalueofproduct = value;
    }

    public void loadCart() {
        arrayList.clear();
        getPresenter().getArrayCart();
        arrayList.addAll(getArrayCart);
        AdapterRecyclerPay.notifyDataSetChanged();
        setText_sumcash_mypay();
        setClickToDatHang(arrayList);
    }


    public void loadDataUser(){
        getPresenter().getTaiKhoanOfShareperence(getApplicationContext());
        getPresenter().loadFullUser(gettaikhoanofshareperence);
    }

    // get time now
    public String getTime(){
        c = Calendar.getInstance();
        today = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH)+1;
        year = c.get(Calendar.YEAR);
        return  ""+year+"-"+month+"-"+today;
    }

    // back
    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(MyPayActivity.this,MyProductActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public String formatMoney(Double money){
        NumberFormat format = new DecimalFormat("#,###");
        String abc =  format.format(money);
        return  abc;
    }
}
