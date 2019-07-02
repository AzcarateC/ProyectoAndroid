package com.example.bejeweled;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class IngresaNombre extends AppCompatActivity {

    private EditText etName;
    private String puntaje;
    private int numero;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresa_nombre);
        puntaje = getIntent().getStringExtra("score");
        etName = (EditText)findViewById(R.id.nombre);
        numero = (int) (Math.random() * 1000) + 1;
    }


    public void volver(View view) {
        this.finish();
    }

    public void guardar(View view) {
        String nombre = etName.getText().toString();
        if (!nombre.isEmpty()){
            //Creo la base de datos y la abro para escritura/lectura
            SQLite admin = new SQLite(this, "jugadores", null, 1);
            SQLiteDatabase db =  admin.getWritableDatabase();

            ContentValues registro = new ContentValues();
            registro.put("codigo", puntaje+nombre+numero);
            registro.put("nombre", nombre);
            registro.put("puntaje", puntaje);
            db.insert("jugadores", null, registro);
            db.close();
            Intent i= new Intent (this,Ranking.class);
            startActivity(i);
            this.finish();
        } else {
            Toast.makeText(IngresaNombre.this, "No ingreso ningun nombre", Toast.LENGTH_SHORT).show();
        }
    }
}
