package com.example.trnhxunnam.pshopmyclone.View.ActivityThanhtoan;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.View.ActivityMain.MainActivity;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyThanhtoanActivity extends AppCompatActivity{

    @BindView(R.id.toolbar_MYDONHANG) Toolbar toolbar;
    @BindView(R.id.menu_mydonhang_left) ImageView image_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mythanhtoan);

        ButterKnife.bind(this);
        setToolbar();
    }

    public void setToolbar(){
        setSupportActionBar(toolbar);
        toolbar.showOverflowMenu();
    }

    @OnClick(R.id.menu_mydonhang_left)
    public void setClickBack(){
        Intent intent1 = new Intent(MyThanhtoanActivity.this, MainActivity.class);
        startActivity(intent1);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        Intent intent = new Intent(MyThanhtoanActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
