package com.example.trnhxunnam.pshopmyclone.View.FragmentRssInNewFeed;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebViewClient;
import com.example.trnhxunnam.pshopmyclone.R;
import com.example.trnhxunnam.pshopmyclone.View.ActivityMain.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    @BindView(R.id.web_view_newfeed) android.webkit.WebView webView;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        ButterKnife.bind(this);
        getIntentData();
        setWebView(url);

    }

    public void getIntentData(){
        Intent intent = getIntent();
        url = intent.getStringExtra("linkrss");
    }

    public void setWebView(String url){
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {

        //super.onBackPressed();
        Intent intent = new Intent(WebViewActivity.this,MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
