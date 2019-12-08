package com.example.galaxytraveller;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;


public class p_preguntas extends AppCompatActivity {

    private Typeface fuenteApp;

    private static final int MAX_COUNTER = 30000;
    private int pregunta = 0, preguntes = 10, encertades = 0, fallades = -1;
    private CountDownTimer timer;
    private ProgressBar progress;
    private boolean enJoc = true;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preguntas);
        ArrayList<Pregunta> todas = new ArrayList<Pregunta>();



        TextView nomPregunta = findViewById(R.id.nomPregunta);
        Button opcio1 = findViewById(R.id.opcio1);
        Button opcio2 = findViewById(R.id.opcio2);
        Button opcio3 = findViewById(R.id.opcio3);
        Button opcio4 = findViewById(R.id.opcio4);


        opcio1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                if(enJoc)
                {
                    timer.cancel();
                    iniciarPregunta(true);
                }
            }
        });

        opcio2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(enJoc)
                {
                    timer.cancel();
                    iniciarPregunta(true);
                }
            }
        });

        opcio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enJoc)
                {
                    timer.cancel();
                    iniciarPregunta(true);
                }
            }
        });

        opcio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(enJoc)
                {
                    timer.cancel();
                    iniciarPregunta(true);
                }
            }
        });


        String dir = Environment.getExternalStorageDirectory().getAbsolutePath();
        String path = dir + "/data/Preguntas.json";
        FileReader file = null;

        try {
            file =  new FileReader(path);

            BufferedReader buffer = new BufferedReader(file);
            Gson gson = new Gson();
            Type tipo = new TypeToken<ArrayList<Pregunta>>(){}.getType();
            todas  = gson.fromJson(buffer, tipo);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<Pregunta> dificultad_seleccionada = new ArrayList<Pregunta>();


        Iterator<Pregunta> iteratorPregunta = todas.iterator();

        while(iteratorPregunta.hasNext())
        {

            Pregunta pregun = iteratorPregunta.next();


            if (pregun.getDificultad().equals(dificultad))
            {

                dificultad_seleccionada.add(pregun);


            }

        }


    }

    public void iniciarPregunta(boolean encertada)
    {
        final TextView nomPregunta = findViewById(R.id.nomPregunta);

        progress = findViewById(R.id.progressBar);
        progress.setMax(MAX_COUNTER);

        if(encertada)
        {
            encertades++;
        }
        else
        {
            fallades++;
        }
        pregunta++;

        if(pregunta <= 10)
        {
            timer = new CountDownTimer(MAX_COUNTER, MAX_COUNTER / 100 )
            {

                public void onTick(long millisUntilFinished)
                {
                    nomPregunta.setText("Pregunta " + pregunta  + ": " +
                            encertades + " / " + fallades + " (" +
                            (encertades + fallades) + ")");
                    progress.setProgress((int) millisUntilFinished);
                }

                public void onFinish()
                {
                    iniciarPregunta(false);
                    this.cancel();
                }
            }.start();
        }
        else
        {
            enJoc = false;
            nomPregunta.setText("Final. OK: " + encertades + ", KO: " + fallades);
        }
    }





}

