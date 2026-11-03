//id del paquete correspondiente
package com.parcial_programacion;
//imports de las clases utilizadas
import com.parcial_programacion.controlador.ControladorAlumno;
import com.parcial_programacion.controlador.ControladorDocente;
import com.parcial_programacion.facade.SistemaFacade;
import com.parcial_programacion.vista.MenuView;

//clase main
public class Main {
    //método main
    public static void main(String[] args) {
        //instancia a la clase facade
        SistemaFacade facade =
                new SistemaFacade();
        //instancias del docente y el alumno
        ControladorDocente controladorDocente =
                new ControladorDocente(facade);

        ControladorAlumno controladorAlumno =
                new ControladorAlumno(facade);
        //llama a la interfaz visual
        new MenuView(
                controladorDocente,
                controladorAlumno
        );
    }
}

