package com.example.trnhxunnam.pshopmyclone.View.FragmentRssInNewFeed;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerNewfeed;
import com.example.trnhxunnam.pshopmyclone.Base.BaseFragment;
import com.example.trnhxunnam.pshopmyclone.Contract.LoadRSSContract;
import com.example.trnhxunnam.pshopmyclone.Model.Infor;
import com.example.trnhxunnam.pshopmyclone.Model.Infor2;
import com.example.trnhxunnam.pshopmyclone.Presenter.LoadRSSPresenter;
import com.example.trnhxunnam.pshopmyclone.R;

import java.util.ArrayList;
import java.util.UUID;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class NewfeedViewPagerFragment extends BaseFragment<LoadRSSPresenter> implements LoadRSSContract.LRSSView {

    final static  String BUNDLE_LINK_RSS = "linkrss";
    ArrayList<Infor> arrayList;
    @BindView(R.id.linearlayout_fragment_viewpager_newfeed) LinearLayout linearLayout ;
    @BindView(R.id.gr_newfeed) GridView gridView;
    AdapterRecyclerNewfeed AdapterRecyclerNewfeed;
    @BindView(R.id.processbar_frame_newfeed) ProgressBar progressBar;
    String link_RSS_suckhoedoisong = "https://www.24h.com.vn/upload/rss/suckhoedoisong.rss";
    String link_RSS_tintuc = "https://www.24h.com.vn/upload/rss/tintuctrongngay.rss";
    String link_RSS_dulich = "https://www.24h.com.vn/upload/rss/dulich24h.rss";
    String link_RSS_cuoi24h = "https://www.24h.com.vn/upload/rss/cuoi24h.rss";
    int loadFail;

    public NewfeedViewPagerFragment(){

        arrayList = new ArrayList<>();
    }

    public static NewfeedViewPagerFragment newinstance(int position) {

        NewfeedViewPagerFragment fragmentIN = new NewfeedViewPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("keycolordanhmuc1", position);
        //bundle.putString("keytext",name);
        fragmentIN.setArguments(bundle);
        return fragmentIN;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getContext(), "create view", Toast.LENGTH_SHORT).show();
        View view = inflater.inflate(R.layout.item_fragment_viewpager_newfeed,container,false);
        ButterKnife.bind(this,view);
        setClickGridView();
        Bundle bundle = getArguments();

        if (bundle != null) {

            int position = bundle.getInt("keycolordanhmuc1");

            switch (position){
                case 0: {
                    getPresenter().load(link_RSS_suckhoedoisong,getContext());
                }break;
                case 1:{
                    getPresenter().load(link_RSS_tintuc,getContext());

                } break;
                case 2:{
                    getPresenter().load(link_RSS_dulich,getContext());

                } break;
                case 3: {

                    getPresenter().load(link_RSS_cuoi24h,getContext());
                }break;
            }

            Log.e("position: ", "" + position);
        }
        return view;
    }

    @Override
    public void loadProcess(ArrayList<Infor> arrayListp) {
        this.arrayList = arrayListp;

//        Toast.makeText(getContext(), "Load", Toast.LENGTH_SHORT).show();
        AdapterRecyclerNewfeed = new AdapterRecyclerNewfeed(getContext(),arrayListp);
        gridView.setAdapter(AdapterRecyclerNewfeed);

        progressBar.setIndeterminate(false);
        progressBar.setVisibility(View.GONE);
        loadFail = 0;
    }

    @Override
    public void loadFail(ArrayList<Infor> arrayList) {

        if (arrayList.size() == 0){
            progressBar.setIndeterminate(false);
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(), "error data", Toast.LENGTH_SHORT).show();
        }else {
            progressBar.setIndeterminate(false);
            progressBar.setVisibility(View.GONE);

            this.arrayList = arrayList;
            AdapterRecyclerNewfeed = new AdapterRecyclerNewfeed(getContext(),this.arrayList);
            gridView.setAdapter(AdapterRecyclerNewfeed);
        }

        loadFail = 1;
    }

    public void setClickGridView(){

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(loadFail == 0) {
                    Intent intent = new Intent(getContext(), WebViewActivity.class);
                    intent.putExtra(BUNDLE_LINK_RSS, arrayList.get(position).getLink());
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "Vui long ket noi internet", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPresenter().closeRealm();
    }
}
