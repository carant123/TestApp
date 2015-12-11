package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.example.carlosantonio.myapplication.models.EncuestaModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carlos Antonio on 27/11/2015.
 */
public class Encuesta_Tab extends Activity implements View.OnClickListener {

    TabHost th;
    ListView lista;
    Button botonenv, bref;
    EditText Nombre, Numero, Comentario;
    private ListView lvEncuesta;
    String classes[] = { "Suma", "photo", "Mensaje","Data", "SumaResta", "GFX", "GFXSurface",
            "SoundStaff","Slider","Tabs","SimpleBrowser","Flipper","SharedPrefs","InternalData",
            "ExternalData","SQLiteExample","Accelerate","HttpExample","TestHttp","Encuesta_Tab"};
    String classes2[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.encuesta);

        th = (TabHost) findViewById(R.id.tabHost_encuesta);

        Nombre = (EditText) findViewById(R.id.etNombreEncuesta);
        Numero = (EditText) findViewById(R.id.etNumeroEncuesta);
        Comentario = (EditText) findViewById(R.id.etComentarioEncuesta);

        lvEncuesta = (ListView) findViewById(R.id.lvEncuestas);

        botonenv = (Button) findViewById(R.id.btregistrarEncuesta);
        bref = (Button) findViewById(R.id.btrefreshencuesta);

        // configura el conjunto de tabs
        th.setup();
        // configura los tabs por separado y los agrega al tab principal
        TabHost.TabSpec specs = th.newTabSpec("Etag1");
        specs.setContent(R.id.tabRegsitro);
        specs.setIndicator("Registro");
        th.addTab(specs);

        specs = th.newTabSpec("Etag2");
        specs.setContent(R.id.tabListaEncuesta);
        specs.setIndicator("Lista Registrada");
        th.addTab(specs);

        botonenv.setOnClickListener(this);
        bref.setOnClickListener(this);

        Lista();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btregistrarEncuesta:


                try {

                    String name = Nombre.getText().toString();
                    String number = Numero.getText().toString();
                    String coment = Comentario.getText().toString();
                    DatosEncuesta entry = new DatosEncuesta(Encuesta_Tab.this);
                    entry.open();
                    entry.createEntry(name, number, coment);
                    entry.close();

                } catch (SQLException e) {
                    e.printStackTrace();

                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                }finally {
                    Toast.makeText(getApplicationContext(), "Registro Completo", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.btrefreshencuesta:
                Lista();
                break;
        }

    }

    public void Lista(){



        try {

            DatosEncuesta db = new DatosEncuesta(Encuesta_Tab.this);
            db.open();
            Cursor cur = db.getData();

            List<EncuestaModel> ListaEncuesta = new ArrayList<EncuestaModel>();
            EncuestaModel Encuesta;


            String classes2[];


            while(cur.moveToNext()){

                Encuesta = new EncuestaModel();

                int id = cur.getInt(cur.getColumnIndex("_id"));

                String nombre = cur.getString(cur.getColumnIndex("Nombre_data"));

                String numero = cur.getString(cur.getColumnIndex("Numero_data"));

                String comentario = cur.getString(cur.getColumnIndex("Comentario_data"));

                //int check = cursor.getInt(cursor.getColumnIndex("estado"));

                Encuesta.setID(id);
                Encuesta.setNombre(nombre);
                Encuesta.setNumero(numero);
                Encuesta.setComentario(comentario);
                //pedido.estado = check;
                //pedido.estado = check;
                ListaEncuesta.add(Encuesta);

            }

            cur.close();
            db.close();

            //lvEncuesta.setAdapter(new ArrayAdapter(Encuesta_Tab.this, android.R.layout.simple_list_item_1, ListaEncuesta));

            EncuestaArrayAdapter adapter = new EncuestaArrayAdapter(Encuesta_Tab.this, R.layout.encuesta_content, ListaEncuesta);
            lvEncuesta.setAdapter(adapter);

            Toast.makeText(getApplicationContext(), "Adaptado", Toast.LENGTH_SHORT).show();

        } catch (SQLException e) {
            e.printStackTrace();

            Toast.makeText(getApplicationContext(), "Adaptado Error", Toast.LENGTH_SHORT).show();
        }


    }

    public class EncuestaArrayAdapter extends ArrayAdapter<EncuestaModel> {

        private List<EncuestaModel> listaEncuesta;
        private int resource;
        private LayoutInflater inflator;

        public EncuestaArrayAdapter(Context context, int resource, List<EncuestaModel> objects) {
            super(context, resource, objects);
            //el layout
            this.resource = resource;
            listaEncuesta = objects;
            inflator = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if(convertView == null) {
                holder = new ViewHolder();
                convertView = inflator.inflate(resource, null);
                holder.nombreLista = (TextView) convertView.findViewById(R.id.tvDetalleEncuesta);
                holder.bdetalle = (Button) convertView.findViewById(R.id.bdetallencuesta);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.nombreLista.setText(listaEncuesta.get(position).getNombre());


            holder.bdetalle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String DataNombre = listaEncuesta.get(position).getNombre();
                    String DataNumero = listaEncuesta.get(position).getNumero();
                    String DataComentario = listaEncuesta.get(position).getComentario();
                    Bundle DataGroup = new Bundle();
                    DataGroup.putString("DNombre",DataNombre);
                    DataGroup.putString("DNumero",DataNumero);
                    DataGroup.putString("DComentario",DataComentario);
                    Intent a = new Intent(Encuesta_Tab.this,EncuestaDetalles.class);
                    a.putExtras(DataGroup);
                    startActivity(a);

                }
            });



            return convertView;
        }



        class ViewHolder{

            private TextView nombreLista;
            private Button bdetalle;

        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        getMenuInflater().inflate(R.menu.menu_refresh_encuesta,menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.lista_refresh){
            Lista();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        //listar2();
        Lista();

    }


}
