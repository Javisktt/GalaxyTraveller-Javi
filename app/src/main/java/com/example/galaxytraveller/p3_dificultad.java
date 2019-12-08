package com.example.galaxytraveller;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;


public class p3_dificultad extends AppCompatActivity {

    private Typeface fuenteApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p3_dificultad);

        Button p3_buttonArrowBack = (Button) findViewById(R.id.p3_buttonArrowBack);
        Button p3_buttonDifBaja = (Button) findViewById(R.id.p3_buttonDifBaja);
        Button p3_buttonDifMedia = (Button) findViewById(R.id.p3_buttonDifMedia);
        Button p3_buttonDifAlta = (Button) findViewById(R.id.p3_buttonDifAlta);
        Button p2_buttonAyuda = (Button) findViewById(R.id.p2_buttonAyuda);

        p3_buttonArrowBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p3_buttonArrowBack = new Intent(p3_dificultad.this, p2_start.class);
                startActivity(p3_buttonArrowBack);
            }

        });

        //Creamos un string donde se guarda la ruta de nuestra fuente
        String ruta_fuente = "fonts/MidNight.ttf";
        //Lo ponemos como un recurso para poder usarlo y hacemos que coja el pathing de nuestra fuente asignandole el string anterior
        this.fuenteApp = Typeface.createFromAsset(getAssets(), ruta_fuente);

        p3_buttonDifBaja.setTypeface(fuenteApp);
        p3_buttonDifMedia.setTypeface(fuenteApp);
        p3_buttonDifAlta.setTypeface(fuenteApp);
        p2_buttonAyuda.setTypeface(fuenteApp);

        p3_buttonDifAlta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button dificultad = (Button) view;

                Intent p3_buttonDifAlta = new Intent(p3_dificultad.this, p_preguntas.class);

                startActivity(p3_buttonDifAlta);

            }
        });

    }
}