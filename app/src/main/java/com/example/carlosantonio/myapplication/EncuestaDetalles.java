package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Carlos Antonio on 10/12/2015.
 */
public class EncuestaDetalles extends Activity {

    TextView tvnombre;
    TextView tvnumero;
    TextView tvcomentario;
    Bundle Data;
    Button BV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encuesta_detalle);
        tvnombre = (TextView) findViewById(R.id.tvdetalle_nombre);
        tvnumero = (TextView) findViewById(R.id.tvdetalle_numero);
        tvcomentario = (TextView) findViewById(R.id.tvdetalle_comentario);
        BV = (Button) findViewById(R.id.btVolver);

        Data = getIntent().getExtras();
        tvnombre.setText(Data.getString("DNombre"));
        tvnumero.setText(Data.getString("DNumero"));
        tvcomentario.setText(Data.getString("DComentario"));

        BV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
