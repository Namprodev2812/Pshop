package com.example.trnhxunnam.pshopmyclone.Adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.trnhxunnam.pshopmyclone.View.FragmentProductInHome.FaceViewPagerFragment;

public class AdapterViewPagerMain extends FragmentStatePagerAdapter {


    TextView textView_cart;
    RelativeLayout cartlayout;
    Activity activity;
    public AdapterViewPagerMain(FragmentManager fm, TextView textView_cart, RelativeLayout cartlayout,Activity activity) {
            super(fm);
        this.textView_cart = textView_cart;
        this.cartlayout = cartlayout;
        this.activity = activity;
    }

    @Override
    public Fragment getItem(int i) {

        FaceViewPagerFragment fragment_viewPager_face = new FaceViewPagerFragment(textView_cart,cartlayout,activity);
        if (i == 0) {
            return fragment_viewPager_face.newinstance(i);
        } else if (i == 1) {
            return fragment_viewPager_face.newinstance(i);
        } else if (i == 2) {
            return fragment_viewPager_face.newinstance(i);
        } else if (i == 3) {
            return fragment_viewPager_face.newinstance(i);
        } else if (i == 4) {
            return fragment_viewPager_face.newinstance(i);
        } else {
            Log.e("getItemAdapter2", "" + i);
        }
        return null;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return 0;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {

            case 0:
                return "Đèn Trần";
            case 1:
                return "Đèn Đứng";
            case 2:
                return "Đèn Bàn";
            case 3:
                return "Đèn Led";
            case 4:
                return "Light Concept";
        }
        return null;
    }
}
