package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Carlos Antonio on 14/11/2015.
 */
public class InternalData extends Activity implements View.OnClickListener {

    EditText sharedData;
    TextView dataResults;
    FileOutputStream fos;
    String FILENAME = "InternalString";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        setupVariables();
    }


    private void setupVariables(){
        Button save = (Button) findViewById(R.id.bSave);
        Button load = (Button) findViewById(R.id.bLoad);
        sharedData = (EditText) findViewById(R.id.etSharedPrefs);
        dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);
        save.setOnClickListener(this);
        load.setOnClickListener(this);
        try {
            fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bSave:
            String data = sharedData.getText().toString();
            //guardar dato via archivo
            /*    File f = new File(FILENAME);
                try {
                    fos = new FileOutputStream(f);
                    //escribir algo
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }*/

                try {
                    fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
                    fos.write(data.getBytes());
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(sharedData.getWindowToken(), 0);

                break;
            case R.id.bLoad:
                new loadSomeStuff().execute(FILENAME);

                /*String collected = null;
                FileInputStream fis = null;
                try {
                    fis = openFileInput(FILENAME);
                    byte[] dataArray = new byte[fis.available()];
                    while (fis.read(dataArray) != -1){
                        collected = new String(dataArray);
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fis.close();
                        dataResults.setText(collected);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }*/


                break;
        }
    }

    //el primer valor es el que enviamos
    public class loadSomeStuff extends AsyncTask<String, Integer,String>{

        ProgressDialog dialog;

        protected void onPreExecute(){
            //example of settings up something
            //la barra de proceso correra en la misma clase
            dialog = new ProgressDialog(InternalData.this);
            dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            dialog.setMax(100);
            dialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            String collected = null;
            FileInputStream fis = null;


            for(int i = 0; i < 20; i++){
                //va a mostrar el proceso cada 5
                publishProgress(5);
                try {
                    Thread.sleep(88);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            dialog.dismiss();

            try {
                fis = openFileInput(FILENAME);
                byte[] dataArray = new byte[fis.available()];
                while (fis.read(dataArray) != -1){
                    collected = new String(dataArray);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    fis.close();
                    return collected;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            dialog.incrementProgressBy(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            dataResults.setText(s);
        }
    }



}
