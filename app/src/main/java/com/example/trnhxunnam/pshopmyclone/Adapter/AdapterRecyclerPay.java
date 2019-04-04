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
import com.example.trnhxunnam.pshopmyclone.Model.Cart;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterRecyclerPay extends RecyclerView.Adapter<AdapterRecyclerPay.ViewHolderPay>{

    Context context;
    ArrayList<Product> arrayList;
    int value = 0;
    Double oneprice ;

    public AdapterRecyclerPay(Context context, ArrayList<Product> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AdapterRecyclerPay.ViewHolderPay onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_recycler_pay, viewGroup, false);
        AdapterRecyclerPay.ViewHolderPay viewHolder = new AdapterRecyclerPay.ViewHolderPay(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterRecyclerPay.ViewHolderPay viewHolder, int i) {

        Glide.with(context).load(arrayList.get(i).getImage()).into(viewHolder.image);
        viewHolder.textView1.setText(arrayList.get(i).getName());
        viewHolder.textView2.setText("Đơn giá: " + formatMoney(arrayList.get(i).getPrice()) + " VNĐ");
        viewHolder.textView4.setText(getSoSanPhamOfItem(arrayList.get(i))+"");

        oneprice = arrayList.get(i).getPrice();
        value = getSoSanPhamOfItem(arrayList.get(i));
        viewHolder.textView3.setText("Tổng: " + formatMoney(oneprice * value) + " VNĐ");

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolderPay extends RecyclerView.ViewHolder {

        @BindView(R.id.title_item_recycler_pay) TextView textView1;
        @BindView(R.id.price_item_recycler_pay) TextView textView2;
        @BindView(R.id.sum_item_recycler_pay) TextView textView3;
        @BindView(R.id.value_item_recycler_pay) TextView textView4;
        @BindView(R.id.image_item_recycler_pay) ImageView image;

        public ViewHolderPay(@NonNull final View itemView) {
            super(itemView);

            ButterKnife.bind(itemView);
        }
    }
    private int getSoSanPhamOfItem(Product product){
        return Cart.getInstance().getCart().get(product);
    }
    private String formatMoney(Double money){
        NumberFormat format = new DecimalFormat("#,###");
        String abc =  format.format(money);
        return  abc;
    }
}
