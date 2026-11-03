package com.parcial_programacion.controlador;

import com.parcial_programacion.facade.SistemaFacade;
import com.parcial_programacion.vista.AlumnoView;

public class ControladorAlumno {

    @SuppressWarnings("FieldMayBeFinal")
    private SistemaFacade facade;
    private AlumnoView vista;

    public ControladorAlumno(SistemaFacade facade) {
        this.facade = facade;
    }

    public void mostrarCuestionario() {

        vista = new AlumnoView(facade);
        vista.setVisible(true);
    }
}
