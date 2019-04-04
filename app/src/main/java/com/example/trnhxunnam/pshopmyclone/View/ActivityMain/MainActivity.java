package com.example.trnhxunnam.pshopmyclone.View.ActivityMain;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.trnhxunnam.pshopmyclone.Base.BaseActivity;
import com.example.trnhxunnam.pshopmyclone.Contract.ActivityShareprenceContract;
import com.example.trnhxunnam.pshopmyclone.Model.AutoLoadNavigationBar;
import com.example.trnhxunnam.pshopmyclone.Presenter.ActivityShareprence;
import com.example.trnhxunnam.pshopmyclone.View.AcivityLogin.MyProfileActivity;
import com.example.trnhxunnam.pshopmyclone.View.ActivityGiohang.MyProductActivity;
import com.example.trnhxunnam.pshopmyclone.View.ActivityNotification.MyNotificationActivity;
import com.example.trnhxunnam.pshopmyclone.View.ActivityProfile.MyUserActivity;
import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.View.FragmentHome.NewFeedNavigationFragment;
import com.example.trnhxunnam.pshopmyclone.View.FragmentHome.PayNavigationFragment;
import com.example.trnhxunnam.pshopmyclone.View.FragmentHome.ProductNavigationFragment;
import com.example.trnhxunnam.pshopmyclone.View.FragmentHome.QRCodeNavigationFragment;
import com.example.trnhxunnam.pshopmyclone.View.FragmentHome.SearchNavigationFragment;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<ActivityShareprence> implements ActivityShareprenceContract.LSView, BottomNavigationView.OnNavigationItemSelectedListener {

    Activity activity;
    int setmenu = 0;
    int backinandroid = 0;
    int getsumcart, getValueLogin;
    @BindView(R.id.cartRelativeLayout) RelativeLayout cartRelativeLayout;
    @BindView(R.id.text_toolbar_main) TextView text_toolbar;
    @BindView(R.id.textNotify) TextView text_cart;
    @BindView(R.id.toolbarmain) Toolbar toolbar;
    @BindView(R.id.menuImage) ImageView imageView;
    @BindView(R.id.cart_main) ImageView imageView_cart;  // you can hide it in part "new feed"
    @BindView(R.id.navigationmain) BottomNavigationView bottomNavigationView;
    Fragment fragmentfull = null;
    MenuItem item1, item2;
    Menu menu;
    int index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        activity = this;
        setToolbar();
        setAutoNavigationBar();
        setNavigationBar();
    }

    public void setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
    }

    @OnClick(R.id.menuImage)
    public void goToProfile() {
        getPresenter().getValueLogin(getApplicationContext());
        int feel = getValueLogin;
        if (feel == 1) {
            Intent intent = new Intent(MainActivity.this, MyUserActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, MyProfileActivity.class);
            startActivity(intent);
        }
    }

    @OnClick(R.id.cart_main)
    public void goToCart() {
        Intent intent = new Intent(MainActivity.this, MyProductActivity.class);
        startActivity(intent);
    }

    public void setNavigationBar() {
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    public void setAutoNavigationBar() {
        index = AutoLoadNavigationBar.getInstance().getINDEX();
        if (index == 0) {
            setTextToolBar("Sản phẩm");
            getPresenter().getSoLuongGioHang();
            text_cart.setText(getsumcart + "");
            fragmentfull = new ProductNavigationFragment(text_cart, cartRelativeLayout, activity);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentmain, fragmentfull).commit();
        } else if (index == 2) {
            getPresenter().setOffApp(getApplicationContext(), 0);
            setTextToolBar("Đơn hàng");
            getPresenter().getSoLuongGioHang();
            text_cart.setText(getsumcart + "");
            fragmentfull = new PayNavigationFragment();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentmain, fragmentfull).commit();
            bottomNavigationView.getMenu().getItem(1).setChecked(true);   // checked: danh cho item 1-n // checkable: item 0 default
            AutoLoadNavigationBar.getInstance().setINDEX(0);
        } else if (index == 3) {
            getPresenter().setOffApp(getApplicationContext(), 0);
            setTextToolBar("Tìm kiếm sản phẩm");
            getPresenter().getSoLuongGioHang();
            text_cart.setText(getsumcart + "");
            fragmentfull = new SearchNavigationFragment(text_cart, cartRelativeLayout);
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentmain, fragmentfull).commit();
            //hideinpartsearch();
            bottomNavigationView.getMenu().getItem(3).setChecked(true);
            AutoLoadNavigationBar.getInstance().setINDEX(0);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.na_product: {
                getPresenter().setOffApp(getApplicationContext(), 0);
                showMenu();
                //setbackmain();
                setTextToolBar("Sản phẩm");
                getPresenter().getSoLuongGioHang();
                text_cart.setText(getsumcart + "");
                fragmentfull = new ProductNavigationFragment(text_cart, cartRelativeLayout, activity);
                AutoLoadNavigationBar.getInstance().setINDEX(0);
            }
            break;
            case R.id.na_pay: {
                getPresenter().setOffApp(getApplicationContext(), 0);
                setTextToolBar("Đơn hàng");
                getPresenter().getSoLuongGioHang();
                text_cart.setText(getsumcart + "");
                fragmentfull = new PayNavigationFragment();
                AutoLoadNavigationBar.getInstance().setINDEX(2);
            }
            break;
            case R.id.na_qrcode: {
                getPresenter().setOffApp(getApplicationContext(), 0);
                setTextToolBar("Scan");

                fragmentfull = new QRCodeNavigationFragment();
            }
            break;
            case R.id.na_search: {
                getPresenter().setOffApp(getApplicationContext(), 0);
                setTextToolBar("Tìm kiếm sản phẩm");
                getPresenter().getSoLuongGioHang();
                text_cart.setText(getsumcart + "");
                fragmentfull = new SearchNavigationFragment(text_cart, cartRelativeLayout);
                hideInPartSearch();
                AutoLoadNavigationBar.getInstance().setINDEX(3);
            }
            break;
            case R.id.na_newfeed: {
                getPresenter().setOffApp(getApplicationContext(), 0);
                hideInPartNewFeed();
                setTextToolBar("Tin tức- Khuyến mãi");
                fragmentfull = new NewFeedNavigationFragment();
            }
            break;
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentmain, fragmentfull).commit();

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.item_menu_main, menu);
        this.menu = menu;

        if (setmenu == 1) {
            hideInPartSearch();
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_notification:
                getPresenter().getValueLogin(getApplicationContext());
                int feel = getValueLogin;
                if (feel == 1) {
                    Intent intent1 = new Intent(MainActivity.this, MyNotificationActivity.class);
                    startActivity(intent1);
                } else {
                    Toast.makeText(this, "Bạn phải đăng nhập để thực hiện thao tác này ", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void setTextToolBar(String title) {
        text_toolbar.setText(title);
    }

    public void hideInPartNewFeed() {
        imageView.setVisibility(View.GONE);

        //item1 = menu.findItem(R.id.menu_shopping);
        imageView_cart.setVisibility(View.GONE);
        item2 = menu.findItem(R.id.menu_notification);
        imageView_cart.setVisibility(View.GONE);
        text_cart.setVisibility(View.GONE);
        //item1.setVisible(false);
        item2.setVisible(false);
    }

    public void hideInPartSearch() {
        imageView.setVisibility(View.GONE);
        // item1 = menu.findItem(R.id.menu_shopping);
        //imageView_cart.setVisibility(View.VISIBLE);
        item2 = menu.findItem(R.id.menu_notification);

        //item1.setVisible(true);
        imageView_cart.setVisibility(View.VISIBLE);
        text_cart.setVisibility(View.VISIBLE);
        item2.setVisible(false);
    }

    public void showMenu() {
        imageView.setVisibility(View.VISIBLE);
        //item1 = menu.findItem(R.id.menu_shopping);
        item2 = menu.findItem(R.id.menu_notification);
        imageView_cart.setVisibility(View.VISIBLE);
        text_cart.setVisibility(View.VISIBLE);
        //item1.setVisible(true);
        item2.setVisible(true);
    }

    @Override
    public void onBackPressed() {
        getPresenter().getOffApp(getApplicationContext());

        if (backinandroid == 0) {
            Toast.makeText(this, "Nhấn tiếp tục một lần nữa để thoát ứng dụng", Toast.LENGTH_SHORT).show();
            getPresenter().setOffApp(getApplicationContext(), 1);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void getSoLuongGioHangProcess(int valueSum) {
        getsumcart = valueSum;
    }

    @Override
    public void getOffAppProcess(int value) {
        backinandroid = value;
    }

    @Override
    public void getValueLoginProcess(int value) {
        getValueLogin = value;
    }
}
