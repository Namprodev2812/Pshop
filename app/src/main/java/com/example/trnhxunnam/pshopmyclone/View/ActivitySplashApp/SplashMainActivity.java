package com.example.trnhxunnam.pshopmyclone.View.ActivitySplashApp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.View.ActivityMain.MainActivity;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CAMERA;

public class SplashMainActivity extends AppCompatActivity {


    final int checkper = 69;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_main);

        if (checkAndRequestPermissions()) {
            goToMain();
        }
    }

    private boolean checkAndRequestPermissions() {

        int readPhoneStatePermissions = ContextCompat.checkSelfPermission(this, "android.permission.WRITE_EXTERNAL_STORAGE");
        int cameraPhoneStatePermissions = ContextCompat.checkSelfPermission(this, "android.permission.CAMERA");
        //int camera2PhoneStatePermissions = ContextCompat.checkSelfPermission(this, "android.hardware.camera");
        //int camera21PhoneStatePermissions = ContextCompat.checkSelfPermission(this, "android.hardware.camera.autofocus");

        List<String> listPermissionsNeeded = new ArrayList();


        if (readPhoneStatePermissions != 0) {
            listPermissionsNeeded.add("android.permission.WRITE_EXTERNAL_STORAGE");
        }

        if (cameraPhoneStatePermissions != 0) {
            listPermissionsNeeded.add("android.permission.CAMERA");
        }
        //if (camera2PhoneStatePermissions != 0) {
            //listPermissionsNeeded.add("android.hardware.camera");
       // }
        //if (camera21PhoneStatePermissions != 0) {
           // listPermissionsNeeded.add("android.hardware.camera.autofocus");
       // }

        if (listPermissionsNeeded.isEmpty()) {
            return true;
        }
        ActivityCompat.requestPermissions(this, (String[]) listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), checkper);
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        int permission = 0;
        switch (requestCode) {
            case checkper:
                for (int i = 0; i < grantResults.length; i++) {
                    switch (i) {
                        case 0:
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED ) {
                                permission ++;
                            } else {
                                finish();
                            }
                            break;

                        case 1:
                            if (grantResults[i] == PackageManager.PERMISSION_GRANTED ) {
                                goToMain();
                            } else {
                                finish();
                            }
                            break;

                        default:
                            break;
                    }
                }
                break;
        }
    }

    public void goToMain() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {

                } finally {
                    Intent intent = new Intent(SplashMainActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        thread.start();

    }
}
