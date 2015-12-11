package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Carlos Antonio on 14/11/2015.
 */
public class ExternalData extends Activity implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    private TextView canWrite, canRead;
    private String state;
    boolean canW, canR;
    Spinner spinner;
    String[]  paths = { "Music", "Pictures", "Download"};
    //la clase File puede representar una direccion a un elemento de android o para crear un archivo
    File path = null;
    File file = null;
    EditText saveFile;
    Button confirm, save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.externaldata);
        canWrite = (TextView) findViewById(R.id.tvCanWrite);
        canRead = (TextView) findViewById(R.id.tvCanRead);
        confirm = (Button) findViewById(R.id.bConfirmSaveAs);
        save = (Button) findViewById(R.id.bSaveFile);
        saveFile = (EditText) findViewById(R.id.etSaveAs);
        confirm.setOnClickListener(this);
        save.setOnClickListener(this);

        checkState();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ExternalData.this, android.R.layout.simple_list_item_1, paths);

        spinner = (Spinner) findViewById(R.id.spinner1);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

    }

    private void checkState() {

        state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            canRead.setText("true");
            canWrite.setText("true");
            canW = canR = true;
        } else if (state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            //read but can't write
            canRead.setText("true");
            canWrite.setText("false");
            canW = false;
            canR = true;

        } else {
            canRead.setText("false");
            canWrite.setText("false");
            canW = canR = false;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        int position = spinner.getSelectedItemPosition();
        switch(position){
            case 0:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
                break;
            case 1:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                break;
            case 2:
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bSaveFile:
                String f = saveFile.getText().toString();
                file = new File(path, f + ".png");
                checkState();

                if(canW = canR = true){

                    path.mkdirs();

                    try {
                        //metodos para leer datos desde una fuente concreta read()
                        InputStream is = getResources().openRawResource(R.drawable.android_hdpi);
                        //metoddos para manejar el flujo de salida write()
                        OutputStream os = new FileOutputStream(file);
                        byte[] data = new byte[is.available()];
                        is.read();
                        os.write(data);
                        is.close();
                        os.close();

                        Toast t = Toast.makeText(ExternalData.this, "File have been saved", Toast.LENGTH_LONG);
                        t.show();

                        //Update files for the user to use
                        MediaScannerConnection.scanFile(ExternalData.this,new String[]{file.toString()},null,
                                new MediaScannerConnection.OnScanCompletedListener() {

                                    @Override
                                    public void onScanCompleted(String s, Uri uri) {
                                        Toast t = Toast.makeText(ExternalData.this, "scan complete", Toast.LENGTH_SHORT );
                                        t.show();
                                    }
                                });

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                break;
            case R.id.bConfirmSaveAs:
                save.setVisibility(View.VISIBLE);
                break;
        }

    }
}
