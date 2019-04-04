package com.example.trnhxunnam.pshopmyclone.View.ActivityNotification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.View.ActivityMain.MainActivity;

public class MyNotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mynotification);
    }
    @Override
    public void onBackPressed() {

        //super.onBackPressed();
        Intent intent = new Intent(MyNotificationActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
