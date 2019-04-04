package com.example.trnhxunnam.pshopmyclone.View.ActivityThanhtoan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.trnhxunnam.pshopmyclone.Base.BaseActivity;
import com.example.trnhxunnam.pshopmyclone.Contract.LoadUserContract;
import com.example.trnhxunnam.pshopmyclone.Model.UserFull;
import com.example.trnhxunnam.pshopmyclone.Presenter.LoadUserPresenter;
import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.APIUntilsDonHang;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.APIUntilsLogin;
import com.example.trnhxunnam.pshopmyclone.Retrofit2.DataClient;

import java.util.ArrayList;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Callback;
import retrofit2.Response;

public class EditUserActivity extends BaseActivity<LoadUserPresenter> implements LoadUserContract.LUView {

    @BindView(R.id.toolbar_MYEDITUSER) Toolbar toolbar;
    @BindView(R.id.btnsave_edituser) Button btn_editUser;
    @BindView(R.id.editname_edituser) EditText edit_name;
    @BindView(R.id.edittaikhoan_edituser) EditText edit_taikhoan;
    @BindView(R.id.editdiachi_edituser) EditText edit_diachi;
    @BindView(R.id.menu_myedituser_left) ImageView back;
    String gettaikhoanofshareperence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edituser);

        ButterKnife.bind(this);
        getPresenter().getTaiKhoanOfShareperence(getApplicationContext());
        getPresenter().loadUser(gettaikhoanofshareperence);
        setToolbar();
    }

    public void setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
    }

    @OnClick(R.id.btnsave_edituser)
    public void setClickToButton() {

        String taikhoan = edit_taikhoan.getText().toString();
        String name = edit_name.getText().toString();
        String diachi = edit_diachi.getText().toString();
        getPresenter().updateUser(taikhoan, name, diachi);
    }

    @OnClick(R.id.menu_myedituser_left)
    public void setBack() {
        Intent intent1 = new Intent(EditUserActivity.this, MyPayActivity.class);
        startActivity(intent1);
    }

    @Override
    public void loadProcess(ArrayList<UserFull> arrayList) {

        String name = arrayList.get(0).getName();
        String taikhoan = arrayList.get(0).getTaikhoan();
        String diachi = arrayList.get(0).getDiachi();

        setData(name, taikhoan, diachi);
    }

    public void setData(String name, String taikhoan, String diachi) {
        edit_name.setText("" + name);
        edit_diachi.setText("" + diachi);
        edit_taikhoan.setText("" + taikhoan);
    }

    @Override
    public void loadFail() {

    }

    @Override
    public void updateUserProcess() {
        Intent intent1 = new Intent(EditUserActivity.this, MyPayActivity.class);
        startActivity(intent1);
    }

    @Override
    public void getValueLoginProcess(int value) {

    }

    @Override
    public void getTaiKhoanOfShareperenceProcess(String taikhoan) {
        gettaikhoanofshareperence = taikhoan;
    }
}
