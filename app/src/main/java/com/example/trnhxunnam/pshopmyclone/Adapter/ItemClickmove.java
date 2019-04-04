package com.example.trnhxunnam.pshopmyclone.Adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.example.trnhxunnam.pshopmyclone.Model.DonHang;
import com.example.trnhxunnam.pshopmyclone.Model.Product;

import java.util.ArrayList;

public interface ItemClickmove {
    void click(final int i, AdapterRecyclerGridMain.ViewHolderPay viewHolder, final ImageView image_move);
    void click1(AdapterRecyclerHistoryPay.ViewHolderHistoryPay viewHolder,ArrayList<DonHang> arrayList);
    void click2(AdapterSearchView.ViewHolder viewHolder,ImageView imageView_move);
    void click3(final AdapterRecyclerStandard.ViewHolder01 viewHolder, final ArrayList<Product> arrayList, final TextView textView3, final TextView textView4);
}
