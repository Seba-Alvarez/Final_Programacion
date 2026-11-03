package com.parcial_programacion.persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.parcial_programacion.modelo.Respuesta;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RespuestaDAO {

    private static final String ARCHIVO =
            "respuestas.json";

    @SuppressWarnings("FieldMayBeFinal")
    private Gson gson;

    public RespuestaDAO() {

        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    // GUARDAR
    @SuppressWarnings("CallToPrintStackTrace")
    public void guardar(
            List<Respuesta> respuestas) {

        try (FileWriter writer =
                     new FileWriter(ARCHIVO)) {

            gson.toJson(respuestas, writer);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    // CARGAR
    public List<Respuesta> cargar() {

        try (FileReader reader =
                     new FileReader(ARCHIVO)) {

            Type tipo =
                    new TypeToken<
                            List<Respuesta>>() {
                    }.getType();

            List<Respuesta> respuestas =
                    gson.fromJson(reader, tipo);

            if (respuestas == null) {
                return new ArrayList<>();
            }

            return respuestas;

        } catch (IOException e) {

            return new ArrayList<>();
        }
    }
}
