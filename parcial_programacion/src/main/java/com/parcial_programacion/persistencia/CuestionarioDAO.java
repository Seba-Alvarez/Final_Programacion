//id del paquete correspondiente
package com.parcial_programacion.persistencia;

//imports de Gson para convertir objetos Java a formato JSON y viceversa
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.parcial_programacion.modelo.Cuestionario;

//DAO significa Data Access Object
//se encarga de guardar y recuperar los cuestionarios utilizando un archivo JSON
public class CuestionarioDAO {

    //constante que almacena el nombre del archivo donde se guardan los cuestionarios
    //static indica que pertenece a la clase y no a cada instancia
    //final indica que su valor no puede modificarse
    private static final String ARCHIVO =
            "cuestionarios.json";

    //atributo que almacena una instancia de Gson
    //se utiliza para convertir objetos Java a JSON y JSON a objetos Java
    @SuppressWarnings("FieldMayBeFinal")
    private Gson gson;

    //constructor de la clase CuestionarioDAO
    //se ejecuta cuando SistemaFacade crea una instancia de esta clase
    //inicializa Gson con la configuración necesaria para trabajar con JSON
    public CuestionarioDAO() {

        //se crea una instancia de GsonBuilder
        //setPrettyPrinting() permite generar archivos JSON con formato legible
        //.create() construye y devuelve el objeto Gson configurado
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    //método para guardar la lista de cuestionarios en el archivo JSON
    //recibe como parámetro la lista de objetos Cuestionario que se desea almacenar
    //es llamado desde SistemaFacade después de agregar o eliminar cuestionarios o preguntas
    @SuppressWarnings("CallToPrintStackTrace")
    public void guardar(
            List<Cuestionario> cuestionarios) {

        //try-with-resources: abre el archivo para escritura y lo cierra automáticamente
        //FileWriter permite escribir información en el archivo indicado por ARCHIVO
        //si el archivo no existe, se crea
        //si ya existe, se reemplaza su contenido
        try (FileWriter writer =
                     new FileWriter(ARCHIVO)) {

            //se convierte la lista de cuestionarios a formato JSON
            //se escribe el resultado directamente en el archivo mediante writer
            gson.toJson(cuestionarios, writer);

        } catch (IOException e) {

            //si ocurre un error al escribir el archivo
            //se muestra la información del error en la consola
            e.printStackTrace();
        }
    }

    //método para cargar los cuestionarios almacenados en el archivo JSON
    //devuelve una lista de objetos Cuestionario
    //es llamado desde el constructor de SistemaFacade cuando se inicia el sistema
    public List<Cuestionario> cargar() {

        //try-with-resources: abre el archivo para lectura y lo cierra automáticamente
        //FileReader permite leer la información almacenada en el archivo JSON
        try (FileReader reader =
                     new FileReader(ARCHIVO)) {

            //se define el tipo de dato que Gson debe reconstruir
            //TypeToken permite indicar que se desea recuperar una lista de Cuestionario
            //esto es necesario porque List utiliza tipos genéricos
            Type tipo =
                    new TypeToken<
                            List<Cuestionario>>() {
                    }.getType();

            //se convierte el contenido JSON del archivo en una lista de objetos Cuestionario
            //se utiliza el tipo definido anteriormente para realizar la conversión
            List<Cuestionario> cuestionarios =
                    gson.fromJson(reader, tipo);

            //se comprueba si Gson devolvió null
            //esto puede ocurrir si el archivo JSON está vacío
            if (cuestionarios == null) {

                //se devuelve una lista vacía para evitar trabajar con un valor null
                return new ArrayList<>();
            }

            //si se cargaron correctamente los cuestionarios
            //se devuelve la lista obtenida desde el archivo
            return cuestionarios;

        } catch (IOException e) {

            //si ocurre un error al leer el archivo
            //por ejemplo, si todavía no existe
            //se devuelve una lista vacía para que el sistema pueda continuar
            return new ArrayList<>();
        }
    }
}
