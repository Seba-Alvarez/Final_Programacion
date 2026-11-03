//id del paquete correspondiente
package com.parcial_programacion.modelo;

//import de ArrayList para crear listas dinámicas
import java.util.ArrayList;
import java.util.List;

//representa un cuestionario dentro del sistema
//almacena su nombre y las preguntas que lo componen
public class Cuestionario {

    //atributo que almacena el nombre del cuestionario
    //se inicializa mediante el constructor
    @SuppressWarnings("FieldMayBeFinal")
    private String nombre;

    //atributo que almacena las preguntas pertenecientes al cuestionario
    //cada elemento de la lista es un objeto de la clase Pregunta
    //se utiliza ArrayList para poder agregar y eliminar preguntas dinámicamente
    @SuppressWarnings("FieldMayBeFinal")
    private List<Pregunta> preguntas;

    //constructor de la clase Cuestionario
    //recibe como parámetro el nombre que tendrá el cuestionario
    //es utilizado desde SistemaFacade cuando se da de alta un nuevo cuestionario
    public Cuestionario(String nombre) {

        //se asigna el nombre recibido al atributo de la clase
        //this.nombre hace referencia al atributo y nombre al parámetro recibido
        this.nombre = nombre;

        //se inicializa la lista de preguntas como una nueva lista vacía
        //se utiliza ArrayList, que implementa la interfaz List
        //cada cuestionario comienza sin preguntas
        this.preguntas = new ArrayList<>();
    }

    //método getter para obtener el nombre del cuestionario
    //permite que otras clases accedan al atributo privado nombre
    public String getNombre() {
        return nombre;
    }

    //método getter para obtener la lista de preguntas del cuestionario
    //devuelve la colección de objetos Pregunta asociados al cuestionario
    //es utilizado, por ejemplo, desde SistemaFacade para recorrer las preguntas
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    //método para agregar una pregunta al cuestionario
    //recibe un objeto Pregunta como parámetro
    //es llamado desde SistemaFacade cuando se registra una nueva pregunta
    public void agregarPregunta(
            Pregunta pregunta) {

        //se incorpora el objeto Pregunta a la lista de preguntas
        //la pregunta queda asociada al cuestionario actual
        preguntas.add(pregunta);
    }

    //método para eliminar una pregunta del cuestionario
    //recibe como parámetro el ID de la pregunta que se desea eliminar
    //es llamado desde SistemaFacade cuando se solicita una baja de pregunta
    public void eliminarPregunta(int id) {

        //removeIf recorre la lista y elimina los elementos que cumplen la condición
        //pregunta representa cada objeto Pregunta de la lista
        //getId() obtiene el identificador de cada pregunta
        //se elimina aquella cuyo ID coincide con el recibido por parámetro
        preguntas.removeIf(
                pregunta -> pregunta.getId() == id
        );
    }

    //sobrescritura del método toString() heredado de la clase Object
    //permite que cuando se muestre un objeto Cuestionario como texto
    //se visualice directamente su nombre en lugar de una referencia al objeto
    //resulta útil cuando se muestran cuestionarios en componentes de la interfaz
    @Override
    public String toString() {
        return nombre;
    }
}
