package com.example.trnhxunnam.pshopmyclone.View.FragmentHome;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterViewPagerMain;
import com.example.trnhxunnam.pshopmyclone.R;

import java.util.ArrayList;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class ProductNavigationFragment extends Fragment {

    @BindView(R.id.viewpager_product_main) ViewPager viewPager;
    //ViewPager viewPager;
    ArrayList<String> arrayList;
    TextView text_cart;
    RelativeLayout relativeLayout_cart;
    Activity activity;
    int index;
    @SuppressLint("ValidFragment")

    public ProductNavigationFragment(TextView text_cart, RelativeLayout cartlayout,Activity activity) {
        this.text_cart = text_cart;
        this.relativeLayout_cart = cartlayout;
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.item_fragment_navigation_product, container, false);
        ButterKnife.bind(this,view);
        //viewPager = (ViewPager) view.findViewById(R.id.viewpager_product_main) ;
        setViewPager();
        return view;
    }

    public void setViewPager() {
        //Toast.makeText(getContext(), "LoadsetViewPager", Toast.LENGTH_LONG).show();
        AdapterViewPagerMain AdapterViewPagerMain = new AdapterViewPagerMain(getActivity().getSupportFragmentManager(),text_cart,relativeLayout_cart,activity);
        viewPager.setAdapter(AdapterViewPagerMain);
        //viewPager.setOffscreenPageLimit(3);
        //viewPager.setCurrentItem(3,true);
        /*
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                index = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        */
    }
}
