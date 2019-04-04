package com.example.trnhxunnam.pshopmyclone.View.FragmentHome;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.trnhxunnam.pshopmyclone.R;
import com.google.zxing.Result;

//import butterknife.Bind;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRCodeNavigationFragment extends Fragment implements ZXingScannerView.ResultHandler {

    @BindView(R.id.scannerView) ZXingScannerView mScannerView;

    public QRCodeNavigationFragment() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_fragment_navigation_qrcode, container, false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {

        final String myResult = result.getText();
        if(myResult.equals("")){
            mScannerView.resumeCameraPreview(this);
        }else {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(myResult));
            startActivity(browserIntent);
        }
    }
}
