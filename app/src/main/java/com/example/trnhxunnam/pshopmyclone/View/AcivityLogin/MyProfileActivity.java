package com.example.trnhxunnam.pshopmyclone.View.AcivityLogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.trnhxunnam.pshopmyclone.Base.BaseActivity;
import com.example.trnhxunnam.pshopmyclone.Contract.LoginContract;
import com.example.trnhxunnam.pshopmyclone.Model.User;
import com.example.trnhxunnam.pshopmyclone.Presenter.LoginPresenter;
import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.View.ActivityMain.MainActivity;
import com.example.trnhxunnam.pshopmyclone.View.ActivityProfile.MyUserActivity;

import java.util.ArrayList;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyProfileActivity extends BaseActivity<LoginPresenter> implements LoginContract.LGView {

    final static String BUNDLE_USER ="taikhoanprofile";
    @BindView(R.id.menu_myprofile_left) ImageView image_left;
    @BindView(R.id.toolbar_myPROFILE) Toolbar toolbar;
    @BindView(R.id.btn_profile_login) Button btn_login;
    @BindView(R.id.edit_profile_mobile) EditText edit_mobile;
    @BindView(R.id.edit_profile_pass) EditText edit_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        ButterKnife.bind(this);
        setToolbar();
    }

    public void setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
    }

    @OnClick(R.id.menu_myprofile_left)
    public void setButtonclick(){
        Intent intent = new Intent(MyProfileActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.btn_profile_login)
    public  void setClickLogin(){
        String mobile = edit_mobile.getText().toString();
        String pass   = edit_pass.getText().toString();

        if(mobile.equals("") && pass.equals("")){
            Toast.makeText(MyProfileActivity.this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else {
            //Toast.makeText(MyProfile.this, "sendata", Toast.LENGTH_SHORT).show();
            getPresenter().login(mobile,pass);
        }

    }

    @Override
    public void loginSuccess(ArrayList<User> arrayList) {

        String taikhoan = arrayList.get(0).getTaiKhoan();
        Intent intent = new Intent(MyProfileActivity.this,MyUserActivity.class);
        intent.putExtra(BUNDLE_USER,taikhoan);
        startActivity(intent);
    }

    @Override
    public void loginFailPass() {
        Toast.makeText(this, "Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginFail() {
        Toast.makeText(this, "Error code", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {

        //super.onBackPressed();
        Intent intent = new Intent(MyProfileActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
