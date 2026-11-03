package com.parcial_programacion.vista;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.parcial_programacion.facade.SistemaFacade;
import com.parcial_programacion.modelo.Cuestionario;
import com.parcial_programacion.modelo.Pregunta;

public class AlumnoView extends JFrame {

    private SistemaFacade facade;

    private JComboBox<Cuestionario> comboCuestionarios;

    private JTextField txtCedula;
    private JTextField txtRespuesta;

    private JLabel lblPregunta;

    private JButton btnComenzar;
    private JButton btnResponder;

    private Cuestionario cuestionario;

    private int indicePregunta = 0;
    private int correctas = 0;

    public AlumnoView(SistemaFacade facade) {

        this.facade = facade;

        setTitle("Cuestionario Alumno");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        crearInterfaz();
        cargarCuestionarios();
    }

    private void crearInterfaz() {

        setLayout(new GridLayout(6, 1));

        // =========================
        // CUESTIONARIO
        // =========================

        JPanel panelCuestionario =
                new JPanel(new FlowLayout());

        panelCuestionario.add(
                new JLabel("Cuestionario:")
        );

        comboCuestionarios =
                new JComboBox<>();

        panelCuestionario.add(
                comboCuestionarios
        );

        add(panelCuestionario);

        // =========================
        // CEDULA
        // =========================

        JPanel panelCedula =
                new JPanel(new FlowLayout());

        panelCedula.add(
                new JLabel("Cédula:")
        );

        txtCedula =
                new JTextField(15);

        panelCedula.add(txtCedula);

        add(panelCedula);

        // =========================
        // COMENZAR
        // =========================

        btnComenzar =
                new JButton("Comenzar");

        add(btnComenzar);

        // =========================
        // PREGUNTA
        // =========================

        lblPregunta =
                new JLabel();

        add(lblPregunta);

        // =========================
        // RESPUESTA
        // =========================

        txtRespuesta =
                new JTextField();

        add(txtRespuesta);

        // =========================
        // RESPONDER
        // =========================

        btnResponder =
                new JButton("Responder");

        add(btnResponder);

        btnResponder.setEnabled(false);

        // =========================
        // EVENTOS
        // =========================

        btnComenzar.addActionListener(e ->
                comenzarCuestionario()
        );

        btnResponder.addActionListener(e ->
                responder()
        );
    }

    private void cargarCuestionarios() {

        comboCuestionarios.removeAllItems();

        for (Cuestionario cuestionario :
                facade.obtenerCuestionarios()) {

            comboCuestionarios.addItem(
                    cuestionario
            );
        }
    }

    private void comenzarCuestionario() {

        cuestionario =
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

        if (txtCedula.getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese su cédula"
            );

            return;
        }

        if (cuestionario
                .getPreguntas()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Este cuestionario no tiene preguntas"
            );

            return;
        }

        indicePregunta = 0;
        correctas = 0;

        comboCuestionarios
                .setEnabled(false);

        txtCedula
                .setEnabled(false);

        btnComenzar
                .setEnabled(false);

        btnResponder
                .setEnabled(true);

        mostrarPregunta();
    }

    private void mostrarPregunta() {

        if (indicePregunta >=
                cuestionario
                        .getPreguntas()
                        .size()) {

            finalizarCuestionario();

            return;
        }

        Pregunta pregunta =
                cuestionario
                        .getPreguntas()
                        .get(indicePregunta);

        lblPregunta.setText(
                "Pregunta "
                + (indicePregunta + 1)
                + ": "
                + pregunta.getEnunciado()
        );

        txtRespuesta.setText("");

        txtRespuesta.requestFocus();
    }

    private void responder() {

        String respuesta =
                txtRespuesta
                        .getText()
                        .trim();

        if (respuesta.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese una respuesta"
            );

            return;
        }

        Pregunta pregunta =
                cuestionario
                        .getPreguntas()
                        .get(indicePregunta);

        boolean correcta =
                facade.comprobarRespuesta(
                        pregunta,
                        respuesta
                );

        if (correcta) {

            correctas++;

            JOptionPane.showMessageDialog(
                    this,
                    "Respuesta correcta"
            );

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Respuesta incorrecta"
            );
        }

        indicePregunta++;

        mostrarPregunta();
    }

    private void finalizarCuestionario() {

        // Guardar resultado
        facade.guardarRespuesta(
        txtCedula.getText().trim(),
        cuestionario.getNombre(),
        correctas
);

        JOptionPane.showMessageDialog(
                this,
                "Cuestionario terminado.\n"
                + "Cédula: "
                + txtCedula.getText()
                + "\nCuestionario: "
                + cuestionario.getNombre()
                + "\nCorrectas: "
                + correctas
                + " de "
                + cuestionario
                        .getPreguntas()
                        .size()
        );

        dispose();
    }
}
