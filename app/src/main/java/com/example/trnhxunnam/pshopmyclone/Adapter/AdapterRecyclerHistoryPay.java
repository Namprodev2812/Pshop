package com.example.trnhxunnam.pshopmyclone.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.trnhxunnam.pshopmyclone.Model.DonHang;
import com.example.trnhxunnam.pshopmyclone.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterRecyclerHistoryPay extends RecyclerView.Adapter<AdapterRecyclerHistoryPay.ViewHolderHistoryPay>{

    ItemClickmove itemClickMove;
    Context context;
    ArrayList<DonHang> arrayList;

    public AdapterRecyclerHistoryPay(ItemClickmove itemClickmove,Context context, ArrayList<DonHang> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.itemClickMove = itemClickmove;
    }

    @NonNull
    @Override
    public AdapterRecyclerHistoryPay.ViewHolderHistoryPay onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_recycler_history_pay, viewGroup, false);
        AdapterRecyclerHistoryPay.ViewHolderHistoryPay viewHolder = new AdapterRecyclerHistoryPay.ViewHolderHistoryPay(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerHistoryPay.ViewHolderHistoryPay viewHolder, final int i) {

        //Glide.with(context).load(arrayList.get(i).getImage()).into(viewHolder.image);
        viewHolder.title.setText(arrayList.get(i).getUser());
        viewHolder.mobile.setText(arrayList.get(i).getMobile()+"");
        viewHolder.time.setText(convertTime(arrayList.get(i).getTimem())+"");
        viewHolder.sumprice.setText( formatMoney(arrayList.get(i).getSumprice()) + " VNĐ");
        viewHolder.indexsum.setText(arrayList.get(i).getIndexsum()+"");

        itemClickMove.click1(viewHolder,arrayList);
    }

    public String convertTime(String time){
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = simpleDateFormat.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");

        return simpleDateFormat1.format(date);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolderHistoryPay extends RecyclerView.ViewHolder {

        @BindView(R.id.title_item_recycler_history_pay) TextView title;
        @BindView(R.id.mobile_item_recycler_history_pay) TextView mobile;
        @BindView(R.id.sum_item_recycler_history_pay) TextView sumprice;
        @BindView(R.id.value_item_recycler_history_pay) TextView indexsum;
        @BindView(R.id.time_item_recycler_history_pay) TextView time;

        ItemClickListener itemClickListener;
        public ViewHolderHistoryPay(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClickView(itemView, getAdapterPosition(), false);
                }
            });
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
    }
    public String formatMoney(Double money){
        NumberFormat format = new DecimalFormat("#,###");
        String abc =  format.format(money);
        return  abc;
    }

}
