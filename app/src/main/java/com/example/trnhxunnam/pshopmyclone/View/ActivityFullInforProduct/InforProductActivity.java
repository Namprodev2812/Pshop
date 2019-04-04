package com.example.trnhxunnam.pshopmyclone.View.ActivityFullInforProduct;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.trnhxunnam.pshopmyclone.Base.BaseActivity;
import com.example.trnhxunnam.pshopmyclone.Contract.InforProductACContract;
import com.example.trnhxunnam.pshopmyclone.Model.Cart;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Presenter.InforProductACPresenter;
import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.View.ActivityGiohang.MyProductActivity;
import com.example.trnhxunnam.pshopmyclone.View.ActivityMain.MainActivity;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InforProductActivity extends BaseActivity<InforProductACPresenter> implements InforProductACContract.LSView {

    @BindView(R.id.toolbar_inforproduct) Toolbar toolbar;
    @BindView(R.id.menu_inforproduct_left) ImageView image_Viewback;
    @BindView(R.id.menu_inforproduct_shopping) ImageView image_shopping;
    @BindView(R.id.imagefull_inforproduct) ImageView image_full;
    @BindView(R.id.btn_inforproduct_add) Button btn_add;
    @BindView(R.id.name_inforproduct) TextView text_name;
    @BindView(R.id.price_inforproduct) TextView text_price;
    @BindView(R.id.body_inforproduct) TextView text_body;
    @BindView(R.id.kind_inforproduct) TextView text_kind;
    ArrayList<Product> arrayList;
    int stt;
    private int add = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inforproduct);
        ButterKnife.bind(this);
        setToolbar();
        getdata();
        setData();
    }

    public void setToolbar() {
        setSupportActionBar(toolbar);
    }

    public void getdata() {
        // arrayList = new ArrayList<>();
        Intent intent = getIntent();
        arrayList = intent.getParcelableArrayListExtra("datafull");
        stt = intent.getIntExtra("iddata", -1);

        for (int i = 0; i < arrayList.size(); i++)
            Log.e("linkimage ", "" + arrayList.get(i).getImage());
    }

    public void setData() {

        Glide.with(getApplicationContext()).load(arrayList.get(stt).getImage()).into(image_full);
        text_name.setText(arrayList.get(stt).getName());
        text_price.setText("" + formatMoney(arrayList.get(stt).getPrice()) + " VNĐ");
        text_body.setText(arrayList.get(stt).getBody());
        text_kind.setText(arrayList.get(stt).getKind());

    }

    @OnClick(R.id.menu_inforproduct_left)
    public void setClickBack() {
        Intent intent = new Intent(InforProductActivity.this, MainActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.menu_inforproduct_shopping)
    public void setClickShopping(){
        Intent intent = new Intent(InforProductActivity.this, MyProductActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.btn_inforproduct_add)
    public void setClickButtonAdd() {
        add = 0;
        add++;

        getPresenter().addToCart(add, arrayList.get(stt));
        Toast.makeText(InforProductActivity.this, "add success", Toast.LENGTH_SHORT).show();
    }


    // set back in control
    @Override
    public void onBackPressed() {

        //super.onBackPressed();
        Intent intent = new Intent(InforProductActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    // convert double sang don vi tien te
    public String formatMoney(Double money) {
        NumberFormat format = new DecimalFormat("#,###");
        String abc = format.format(money);
        return abc;
    }

}
