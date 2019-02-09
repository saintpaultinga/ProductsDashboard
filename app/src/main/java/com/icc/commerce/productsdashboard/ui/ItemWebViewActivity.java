package com.icc.commerce.productsdashboard.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.icc.commerce.productsdashboard.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class ItemWebViewActivity extends AppCompatActivity {
    @BindView(R.id.webView)
    WebView mWebView;


    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_webwiew_layout);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            final String url = bundle.getString("url");
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadUrl(url);
        } else {
            Timber.d("Bundle was null!");
        }
    }
}
