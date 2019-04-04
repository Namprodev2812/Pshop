package com.example.trnhxunnam.pshopmyclone.View.FragmentHome;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterViewPagerMain;
import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterViewPagerNewfeed;
import com.example.trnhxunnam.pshopmyclone.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class NewFeedNavigationFragment extends Fragment {

    @BindView(R.id.viewpager_newfeed) ViewPager viewpager;

    public NewFeedNavigationFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.item_fragment_navigation_newfeed,container,false);
        ButterKnife.bind(this,view);
        setViewPager();
        return view;
    }

    public void setViewPager() {
        AdapterViewPagerNewfeed AdapterViewPagerNewfeed = new AdapterViewPagerNewfeed(getActivity().getSupportFragmentManager());
        viewpager.setAdapter(AdapterViewPagerNewfeed);
        //viewpager.setOffscreenPageLimit(4);
    }
}
