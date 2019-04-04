package com.example.trnhxunnam.pshopmyclone.Adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdapterRecyclerGridMain extends RecyclerView.Adapter<AdapterRecyclerGridMain.ViewHolderPay> {

    ItemClickmove itemClickmove;
    public ArrayList<Product> arrayList;
    RelativeLayout destView;
    Context context;
    Activity activity;

    public AdapterRecyclerGridMain(ItemClickmove itemClickmove,Context context, ArrayList<Product> arrayList, RelativeLayout cartlayout, Activity activity) {
        this.context = context;
        this.arrayList = arrayList;
        this.destView = cartlayout;
        this.activity = activity;
        this.itemClickmove =  itemClickmove;
    }

    @NonNull
    @Override
    public AdapterRecyclerGridMain.ViewHolderPay onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_grid_main, viewGroup, false);

        AdapterRecyclerGridMain.ViewHolderPay viewHolder = new AdapterRecyclerGridMain.ViewHolderPay(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerGridMain.ViewHolderPay viewHolder, int i) {

        Glide.with(context).load(arrayList.get(i).getImage()).into(viewHolder.imageView);
        Glide.with(context).load(arrayList.get(i).getImage()).into(viewHolder.imageView_move);
        viewHolder.textView1.setText(arrayList.get(i).getName());
        viewHolder.textView2.setText("" + formatMoney(arrayList.get(i).getPrice()) + " VNĐ");

        setClickToAdd(i, viewHolder, viewHolder.imageView_move);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolderPay extends RecyclerView.ViewHolder {

        ItemClickListener itemClickListener;
        ItemClickListenerUp itemClickListenerUp;

        @BindView(R.id.text_title_item_grid_main) TextView textView1;
        @BindView(R.id.text_money_item_grid_main) TextView textView2;
        @BindView(R.id.image_item_grid_main) ImageView imageView;
        @BindView(R.id.image_add_item_grid_main) ImageView imageView_add;
        @BindView(R.id.profile_image) ImageView imageView_move;

        public ViewHolderPay(@NonNull final View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClickView(itemView, getAdapterPosition(), false);
                }
            });

//            imageView_add.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    itemClickListenerUp.onClickup(imageView_add, getAdapterPosition(), false);
//                }
//            });
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public void setItemClickListeneradd(ItemClickListenerUp itemClickListenerup) {
            this.itemClickListenerUp = itemClickListenerup;
        }

        @OnClick(R.id.image_add_item_grid_main)
        public void clickAdd(){
            itemClickListenerUp.onClickup(imageView_add, getAdapterPosition(), false);
        }

    }

    public String formatMoney(Double money) {
        NumberFormat format = new DecimalFormat("#,###");
        String abc = format.format(money);
        return abc;
    }

    public void setClickToAdd(final int i, AdapterRecyclerGridMain.ViewHolderPay viewHolder, final ImageView image_move) {
        itemClickmove.click(i,viewHolder,image_move);
    }

}