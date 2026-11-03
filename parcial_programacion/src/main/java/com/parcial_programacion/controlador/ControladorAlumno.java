//id del paquete correspondiente
package com.parcial_programacion.controlador;

//import de la clase SistemaFacade
//permite comunicarse con la fachada y acceder a las operaciones del sistema
import com.parcial_programacion.facade.SistemaFacade;

//import de la vista correspondiente al alumno
//permite crear y mostrar la interfaz donde el alumno realiza los cuestionarios
import com.parcial_programacion.vista.AlumnoView;

//se encarga de gestionar la apertura de la interfaz del alumno
//y comunicarla con la fachada del sistema
public class ControladorAlumno {

    //atributo que almacena la referencia a SistemaFacade
    //se utiliza para acceder a las operaciones del sistema
    //recibe la instancia desde Main a través del constructor
    @SuppressWarnings("FieldMayBeFinal")
    private SistemaFacade facade;

    //atributo que almacena la referencia a la vista del alumno
    //permite crear y controlar la ventana donde se realizan los cuestionarios
    private AlumnoView vista;

    //constructor de la clase ControladorAlumno
    //recibe como parámetro una instancia de SistemaFacade
    //esta instancia es creada previamente en Main y se comparte con los controladores
    public ControladorAlumno(SistemaFacade facade) {

        //se asigna la fachada recibida al atributo de la clase
        //de esta manera el controlador puede acceder a sus métodos
        this.facade = facade;
    }

    //método para mostrar la interfaz correspondiente al alumno
    //es llamado desde MenuView cuando se selecciona la opción del alumno
    public void mostrarCuestionario() {

        //se crea una instancia de AlumnoView
        //se le pasa la fachada para que la vista pueda acceder a las operaciones
        //necesarias para obtener cuestionarios, comprobar respuestas y guardar resultados
        vista = new AlumnoView(facade);

        //se hace visible la ventana del alumno
        //a partir de este momento el alumno puede interactuar con la interfaz
        vista.setVisible(true);
    }
}
