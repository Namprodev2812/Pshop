package com.example.trnhxunnam.pshopmyclone.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trnhxunnam.pshopmyclone.Model.Cart;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdapterRecyclerStandard extends RecyclerView.Adapter<AdapterRecyclerStandard.ViewHolder01> {

    ItemClickmove itemClickmove;
    Context context;
    ArrayList<Product> arrayList;
    int value = 0;
    Double price ;
    Double oneprice;

    public AdapterRecyclerStandard(ItemClickmove itemClickmove,Context context, ArrayList<Product> arrayList) {
        this.itemClickmove = itemClickmove;
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder01 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_recycler_standard, viewGroup, false);
        ViewHolder01 viewHolder = new ViewHolder01(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder01 viewHolder, int i) {

        Glide.with(context).load(arrayList.get(i).getImage()).into(viewHolder.image);
        viewHolder.textView1.setText(arrayList.get(i).getName());
        viewHolder.textView2.setText("Đơn giá: " + formatMoney(arrayList.get(i).getPrice()) + " VNĐ");
        viewHolder.textView4.setText(getSoSanPhamOfItem(arrayList.get(i))+"");

        oneprice = arrayList.get(i).getPrice();
        value = getSoSanPhamOfItem(arrayList.get(i));
        price =  value * oneprice;
        viewHolder.textView3.setText("Tổng: " + formatMoney(oneprice * value) + " VNĐ");


        itemClickmove.click3(viewHolder,arrayList,viewHolder.textView3,viewHolder.textView4);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder01 extends RecyclerView.ViewHolder {

        ItemClickListener itemClickListener;
        ItemClickListenerUp itemClickListenerUp;
        ItemClickListenerDown itemClickListenerDown;
        @BindView(R.id.title_item_recycler_standard) TextView textView1;
        @BindView(R.id.price_item_recycler_standard) TextView textView2;
        @BindView(R.id.sum_item_recycler_standard) TextView textView3;
        @BindView(R.id.value_item_recycler_standard) TextView textView4;
        @BindView(R.id.image_item_recycler_standard) ImageView image;
        @BindView(R.id.up_item_recycler_standard) ImageView imageup;
        @BindView(R.id.down_item_recycler_standard) ImageView imagedown;
        public @BindView(R.id.view_background) RelativeLayout relativeLayout;
        public @BindView(R.id.linear_item) LinearLayout linearLayout_touch_item;

        public ViewHolder01(@NonNull final View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.onClickView(itemView, getAdapterPosition(), false);
                }
            });

//            imageup.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    itemClickListenerUp.onClickup(imageup, getAdapterPosition(), false);
//                }
//            });
//
//            imagedown.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    itemClickListenerDown.onClickdown(imageup, getAdapterPosition(), false);
//                }
//            });
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public void setItemClickListenerup(ItemClickListenerUp itemClickListenerup) {
            this.itemClickListenerUp = itemClickListenerup;
        }

        public void setItemClickListenerdown(ItemClickListenerDown itemClickListenerdown) {
            this.itemClickListenerDown = itemClickListenerdown;
        }

        @OnClick(R.id.up_item_recycler_standard)
        public void clickUp(){
            itemClickListenerUp.onClickup(imageup, getAdapterPosition(), false);
        }
        @OnClick(R.id.down_item_recycler_standard)
        public void clickDown(){
            itemClickListenerDown.onClickdown(imageup, getAdapterPosition(), false);
        }
    }

    public String formatMoney(Double money){
        NumberFormat format = new DecimalFormat("#,###");
        String abc =  format.format(money);
        return  abc;
    }

    public int getSoSanPhamOfItem(Product product){
        return Cart.getInstance().getCart().get(product);
    }
}
