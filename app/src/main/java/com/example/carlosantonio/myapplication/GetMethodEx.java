package com.example.carlosantonio.myapplication;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Carlos Antonio on 17/11/2015.
 */
public class GetMethodEx {

    public String getInternetData() throws Exception {
        BufferedReader in1 = null;
        String data = null;
        InputStream in;

        URL url = new URL("http://monstalkers.hostoi.com/data/get_all_comments.php");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            in = new BufferedInputStream(urlConnection.getInputStream());
            // Acciones a realizar con el flujo de datos
            data = in.toString();
            return data;
        } finally {
            urlConnection.disconnect();
        }

        /*try{

            HttpClient client = new DefaultHttpClient();
            URI website = new URI("http://www.mybringback.com");
            HttpGet request = new HttpGet();
            request.setURI(website);
            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String l = "";
            String nl = System.getProperty("line.separator");

            while ((l = in.readLine()) != null){
                sb.append(l + nl);
            }
            in.close();
            data = sb.toString();
            return data;
        }finally {
            if(in != null){
                try{
                    in.close();
                    return data;
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

*/

    }

}
