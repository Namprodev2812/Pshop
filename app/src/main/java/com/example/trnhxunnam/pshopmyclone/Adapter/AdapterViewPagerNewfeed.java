package com.example.trnhxunnam.pshopmyclone.Adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import com.example.trnhxunnam.pshopmyclone.View.FragmentRssInNewFeed.NewfeedViewPagerFragment;

//public class AdapterViewPagerNewfeed extends FragmentStatePagerAdapter {
public class AdapterViewPagerNewfeed extends FragmentPagerAdapter {

    public AdapterViewPagerNewfeed(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {

        NewfeedViewPagerFragment fragment_viewpager_newfeed = new NewfeedViewPagerFragment();
        if (i == 0) {
            return fragment_viewpager_newfeed.newinstance(i);
        } else if (i == 1) {
            return fragment_viewpager_newfeed.newinstance(i);
        } else if (i == 2) {
            return fragment_viewpager_newfeed.newinstance(i);
        } else if (i == 3) {
            return fragment_viewpager_newfeed.newinstance(i);
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
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {

            case 0:
                return "Đời sống";
            case 1:
                return "Tin tức";
            case 2:
                return "Du lịch";
            case 3:
                return "Cười 24h";
        }
        return null;
    }
}
