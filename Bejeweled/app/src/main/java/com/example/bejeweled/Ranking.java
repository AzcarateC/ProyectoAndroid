package com.example.bejeweled;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ShareActionProvider;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Ranking extends AppCompatActivity {

    public TextView textView1;
    public TextView textView2;
    public TextView textView3;
    public TextView textView4;
    public TextView textView5;
    public TextView textView6;
    public TextView textView7;
    public TextView textView8;
    public TextView textView9;
    public TextView textView10;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        textView1 = findViewById(R.id.txt1);
        textView2 = findViewById(R.id.txt2);
        textView3 = findViewById(R.id.txt3);
        textView4 = findViewById(R.id.txt4);
        textView5 = findViewById(R.id.txt5);
        textView6 = findViewById(R.id.txt6);
        textView7 = findViewById(R.id.txt7);
        textView8 = findViewById(R.id.txt8);
        textView9 = findViewById(R.id.txt9);
        textView10 = findViewById(R.id.txt10);

        TextView[] arreglodeText = {textView1,textView2,textView3,textView4,textView5,textView6,textView7,textView8,textView9,textView10};

        //Creo la base de datos y la abro para escritura/lectura
        SQLite admin = new SQLite(this, "jugadores", null, 1);
        SQLiteDatabase db =  admin.getWritableDatabase();

        //CONSULTA A BASE DE DATOS
        Cursor registros = db.rawQuery("SELECT * FROM jugadores ORDER BY puntaje DESC", null);

        if(registros.moveToFirst()) {
            //cuantos registros borro
            int cantidad = 0;
            do {
                String codigo = String.valueOf(cantidad);
                String nombre = registros.getString(1);
                String puntaje = registros.getString(2);
                arreglodeText[cantidad].setText(codigo + " " + nombre + " " + " " + puntaje);
                cantidad++;
            } while (registros.moveToNext());
        } else {
            for (int i = 0; i < arreglodeText.length; i++){
                arreglodeText[i].setText("VACIO");
            }
        }
        db.close();
    }

    public void onClikTerminar(View view) {
        this.finish();
    }

}