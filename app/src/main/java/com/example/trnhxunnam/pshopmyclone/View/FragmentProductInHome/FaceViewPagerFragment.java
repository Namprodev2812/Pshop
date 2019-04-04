package com.example.trnhxunnam.pshopmyclone.View.FragmentProductInHome;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.trnhxunnam.pshopmyclone.Adapter.ItemClickListener;
import com.example.trnhxunnam.pshopmyclone.Adapter.ItemClickListenerUp;
import com.example.trnhxunnam.pshopmyclone.Adapter.ItemClickmove;
import com.example.trnhxunnam.pshopmyclone.Base.BaseFragment;
import com.example.trnhxunnam.pshopmyclone.Contract.LoadProductContract;
import com.example.trnhxunnam.pshopmyclone.Model.Cart;
import com.example.trnhxunnam.pshopmyclone.Model.DonHang;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Presenter.LoadProductPresenter;
import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.CropImagetoAnimation.CricleAnimationUntil;
import com.example.trnhxunnam.pshopmyclone.View.ActivityFullInforProduct.InforProductActivity;

import java.util.ArrayList;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.content.Context.MODE_PRIVATE;

@SuppressLint("ValidFragment")
public class FaceViewPagerFragment extends BaseFragment<LoadProductPresenter> implements LoadProductContract.LPView,SwipeRefreshLayout.OnRefreshListener,ItemClickmove {

    final static String BUNDLE_PRODUCT = "datafull";
    final static String BUNDLE_ID = "iddata";
    final static String BUNDLE_FRAGMENT_COLOR = "keycolordanhmuc";
    ItemClickmove itemClickmove;
    ArrayList<Product> arrayList;
    @BindView(R.id.gr_main) RecyclerView recyclerView;
    //RecyclerView recyclerView;
    AdapterRecyclerGridMain adapter_gr_main2;
    static TextView text_cart;
    static RelativeLayout cartlayout;
    static Activity activity;
    @BindView(R.id.swipe_fragment_viewpager_face) SwipeRefreshLayout swipe;
    //SwipeRefreshLayout swipe;
    int positionmain;
    int itemCounter = 0;
    int add = 0;

    @SuppressLint("ValidFragment")
    public FaceViewPagerFragment( TextView textView_cart, RelativeLayout cartlayout,Activity activity){

        this.text_cart = textView_cart;
        this.cartlayout = cartlayout;
        this.activity = activity;
        arrayList = new ArrayList<>();
    }

    public static FaceViewPagerFragment newinstance(int position) {

        FaceViewPagerFragment fragmentIN = new FaceViewPagerFragment(text_cart,cartlayout,activity);
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_FRAGMENT_COLOR, position);
        //bundle.putString("keytext",name);
        fragmentIN.setArguments(bundle);
        return fragmentIN;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        itemClickmove = this;
        View view = inflater.inflate(R.layout.item_fragment_viewpager_face,container,false);
        ButterKnife.bind(this,view);
        //swipe = (SwipeRefreshLayout) view.findViewById(R.id.swipe_fragment_viewpager_face);
        //recyclerView = (RecyclerView) view.findViewById(R.id.gr_main);
        setSwipe();

        Bundle bundle = getArguments();

        if (bundle != null) {

            int position = bundle.getInt("keycolordanhmuc");
            positionmain = position;
            switch (position){
                case 0: {
                    getPresenter().load("light01");
                }break;
                case 1:{
                    getPresenter().load("light02");

                } break;
                case 2:{
                    getPresenter().load("light03");

                } break;
                case 3: {

                    getPresenter().load("light04");
                }break;
                case 4: {
                    getPresenter().load("light05");

                }break;
            }

            Log.e("position: ", "" + position);
        }
        return view;
    }

    @Override
    public void loadProcess(ArrayList<Product> arrayListp) {
        this.arrayList = arrayListp;
        //Log.e("SIZELOAD",""+arrayListp.size());
        adapter_gr_main2 = new AdapterRecyclerGridMain(itemClickmove,getContext(),arrayListp,cartlayout,getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adapter_gr_main2);
        swipe.setRefreshing(false);
    }

    @Override
    public void loadFail() {
//        Toast.makeText(getContext(), "Error data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSumCartProcess(int value) {
        itemCounter = value;
    }

    @Override
    public void onRefresh() {
        swipe.setRefreshing(true);
                switch (positionmain){
                    case 0: {
                        getPresenter().load("light01");
                    }break;
                    case 1:{
                        getPresenter().load("light02");

                    } break;
                    case 2:{
                        getPresenter().load("light03");

                    } break;
                    case 3: {

                        getPresenter().load("light04");
                    }break;
                    case 4: {
                        getPresenter().load("light05");

                    }break;
                }
    }

    public void onsw(){
        swipe.setRefreshing(true);
    }

    public void offsw(){
        swipe.setRefreshing(false);
    }

    public void setSwipe(){
        swipe.setColorSchemeColors(Color.BLUE);
        swipe.setOnRefreshListener(this);
    }

    public void makeFlyAnimation(ImageView targetView) {

        new CricleAnimationUntil().attachActivity(getActivity()).setTargetView(targetView).setMoveDuration(100).setDestView(cartlayout).setAnimationListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                getPresenter().getSumCart();
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
    public void addItemToCart() {
        text_cart.setText(String.valueOf(itemCounter));

    }

//    public void setAnimation(int value){
//        SharedPreferences sharedPreferences = getContext().getSharedPreferences("animation", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putInt("booblean",value);
//        editor.apply();
//    }

    @Override
    public void click(final int i, AdapterRecyclerGridMain.ViewHolderPay viewHolder, final ImageView image_move) {
        viewHolder.setItemClickListeneradd(new ItemClickListenerUp() {
            @Override
            public void onClickup(ImageView imageView, int position, boolean isLongClick) {
                add = 0;
                add++;
                getPresenter().addToCart(add,arrayList.get(i));
                if (image_move != null){
                    makeFlyAnimation(image_move);
                }
            }
        });

        viewHolder.setItemClickListener(new ItemClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClickView(View view, int position, boolean isLongClick) {
                ImageView imageView = (ImageView) view.findViewById(R.id.image_item_grid_main);
                Intent intent = new Intent(getContext(), InforProductActivity.class);
                intent.putExtra(BUNDLE_PRODUCT, arrayList);
                intent.putExtra(BUNDLE_ID, position);
                ActivityOptionsCompat optionsCompat = null;

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) getContext(), imageView, imageView.getTransitionName());
                }

                activity.startActivity(intent, optionsCompat.toBundle());
            }
        });
    }

    @Override
    public void click1(AdapterRecyclerHistoryPay.ViewHolderHistoryPay viewHolder, ArrayList<DonHang> arrayList) {

    }

    @Override
    public void click2(AdapterSearchView.ViewHolder viewHolder, ImageView imageView_move) {

    }

    @Override
    public void click3(AdapterRecyclerStandard.ViewHolder01 viewHolder, ArrayList<Product> arrayList, TextView textView3, TextView textView4) {

    }


}
