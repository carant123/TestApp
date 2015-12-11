package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Carlos Antonio on 10/11/2015.
 */
public class SimpleBrowser extends Activity implements View.OnClickListener {

    EditText url;
    WebView ourBrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simplebrowser);

        ourBrow = (WebView) findViewById(R.id.wvBrowser);

        //activa la configuracion de paginas con javascript
        ourBrow.getSettings().setJavaScriptEnabled(true);
        //webview con zoom
        ourBrow.getSettings().setLoadWithOverviewMode(true);
        // calidad de escritorio
        ourBrow.getSettings().setUseWideViewPort(true);
        //establece que los cambios de url o al presionar un boton se hara por el Webview
        ourBrow.setWebViewClient(new ourViewClient());
        try {
            ourBrow.loadUrl("http://www.mybringback.com");
        }catch (Exception e){
            e.printStackTrace();
        }
        Button go = (Button) findViewById(R.id.bGo);
        Button back = (Button) findViewById(R.id.bBack);
        Button refresh = (Button) findViewById(R.id.bRefresh);
        Button foward = (Button) findViewById(R.id.bFoward);
        Button clearHistory = (Button) findViewById(R.id.bHistory);
        url = (EditText) findViewById(R.id.etURL);

        go.setOnClickListener(this);
        back.setOnClickListener(this);
        refresh.setOnClickListener(this);
        foward.setOnClickListener(this);
        clearHistory.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bGo:
                String theWebsite = url.getText().toString();
                ourBrow.loadUrl(theWebsite);
                //hide the keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(url.getWindowToken(),0);
                break;
            case R.id.bBack:
                if(ourBrow.canGoBack())
                ourBrow.goBack();
                break;
            case R.id.bRefresh:
                ourBrow.reload();
                break;
            case R.id.bFoward:
                if(ourBrow.canGoForward())
                ourBrow.goForward();
                break;
            case R.id.bHistory:
                ourBrow.clearHistory();
                break;

        }
    }
}
