package com.example.trnhxunnam.pshopmyclone.View.ActivityProfile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.trnhxunnam.pshopmyclone.Contract.LoadUserContract;
import com.example.trnhxunnam.pshopmyclone.Model.UserFull;
import com.example.trnhxunnam.pshopmyclone.Presenter.LoadUserPresenter;
import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.APIUntilsLogin;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.DataClient;
import com.example.trnhxunnam.pshopmyclone.View.AcivityLogin.MyProfileActivity;
import com.example.trnhxunnam.pshopmyclone.View.ActivityMain.MainActivity;

import java.util.ArrayList;
import java.util.List;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyUserActivity extends BaseActivity<LoadUserPresenter> implements LoadUserContract.LUView {

    @BindView(R.id.toolbar_myUSER) Toolbar toolbar;
    @BindView(R.id.image_myuser) ImageView img_avarta;
    @BindView(R.id.menu_myuser_left) ImageView img_back;
    @BindView(R.id.btn_myuser_delete) Button btn_delete;
    @BindView(R.id.btn_myuser_logout) Button btn_logout;
    @BindView(R.id.textview_account_myuser) TextView tv_account;
    @BindView(R.id.textview_password_myuser) TextView tv_pass;
    @BindView(R.id.textview_email_myuser) TextView tv_mail;
    @BindView(R.id.textview_name_account_myuser) TextView tv_name;
    @BindView(R.id.textview_diachi_myuser) TextView tv_diachi;
    String taikhoan;

    int getvalueLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_user);

        ButterKnife.bind(this);
        setToolbar();
        getPresenter().getValueLogin(this);
        if(getvalueLogin == 0) {
            getTaiKhoanOfMyProfile();
        }else {
            getPresenter().getTaiKhoanOfShareperence(getApplicationContext());
        }

        connectToServer();
        setLogout();
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
    }

    private void setInfor(UserFull userfull){
        Glide.with(getApplicationContext()).load(userfull.getHinhanh()).into(img_avarta);
        tv_account.setText("" + userfull.getTaikhoan());
        tv_mail.setText("" + userfull.getEmail());
        tv_name.setText(""+userfull.getName());
        tv_diachi.setText(""+userfull.getDiachi());
    }

    @Override
    public void loadProcess(ArrayList<UserFull> arrayList) {
        setInfor(arrayList.get(0));
        getPresenter().setValueLoginFull(this,arrayList.get(0));
    }

    @Override
    public void loadFail() {
        Toast.makeText(this, "Vui lòng điền đầy đủ thông tin user", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateUserProcess() {

    }

    @Override
    public void getValueLoginProcess(int value) {
        getvalueLogin = value;
    }

    @Override
    public void getTaiKhoanOfShareperenceProcess(String taikhoan) {
        this.taikhoan = taikhoan;
    }

    private void getTaiKhoanOfMyProfile(){
        Intent intent = getIntent();
        taikhoan =  intent.getStringExtra("taikhoanprofile");
    }

    public void connectToServer(){
        getPresenter().loadUser(taikhoan);
    }

    @OnClick(R.id.btn_myuser_delete)
    public void setDelete(){
        Intent intent = new Intent(MyUserActivity.this,MainActivity.class);
        startActivity(intent);
    }
    @OnClick(R.id.menu_myuser_left)
    public void setExit(){
        Intent intent = new Intent(MyUserActivity.this, MainActivity.class);
        startActivity(intent);
        getPresenter().setValueLogin(getApplicationContext(),1);
    }

    @OnClick(R.id.btn_myuser_logout)
    public void setLogout(){
        Intent intent = new Intent(MyUserActivity.this,MyProfileActivity.class);
        startActivity(intent);
        getPresenter().setValueLogin(getApplicationContext(),0);
    }
    @Override
    public void onBackPressed() {

        //super.onBackPressed();
        Intent intent = new Intent(MyUserActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}
