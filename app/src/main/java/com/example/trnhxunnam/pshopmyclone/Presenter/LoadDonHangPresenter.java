package com.example.trnhxunnam.pshopmyclone.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.trnhxunnam.pshopmyclone.Base.BasePresenter;
import com.example.trnhxunnam.pshopmyclone.Contract.LoadDonHangContract;
import com.example.trnhxunnam.pshopmyclone.Model.DonHang;
import com.example.trnhxunnam.pshopmyclone.Model.Timesave;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.APIUntilsDonHang;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadDonHangPresenter extends BasePresenter<LoadDonHangContract.LDHView> implements LoadDonHangContract.LDHPresenter {

    final static String TP = "timepicker";
    final static String TP_DAYLEFT = "timedayleft";
    final static String TP_DAYRIGHT = "timedayright";
    final static String TP_MONTH = "timemonth";
    final static String TP_YEAR = "timeyear";


    private void dataMonth( String theFirst,String theSecond) {

        Log.e("timesetting",""+theFirst+"....."+theSecond);
        DataClient dataClient = APIUntilsDonHang.getdata();
        Call<List<DonHang>> listCall = dataClient.loaddonhangmonth(theFirst,theSecond);

        listCall.enqueue(new Callback<List<DonHang>>() {
            @Override
            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {

                ArrayList<DonHang> donHangArrayList = new ArrayList<DonHang>();
                donHangArrayList.addAll(response.body());
                Log.e("onResLoaddata", ""+response.body().size());
                if (donHangArrayList.size() > 0) {
                    for (int i = 0; i < donHangArrayList.size(); i++) {
//                        Log.e("BBBLOADDATA", "ID: " + donHangArrayList.get(i).getID());
//                        Log.e("BBBLOADDATA", "Image: " + donHangArrayList.get(i).getImage());
//                        Log.e("BBBLOADDATA", "Name :" + donHangArrayList.get(i).getName());
//                        Log.e("BBBLOADDATA", "price :" + donHangArrayList.get(i).getPrice());
//                        Log.e("BBBLOADDATA", "indexsum :" + donHangArrayList.get(i).getIndexsum());
//                        Log.e("BBBLOADDATA", "sumprice :" + donHangArrayList.get(i).getSumprice());
//                        Log.e("BBBLOADDATA", "time :" + donHangArrayList.get(i).getTimem());
                    }
                }

                getView().loadProcess(donHangArrayList);
            }

            @Override
            public void onFailure(Call<List<DonHang>> call, Throwable t) {
                Log.e("ERRORLOADDATA69", "" + t.getMessage());
                getView().loadFail();
            }
        });
    }

    private void dataWeek(String first,String thesecond) {

        DataClient dataClient = APIUntilsDonHang.getdata();
        Call<List<DonHang>> listCall = dataClient.loaddonhangmonth(first,thesecond);

        listCall.enqueue(new Callback<List<DonHang>>() {
            @Override
            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {

                ArrayList<DonHang> donHangArrayList = new ArrayList<DonHang>();
                donHangArrayList.addAll(response.body());
                Log.e("onResLoaddata", ""+response.body().size());
                if (donHangArrayList.size() > 0) {
                    for (int i = 0; i < donHangArrayList.size(); i++) {
//                        Log.e("BBBLOADDATA", "ID: " + donHangArrayList.get(i).getID());
//                        Log.e("BBBLOADDATA", "Image: " + donHangArrayList.get(i).getImage());
//                        Log.e("BBBLOADDATA", "Name :" + donHangArrayList.get(i).getName());
//                        Log.e("BBBLOADDATA", "price :" + donHangArrayList.get(i).getPrice());
//                        Log.e("BBBLOADDATA", "indexsum :" + donHangArrayList.get(i).getIndexsum());
//                        Log.e("BBBLOADDATA", "sumprice :" + donHangArrayList.get(i).getSumprice());
//                        Log.e("BBBLOADDATA", "time :" + donHangArrayList.get(i).getTimem());
                    }
                }

                getView().loadProcess(donHangArrayList);
            }

            @Override
            public void onFailure(Call<List<DonHang>> call, Throwable t) {
                Log.e("ERRORLOADDATA", "" + t.getMessage());
                getView().loadFail();
            }
        });
    }


    private void dataBeforeWeek(String first,String thesecond) {

        DataClient dataClient = APIUntilsDonHang.getdata();
        Call<List<DonHang>> listCall = dataClient.loaddonhangmonth(first,thesecond);

        listCall.enqueue(new Callback<List<DonHang>>() {
            @Override
            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {

                ArrayList<DonHang> donHangArrayList = new ArrayList<DonHang>();
                donHangArrayList.addAll(response.body());
                Log.e("onResLoaddata", ""+response.body().size());
                if (donHangArrayList.size() > 0) {
                    for (int i = 0; i < donHangArrayList.size(); i++) {
//                        Log.e("BBBLOADDATA", "ID: " + donHangArrayList.get(i).getID());
//                        Log.e("BBBLOADDATA", "Image: " + donHangArrayList.get(i).getImage());
//                        Log.e("BBBLOADDATA", "Name :" + donHangArrayList.get(i).getName());
//                        Log.e("BBBLOADDATA", "price :" + donHangArrayList.get(i).getPrice());
//                        Log.e("BBBLOADDATA", "indexsum :" + donHangArrayList.get(i).getIndexsum());
//                        Log.e("BBBLOADDATA", "sumprice :" + donHangArrayList.get(i).getSumprice());
//                        Log.e("BBBLOADDATA", "time :" + donHangArrayList.get(i).getTimem());
                    }
                }

                getView().loadProcess(donHangArrayList);
            }

            @Override
            public void onFailure(Call<List<DonHang>> call, Throwable t) {
                Log.e("ERRORLOADDATA", "" + t.getMessage());
                getView().loadFail();
            }
        });
    }

    private void dataAll() {

        DataClient dataClient = APIUntilsDonHang.getdata();
        Call<List<DonHang>> listCall = dataClient.loaddonhangall("null");

        listCall.enqueue(new Callback<List<DonHang>>() {
            @Override
            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {

                ArrayList<DonHang> donHangArrayList = new ArrayList<DonHang>();
                donHangArrayList.addAll(response.body());
                Log.e("onResLoaddata", ""+response.body().size());
                if (donHangArrayList.size() > 0) {
                    for (int i = 0; i < donHangArrayList.size(); i++) {
//                        Log.e("BBBLOADDATA", "ID: " + donHangArrayList.get(i).getID());
//                        Log.e("BBBLOADDATA", "Image: " + donHangArrayList.get(i).getImage());
//                        Log.e("BBBLOADDATA", "Name :" + donHangArrayList.get(i).getName());
//                        Log.e("BBBLOADDATA", "price :" + donHangArrayList.get(i).getPrice());
//                        Log.e("BBBLOADDATA", "indexsum :" + donHangArrayList.get(i).getIndexsum());
//                        Log.e("BBBLOADDATA", "sumprice :" + donHangArrayList.get(i).getSumprice());
//                        Log.e("BBBLOADDATA", "time :" + donHangArrayList.get(i).getTimem());
                    }
                }

                getView().loadProcess(donHangArrayList);
            }

            @Override
            public void onFailure(Call<List<DonHang>> call, Throwable t) {
                Log.e("ERRORLOADDATA", "" + t.getMessage());
                getView().loadFail();
            }
        });
    }

    private void setShareperenceNow(Context context,Timesave timeSave) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(TP_YEAR, timeSave.getYear());
        editor.putInt(TP_MONTH, timeSave.getMonth());
        editor.putInt(TP_DAYLEFT, timeSave.getDayleft());
        editor.putInt(TP_DAYRIGHT, timeSave.getDayright());
        editor.apply();
    }

    private Timesave getShareperenceNow(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(TP, Context.MODE_PRIVATE);

        int year = sharedPreferences.getInt(TP_YEAR, 0);
        int month = sharedPreferences.getInt(TP_MONTH, 0);
        int dayleft = sharedPreferences.getInt(TP_DAYLEFT, 0);
        int dayright = sharedPreferences.getInt(TP_DAYRIGHT, 0);
        return new Timesave(month,year,dayleft,dayright);
    }

    @Override
    public void loadMonth(String first,String second) {
        dataMonth(first,second);
    }

    @Override
    public void loadAll() {
            dataAll();
    }

    @Override
    public void loadWeek(String first,String thesecond) {
        dataWeek( first, thesecond);
    }

    @Override
    public void loadBeforeWeek(String first,String thesecond) {
        dataBeforeWeek(first, thesecond);
    }

    @Override
    public void setShareperence(Context context, Timesave timeSave) {
        setShareperenceNow(context,timeSave);
    }

    @Override
    public void getShareperence(Context context) {
        getView().getShareperenceProcess(getShareperenceNow(context));
    }
}
