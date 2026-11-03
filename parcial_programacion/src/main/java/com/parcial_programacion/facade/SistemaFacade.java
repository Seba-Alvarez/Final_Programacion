//id del paquete correspondiente
package com.parcial_programacion.facade;

//imports de las clases del modelo utilizadas
import com.parcial_programacion.modelo.Cuestionario;
import com.parcial_programacion.modelo.Pregunta;
import com.parcial_programacion.modelo.Respuesta;

//imports de las clases de persistencia encargadas de guardar y cargar los datos
import com.parcial_programacion.persistencia.CuestionarioDAO;
import com.parcial_programacion.persistencia.RespuestaDAO;

//import de la interfaz List para trabajar con colecciones de objetos
import java.util.List;

//clase SistemaFacade
//funciona como fachada del sistema, centralizando las operaciones
//y comunicando los controladores con el modelo y la persistencia
public class SistemaFacade {

    //lista que almacena los cuestionarios del sistema
    //los datos se cargan desde CuestionarioDAO al iniciar el sistema
    @SuppressWarnings("FieldMayBeFinal")
    private List<Cuestionario> cuestionarios;

    //lista que almacena las respuestas registradas por los alumnos
    //los datos se cargan desde RespuestaDAO al iniciar el sistema
    @SuppressWarnings("FieldMayBeFinal")
    private List<Respuesta> respuestas;

    //variable que almacena el próximo identificador que se asignará a una pregunta
    private int siguienteIdPregunta;

    //atributo que permite comunicarse con la clase CuestionarioDAO
    //se encarga de cargar y guardar los cuestionarios y sus preguntas
    @SuppressWarnings("FieldMayBeFinal")
    private CuestionarioDAO cuestionarioDAO;

    //atributo que permite comunicarse con la clase RespuestaDAO
    //se encarga de cargar y guardar las respuestas de los alumnos
    @SuppressWarnings("FieldMayBeFinal")
    private RespuestaDAO respuestaDAO;

    //constructor de la clase SistemaFacade
    //se ejecuta cuando se crea una instancia de esta clase
    //recibe los datos desde los archivos de persistencia
    public SistemaFacade() {

        //se crea una instancia de CuestionarioDAO
        //esta clase se utiliza para leer y escribir los cuestionarios
        cuestionarioDAO =
                new CuestionarioDAO();

        //se crea una instancia de RespuestaDAO
        //esta clase se utiliza para leer y escribir las respuestas
        respuestaDAO =
                new RespuestaDAO();

        //se cargan los cuestionarios almacenados
        //la información proviene del método cargar() de CuestionarioDAO
        cuestionarios =
                cuestionarioDAO.cargar();

        //se cargan las respuestas almacenadas
        //la información proviene del método cargar() de RespuestaDAO
        respuestas =
                respuestaDAO.cargar();

        //se obtiene el siguiente identificador disponible para las preguntas
        //se llama al método privado obtenerSiguienteIdPregunta()
        siguienteIdPregunta =
                obtenerSiguienteIdPregunta();
    }

    // =========================
    // CUESTIONARIOS
    // =========================

    //método para dar de alta un nuevo cuestionario
    //recibe el nombre del cuestionario como parámetro
    //es invocado desde el controlador que gestione los cuestionarios
    public void agregarCuestionario(
            String nombre) {

        //se crea una instancia de la clase Cuestionario
        //se utiliza su constructor para asignarle el nombre recibido
        Cuestionario cuestionario =
                new Cuestionario(nombre);

        //se agrega el nuevo cuestionario a la lista de cuestionarios
        cuestionarios.add(cuestionario);

        //se guardan los cuestionarios actualizados
        //se conecta con CuestionarioDAO para persistir los cambios
        cuestionarioDAO.guardar(
                cuestionarios
        );
    }

    //método para obtener todos los cuestionarios registrados
    //devuelve la lista de cuestionarios almacenada en la fachada
    //puede ser utilizado por un controlador para mostrar los cuestionarios en la vista
    public List<Cuestionario> obtenerCuestionarios() {

        return cuestionarios;
    }

    //método para eliminar un cuestionario
    //recibe el objeto Cuestionario que se desea eliminar
    public void eliminarCuestionario(
            Cuestionario cuestionario) {

        //se elimina el cuestionario de la lista
        cuestionarios.remove(cuestionario);

        //se guardan los cambios mediante CuestionarioDAO
        //de esta manera la eliminación también queda registrada en la persistencia
        cuestionarioDAO.guardar(
                cuestionarios
        );
    }

    // =========================
    // PREGUNTAS
    // =========================

    //método para dar de alta una nueva pregunta dentro de un cuestionario
    //recibe el cuestionario al que pertenece, el enunciado y la respuesta correcta
    //el controlador correspondiente solicita esta operación a la fachada
    public void agregarPregunta(
            Cuestionario cuestionario,
            String enunciado,
            String respuesta) {

        //se crea una instancia de la clase Pregunta
        //se utiliza su constructor, enviándole el ID, el enunciado y la respuesta correcta
        //siguienteIdPregunta++ asigna el ID actual y luego incrementa la variable
        Pregunta pregunta =
                new Pregunta(
                        siguienteIdPregunta++,
                        enunciado,
                        respuesta
                );

        //se agrega la pregunta al cuestionario correspondiente
        //se utiliza el método agregarPregunta() de la clase Cuestionario
        cuestionario.agregarPregunta(
                pregunta
        );

        //se guardan los cuestionarios actualizados
        //la pregunta queda almacenada junto con el cuestionario al que pertenece
        //se utiliza CuestionarioDAO para guardar los cambios
        cuestionarioDAO.guardar(
                cuestionarios
        );
    }

    //método para eliminar una pregunta de un cuestionario
    //recibe el cuestionario y el ID de la pregunta que se desea eliminar
    public void eliminarPregunta(
        Cuestionario cuestionario,
        int id) {

        //se llama al método eliminarPregunta() de la clase Cuestionario
        //la eliminación de la pregunta se realiza dentro de esa clase
        //utilizando el ID recibido para identificarla
        cuestionario.eliminarPregunta(id);

        //se guardan los cuestionarios actualizados
        //se utiliza CuestionarioDAO para persistir la eliminación
        cuestionarioDAO.guardar(
                cuestionarios);
    }


    // =========================
    // COMPROBAR RESPUESTA
    // =========================

    //método para comprobar si la respuesta ingresada por un alumno es correcta
    //recibe la pregunta que se está respondiendo y el texto ingresado por el alumno
    //devuelve true si la respuesta coincide y false si es incorrecta
    public boolean comprobarRespuesta(
            Pregunta pregunta,
            String respuesta) {

        //se obtiene la respuesta correcta desde el objeto Pregunta
        //se utiliza getRespuestaCorrecta() para acceder al atributo correspondiente
        //equalsIgnoreCase() compara los textos sin distinguir mayúsculas y minúsculas
        //trim() elimina los espacios al principio y al final de la respuesta ingresada
        return pregunta
                .getRespuestaCorrecta()
                .equalsIgnoreCase(
                        respuesta.trim()
                );
    }

    // =========================
    // GUARDAR RESPUESTA
    // =========================

    //método para registrar el resultado de un alumno al completar un cuestionario
    //recibe la cédula del alumno, el nombre del cuestionario y la cantidad de aciertos
    //el controlador del alumno solicita esta operación cuando termina el cuestionario
    public void guardarRespuesta(
            String cedula,
            String nombreCuestionario,
            int aciertos) {

        //se crea una instancia de la clase Respuesta
        //se utiliza su constructor enviándole los datos del resultado obtenido
        Respuesta respuesta =
                new Respuesta(
                        cedula,
                        nombreCuestionario,
                        aciertos
                );

        //se agrega el resultado a la lista de respuestas
        respuestas.add(respuesta);

        //se guardan todas las respuestas actualizadas
        //se conecta con RespuestaDAO para almacenar los resultados
        respuestaDAO.guardar(
                respuestas
        );
    }

    // =========================
    // OBTENER RESPUESTAS
    // =========================

    //método para obtener la lista de respuestas registradas
    //devuelve todos los resultados almacenados
    //puede ser utilizado por un controlador para consultar los resultados de los alumnos
    public List<Respuesta> obtenerRespuestas() {

        return respuestas;
    }

    // =========================
    // ID DE PREGUNTAS
    // =========================

    //método privado para calcular el próximo ID disponible de una pregunta
    //se ejecuta desde el constructor de SistemaFacade
    //permite evitar que los nuevos identificadores se repitan al reiniciar el sistema
    private int obtenerSiguienteIdPregunta() {

        //variable que almacena el mayor ID encontrado
        //se inicializa en cero
        int mayorId = 0;

        //recorre todos los cuestionarios almacenados
        for (Cuestionario cuestionario :
                cuestionarios) {

            //recorre todas las preguntas pertenecientes a cada cuestionario
            for (Pregunta pregunta :
                    cuestionario.getPreguntas()) {

                //se compara el ID de cada pregunta con el mayor ID encontrado
                if (pregunta.getId() > mayorId) {

                    //si encuentra un ID mayor, actualiza el valor de mayorId
                    mayorId = pregunta.getId();
                }
            }
        }

        //devuelve el mayor ID encontrado más uno
        //ese será el identificador que se utilizará para la próxima pregunta
        return mayorId + 1;
    }
}
