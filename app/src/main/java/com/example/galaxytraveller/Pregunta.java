package com.example.galaxytraveller;

public class Pregunta {
    private String pregunta;

    private String respuestaCorrecta;

    private String respuestaInCorrecta1;

    private String respuestaInCorrecta2;

    private String respuestaInCorrecta3;

    private String dificultad;

    private String idioma;


    public Pregunta(String pregunta, String respuestaCorrecta,
                    String respuestaInCorrecta1, String respuestaInCorrecta2,
                    String respuestaInCorrecta3, String dificultad, String idioma) {
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        this.respuestaInCorrecta1 = respuestaInCorrecta1;
        this.respuestaInCorrecta2 = respuestaInCorrecta2;
        this.respuestaInCorrecta3 = respuestaInCorrecta3;
        this.dificultad = dificultad;
        this.idioma = idioma;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public String getRespuestaInCorrecta1() {
        return respuestaInCorrecta1;
    }

    public String getRespuestaIncorrecta2() {
        return respuestaInCorrecta2;
    }

    public String getRespuestaIncorrecta3() {
        return respuestaInCorrecta3;
    }

    public String getDificultad() {
        return dificultad;
    }

    public String getIdioma() {
        return idioma;
    }
}
