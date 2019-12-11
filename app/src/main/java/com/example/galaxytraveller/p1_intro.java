package com.example.galaxytraveller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Locale;


public class p1_intro extends AppCompatActivity {


    private static final int REQUEST_CODE_ASK_PERMISSION = 111;
    private Typeface fuenteApp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.p1_intro);

        Button p1_buttonEmpezar = (Button) findViewById(R.id.p1_buttonEmpezar);
        Button button_cat = (Button) findViewById(R.id.button_cat);
        Button button_esp = (Button) findViewById(R.id.button_esp);
        Button button_en = (Button) findViewById(R.id.button_en);
        Button p1_buttonAyuda = (Button) findViewById(R.id.p2_buttonAyuda);
        TextView textView_titulo = (TextView) findViewById(R.id.textView_titulo);
        TextView textView_desc1 = (TextView) findViewById(R.id.textView_desc1);
        TextView textView_desc2 = (TextView) findViewById(R.id.textView_desc2);
        TextView textView_desc3 = (TextView) findViewById(R.id.textView_desc3);

        //Creamos un string donde se guarda la ruta de nuestra fuente
        String ruta_fuente = "fonts/MidNight.ttf";
        //Lo ponemos como un recurso para poder usarlo y hacemos que coja el pathing de nuestra fuente asignandole el string anterior
        this.fuenteApp = Typeface.createFromAsset(getAssets(), ruta_fuente);


        // Cambiamos la fuente a todos los botones
        p1_buttonEmpezar.setTypeface(fuenteApp);
        p1_buttonAyuda.setTypeface(fuenteApp);
        textView_titulo.setTypeface(fuenteApp);
        textView_desc1.setTypeface(fuenteApp);
        textView_desc2.setTypeface(fuenteApp);
        textView_desc3.setTypeface(fuenteApp);

        permisos();

        p1_buttonAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p1_buttonAyuda = new Intent(p1_intro.this, p_ayuda.class);
                startActivity(p1_buttonAyuda);
            }
        });

        p1_buttonEmpezar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p1_buttonEmpezar = new Intent(p1_intro.this, p2_start.class);
                startActivity(p1_buttonEmpezar);
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
        res.updateConfiguration(conf, dm);
    }

    public Boolean permisos ()
    {


        Boolean permitido = false;

        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            // Si executem la versió Marshmallow (6.0) o posterior, haurem de demanar
            // permisos en temps d'execució

            // Comprovem si l'usuari ja ens ha donat permisos en una execió anterior
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED)
            {


                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_ASK_PERMISSION);

                // Codi que volem executar
                permitido = true;

            }
            else
            {
                // Si l'usuari ja ens havia atorgat permisos en una execució anterior,
                // executem directament el nostre codi

                permitido = true;
                // Codi que volem executar
            }
        }
        else
        {
            // Si executem una versió anterior a la versió Marshmallow (6.0),
            // no cal demanar cap permís, i podem executar el nostre codi directament
            permitido = true;
            // Codi que volem executar
        }

        return permitido;

    }
}
