package com.example.trnhxunnam.pshopmyclone.View.ActivityGiohang;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.trnhxunnam.pshopmyclone.Base.BaseActivity;
import com.example.trnhxunnam.pshopmyclone.Contract.MyProductContract;
import com.example.trnhxunnam.pshopmyclone.Model.Cart;
import com.example.trnhxunnam.pshopmyclone.Model.Product;
import com.example.trnhxunnam.pshopmyclone.Presenter.MyProductPresenter;
import com.example.trnhxunnam.pshopmyclone.View.ActivityMain.MainActivity;
import com.example.trnhxunnam.pshopmyclone.View.ActivitySplashApp.SplashMainActivity;
import com.example.trnhxunnam.pshopmyclone.View.ActivityThanhtoan.MyPayActivity;
import com.example.trnhxunnam.pshopmyclone.View.FragmentHome.QRCodeNavigationFragment;
import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.View.FragmentProductHome.StandardMyProductFragment;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyProductActivity extends BaseActivity<MyProductPresenter> implements MyProductContract.LSView,BottomNavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar_MYPRODUCT) Toolbar toolbar;
    @BindView(R.id.menu_myproduct_left) ImageView image_left;
    @BindView(R.id.menu_myproduct_delete) ImageView image_delete;
    @BindView(R.id.navigation_MYPRODUCT) BottomNavigationView bottomNavigationView;
    Fragment fragmentfull = null;
    int getsumcart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_product);

        ButterKnife.bind(this);
        hideMenu();
        setToolbar();
        setNavigationBar();
        setAutoNavigationBar();
    }

    public void setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
    }

    @OnClick(R.id.menu_myproduct_left)
    public void setBackMain() {
        Intent intent1 = new Intent(MyProductActivity.this, MainActivity.class);
        startActivity(intent1);
    }

    public void setNavigationBar() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.getMenu().getItem(0).setCheckable(false);
    }

    public void setAutoNavigationBar() {
        fragmentfull = new StandardMyProductFragment(image_delete);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_MYPRODUCT, fragmentfull).commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.na_myproduct_search: {

                Intent intent = new Intent(MyProductActivity.this,SplashMainActivity.class);
                startActivity(intent);
            }
            break;
            case R.id.na_myproduct_qcode: {

                fragmentfull = new QRCodeNavigationFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_MYPRODUCT, fragmentfull).commit();
            }
            break;
            case R.id.na_myproduct_cash: {

                int feel = getValueLogin();
                if(feel == 1){
                    getPresenter().getSumCart();
                    if(getsumcart > 0){
                    Intent intent = new Intent(MyProductActivity.this,MyPayActivity.class);
                    startActivity(intent);
                    }else{
                        Toast.makeText(this, "vui lòng thêm sản phẩm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(this, "Bạn phải đăng nhập để thực hiện thao tác này ", Toast.LENGTH_SHORT).show();
                }
            }
            break;
        }
        return true;
    }

    public void hideMenu(){
        image_delete.setVisibility(View.GONE);
    }

    @OnClick(R.id.menu_myproduct_delete)
    public void setDelete(){
        showAlertDialog();
    }

    public void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete");
        builder.setMessage("Bạn có thực sự muốn xóa giỏ hàng ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Chấp nhận", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                getPresenter().clearAllCart();
                setAutoNavigationBar();
            }
        });
        builder.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private int getValueLogin() {
        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        return sharedPreferences.getInt("feel", 0);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
       Intent intent = new Intent(MyProductActivity.this,MainActivity.class);
       intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
       startActivity(intent);
    }

    @Override
    public void getSumCartProcess(int value) {
        getsumcart = value;
    }
}
