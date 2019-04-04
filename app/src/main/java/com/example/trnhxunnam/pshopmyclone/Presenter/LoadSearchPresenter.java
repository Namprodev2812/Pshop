package com.example.trnhxunnam.pshopmyclone.Presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.trnhxunnam.pshopmyclone.Base.BasePresenter;
import com.example.trnhxunnam.pshopmyclone.Contract.LoadSearchContract;
import com.example.trnhxunnam.pshopmyclone.Model.Cart;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.APIUntils;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadSearchPresenter extends BasePresenter<LoadSearchContract.LSView> implements LoadSearchContract.LSPresenter {


    @Override
    public void load() {
        data();
    }

    @Override
    public void getSumCart() {
        getView().getSumCartProcess(getsumcartnow());
    }

    @Override
    public void addToCart( int add, Product product) {
        addtoCartnow(add,product);
    }

    private void data() {

        DataClient dataClient = APIUntils.getdata();
        Call<List<Product>> listCall = dataClient.loadSearch("null");

        listCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                ArrayList<Product> mangsanpham = new ArrayList<Product>();
                mangsanpham.addAll(response.body());
                Log.e("onResLoaddata", ""+response.body().size());
                if (mangsanpham.size() > 0) {
                    for (int i = 0; i < mangsanpham.size(); i++) {
                        Log.e("BBBLOADDATA", "ID: " + mangsanpham.get(i).getID());
                        Log.e("BBBLOADDATA", "Name: " + mangsanpham.get(i).getName());
                        Log.e("BBBLOADDATA", "Avarta :" + mangsanpham.get(i).getImage());
                        Log.e("BBBLOADDATA", "Body :" + mangsanpham.get(i).getBody());
                    }
                }

                getView().loadProcess(mangsanpham);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("ERRORLOADDATA", "" + t.getMessage());
                //presenter.ldfail();
                getView().loadFail();
            }
        });
    }
    private int getsumcartnow() {
        int sum = 0;
        for (Product product : Cart.getInstance().getCart().keySet()) {

            int value = Cart.getInstance().getCart().get(product);
            sum = sum + value;
        }
        return sum;
    }
    private void addtoCartnow( int add,Product product) {

        int oldValue = 0;
        if (Cart.getInstance().getCart().get(product) == null) {
            Cart.getInstance().addProduct(product, 0);
        } else {
            oldValue = Cart.getInstance().getCart().get(product);
        }
        Cart.getInstance().addProduct(product, add + oldValue);
    }
}
