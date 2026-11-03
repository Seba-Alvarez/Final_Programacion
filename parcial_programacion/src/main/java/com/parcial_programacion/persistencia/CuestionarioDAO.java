package com.parcial_programacion.persistencia;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.parcial_programacion.modelo.Cuestionario;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CuestionarioDAO {

    private static final String ARCHIVO =
            "cuestionarios.json";

    @SuppressWarnings("FieldMayBeFinal")
    private Gson gson;

    public CuestionarioDAO() {

        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void guardar(
            List<Cuestionario> cuestionarios) {

        try (FileWriter writer =
                     new FileWriter(ARCHIVO)) {

            gson.toJson(cuestionarios, writer);

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public List<Cuestionario> cargar() {

        try (FileReader reader =
                     new FileReader(ARCHIVO)) {

            Type tipo =
                    new TypeToken<
                            List<Cuestionario>>() {
                    }.getType();

            List<Cuestionario> cuestionarios =
                    gson.fromJson(reader, tipo);

            if (cuestionarios == null) {
                return new ArrayList<>();
            }

            return cuestionarios;

        } catch (IOException e) {

            return new ArrayList<>();
        }
    }
}
