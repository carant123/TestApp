package com.example.carlosantonio.myapplication;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Carlos Antonio on 10/11/2015.
 */
public class ourViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }
}
