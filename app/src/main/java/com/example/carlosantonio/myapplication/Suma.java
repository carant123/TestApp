package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Carlos Antonio on 07/10/2015.
 */
public class Suma extends Activity {

    EditText E1;
    EditText E2;
    EditText ERespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        "establecer el layout"
        setContentView(R.layout.sumalayout);
     //   "relacionar los elementos "
        E1 = (EditText) findViewById(R.id.editText1);
        E2 = (EditText) findViewById(R.id.editText2);
        ERespuesta = (EditText) findViewById(R.id.editTextResultado);

    }

    public void btnSumar(View v){

   //     "tomar los valores de los edittext"
        int N1 = Integer.parseInt(E1.getText().toString());
        int N2 = Integer.parseInt(E2.getText().toString());
        int Suma = N1 + N2;

     //   "pegar valor al edittext"
        ERespuesta.setText(String.valueOf(Suma));

    }

}
