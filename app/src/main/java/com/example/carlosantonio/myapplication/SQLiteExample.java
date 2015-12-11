package com.example.carlosantonio.myapplication;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Carlos Antonio on 15/11/2015.
 */
public class SQLiteExample extends Activity implements View.OnClickListener {


    Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
    TextView sqlName, sqlHotness, sqlRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqlliteexample);

        sqlUpdate = (Button) findViewById(R.id.bSQLUpadte);
        sqlView = (Button) findViewById(R.id.bSQLopenView);
        sqlName = (TextView) findViewById(R.id.etSQLName);
        sqlHotness = (TextView) findViewById(R.id.etSQLHotness);

        sqlUpdate.setOnClickListener(this);
        sqlView.setOnClickListener(this);

        sqlRow = (TextView) findViewById(R.id.etSQLrowInfo);
        sqlModify = (Button) findViewById(R.id.bSQLmodify);
        sqlGetInfo = (Button) findViewById(R.id.bgetInfo);
        sqlDelete = (Button) findViewById(R.id.bSQLdelete);
        sqlModify.setOnClickListener(this);
        sqlGetInfo.setOnClickListener(this);
        sqlDelete.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.bSQLUpadte:

                boolean didItWork = true;
                try {

                    String name = sqlName.getText().toString();
                    String hotness = sqlHotness.getText().toString();

                    HotOrNot entry = new HotOrNot(SQLiteExample.this);
                    entry.open();
                    entry.createEntry(name, hotness);
                    entry.close();

                } catch (Exception e){
                    didItWork = false;
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();

                }finally{
                    if(didItWork){
                        Dialog d = new Dialog(this);
                        d.setTitle("Heck Yeah!");
                        TextView tv = new TextView(this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }
                }

                break;

            case R.id.bSQLopenView:
                Intent i = new Intent("com.example.carlosantonio.myapplication.SQLView");
                startActivity(i);
                break;

            case R.id.bgetInfo:

                try{
                    String s = sqlRow.getText().toString();
                    long l = Long.parseLong(s);
                    HotOrNot hon = new HotOrNot(this);
                    hon.open();
                    String returnedName = hon.getName(l);
                    String returnedHotness = hon.getHotness(l);
                    hon.close();

                    sqlName.setText(returnedName);
                    sqlHotness.setText(returnedHotness);

                } catch (Exception e){

                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();

                }

                break;
            case R.id.bSQLmodify:

                try{

                    String mName = sqlName.getText().toString();
                    String mHotness = sqlHotness.getText().toString();

                    String sRow = sqlRow.getText().toString();
                    long lRow = Long.parseLong(sRow);

                    HotOrNot ex = new HotOrNot(this);
                    ex.open();
                    ex.updateEntry(lRow, mName, mHotness);
                    ex.close();

                } catch (Exception e){

                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();

                }

                break;
            case R.id.bSQLdelete:
                try{
                    String sRow1 = sqlRow.getText().toString();
                    long lRow1 = Long.parseLong(sRow1);
                    HotOrNot ex1 = new HotOrNot(this);
                    ex1.open();
                    ex1.delete(lRow1);
                    ex1.close();

                } catch (Exception e){

                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();

                }
                break;

        }

    }
}
