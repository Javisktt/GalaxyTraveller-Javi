package com.example.galaxytraveller;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;


public class p_preguntas extends AppCompatActivity {


    public static final int MILLIS = 31000;
    public static final int INTERVAL = 1000;
    public static final byte RONDA_START = 0;
    public static final byte RON_TOTALES = 9;
    static final int REQUEST_CODE_ASK_PERMISSION = 111;

    private byte rondas;

    private boolean correcta;
    private Pregunta actual;
    private ArrayList<Pregunta> dificultad_seleccionada = new ArrayList<Pregunta>();
    private TextView nomPregunta;
    private Button opcio1;
    private Button opcio2;
    private Button opcio3;
    private Button opcio4;
    private Button button_enrere;
    Chronometer cronometro;
    boolean correr=false;
    long parar;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preguntas);
        ArrayList <Pregunta> todas = new ArrayList<Pregunta>();

        cronometro = findViewById(R.id.cronometro);
        boolean permitido = permisos ();
        opcio1 = findViewById(R.id.opcio1);
        opcio2 = findViewById(R.id.opcio2);
        opcio3 = findViewById(R.id.opcio3);
        opcio4 = findViewById(R.id.opcio4);
        button_enrere = findViewById(R.id.button_enrere);

        // Arranca el cronometro
        startChronometro();



        opcio1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                RellenaPregunta();
                cogeRespuesta(opcio1,actual);


            }
        });

        opcio2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RellenaPregunta();
                cogeRespuesta(opcio2,actual);

            }
        });

        opcio3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RellenaPregunta();
                cogeRespuesta(opcio3,actual);

            }
        });

        opcio4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RellenaPregunta();
                cogeRespuesta(opcio4,actual);


            }
        });

        button_enrere.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent button_enrere = new Intent(p_preguntas.this, p1_intro.class);
                startActivity(button_enrere);

            }
        });


        if (permitido == true)
        {


            String dificultad = "Medio";

            Gson gson = new Gson();

            //if (dificultad_seleccionada.isEmpty())
            //{



            String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/data/Preguntas.json";


            FileReader file = null;


            try {

                file =  new FileReader(path);

                BufferedReader buffer = new BufferedReader(file);


                Type tipo = new TypeToken<ArrayList<Pregunta>>(){}.getType();


                todas  = gson.fromJson(buffer, tipo);


            } catch (FileNotFoundException e)
            {

                e.printStackTrace();

            }




            Iterator<Pregunta> iteratorPregunta = todas.iterator();

            while(iteratorPregunta.hasNext())
            {

                Pregunta pregun = iteratorPregunta.next();

                if (pregun.getDificultad().equals(dificultad))
                {

                    dificultad_seleccionada.add(pregun);


                }

            }


            rondas = RONDA_START;




            RellenaPregunta();



            /*while (rondas < RON_TOTALES && rondas < dificultad_seleccionada.size())
            {
            }*/




        }

    }

    private void startChronometro(){
        if (!correr) {
            cronometro.setBase(SystemClock.elapsedRealtime());
            cronometro.start();
            correr = true;
        }
    }

    private void stopChronometro() {
        if(correr = true){
            cronometro.stop();
            parar = SystemClock.elapsedRealtime() - cronometro.getBase();
            correr = false;
        }
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

    //Rellena una pregunta
    public void RellenaPregunta()
    {

        actual = preguntaRandom(dificultad_seleccionada);
        nomPregunta = findViewById(R.id.nomPregunta);
        nomPregunta.setText(actual.getPregunta());
        respuestaRandom(actual, opcio1, opcio2, opcio3, opcio4);


    }



/* Se elige una respuesta, guardada en un objeto de tipo Pregunta, con un número rándom,
para evitar la predicción de la próxima
 */

    public Pregunta preguntaRandom (ArrayList<Pregunta> dificultadSeleccionada)
    {

        Pregunta selected;

        byte tocaNumero;

        tocaNumero =  (byte) (Math.random() * dificultadSeleccionada.size());

        selected = dificultadSeleccionada.get(tocaNumero);

        return selected;


    }

    /*Coincidiendo con la anterior, hacemos que cada respuesta posible, aunque haya tocado
    antes, también sea difícil predecir en que botón está*/

    public void respuestaRandom (Pregunta actual, Button opcion1, Button opcion2,
                                 Button opcion3, Button opcion4)
    {

        ArrayList<String> respuestas = new ArrayList<>();

        respuestas.add(actual.getRespuestaCorrecta());

        respuestas.add(actual.getRespuestaInCorrecta1());

        respuestas.add(actual.getRespuestaIncorrecta2());

        respuestas.add(actual.getRespuestaIncorrecta3());


        asignacion (opcion1, respuestas);

        asignacion (opcion2, respuestas);

        asignacion (opcion3, respuestas);

        asignacion (opcion4, respuestas);

    }

    public void asignacion (Button opcion, ArrayList<String> respuestas)
    {

        final byte INTER_MAX = 4;

        byte tocaNumero;

        tocaNumero = (byte) (Math.random() * respuestas.size());

        opcion.setText(respuestas.get(tocaNumero));

        respuestas.remove(tocaNumero);

    }


    public void cogeRespuesta(View v, Pregunta actual)
    {

        boolean correcta = false;

        Button respuesta = (Button) v;


        if (actual.getRespuestaCorrecta().equals(respuesta.getText().toString()))
        {
            respuesta.setTextColor(Color.GREEN);



        }
        else
        {
            respuesta.setTextColor(Color.RED);

        }


    }

    public void tornaraBlanc(View v, Pregunta actual)
    {
        boolean correcta = false;
        Button respuesta = (Button) v;

        if (actual.getRespuestaCorrecta().equals(respuesta.getText().toString()))
        {
            respuesta.setTextColor(Color.WHITE);



        }
        else
        {
            respuesta.setTextColor(Color.WHITE);

        }


    }

}

