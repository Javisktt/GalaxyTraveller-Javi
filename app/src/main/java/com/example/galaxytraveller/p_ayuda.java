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
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;


public class p_ayuda extends AppCompatActivity {

    private Typeface fuenteApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p_ayuda);

        Button p2_buttonInicio = (Button) findViewById(R.id.p2_buttonInicio);
        Button button_cat = (Button) findViewById(R.id.button_cat);
        Button button_en = (Button) findViewById(R.id.button_en);
        Button button_esp = (Button) findViewById(R.id.button_esp);
        ImageView galaxy_traveller = (ImageView) findViewById(R.id.galaxy_traveller);
        TextView textView_titulo = (TextView) findViewById(R.id.textView_titulo);
        TextView textView_desc1 = (TextView) findViewById(R.id.textView_desc1);
        TextView textView_desc2 = (TextView) findViewById(R.id.textView_desc2);
        TextView textView_desc3 = (TextView) findViewById(R.id.textView_desc3);

        //Creamos un string donde se guarda la ruta de nuestra fuente
        String ruta_fuente = "fonts/MidNight.ttf";

        //Lo ponemos como un recurso para poder usarlo y hacemos que coja el pathing de nuestra fuente asignandole el string anterior
        this.fuenteApp = Typeface.createFromAsset(getAssets(), ruta_fuente);

        // Cambiamos la fuente a todos los TextViews
        textView_titulo.setTypeface(fuenteApp);
        textView_desc1.setTypeface(fuenteApp);
        textView_desc2.setTypeface(fuenteApp);
        textView_desc3.setTypeface(fuenteApp);

        galaxy_traveller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galaxy_traveller = new Intent(p_ayuda.this, p1_intro.class);
                startActivity(galaxy_traveller);
            }
         });

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

