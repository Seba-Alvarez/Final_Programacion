package com.parcial_programacion.facade;

import com.parcial_programacion.modelo.Cuestionario;
import com.parcial_programacion.modelo.Pregunta;
import com.parcial_programacion.modelo.Respuesta;
import com.parcial_programacion.persistencia.CuestionarioDAO;
import com.parcial_programacion.persistencia.RespuestaDAO;

import java.util.List;

public class SistemaFacade {

    @SuppressWarnings("FieldMayBeFinal")
    private List<Cuestionario> cuestionarios;

    @SuppressWarnings("FieldMayBeFinal")
    private List<Respuesta> respuestas;

    private int siguienteIdPregunta;

    @SuppressWarnings("FieldMayBeFinal")
    private CuestionarioDAO cuestionarioDAO;

    @SuppressWarnings("FieldMayBeFinal")
    private RespuestaDAO respuestaDAO;

    public SistemaFacade() {

        cuestionarioDAO =
                new CuestionarioDAO();

        respuestaDAO =
                new RespuestaDAO();

        cuestionarios =
                cuestionarioDAO.cargar();

        respuestas =
                respuestaDAO.cargar();

        siguienteIdPregunta =
                obtenerSiguienteIdPregunta();
    }

    // =========================
    // CUESTIONARIOS
    // =========================

    public void agregarCuestionario(
            String nombre) {

        Cuestionario cuestionario =
                new Cuestionario(nombre);

        cuestionarios.add(cuestionario);

        cuestionarioDAO.guardar(
                cuestionarios
        );
    }

    public List<Cuestionario> obtenerCuestionarios() {

        return cuestionarios;
    }

    public void eliminarCuestionario(
            Cuestionario cuestionario) {

        cuestionarios.remove(cuestionario);

        cuestionarioDAO.guardar(
                cuestionarios
        );
    }

    // =========================
    // PREGUNTAS
    // =========================

    public void agregarPregunta(
            Cuestionario cuestionario,
            String enunciado,
            String respuesta) {

        Pregunta pregunta =
                new Pregunta(
                        siguienteIdPregunta++,
                        enunciado,
                        respuesta
                );

        

        cuestionario.agregarPregunta(
                pregunta
        );

        cuestionarioDAO.guardar(
                cuestionarios
        );
    }

    public void eliminarPregunta(
        Cuestionario cuestionario,
        int id) {

    cuestionario.eliminarPregunta(id);

    cuestionarioDAO.guardar(
            cuestionarios);
    }


    // =========================
    // COMPROBAR RESPUESTA
    // =========================

    public boolean comprobarRespuesta(
            Pregunta pregunta,
            String respuesta) {

        return pregunta
                .getRespuestaCorrecta()
                .equalsIgnoreCase(
                        respuesta.trim()
                );
    }

    // =========================
    // GUARDAR RESPUESTA
    // =========================

    public void guardarRespuesta(
            String cedula,
            String nombreCuestionario,
            int aciertos) {

        Respuesta respuesta =
                new Respuesta(
                        cedula,
                        nombreCuestionario,
                        aciertos
                );

        respuestas.add(respuesta);

        respuestaDAO.guardar(
                respuestas
        );
    }

    // =========================
    // OBTENER RESPUESTAS
    // =========================

    public List<Respuesta> obtenerRespuestas() {

        return respuestas;
    }

    // =========================
    // ID DE PREGUNTAS
    // =========================

    private int obtenerSiguienteIdPregunta() {

        int mayorId = 0;

        for (Cuestionario cuestionario :
                cuestionarios) {

            for (Pregunta pregunta :
                    cuestionario.getPreguntas()) {

                if (pregunta.getId() > mayorId) {

                    mayorId = pregunta.getId();
                }
            }
        }

        return mayorId + 1;
    }
}
