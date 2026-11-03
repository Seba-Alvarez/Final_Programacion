package com.parcial_programacion.modelo;

public class Respuesta {

    @SuppressWarnings("FieldMayBeFinal")
    private String cedula;
    @SuppressWarnings("FieldMayBeFinal")
    private String cuestionario;
    @SuppressWarnings("FieldMayBeFinal")
    private int aciertos;

    public Respuesta(
            String cedula,
            String cuestionario,
            int aciertos) {

        this.cedula = cedula;
        this.cuestionario = cuestionario;
        this.aciertos = aciertos;
    }

    public String getCedula() {
        return cedula;
    }

    public String getCuestionario() {
        return cuestionario;
    }

    public int getAciertos() {
        return aciertos;
    }
}

