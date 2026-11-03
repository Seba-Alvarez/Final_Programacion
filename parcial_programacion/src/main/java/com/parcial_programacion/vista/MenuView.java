
package com.parcial_programacion.vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.parcial_programacion.controlador.ControladorAlumno;
import com.parcial_programacion.controlador.ControladorDocente;

public class MenuView extends JFrame {

    private final Color fondo = new Color(240, 244, 250);
    private final Color azul = new Color(45, 91, 155);
    private final Color verde = new Color(39, 150, 115);
    private final Color texto = new Color(45, 55, 72);

    public MenuView(
            ControladorDocente controladorDocente,
            ControladorAlumno controladorAlumno) {

        setTitle("Sistema de Preguntas");
        setSize(520, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setBackground(fondo);
        panelPrincipal.setLayout(new BoxLayout(
                panelPrincipal, BoxLayout.Y_AXIS
        ));
        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        );

        // Título
        JLabel titulo = new JLabel("Sistema de Preguntas");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 27));
        titulo.setForeground(azul);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtítulo
        JLabel subtitulo = new JLabel("¡Bienvenido!");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 17));
        subtitulo.setForeground(texto);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instruccion = new JLabel(
                "Seleccioná tu tipo de usuario"
        );
        instruccion.setFont(new Font("SansSerif", Font.PLAIN, 14));
        instruccion.setForeground(new Color(100, 110, 125));
        instruccion.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Botones
        JButton btnDocente = crearBoton(
                "DOCENTE", azul
        );

        JButton btnAlumno = crearBoton(
                "ALUMNO", verde
        );

        btnDocente.addActionListener(e ->
                controladorDocente.mostrarMenu()
        );

        btnAlumno.addActionListener(e ->
                controladorAlumno.mostrarCuestionario()
        );

        // Espaciado y distribución
        panelPrincipal.add(titulo);
        panelPrincipal.add(Box.createVerticalStrut(8));
        panelPrincipal.add(subtitulo);
        panelPrincipal.add(Box.createVerticalStrut(25));
        panelPrincipal.add(instruccion);
        panelPrincipal.add(Box.createVerticalStrut(20));
        panelPrincipal.add(btnDocente);
        panelPrincipal.add(Box.createVerticalStrut(12));
        panelPrincipal.add(btnAlumno);

        add(panelPrincipal);

        setVisible(true);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);

        boton.setFont(new Font("SansSerif", Font.BOLD, 15));
        boton.setForeground(Color.WHITE);
        boton.setBackground(color);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setOpaque(true);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);
        boton.setMaximumSize(new Dimension(300, 45));
        boton.setPreferredSize(new Dimension(300, 45));
        boton.setBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        );

        return boton;
    }
}
