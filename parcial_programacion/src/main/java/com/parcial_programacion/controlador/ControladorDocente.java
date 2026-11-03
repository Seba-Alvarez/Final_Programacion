package com.parcial_programacion.controlador;

import com.parcial_programacion.facade.SistemaFacade;
import com.parcial_programacion.vista.DocenteView;

public class ControladorDocente {

    @SuppressWarnings("FieldMayBeFinal")
    private SistemaFacade facade;
    private DocenteView vista;

    public ControladorDocente(SistemaFacade facade) {
        this.facade = facade;
    }

    public void mostrarMenu() {
        vista = new DocenteView(this, facade);
        vista.setVisible(true);
    }
}
