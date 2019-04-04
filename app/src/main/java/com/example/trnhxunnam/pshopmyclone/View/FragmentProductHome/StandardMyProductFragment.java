package com.example.trnhxunnam.pshopmyclone.View.FragmentProductHome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterSearchView;
import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerGridMain;
import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerHistoryPay;
import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerStandard;
import com.example.trnhxunnam.pshopmyclone.Adapter.ItemClickListener;
import com.example.trnhxunnam.pshopmyclone.Adapter.ItemClickListenerDown;
import com.example.trnhxunnam.pshopmyclone.Adapter.ItemClickListenerUp;
import com.example.trnhxunnam.pshopmyclone.Adapter.ItemClickmove;
import com.example.trnhxunnam.pshopmyclone.AdapterTouchHelperInGH.RecyclerItemTouchHelper;
import com.example.trnhxunnam.pshopmyclone.AdapterTouchHelperInGH.RecyclerItemTouchHelperListener;
import com.example.trnhxunnam.pshopmyclone.Base.BaseFragment;
import com.example.trnhxunnam.pshopmyclone.Contract.FragmentStandContract;
import com.example.trnhxunnam.pshopmyclone.Model.Cart;
import com.example.trnhxunnam.pshopmyclone.Model.DonHang;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Model.UserinShareperence;
import com.example.trnhxunnam.pshopmyclone.Presenter.FragmentStandPresenter;
import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.View.AcivityLogin.MyProfileActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("ValidFragment")
public class StandardMyProductFragment extends BaseFragment<FragmentStandPresenter> implements FragmentStandContract.LSView,RecyclerItemTouchHelperListener, ItemClickmove {

    ArrayList<Product> getArrayCart;
    ItemClickmove itemClickmove;
    @BindView(R.id.coordinator_main) CoordinatorLayout coordinatorLayout;
    @BindView(R.id.recyclerview_standard) RecyclerView recyclerView;
    ArrayList<Product> arrayList;

    @BindView(R.id.text_login_myproduct) TextView tv_login;
    @BindView(R.id.textsum_myproduct) TextView textsum_myproduct;
    UserinShareperence userinShareperence;
    ImageView image_login;
    @BindView(R.id.image_login_myproduct) ImageView image_avarta;
    AdapterRecyclerStandard AdapterRecyclerStandard;
    LinearLayoutManager linearLayoutManager;

    double aoneprice, aprice,getsumpricecart;
    int avalue,getvalueLogin;


    @SuppressLint("ValidFragment")
    public StandardMyProductFragment(ImageView imageView) {
        this.image_login = imageView;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        itemClickmove = this;
        View view = inflater.inflate(R.layout.item_fragment_standard, container, false);
        ButterKnife.bind(this,view);
        setViewLogin();
        arrayList = new ArrayList<>();
        loadcart();

        return view;
    }


    public void loadcart() {
        getPresenter().getArrayCart();
        arrayList = getArrayCart;
        image_login.setVisibility(View.VISIBLE);
        linearLayoutManager = new LinearLayoutManager(getContext());
        AdapterRecyclerStandard = new AdapterRecyclerStandard(itemClickmove, getContext(), arrayList);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(AdapterRecyclerStandard);

        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView);
        savesumheader();
    }

    @Override
    public void onSwiper(RecyclerView.ViewHolder viewHolder, int direction, final int position) {

        if (viewHolder instanceof AdapterRecyclerStandard.ViewHolder01) {
            getPresenter().deleteOneInCartSwiper(arrayList.get(viewHolder.getAdapterPosition()));
            savesumheader();
            reload();
        }
    }

    @OnClick(R.id.text_login_myproduct)
    public void setClickLogin() {
        Intent intent = new Intent(getContext(), MyProfileActivity.class);
        startActivity(intent);
    }

    public void setViewLogin() {
        getPresenter().getValueLogin(getContext());
        int feel = getvalueLogin;
//        Log.e("fellvalue:",""+feel);
        if (feel == 1) {
            getPresenter().getTaiKhoanOfShareperence(getContext());
            tv_login.setText("" + userinShareperence.getTaikhoan());
            Glide.with(getContext()).load(userinShareperence.getUrl()).into(image_avarta);
            Log.e("imageavartaurl",""+userinShareperence.getUrl());
        } else {

        }
    }

    public String formatmoney(Double money) {
        NumberFormat format = new DecimalFormat("#,###");
        String abc = format.format(money);
        return abc;
    }

    public void savesumheader() {
        getPresenter().getSumPriceCart();
        textsum_myproduct.setText(formatmoney(getsumpricecart) + " VNĐ");
    }

    @Override
    public void click(int i, AdapterRecyclerGridMain.ViewHolderPay viewHolder, ImageView image_move) {

    }

    @Override
    public void click1(AdapterRecyclerHistoryPay.ViewHolderHistoryPay viewHolder, ArrayList<DonHang> arrayList) {

    }

    @Override
    public void click2(AdapterSearchView.ViewHolder viewHolder, ImageView imageView_move) {

    }

    @Override
    public void click3(final AdapterRecyclerStandard.ViewHolder01 viewHolder, final ArrayList<Product> arrayList1, final TextView textView3, final TextView textView4) {

        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClickView(View view, int position, boolean isLongClick) {
                Toast.makeText(getContext(), "" + position, Toast.LENGTH_SHORT).show();
            }
        });

        viewHolder.setItemClickListenerup(new ItemClickListenerUp() {
            @Override
            public void onClickup(ImageView imageView, int position, boolean isLongClick) {
                //Log.d("positionup",""+position);
                getPresenter().addOneToCart(arrayList1.get(position));
                aoneprice = arrayList1.get(position).getPrice();
                getPresenter().getValueOfProduct(arrayList1.get(position));

                textView4.setText("" + avalue);
                aprice = avalue * aoneprice;
                textView3.setText("Tổng: " + formatmoney(aoneprice * avalue) + "VNĐ");

                savesumheader();
            }
        });

        viewHolder.setItemClickListenerdown(new ItemClickListenerDown() {
            @Override
            public void onClickdown(ImageView imageView, int position, boolean isLongClick) {

                getPresenter().removeInCart(arrayList1.get(position));
                getPresenter().getValueOfProduct(arrayList1.get(position));
                aoneprice = arrayList1.get(position).getPrice();
                aprice = avalue * aoneprice;

                if (avalue >= 1) {

                    textView4.setText("" + avalue);
                    textView3.setText("Tổng: " + formatmoney(aprice) + "VNĐ");

                    savesumheader();

                } else {
                    getPresenter().deleteOneInCart(arrayList1.get(position));
                    reload();
                }
            }
        });
    }

    public void reload() {
        arrayList.clear();
        getPresenter().getArrayCart();
        arrayList.addAll(getArrayCart);
        AdapterRecyclerStandard.notifyDataSetChanged();
        savesumheader();
    }

    @Override
    public void getSumPriceProcess(double sumprice) {
        getsumpricecart = sumprice;
    }

    @Override
    public void getValueOfProduct(int value) {
        avalue = value;
    }

    @Override
    public void getArrayCartProcess(ArrayList<Product> arrayList) {
        getArrayCart = new ArrayList<>();
        getArrayCart.addAll(arrayList) ;
    }

    @Override
    public void getValueLoginProcess1(int value) {
        getvalueLogin = value;
        //Log.e("fellvalue:",""+getvalueLogin);
    }

    @Override
    public void getTaiKhoanOfShareperenceProcess(UserinShareperence userinShareperence) {
        this.userinShareperence = userinShareperence;
    }
}
