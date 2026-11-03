package com.parcial_programacion.modelo;

import java.util.ArrayList;
import java.util.List;

public class Cuestionario {

    @SuppressWarnings("FieldMayBeFinal")
    private String nombre;
    @SuppressWarnings("FieldMayBeFinal")
    private List<Pregunta> preguntas;

    public Cuestionario(String nombre) {

        this.nombre = nombre;
        this.preguntas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void agregarPregunta(
            Pregunta pregunta) {

        preguntas.add(pregunta);
    }

    public void eliminarPregunta(int id) {

        preguntas.removeIf(
                pregunta -> pregunta.getId() == id
        );
    }

    @Override
    public String toString() {
        return nombre;
    }
}
