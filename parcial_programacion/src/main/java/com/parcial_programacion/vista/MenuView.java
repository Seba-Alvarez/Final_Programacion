//id del paquete correspondiente
package com.parcial_programacion.vista;

//imports de AWT para trabajar con colores, componentes, cursores,
//dimensiones y fuentes de texto
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

//clase MenuView
//pertenece a la capa Vista
//hereda de JFrame, por lo tanto representa una ventana gráfica
//es el menú principal del sistema y permite seleccionar el tipo de usuario
public class MenuView extends JFrame {

    //atributo que almacena el color de fondo de la ventana
    //se utiliza para mantener un diseño visual uniforme
    private final Color fondo = new Color(240, 244, 250);

    //atributo que almacena el color azul utilizado principalmente para el docente
    private final Color azul = new Color(45, 91, 155);

    //atributo que almacena el color verde utilizado principalmente para el alumno
    private final Color verde = new Color(39, 150, 115);

    //atributo que almacena el color utilizado para los textos
    private final Color texto = new Color(45, 55, 72);

    //constructor de la clase MenuView
    //recibe dos controladores como parámetros:
    //ControladorDocente y ControladorAlumno
    //ambos son enviados desde Main al crear la ventana principal
    //permite establecer la conexión entre los botones y los controladores
    public MenuView(
            ControladorDocente controladorDocente,
            ControladorAlumno controladorAlumno) {

        //se establece el título de la ventana principal
        setTitle("Sistema de Preguntas");

        //se establece el ancho y alto de la ventana en píxeles
        setSize(520, 350);

        //se establece que al cerrar la ventana principal se finalice el programa
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //se centra la ventana en la pantalla
        setLocationRelativeTo(null);

        //se impide que el usuario pueda modificar el tamaño de la ventana
        setResizable(false);

        // =========================
        // PANEL PRINCIPAL
        // =========================

        //se crea el panel principal que contendrá los elementos de la interfaz
        JPanel panelPrincipal = new JPanel();

        //se establece el color de fondo del panel
        panelPrincipal.setBackground(fondo);

        //se establece un BoxLayout en dirección vertical
        //los componentes se organizan uno debajo del otro
        panelPrincipal.setLayout(new BoxLayout(
                panelPrincipal, BoxLayout.Y_AXIS
        ));

        //se establece un margen interno de 30 píxeles arriba y abajo
        //y de 40 píxeles a los costados
        panelPrincipal.setBorder(
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        );

        // =========================
        // TÍTULO
        // =========================

        //se crea una etiqueta con el título principal del sistema
        JLabel titulo = new JLabel("Sistema de Preguntas");

        //se establece el tipo de fuente, estilo negrita y tamaño 27
        titulo.setFont(new Font("SansSerif", Font.BOLD, 27));

        //se establece el color azul para el título
        titulo.setForeground(azul);

        //se centra horizontalmente el título dentro del panel
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // =========================
        // SUBTÍTULO
        // =========================

        //se crea una etiqueta con el mensaje de bienvenida
        JLabel subtitulo = new JLabel("¡Bienvenido!");

        //se establece la fuente, estilo normal y tamaño 17
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 17));

        //se establece el color del texto
        subtitulo.setForeground(texto);

        //se centra horizontalmente el subtítulo
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // =========================
        // INSTRUCCIÓN
        // =========================

        //se crea una etiqueta que indica al usuario qué debe hacer
        JLabel instruccion = new JLabel(
                "Seleccioná tu tipo de usuario"
        );

        //se establece la fuente, estilo normal y tamaño 14
        instruccion.setFont(new Font("SansSerif", Font.PLAIN, 14));

        //se establece un color gris para el texto
        instruccion.setForeground(new Color(100, 110, 125));

        //se centra horizontalmente la instrucción
        instruccion.setAlignmentX(Component.CENTER_ALIGNMENT);

        // =========================
        // BOTONES
        // =========================

        //se crea el botón correspondiente al docente
        //se utiliza el método crearBoton() para mantener el mismo diseño
        //se envía el texto DOCENTE y el color azul
        JButton btnDocente = crearBoton(
                "DOCENTE", azul
        );

        //se crea el botón correspondiente al alumno
        //se utiliza el método crearBoton() con el texto ALUMNO y el color verde
        JButton btnAlumno = crearBoton(
                "ALUMNO", verde
        );

        // =========================
        // EVENTOS DE LOS BOTONES
        // =========================

        //se agrega un ActionListener al botón Docente
        //cuando el usuario presiona el botón se ejecuta mostrarMenu()
        //del ControladorDocente
        //el controlador se encarga de abrir la interfaz DocenteView
        btnDocente.addActionListener(e ->
                controladorDocente.mostrarMenu()
        );

        //se agrega un ActionListener al botón Alumno
        //cuando el usuario presiona el botón se ejecuta mostrarCuestionario()
        //del ControladorAlumno
        //el controlador se encarga de abrir la interfaz AlumnoView
        btnAlumno.addActionListener(e ->
                controladorAlumno.mostrarCuestionario()
        );

        // =========================
        // ESPACIADO Y DISTRIBUCIÓN
        // =========================

        //se agrega el título al panel principal
        panelPrincipal.add(titulo);

        //se agrega un espacio vertical de 8 píxeles
        panelPrincipal.add(Box.createVerticalStrut(8));

        //se agrega el subtítulo
        panelPrincipal.add(subtitulo);

        //se agrega un espacio vertical de 25 píxeles
        panelPrincipal.add(Box.createVerticalStrut(25));

        //se agrega la instrucción para seleccionar el tipo de usuario
        panelPrincipal.add(instruccion);

        //se agrega un espacio vertical de 20 píxeles
        panelPrincipal.add(Box.createVerticalStrut(20));

        //se agrega el botón del docente
        panelPrincipal.add(btnDocente);

        //se agrega un espacio vertical de 12 píxeles
        panelPrincipal.add(Box.createVerticalStrut(12));

        //se agrega el botón del alumno
        panelPrincipal.add(btnAlumno);

        //se incorpora el panel principal a la ventana
        add(panelPrincipal);

        //se hace visible la ventana principal
        setVisible(true);
    }

    //método privado y auxiliar para crear botones personalizados
    //recibe dos parámetros:
    //texto: indica el texto que tendrá el botón
    //color: indica el color de fondo del botón
    //devuelve un objeto JButton ya configurado visualmente
    //es utilizado por el constructor para crear los botones Docente y Alumno
    private JButton crearBoton(String texto, Color color) {

        //se crea un nuevo botón con el texto recibido como parámetro
        JButton boton = new JButton(texto);

        //se establece la fuente, estilo negrita y tamaño 15
        boton.setFont(new Font("SansSerif", Font.BOLD, 15));

        //se establece el color blanco para el texto del botón
        boton.setForeground(Color.WHITE);

        //se establece el color de fondo recibido como parámetro
        boton.setBackground(color);

        //se desactiva el efecto visual de enfoque del botón
        boton.setFocusPainted(false);

        //se desactiva el dibujo del borde predeterminado
        boton.setBorderPainted(false);

        //se establece el botón como opaco para que se visualice su color de fondo
        boton.setOpaque(true);

        //se establece el cursor de tipo mano cuando el usuario pasa sobre el botón
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        //se centra horizontalmente el botón dentro del panel
        boton.setAlignmentX(Component.CENTER_ALIGNMENT);

        //se establece el tamaño máximo permitido del botón
        boton.setMaximumSize(new Dimension(300, 45));

        //se establece el tamaño preferido del botón
        boton.setPreferredSize(new Dimension(300, 45));

        //se establece un margen interno de 10 píxeles arriba y abajo
        //y 20 píxeles a los costados
        boton.setBorder(
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
        );

        //se devuelve el botón ya configurado
        //el constructor lo incorpora al panel principal
        return boton;
    }
}
