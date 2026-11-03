package com.parcial_programacion.modelo;

public class Pregunta {

    @SuppressWarnings("FieldMayBeFinal")
    private int id;
    private String enunciado;
    private String respuestaCorrecta;

    public Pregunta(int id, String enunciado, String respuestaCorrecta) {
        this.id = id;
        this.enunciado = enunciado;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public int getId() {
        return id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    @Override
    public String toString() {
        return id + " - " + enunciado;
    }
}
