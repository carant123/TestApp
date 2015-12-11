package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Carlos Antonio on 18/10/2015.
 */
public class Mensaje extends Activity implements View.OnClickListener{

    EditText Nombre;
    EditText Correo;
    String Vnombre, Vcorreo;
    Button bEnviar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mensajelayout);
        inicializar();
        bEnviar.setOnClickListener(this);
    }

    public void inicializar(){
        Nombre = (EditText) findViewById(R.id.editTextNombre);
        Correo = (EditText) findViewById(R.id.editTextCorreo);
        bEnviar = (Button) findViewById(R.id.EnviarBoton);

    }

    @Override
    public void onClick(View view) {

        convertiredittextatext();
        String emailaddress[] = { Vcorreo };
        String message = "Mensaje"
                + Vnombre
                + "correo del mensaje"
                + Vcorreo ;
        Intent mensajeIntent = new Intent(Intent.ACTION_SEND);
        mensajeIntent.putExtra(Intent.EXTRA_EMAIL, Vcorreo);
        mensajeIntent.putExtra(Intent.EXTRA_SUBJECT, "Men");
        mensajeIntent.setType("plain/text");
        mensajeIntent.putExtra(Intent.EXTRA_TEXT, message);
        startActivity(mensajeIntent);

    }

    public void convertiredittextatext(){

        Vnombre = Nombre.getText().toString();
        Vcorreo = Correo.getText().toString();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
