package com.parcial_programacion.vista;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.parcial_programacion.controlador.ControladorDocente;
import com.parcial_programacion.facade.SistemaFacade;
import com.parcial_programacion.modelo.Cuestionario;
import com.parcial_programacion.modelo.Pregunta;

public class DocenteView extends JFrame {

    @SuppressWarnings("FieldMayBeFinal")
    private SistemaFacade facade;

    private JTextField txtNombreCuestionario;
    private JTextField txtPregunta;
    private JTextField txtRespuesta;
    private JTextField txtIdPregunta;

    private JComboBox<Cuestionario> comboCuestionarios;

    private JTextArea areaPreguntas;

    public DocenteView(
            ControladorDocente controlador,
            SistemaFacade facade) {

        this.facade = facade;

        setTitle("Panel Docente");
        setSize(600, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        crearInterfaz();
        actualizarCuestionarios();
    }

    private void crearInterfaz() {

        JPanel panel =
                new JPanel(new GridLayout(0, 2));

        // =========================
        // CUESTIONARIO
        // =========================

        panel.add(
                new JLabel("Nombre cuestionario:")
        );

        txtNombreCuestionario =
                new JTextField();

        panel.add(
                txtNombreCuestionario
        );

        JButton btnAgregarCuestionario =
                new JButton("Crear cuestionario");

        JButton btnEliminarCuestionario =
                new JButton("Eliminar cuestionario");

        panel.add(
                btnAgregarCuestionario
        );

        panel.add(
                btnEliminarCuestionario
        );

        panel.add(
                new JLabel("Cuestionario:")
        );

        comboCuestionarios =
                new JComboBox<>();

        panel.add(
                comboCuestionarios
        );

        // =========================
        // PREGUNTA
        // =========================

        panel.add(
                new JLabel("Pregunta:")
        );

        txtPregunta =
                new JTextField();

        panel.add(
                txtPregunta
        );

        panel.add(
                new JLabel("Respuesta correcta:")
        );

        txtRespuesta =
                new JTextField();

        panel.add(
                txtRespuesta
        );

        JButton btnAgregarPregunta =
                new JButton("Agregar pregunta");

        panel.add(
                btnAgregarPregunta
        );

        // Espacio vacío para mantener
        // el diseño de dos columnas
        panel.add(
                new JLabel("")
        );

        // =========================
        // ELIMINAR PREGUNTA
        // =========================

        panel.add(
                new JLabel("ID pregunta:")
        );

        txtIdPregunta =
                new JTextField();

        panel.add(
                txtIdPregunta
        );

        JButton btnEliminarPregunta =
                new JButton("Eliminar pregunta");

        panel.add(
                btnEliminarPregunta
        );

        panel.add(
                new JLabel("")
        );

        add(
                panel,
                BorderLayout.NORTH
        );

        // =========================
        // LISTA DE PREGUNTAS
        // =========================

        areaPreguntas =
                new JTextArea();

        areaPreguntas.setEditable(false);

        add(
                new JScrollPane(areaPreguntas),
                BorderLayout.CENTER
        );

        // =========================
        // CREAR CUESTIONARIO
        // =========================

        btnAgregarCuestionario.addActionListener(e -> {

            String nombre =
                    txtNombreCuestionario
                            .getText()
                            .trim();

            if (nombre.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese un nombre"
                );

                return;
            }

            facade.agregarCuestionario(
                    nombre
            );

            txtNombreCuestionario.setText("");

            actualizarCuestionarios();
        });

        // =========================
        // ELIMINAR CUESTIONARIO
        // =========================

        btnEliminarCuestionario.addActionListener(e -> {

            Cuestionario cuestionario =
                    (Cuestionario)
                            comboCuestionarios
                                    .getSelectedItem();

            if (cuestionario == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un cuestionario"
                );

                return;
            }

            int confirmar =
                    JOptionPane.showConfirmDialog(
                            this,
                            "¿Está seguro de eliminar el cuestionario?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION
                    );

            if (confirmar != JOptionPane.YES_OPTION) {
                return;
            }

            facade.eliminarCuestionario(
                    cuestionario
            );

            actualizarCuestionarios();
        });

        // =========================
        // CAMBIAR CUESTIONARIO
        // =========================

        comboCuestionarios.addActionListener(e ->
                actualizarPreguntas()
        );

        // =========================
        // AGREGAR PREGUNTA
        // =========================

        btnAgregarPregunta.addActionListener(e -> {

            Cuestionario cuestionario =
                    (Cuestionario)
                            comboCuestionarios
                                    .getSelectedItem();

            if (cuestionario == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un cuestionario"
                );

                return;
            }

            String pregunta =
                    txtPregunta
                            .getText()
                            .trim();

            String respuesta =
                    txtRespuesta
                            .getText()
                            .trim();

            if (pregunta.isEmpty()
                    || respuesta.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Complete todos los campos"
                );

                return;
            }

            facade.agregarPregunta(
                    cuestionario,
                    pregunta,
                    respuesta
            );

            txtPregunta.setText("");
            txtRespuesta.setText("");

            actualizarPreguntas();
        });

        // =========================
        // ELIMINAR PREGUNTA
        // =========================

        btnEliminarPregunta.addActionListener(e -> {

            Cuestionario cuestionario =
                    (Cuestionario)
                            comboCuestionarios
                                    .getSelectedItem();

            if (cuestionario == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un cuestionario"
                );

                return;
            }

            String textoId =
                    txtIdPregunta
                            .getText()
                            .trim();

            if (textoId.isEmpty()) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese el ID de la pregunta"
                );

                return;
            }

            try {

                int id =
                        Integer.parseInt(
                                textoId
                        );

                boolean existe = false;

                for (Pregunta pregunta :
                        cuestionario.getPreguntas()) {

                    if (pregunta.getId() == id) {
                        existe = true;
                        break;
                    }
                }

                if (!existe) {

                    JOptionPane.showMessageDialog(
                            this,
                            "No existe una pregunta con ese ID"
                    );

                    return;
                }

                int confirmar =
                        JOptionPane.showConfirmDialog(
                                this,
                                "¿Está seguro de eliminar la pregunta?",
                                "Confirmar eliminación",
                                JOptionPane.YES_NO_OPTION
                        );

                if (confirmar !=
                        JOptionPane.YES_OPTION) {

                    return;
                }

                facade.eliminarPregunta(
                        cuestionario,
                        id
                );

                txtIdPregunta.setText("");

                actualizarPreguntas();

            } catch (NumberFormatException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese un ID válido"
                );
            }
        });
    }

    private void actualizarCuestionarios() {

        comboCuestionarios.removeAllItems();

        for (Cuestionario cuestionario :
                facade.obtenerCuestionarios()) {

            comboCuestionarios.addItem(
                    cuestionario
            );
        }

        actualizarPreguntas();
    }

    private void actualizarPreguntas() {

        areaPreguntas.setText("");

        Cuestionario cuestionario =
                (Cuestionario)
                        comboCuestionarios
                                .getSelectedItem();

        if (cuestionario == null) {
            return;
        }

        for (Pregunta pregunta :
                cuestionario.getPreguntas()) {

            areaPreguntas.append(
                    "ID: "
                    + pregunta.getId()
                    + "\n"
                    + "Pregunta: "
                    + pregunta.getEnunciado()
                    + "\n"
                    + "Respuesta correcta: "
                    + pregunta.getRespuestaCorrecta()
                    + "\n"
                    + "--------------------------\n"
            );
        }
    }
}
