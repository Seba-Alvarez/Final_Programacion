//id del paquete correspondiente
package com.parcial_programacion.controlador;

//import de la clase SistemaFacade
//permite comunicarse con la fachada y acceder a las operaciones del sistema
import com.parcial_programacion.facade.SistemaFacade;
import com.parcial_programacion.vista.DocenteView;

//se encarga de gestionar el acceso del docente a su interfaz
//y comunicar la vista con la fachada del sistema
public class ControladorDocente {

    //atributo que almacena la referencia a SistemaFacade
    //permite acceder a las operaciones del sistema, como agregar o eliminar cuestionarios y preguntas
    //recibe la instancia desde Main a través del constructor
    @SuppressWarnings("FieldMayBeFinal")
    private SistemaFacade facade;

    //atributo que almacena la referencia a la vista del docente
    //permite crear y controlar la ventana donde se administran los cuestionarios
    private DocenteView vista;

    //constructor de la clase ControladorDocente
    //recibe como parámetro una instancia de SistemaFacade
    //esta instancia es creada previamente en Main y compartida con los demás controladores
    public ControladorDocente(SistemaFacade facade) {

        //se asigna la fachada recibida al atributo de la clase
        //de esta manera el controlador puede utilizar sus métodos
        this.facade = facade;
    }

    //método para mostrar el menú correspondiente al docente
    //es llamado desde MenuView cuando se selecciona la opción del docente
    public void mostrarMenu() {

        //se crea una instancia de DocenteView
        //se le pasan dos parámetros:
        //this representa el controlador actual (ControladorDocente)
        //facade representa la fachada que permite acceder a las operaciones del sistema
        //de esta manera la vista puede comunicarse tanto con el controlador como con la fachada
        vista = new DocenteView(this, facade);

        //se hace visible la ventana del docente
        //a partir de este momento el docente puede interactuar con la interfaz
        vista.setVisible(true);
    }
}
