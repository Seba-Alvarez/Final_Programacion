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
import com.parcial_programacion.modelo.Respuesta;

//DAO significa Data Access Object
//se encarga de guardar y recuperar los resultados de los alumnos
//utilizando un archivo JSON
public class RespuestaDAO {

    //constante que almacena el nombre del archivo donde se guardan los resultados
    //static indica que pertenece a la clase y no a cada instancia
    //final indica que su valor no puede modificarse
    private static final String ARCHIVO =
            "respuestas.json";

    //atributo que almacena una instancia de Gson
    //se utiliza para convertir objetos Java a JSON y JSON a objetos Java
    @SuppressWarnings("FieldMayBeFinal")
    private Gson gson;

    //constructor de la clase RespuestaDAO
    //se ejecuta cuando SistemaFacade crea una instancia de esta clase
    //inicializa Gson con la configuración necesaria para trabajar con JSON
    public RespuestaDAO() {

        //se crea una instancia de GsonBuilder
        //setPrettyPrinting() permite generar archivos JSON con formato legible
        //.create() construye y devuelve el objeto Gson configurado
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    // =========================
    // GUARDAR
    // =========================

    //método para guardar la lista de resultados en el archivo JSON
    //recibe como parámetro una lista de objetos Respuesta
    //es llamado desde SistemaFacade cuando se registra un nuevo resultado
    @SuppressWarnings("CallToPrintStackTrace")
    public void guardar(
            List<Respuesta> respuestas) {

        //try-with-resources: abre el archivo para escritura y lo cierra automáticamente
        //FileWriter permite escribir información en el archivo indicado por ARCHIVO
        //si el archivo no existe, se crea
        //si ya existe, se reemplaza su contenido
        try (FileWriter writer =
                     new FileWriter(ARCHIVO)) {

            //se convierte la lista de resultados a formato JSON
            //se escribe la información directamente en el archivo mediante writer
            gson.toJson(respuestas, writer);

        } catch (IOException e) {

            //si ocurre un error al escribir el archivo
            //se muestra la información del error en la consola
            e.printStackTrace();
        }
    }

    // =========================
    // CARGAR
    // =========================

    //método para cargar los resultados almacenados en el archivo JSON
    //devuelve una lista de objetos Respuesta
    //es llamado desde el constructor de SistemaFacade cuando se inicia el sistema
    public List<Respuesta> cargar() {

        //try-with-resources: abre el archivo para lectura y lo cierra automáticamente
        //FileReader permite leer la información almacenada en el archivo JSON
        try (FileReader reader =
                     new FileReader(ARCHIVO)) {

            //se define el tipo de dato que Gson debe reconstruir
            //TypeToken permite indicar que se desea recuperar una lista de Respuesta
            //esto es necesario porque List utiliza tipos genéricos
            Type tipo =
                    new TypeToken<
                            List<Respuesta>>() {
                    }.getType();

            //se convierte el contenido JSON del archivo en una lista de objetos Respuesta
            //se utiliza el tipo definido anteriormente para realizar la conversión
            List<Respuesta> respuestas =
                    gson.fromJson(reader, tipo);

            //se comprueba si Gson devolvió null
            //esto puede ocurrir si el archivo JSON está vacío
            if (respuestas == null) {

                //se devuelve una lista vacía para evitar trabajar con un valor null
                return new ArrayList<>();
            }

            //si se cargaron correctamente los resultados
            //se devuelve la lista obtenida desde el archivo
            return respuestas;

        } catch (IOException e) {

            //si ocurre un error al leer el archivo
            //por ejemplo, si todavía no existe
            //se devuelve una lista vacía para que el sistema pueda continuar
            return new ArrayList<>();
        }
    }
}
