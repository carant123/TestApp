package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Carlos Antonio on 17/11/2015.
 */
public class HttpExample extends Activity{

    TextView httpStuff;
    HttpClient client;

    final static String URL = "http://api.twitter.com/1/statuses/user_timeline.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.httpex);
        httpStuff = (TextView) findViewById(R.id.tvHttp);

        //client = new DefaultHttpClient();
        GetMethodEx test = new GetMethodEx();
        String returned;
        try{
            returned = test.getInternetData();
            httpStuff.setText(returned);

        }catch (Exception e){
            e.printStackTrace();
            //Toast.makeText(HttpExample.this, "No cargo", Toast.LENGTH_SHORT).show();
        }

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            // Operaciones http
            Toast.makeText(HttpExample.this, "Cargo", Toast.LENGTH_SHORT).show();

        } else {
            // Mostrar errores
            Toast.makeText(HttpExample.this, "No cargo", Toast.LENGTH_SHORT).show();
        }



    }
        /*public JSONObject lastTweet(String username) throws ClientProtocolException, IOException, JSONException{
            StringBuilder url = new StringBuilder(URL);
            HttpGet get = new HttpGet(url.toString());

        }*/

    }

