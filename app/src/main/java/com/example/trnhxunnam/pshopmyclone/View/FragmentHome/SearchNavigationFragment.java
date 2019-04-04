package com.example.trnhxunnam.pshopmyclone.View.FragmentHome;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterSearchView;
import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerGridMain;
import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerHistoryPay;
import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerStandard;
import com.example.trnhxunnam.pshopmyclone.Adapter.ItemClickListenerUp;
import com.example.trnhxunnam.pshopmyclone.Adapter.ItemClickmove;
import com.example.trnhxunnam.pshopmyclone.Base.BaseFragment;
import com.example.trnhxunnam.pshopmyclone.Contract.LoadSearchContract;
import com.example.trnhxunnam.pshopmyclone.Model.Cart;
import com.example.trnhxunnam.pshopmyclone.Model.DonHang;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Presenter.LoadSearchPresenter;
import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.CropImagetoAnimation.CricleAnimationUntil;

import java.util.ArrayList;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

@SuppressLint("ValidFragment")
public class SearchNavigationFragment extends BaseFragment<LoadSearchPresenter> implements LoadSearchContract.LSView, ItemClickmove, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipe_fragment_searchfragment) SwipeRefreshLayout swipe;
    ItemClickmove itemClickmove;
    @BindView(R.id.search_child_main) android.support.v7.widget.SearchView searchView;
    @BindView(R.id.na_recyclerview_search_main) RecyclerView recyclerView;
    AdapterSearchView adapterSearchView;
    ArrayList<Product> arrayList;
    int itemCounter = 0;
    TextView text_cart;
    RelativeLayout cartlayout;
    int add = 0, getsumcart;

    public SearchNavigationFragment(TextView textView_cart, RelativeLayout cartlayout) {

        this.cartlayout = cartlayout;
        this.text_cart = textView_cart;
        arrayList = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        itemClickmove = this;
        View view = inflater.inflate(R.layout.item_fragment_navigation_search, container, false);
        ButterKnife.bind(this,view);
        setSwipe();
        getPresenter().load();

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
        return view;
    }

    public void setSwipe() {
        swipe.setColorSchemeColors(Color.BLUE);
        swipe.setOnRefreshListener(this);
    }

    @Override
    public void loadProcess(ArrayList<Product> arrayList) {
        this.arrayList = arrayList;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        adapterSearchView = new AdapterSearchView(itemClickmove, getContext(), this.arrayList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapterSearchView);
        adapterSearchView.notifyDataSetChanged();

        swipe.setRefreshing(false);
    }

    public void makeFlyAnimation(ImageView targetView) {

        new CricleAnimationUntil().attachActivity(getActivity()).setTargetView(targetView).setMoveDuration(100).setDestView(cartlayout).setAnimationListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                itemCounter = getsumcart;
                addItemToCart();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        }).startAnimation();


    }

    @Override
    public void loadFail() {
        Toast.makeText(getContext(), "Load data fail! ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSumCartProcess(int value) {
        getsumcart = value;
    }


    public void addItemToCart() {

        text_cart.setText(String.valueOf(itemCounter));
    }

    @Override
    public void click(int i, AdapterRecyclerGridMain.ViewHolderPay viewHolder, ImageView image_move) {

    }

    @Override
    public void click1(AdapterRecyclerHistoryPay.ViewHolderHistoryPay viewHolder, ArrayList<DonHang> arrayList) {

    }

    @Override
    public void click2(final AdapterSearchView.ViewHolder viewHolder, final ImageView imageView_sub) {
        viewHolder.setItemClickListeneradd(new ItemClickListenerUp() {
            @Override
            public void onClickup(ImageView imageView, int position, boolean isLongClick) {
                //Toast.makeText(context, "add product", Toast.LENGTH_SHORT).show();

                add = 0;
                add++;
                getPresenter().addToCart(add, arrayList.get(position));

                if (imageView_sub != null) {
                    makeFlyAnimation(imageView_sub);
                }
            }
        });
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
