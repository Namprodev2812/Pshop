package com.example.trnhxunnam.pshopmyclone.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AdapterSearchView2 extends RecyclerView.Adapter <AdapterSearchView2.ViewHolder> implements Filterable {
    ItemClickmove itemClickmove;
    Context context;
    ArrayList<Product> arrayList;
    ArrayList<Product> arrayListfull;

    public AdapterSearchView2(ItemClickmove itemClickmove,Context context,ArrayList<Product> arrayList){
        this.context = context;
        this.arrayList = arrayList;
        arrayListfull = new ArrayList<>(arrayList);
        this.itemClickmove = itemClickmove;
    }

    @NonNull
    @Override
    public AdapterSearchView2.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View  view = layoutInflater.inflate(R.layout.item_searchview,viewGroup,false);
        AdapterSearchView2.ViewHolder viewHolder = new AdapterSearchView2.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final AdapterSearchView2.ViewHolder viewHolder, final int i) {

        Glide.with(context).load(arrayList.get(i).getImage()).into(viewHolder.imageView);
        Glide.with(context).load(arrayList.get(i).getImage()).into(viewHolder.img_sub);
        viewHolder.textView1.setText(arrayList.get(i).getName());
        viewHolder.textView2.setText(""+formatMoney(arrayList.get(i).getPrice()));

//        itemClickmove.click2(viewHolder,viewHolder.img_sub);
        viewHolder.setItemClickListeneradd(new ItemClickListenerUp() {
            @Override
            public void onClickup(ImageView imageView, int position, boolean isLongClick) {

            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Product> filterlist = new ArrayList<>();

            if(constraint == null|| constraint.length() == 0){

                filterlist.addAll(arrayListfull);

            }else{

                String filterpattern = constraint.toString().toLowerCase().trim();
                for(Product product:arrayListfull){
                    if(product.getName().toLowerCase().contains(filterpattern)){
                        filterlist.add(product);
                    }
                }
            }

            FilterResults results1 = new FilterResults();
            results1.values = filterlist;
            return results1;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            arrayList.clear();
            arrayList.addAll((ArrayList)results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{

        ItemClickListenerUp itemClickListenerUp;
        @BindView(R.id.item_search_text) TextView textView1;
        @BindView(R.id.item_price_search_text) TextView textView2;
        @BindView(R.id.item_search_image) ImageView imageView;
        //@BindView(R.id.item_search_image) ImageView imageshopping;
        @BindView(R.id.item_search_image_sub) ImageView img_sub;
        @BindView(R.id.item_search_addproduct) ImageView imageView_add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
            imageView_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListenerUp.onClickup(imageView_add, getAdapterPosition(), false);
                }
            });
        }

        public void setItemClickListeneradd(ItemClickListenerUp itemClickListenerup) {
            this.itemClickListenerUp = itemClickListenerup;
        }

        @OnClick(R.id.item_search_addproduct)
        public void clickAdd(){
            itemClickListenerUp.onClickup(imageView_add, getAdapterPosition(), false);
        }
    }

    public String formatMoney(Double money){
        NumberFormat format = new DecimalFormat("#,###");
        String abc =  format.format(money);
        return  abc;
    }

}
