package com.example.trnhxunnam.pshopmyclone.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trnhxunnam.pshopmyclone.Model.DonHangfull;
import com.example.trnhxunnam.pshopmyclone.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterRecyclerViewDonhangfull extends RecyclerView.Adapter<AdapterRecyclerViewDonhangfull.ViewHolderPay> {

    Context context;
    ArrayList<DonHangfull> arrayList;

    public AdapterRecyclerViewDonhangfull(Context context, ArrayList<DonHangfull> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AdapterRecyclerViewDonhangfull.ViewHolderPay onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_recyclerview_donhangfull, viewGroup, false);
        AdapterRecyclerViewDonhangfull.ViewHolderPay viewHolder = new AdapterRecyclerViewDonhangfull.ViewHolderPay(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerViewDonhangfull.ViewHolderPay viewHolder, int i) {

        Glide.with(context).load(arrayList.get(i).getImage()).into(viewHolder.image);
        viewHolder.textView1.setText(arrayList.get(i).getName());
        viewHolder.textView2.setText("Đơn giá: " + formatMoney(arrayList.get(i).getPrice()) + " VNĐ");
        viewHolder.textView4.setText(arrayList.get(i).getIndexsum() + "");
        viewHolder.textView3.setText("Tổng: " + formatMoney(arrayList.get(i).getSumprice()) + " VNĐ");
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolderPay extends RecyclerView.ViewHolder {

        @BindView(R.id.title_item_recycler_donhangfull) TextView textView1;
        @BindView(R.id.price_item_recycler_donhangfull) TextView textView2;
        @BindView(R.id.sum_item_recycler_donhangfull) TextView textView3;
        @BindView(R.id.value_item_recycler_donhangfull) TextView textView4;
        @BindView(R.id.image_item_recycler_donhangfull) ImageView image;

        public ViewHolderPay(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public String formatMoney(Double money) {
        NumberFormat format = new DecimalFormat("#,###");
        String abc = format.format(money);
        return abc;
    }
}
