package com.example.carlosantonio.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.sql.SQLException;

/**
 * Created by Carlos Antonio on 27/11/2015.
 */
public class DatosEncuesta{

    public static final String KEY_ROWID = "_id";
    public static final String KEY_NOMBRE = "Nombre_data";
    public static final String KEY_NUMERO = "Numero_data";
    public static final String KEY_COMENTARIO = "Comentario_data";

    private static final String DATABASE_NAME = "HotOrNotdb3";
    private static final String DATABASE_TABLE = "EncuestaTable";
    private static final int DATABSE_VERSION = 1;

    private DbHelper ourHelper;
    private final Context ourContext;
    private SQLiteDatabase ourDatabase;


    private static class DbHelper extends SQLiteOpenHelper {

        public DbHelper(Context context) {
            super(context, DATABASE_NAME, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            db.execSQL( "CREATE TABLE " + DATABASE_TABLE + " (" +
                            KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            KEY_NOMBRE + " TEXT NOT NULL, " +
                            KEY_NUMERO + " TEXT NOT NULL, " +
                            KEY_COMENTARIO + " TEXT NOT NULL);"
            );

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
            onCreate(db);
        }
    }

    public DatosEncuesta (Context c){
        ourContext = c;
    }

    //se podria agreagar a open() throws SQLException
    public DatosEncuesta open() throws SQLException {
        ourHelper = new DbHelper(ourContext);
        ourDatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourHelper.close();
    }



    public long createEntry(String nombre, String numero, String comentario) {
        ContentValues cv = new ContentValues();
        cv.put(KEY_NOMBRE,nombre);
        cv.put(KEY_NUMERO, numero);
        cv.put(KEY_COMENTARIO, comentario);
        return ourDatabase.insert(DATABASE_TABLE, null,cv);
    }

    public Cursor getData() {
        String[] columns = new String[]{ KEY_ROWID, KEY_NOMBRE, KEY_NUMERO, KEY_COMENTARIO};
        //String[] columns2 = new String[]{KEY_NOMBRE};
        Cursor c = ourDatabase.query(DATABASE_TABLE,columns,null,null,null,null,null);
        //String result = "";

        /*int iRows = c.getColumnIndex(KEY_ROWID);
        int iNombre = c.getColumnIndex(KEY_NOMBRE);
        int iNumero = c.getColumnIndex(KEY_NUMERO);
        int iComentario = c.getColumnIndex(KEY_COMENTARIO);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            result = result + c.getString(iRows) + " " + c.getString(iNombre) + " " + c.getString(iNumero) + "\n";
        }*/

        return c;
    }

    /*public String getName(long l) throws SQLException{

        String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_HOTNESS};
        Cursor c = ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID + "=" + l,null,null,null,null);
        if (c != null){
            c.moveToNext();
            String name = c.getString(1);
            return name;
        }


        return null;
    }

    public String getHotness(long l) throws SQLException{

        String[] columns = new String[]{ KEY_ROWID, KEY_NAME, KEY_HOTNESS};
        Cursor c = ourDatabase.query(DATABASE_TABLE,columns,KEY_ROWID + "=" + l,null,null,null,null);
        if (c != null){
            c.moveToNext();
            String hotness = c.getString(2);
            return hotness;
        }

        return null;

    }

    public void updateEntry(long lRow, String mName, String mHotness) throws SQLException{
        ContentValues cvUpdate = new ContentValues();
        cvUpdate.put(KEY_NAME, mName);
        cvUpdate.put(KEY_HOTNESS, mHotness);
        ourDatabase.update(DATABASE_TABLE, cvUpdate, KEY_ROWID + "=" + lRow, null);
    }

    public void delete(long lRow1) throws SQLException{
        ourDatabase.delete(DATABASE_TABLE, KEY_ROWID + "=" + lRow1, null);
    }*/

}
