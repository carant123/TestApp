package com.example.carlosantonio.myapplication;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.DigitalClock;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Carlos Antonio on 30/11/2015.
 */
public class Notificaciones extends Activity implements View.OnClickListener {

    Button btnotifi;
    TextView tvH, tvH1, tvH2;
    String currentDateTimeString;
    DigitalClock a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificacion_example);
        btnotifi = (Button) findViewById(R.id.btNotificacion);
        tvH = (TextView) findViewById(R.id.tvHora);
        tvH1 = (TextView) findViewById(R.id.tvHora1);
        tvH2 = (TextView) findViewById(R.id.tvHora2);
        a = (DigitalClock) findViewById(R.id.digitalClock);



        btnotifi.setOnClickListener(this);
        //currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        //Date fecha = new java.util.Date();

        Date date = new Date();
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //String formattedDate = df.format(c.getTime());



//Caso 1: obtener la hora y salida por pantalla con formato:
        //DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        //tvH.setText(df.format(c.getTime()));
        //tvH.setText("Hora: "+hourFormat.format(date));
//Caso 2: obtener la fecha y salida por pantalla con formato:
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        tvH1.setText("Fecha: "+dateFormat.format(date));
//Caso 3: obtenerhora y fecha y salida por pantalla con formato:
        DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        tvH2.setText("Hora y fecha: "+hourdateFormat.format(date));



        if (dateFormat.format(date).toString() == "30/11/2015"){
            Notificar();
        };

    }



    public void Notificar(){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle("My Notificacion");
        builder.setContentText("This is my first notification ...");


        Intent intent = new Intent(this,Notificaciones.class);

        // Crear pila
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Añadir actividad padre
        stackBuilder.addParentStack(Encuesta_Tab.class);
        // Referenciar Intent para la notificación, donde dirigira al presionar la notificacion
        stackBuilder.addNextIntent(intent);

        // Obtener PendingIntent resultante de la pila
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        // Remover notificacion al interactuar con ella
        builder.setAutoCancel(true);

        // Construir la notificación y emitirla
        NotificationManager NM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NM.notify(0,builder.build());
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btNotificacion:
                Notificar();
                break;
        }
    }
}
