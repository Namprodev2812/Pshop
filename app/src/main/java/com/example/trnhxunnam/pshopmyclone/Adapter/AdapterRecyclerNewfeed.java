package com.example.trnhxunnam.pshopmyclone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trnhxunnam.pshopmyclone.Model.Infor;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.R;

import java.util.ArrayList;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterRecyclerNewfeed extends BaseAdapter {

    public ArrayList<Infor> arrayList;
    Context context;
    @BindView(R.id.image_item_grid_newfeed) ImageView imageView;
    @BindView(R.id.text_title_item_grid_newfeed) TextView textView1;
    @BindView(R.id.text_time_item_grid_newfeed) TextView textView2;

    public AdapterRecyclerNewfeed(Context context, ArrayList<Infor> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_grid_newfeed,null);
        ButterKnife.bind(this,view);
        Glide.with(context).load(arrayList.get(i).getImage()).into(imageView);
        textView1.setText(arrayList.get(i).getTitle());
        textView2.setText("24 Th3");

        return view;
    }
}
