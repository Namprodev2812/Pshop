package com.example.trnhxunnam.pshopmyclone.View.FragmentHome;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterSearchView;
import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerGridMain;
import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerHistoryPay;
import com.example.trnhxunnam.pshopmyclone.Adapter.AdapterRecyclerStandard;
import com.example.trnhxunnam.pshopmyclone.Adapter.ItemClickListener;
import com.example.trnhxunnam.pshopmyclone.Adapter.ItemClickmove;
import com.example.trnhxunnam.pshopmyclone.Base.BaseFragment;
import com.example.trnhxunnam.pshopmyclone.Contract.LoadDonHangContract;
import com.example.trnhxunnam.pshopmyclone.Model.DonHang;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Model.Timesave;
import com.example.trnhxunnam.pshopmyclone.Presenter.LoadDonHangPresenter;
import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.View.Activitydonhangfull.DonhangfullActivity;

import java.util.ArrayList;
import java.util.Calendar;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

@SuppressLint("ValidFragment")
public class PayNavigationFragment extends BaseFragment<LoadDonHangPresenter> implements Animation.AnimationListener, LoadDonHangContract.LDHView, SwipeRefreshLayout.OnRefreshListener, ItemClickmove {

    final static String BUNDLE_MADONHANG= "madonhang";
    ItemClickmove itemClickmove;
    @BindView(R.id.swipe_fragment_historydonhang) SwipeRefreshLayout swipe;
    ArrayList<DonHang> arrayDonHang;
    AdapterRecyclerHistoryPay AdapterRecyclerHistoryPay;
    @BindView(R.id.recyclerview_fragment_pay) RecyclerView recyclerView;
    @BindView(R.id.btn_fragment_pay_search_timepickerleft) Button btn_time_left;
    @BindView(R.id.btn_fragment_pay_search_timepickerright) Button btn_time_right;
    @BindView(R.id.btn_fragment_pay_search) Button btn_time_search;
    @BindView(R.id.btn_playlist_time) ImageButton imageButton;
    Calendar c;
    int month, year, dayleft, dayright, dayleftsearch, dayrightsearch, monthleftsearch, monthrightsearch, yearleftsearch, yearrightsearch;
    DatePickerDialog dpd;
    @BindView(R.id.ll_time_paymain) LinearLayout linearLayout;
    Animation animationdown, animationup;
    @BindView(R.id.btn_fragment_pay_search_all) TextView text_all;
    @BindView(R.id.btn_fragment_pay_search_toweek) TextView text_toweek;
    @BindView(R.id.btn_fragment_pay_search_beforeweek) TextView text_beforeweek;
    @BindView(R.id.btn_fragment_pay_search_tomonth) TextView text_tomonth;
    @BindView(R.id.text_time_pay) TextView text_time_header;
    @BindView(R.id.notification_fragment_history_pay) TextView text_notification;
    int i = 0;

    @SuppressLint("ValidFragment")

    public PayNavigationFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        itemClickmove = this;
        View view = inflater.inflate(R.layout.item_fragment_navigation_pay, container, false);

        ButterKnife.bind(this,view);
        animation(view);

        setSwipe();

        // getTime - setTime The frist
        c = Calendar.getInstance();
        dayleft = 1;
        dayright = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH) + 1;
        year = c.get(Calendar.YEAR);

        getPresenter().setShareperence(getContext(),new Timesave(month,year,dayleft,dayright));
        btn_time_left.setText(getTimeNow(dayleft, month, year));
        btn_time_right.setText(getTimeNow(dayright, month, year));

        text_time_header.setText(getTimeNow(dayleft, month, year) + " - " + getTimeNow(dayright, month, year));
        //

        // set RecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        arrayDonHang = new ArrayList<>();
        AdapterRecyclerHistoryPay = new AdapterRecyclerHistoryPay(itemClickmove, getContext(), arrayDonHang);
        recyclerView.setAdapter(AdapterRecyclerHistoryPay);

        getPresenter().loadMonth(getTimeNowToDataBase(dayleft, month, year), getTimeNowToDataBase(dayright, month, year));
        setColor(text_tomonth);
        return view;
    }

    public void animation(View view) {

        animationdown = AnimationUtils.loadAnimation(getContext(), R.anim.slide_down);
        animationup = AnimationUtils.loadAnimation(getContext(), R.anim.slide_up);

    }

    public void setSwipe() {
        swipe.setColorSchemeColors(Color.BLUE);
        swipe.setOnRefreshListener(this);
    }

    @OnClick(R.id.btn_playlist_time)
    public void setClickToShow() {
        linearLayout.setVisibility(View.VISIBLE);

        if (i == 0) {

            setClickShow();
        } else {
            setClickHide();
        }
    }

    public void setClickShow() {
        linearLayout.startAnimation(animationdown);
        recyclerView.setVisibility(View.GONE);
        swipe.setVisibility(View.GONE);
        i = 1;
        //setcolor();
    }

    public void setClickHide() {
        linearLayout.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        swipe.setVisibility(View.VISIBLE);
        //imageView.startAnimation(animationup);
        i = 0;
    }

    @Override
    public void onAnimationStart(Animation animation) {
        Log.e("onAnimationStart", "");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Log.e("onAnimationEnd", "");
        if (animation == this.animationup) {
            linearLayout.setVisibility(View.GONE);
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        Log.e("onAnimationRepeat", "");
    }


    @SuppressLint("NewApi")
    public void setclicktotextblue(TextView textView) {
        textView.setTextColor(Color.parseColor("#FFFFFF"));
        textView.setBackground(getContext().getResources().getDrawable(R.drawable.borderblue));
    }

    @OnClick({R.id.btn_fragment_pay_search_all,
            R.id.btn_fragment_pay_search_toweek,R
            .id.btn_fragment_pay_search_beforeweek,
            R.id.btn_fragment_pay_search_tomonth,
            R.id.btn_fragment_pay_search_timepickerleft,
            R.id.btn_fragment_pay_search_timepickerright})
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_fragment_pay_search_all:
                btn_time_search.setBackgroundResource(R.drawable.border2);
                btn_time_search.setEnabled(false);
                Toast.makeText(getContext(), "click", Toast.LENGTH_SHORT).show();
                setclicktotextblue(text_all);
                setclicktotextgray(text_toweek);
                setclicktotextgray(text_beforeweek);
                setclicktotextgray(text_tomonth);

                dayleft = c.get(Calendar.DAY_OF_MONTH);
                dayright = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH) + 1;
                year = c.get(Calendar.YEAR);

                getPresenter().setShareperence(getContext(),new Timesave(month,year,dayleft,dayright));
                btn_time_left.setText("");
                btn_time_right.setText("");
                text_time_header.setText("");

                getPresenter().loadAll();
                setClickHide();

                break;

            case R.id.btn_fragment_pay_search_toweek:
                btn_time_search.setBackgroundResource(R.drawable.border2);
                btn_time_search.setEnabled(false);
                setClickHide();
                setclicktotextblue(text_toweek);
                setclicktotextgray(text_all);
                setclicktotextgray(text_beforeweek);
                setclicktotextgray(text_tomonth);

                dayright = c.get(Calendar.DAY_OF_MONTH);
                int stt = c.get(Calendar.DAY_OF_WEEK);
                dayleft = dayright - (stt - 2);
                month = c.get(Calendar.MONTH) + 1;
                year = c.get(Calendar.YEAR);

                getPresenter().setShareperence(getContext(),new Timesave(month,year,dayleft,dayright));
                btn_time_left.setText(getTimeNow(dayleft, month, year));
                btn_time_right.setText(getTimeNow(dayright, month, year));
                text_time_header.setText(getTimeNow(dayleft, month, year) + " - " + getTimeNow(dayright, month, year));
                getPresenter().loadMonth(getTimeNowToDataBase(dayleft, month, year), getTimeNowToDataBase(dayright, month, year));
                break;


            case R.id.btn_fragment_pay_search_beforeweek:
                btn_time_search.setBackgroundResource(R.drawable.border2);
                btn_time_search.setEnabled(false);
                setClickHide();
                setclicktotextblue(text_beforeweek);
                setclicktotextgray(text_all);
                setclicktotextgray(text_toweek);
                setclicktotextgray(text_tomonth);


                int stt2 = c.get(Calendar.DAY_OF_WEEK);
                int myday = c.get(Calendar.DAY_OF_MONTH);
                dayright = myday - stt2 + 1;
                dayleft = dayright - 6;
                month = c.get(Calendar.MONTH) + 1;
                int month2 = month;
                year = c.get(Calendar.YEAR);
                int year2 = year;
                if (dayleft == 0) {
                    dayleft = 31;
                    btn_time_right.setText(getTimeNow(dayright, month, year));
                    month = month - 1;
                    if (month == 0) {
                        month = 12;
                        year = year - 1;
                        btn_time_left.setText(getTimeNow(dayleft, month, year));
                    }
                }

                getPresenter().setShareperence(getContext(),new Timesave(month,year,dayleft,dayright));
                btn_time_right.setText(getTimeNow(dayright, month, year));
                btn_time_left.setText(getTimeNow(dayleft, month, year));
                text_time_header.setText(getTimeNow(dayleft, month, year) + " - " + getTimeNow(dayright, month2, year2));
                getPresenter().loadMonth(getTimeNowToDataBase(dayleft, month, year), getTimeNowToDataBase(dayright, month, year));
                break;


            case R.id.btn_fragment_pay_search_tomonth:
                btn_time_search.setBackgroundResource(R.drawable.border2);
                btn_time_search.setEnabled(false);
                setClickHide();
                // set color
                setclicktotextblue(text_tomonth);
                setclicktotextgray(text_toweek);
                setclicktotextgray(text_beforeweek);
                setclicktotextgray(text_all);
                //

                dayleft = 1;
                dayright = c.get(Calendar.DAY_OF_MONTH);
                month = c.get(Calendar.MONTH) + 1;
                year = c.get(Calendar.YEAR);

                getPresenter().setShareperence(getContext(),new Timesave(month,year,dayleft,dayright));
                btn_time_left.setText(getTimeNow(dayleft, month, year));
                btn_time_right.setText(getTimeNow(dayright, month, year));

                text_time_header.setText(getTimeNow(dayleft, month, year) + " - " + getTimeNow(dayright, month, year));
                getPresenter().loadMonth(getTimeNowToDataBase(dayleft, month, year), getTimeNowToDataBase(dayright, month, year));
                break;


            case R.id.btn_fragment_pay_search_timepickerleft:
                setclicktotextgray(text_tomonth);
                setclicktotextgray(text_toweek);
                setclicktotextgray(text_beforeweek);
                setclicktotextgray(text_all);
                btn_time_search.setEnabled(true);
                btn_time_search.setBackgroundResource(R.drawable.borderblue);
                getPresenter().getShareperence(getContext());
                showDatePickerLeft(year, month, dayleft);
                break;

            case R.id.btn_fragment_pay_search_timepickerright:
                setclicktotextgray(text_tomonth);
                setclicktotextgray(text_toweek);
                setclicktotextgray(text_beforeweek);
                setclicktotextgray(text_all);
                btn_time_search.setEnabled(true);
                btn_time_search.setBackgroundResource(R.drawable.borderblue);
                getPresenter().getShareperence(getContext());
                showDatePickerRight(year, month, dayright);
                break;
            case R.id.btn_fragment_pay_search:
                setClickHide();
                // set color
                setclicktotextgray(text_tomonth);
                setclicktotextgray(text_toweek);
                setclicktotextgray(text_beforeweek);
                setclicktotextgray(text_all);
                Log.e("TIMESEARCH", "" + dayleftsearch + "-" + monthleftsearch + "-" + yearleftsearch + "-----" + dayrightsearch + "-" + monthrightsearch + "-" + yearrightsearch);

                if (dayleftsearch == 0 && monthleftsearch == 0 && yearleftsearch == 0) {
                    dayleftsearch = dayleft;
                    monthleftsearch = month;
                    yearleftsearch = year;
                } else if (dayrightsearch == 0 && monthrightsearch == 0 && yearrightsearch == 0) {
                    dayrightsearch = dayright;
                    monthrightsearch = month;
                    yearrightsearch = year;
                }
                getPresenter().loadMonth(getTimeNowToDataBase(dayleftsearch, monthleftsearch, yearleftsearch), getTimeNowToDataBase(dayrightsearch, monthrightsearch, yearrightsearch));
                break;
        }
    }

    @SuppressLint("NewApi")
    public void setclicktotextgray(TextView textView) {
        textView.setTextColor(Color.BLACK);
        textView.setBackground(getContext().getResources().getDrawable(R.drawable.border2));
    }

    public void showDatePickerLeft(int year1, final int month1, int day1) {

        dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                btn_time_left.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                text_time_header.setText(getTimeNow(mDay, mMonth + 1, mYear) + " - " + getTimeNow(dayright, month, year));
                dayleft = mDay;
                month = mMonth + 1;
                year = mYear;
                dayleftsearch = mDay;
                monthleftsearch = mMonth + 1;
                yearleftsearch = mYear;

                getPresenter().setShareperence(getContext(),new Timesave(month,year,dayleft,dayright));
            }
        }, year1, month1 - 1, day1);
        Log.e("", "" + day1 + "/" + month1 + "/" + year1);
        dpd.show();
    }

    public void showDatePickerRight(int year1, final int month1, int day1) {

        dpd = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                btn_time_right.setText(mDay + "/" + (mMonth + 1) + "/" + mYear);
                text_time_header.setText(getTimeNow(dayleft, month, year) + " - " + getTimeNow(mDay, mMonth + 1, mYear));
                dayright = mDay;
                month = mMonth + 1;
                year = mYear;
                dayrightsearch = mDay;
                monthrightsearch = mMonth + 1;
                yearrightsearch = mYear;

                getPresenter().setShareperence(getContext(),new Timesave(month,year,dayleft,dayright));
            }

        }, year1, month1 - 1, day1);
        dpd.show();
    }

    public String getTimeNow(int day, int month, int year) {
        //Log.e("Timenow", "" + day + "/" + month + "/" + year);
        String time = "" + day + "/" + month + "/" + year;
        return time;
    }

    public String getTimeNowToDataBase(int day, int month, int year) {
        String time = "" + year + "-" + month + "-" + day;
        return time;
    }

    public void setColor(TextView textView) {
        setclicktotextblue(textView);
        setclicktotextgray(text_toweek);
        setclicktotextgray(text_beforeweek);
        setclicktotextgray(text_all);
    }


    @Override
    public void loadProcess(ArrayList<DonHang> arrayList) {

        arrayDonHang.clear();
        arrayDonHang.addAll(arrayList);
        Log.e("arrayDonHang", "" + arrayList.get(0).getTimem());
        AdapterRecyclerHistoryPay.notifyDataSetChanged();
        text_notification.setVisibility(View.GONE);
        swipe.setRefreshing(false);
    }

    @Override
    public void loadFail() {
        arrayDonHang.clear();
        AdapterRecyclerHistoryPay.notifyDataSetChanged();
        text_notification.setVisibility(View.VISIBLE);
    }

    @Override
    public void getShareperenceProcess(Timesave timesave) {

        month = timesave.getMonth();
        year  = timesave.getYear();
        dayleft = timesave.getDayleft();
        dayright = timesave.getDayright();
    }

    @Override
    public void onRefresh() {

        swipe.setRefreshing(true);
        getPresenter().loadMonth(getTimeNowToDataBase(dayleft, month, year), getTimeNowToDataBase(dayright, month, year));
    }

    @Override
    public void click(int i, AdapterRecyclerGridMain.ViewHolderPay viewHolder, ImageView image_move) {

    }

    @Override
    public void click1(AdapterRecyclerHistoryPay.ViewHolderHistoryPay viewHolder, final ArrayList<DonHang> arrayList) {

        viewHolder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClickView(View view, int position, boolean isLongClick) {

                Intent intent = new Intent(getContext(), DonhangfullActivity.class);
                intent.putExtra(BUNDLE_MADONHANG, arrayList.get(i).getMadonhang());
                //Log.e("madonhangsend",arrayList.get(position).getMadonhang());
                getContext().startActivity(intent);
            }
        });
    }

    @Override
    public void click2(AdapterSearchView.ViewHolder viewHolder, ImageView imageView_move) {

    }

    @Override
    public void click3(AdapterRecyclerStandard.ViewHolder01 viewHolder, ArrayList<Product> arrayList, TextView textView3, TextView textView4) {

    }
}
