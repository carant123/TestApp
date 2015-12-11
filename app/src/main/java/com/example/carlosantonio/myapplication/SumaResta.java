package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Carlos Antonio on 26/10/2015.
 */
public class SumaResta extends Activity implements View.OnClickListener{

    TextView resultado;
    Button suma;
    Button resta;
    int N;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suma_resta);
        iniciatialize();
    }

    public void iniciatialize(){
        resultado = (TextView) findViewById(R.id.TextCantSumRest);
        suma = (Button) findViewById(R.id.idAddone);
        resta = (Button) findViewById(R.id.idSubstractone);
        N = 0;
        resultado.setText("Your total is 0");
        suma.setOnClickListener(this);
        resta.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.idAddone:
                N++;
                resultado.setText("Your total is " + N);

                break;

            case R.id.idSubstractone:
                N--;
                resultado.setText("Your total is " + N);

                break;
        }
    }
}
