package com.example.trnhxunnam.pshopmyclone.Presenter;

import android.util.Log;

import com.example.trnhxunnam.pshopmyclone.Base.BasePresenter;
import com.example.trnhxunnam.pshopmyclone.Contract.LoadProductContract;
import com.example.trnhxunnam.pshopmyclone.Model.Cart;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.APIUntils;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.DataClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoadProductPresenter extends BasePresenter<LoadProductContract.LPView> implements LoadProductContract.LPPresenter {


    @Override
    public void load(String kind) {
        data(kind);
    }

    @Override
    public void addToCart(int add,Product product) {
            addToCartNow(add,product);
    }

    @Override
    public void getSumCart() {
        getView().getSumCartProcess(getSumCartNow());
    }

    private void data(String kind) {

        Log.e("createDATA", "your name: " + kind);

        DataClient dataClient = APIUntils.getdata();
        Call<List<Product>> listCall = dataClient.loadp(kind);

        listCall.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                ArrayList<Product> mangsanpham = new ArrayList<Product>();
                mangsanpham.addAll(response.body());
                Log.e("onResLoaddata", "" + response.body().size());
                getView().loadProcess(mangsanpham);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("ERRORLOADDATA", "" + t.getMessage());
                getView().loadFail();
            }
        });
    }
    private void addToCartNow( int add,Product product) {
        int oldValue = 0;
        if(Cart.getInstance().getCart().get(product) == null){
            Cart.getInstance().addProduct(product,0);
        }else {
            oldValue = Cart.getInstance().getCart().get(product);
        }
        Cart.getInstance().addProduct(product,add+oldValue);
    }
    private int getSumCartNow(){
        int sum =0;
        for(Product product: Cart.getInstance().getCart().keySet()){
            int value = Cart.getInstance().getCart().get(product);
            sum =  sum+value;
        }
        return sum;
    }
}
