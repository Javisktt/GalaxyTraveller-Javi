package com.example.galaxytraveller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import java.util.Locale;


public class p2_start extends AppCompatActivity {

    private Typeface fuenteApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p2_start);

        Button p2_buttonInicio = (Button) findViewById(R.id.p2_buttonInicio);
        final Button p2_buttonJugar = (Button) findViewById(R.id.p2_buttonJugar);
        Button p2_buttonRanking = (Button) findViewById(R.id.p2_buttonRanking);
        Button button_cat = (Button) findViewById(R.id.button_cat);
        Button button_en = (Button) findViewById(R.id.button_en);
        Button button_esp = (Button) findViewById(R.id.button_esp);
        Button p2_buttonAyuda = (Button) findViewById(R.id.p2_buttonAyuda);
        Button p2_buttonPersonPj = (Button) findViewById(R.id.p2_buttonPersonPj);

        //Creamos un string donde se guarda la ruta de nuestra fuente
        String ruta_fuente = "fonts/MidNight.ttf";

        //Lo ponemos como un recurso para poder usarlo y hacemos que coja el pathing de nuestra fuente asignandole el string anterior
        this.fuenteApp = Typeface.createFromAsset(getAssets(), ruta_fuente);

        // Cambiamos la fuente a todos los botones
        p2_buttonInicio.setTypeface(fuenteApp);
        p2_buttonJugar.setTypeface(fuenteApp);
        p2_buttonRanking.setTypeface(fuenteApp);
        p2_buttonAyuda.setTypeface(fuenteApp);
        p2_buttonPersonPj.setTypeface(fuenteApp);

        p2_buttonInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p2_buttonInicio = new Intent(p2_start.this, p1_intro.class);
                startActivity(p2_buttonInicio);
            }

        });

        p2_buttonJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p2_buttonJugar = new Intent(p2_start.this, p3_dificultad.class);
                startActivity(p2_buttonJugar);
            }

        });

        p2_buttonAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p2_buttonAyuda = new Intent(p2_start.this, p_ayuda.class);
                startActivity(p2_buttonAyuda);
            }
        });


        /*
        p2_buttonRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p2_buttonRanking = new Intent(p2_start.this, ranking.class);
                startActivity(ranking);
            }

        });
        */
        button_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppLocale("ca");
                recreate();
            }
        });

        button_esp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppLocale("es");
                recreate();
            }
        });

        button_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAppLocale("en");
                recreate();
            }
        });


    }
    private void setAppLocale(String localCode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            conf.setLocale(new Locale(localCode.toLowerCase()));
        } else {
            conf.locale = new Locale(localCode.toLowerCase());
        }
        res.updateConfiguration(conf, dm);}
}
