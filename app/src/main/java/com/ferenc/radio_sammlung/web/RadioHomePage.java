package com.ferenc.radio_sammlung.web;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ferenc.radio_sammlung.R;

public class RadioHomePage extends AppCompatActivity {

    private String webLink;

    private WebView homePageView;
    private WebSettings webSettings;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {



        webLink = getIntent().getStringExtra("link");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_view);

        homePageView = findViewById(R.id.homePageView);
        webSettings = homePageView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        homePageView.loadUrl(webLink);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }
}
