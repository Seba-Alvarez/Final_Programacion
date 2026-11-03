//id del paquete correspondiente
package com.parcial_programacion.vista;

//imports de AWT para organizar los componentes gráficos
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

//hereda de JFrame, por lo tanto representa una ventana gráfica
//permite al docente crear y eliminar cuestionarios
//también permite agregar y eliminar preguntas dentro de cada cuestionario
public class DocenteView extends JFrame {

    //atributo que almacena una referencia a SistemaFacade
    //se recibe mediante el constructor desde ControladorDocente
    //permite acceder a las operaciones de alta, baja y consulta de cuestionarios y preguntas
    @SuppressWarnings("FieldMayBeFinal")
    private SistemaFacade facade;

    //campo de texto donde el docente ingresa el nombre del nuevo cuestionario
    private JTextField txtNombreCuestionario;

    //campo de texto donde el docente ingresa el enunciado de una nueva pregunta
    private JTextField txtPregunta;

    //campo de texto donde el docente ingresa la respuesta correcta de la pregunta
    private JTextField txtRespuesta;

    //campo de texto donde el docente ingresa el ID de la pregunta que desea eliminar
    private JTextField txtIdPregunta;

    //componente JComboBox que permite seleccionar un cuestionario existente
    //cada elemento de la lista es un objeto de tipo Cuestionario
    private JComboBox<Cuestionario> comboCuestionarios;

    //área de texto que permite visualizar las preguntas del cuestionario seleccionado
    private JTextArea areaPreguntas;

    //constructor de la clase DocenteView
    //recibe dos parámetros:
    //ControladorDocente: referencia al controlador que abrió esta ventana
    //SistemaFacade: referencia a la fachada que permite realizar las operaciones
    //es llamado desde ControladorDocente mediante new DocenteView(this, facade)
    public DocenteView(
            ControladorDocente controlador,
            SistemaFacade facade) {

        //se asigna la fachada recibida al atributo de la clase
        //permite utilizar sus métodos para administrar los cuestionarios
        this.facade = facade;

        //se establece el título de la ventana
        setTitle("Panel Docente");

        //se establece el ancho y alto de la ventana en píxeles
        setSize(600, 550);

        //se centra la ventana en la pantalla
        setLocationRelativeTo(null);

        //se establece que al cerrar esta ventana se liberen sus recursos
        //sin finalizar todo el programa
        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        //se llama al método que crea y organiza los componentes gráficos
        crearInterfaz();

        //se llama al método que carga los cuestionarios disponibles
        //y actualiza la visualización de sus preguntas
        actualizarCuestionarios();
    }

    //método privado que crea y organiza todos los componentes de la interfaz
    //se ejecuta desde el constructor de DocenteView
    private void crearInterfaz() {

        //se crea un panel utilizando GridLayout
        //el valor 0 indica que las filas se determinan según la cantidad de componentes
        //el valor 2 establece que se utilizan dos columnas
        JPanel panel =
                new JPanel(new GridLayout(0, 2));

        // =========================
        // CUESTIONARIO
        // =========================

        //se agrega una etiqueta que identifica el campo del nombre del cuestionario
        panel.add(
                new JLabel("Nombre cuestionario:")
        );

        //se crea un campo de texto para ingresar el nombre del cuestionario
        txtNombreCuestionario =
                new JTextField();

        //se incorpora el campo de texto al panel
        panel.add(
                txtNombreCuestionario
        );

        //se crea el botón que permite dar de alta un cuestionario
        JButton btnAgregarCuestionario =
                new JButton("Crear cuestionario");

        //se crea el botón que permite dar de baja un cuestionario
        JButton btnEliminarCuestionario =
                new JButton("Eliminar cuestionario");

        //se agrega el botón de creación al panel
        panel.add(
                btnAgregarCuestionario
        );

        //se agrega el botón de eliminación al panel
        panel.add(
                btnEliminarCuestionario
        );

        //se agrega una etiqueta para identificar el selector de cuestionarios
        panel.add(
                new JLabel("Cuestionario:")
        );

        //se crea un JComboBox vacío
        //posteriormente se cargarán los cuestionarios disponibles
        comboCuestionarios =
                new JComboBox<>();

        //se incorpora el selector al panel
        panel.add(
                comboCuestionarios
        );

        // =========================
        // PREGUNTA
        // =========================

        //se agrega una etiqueta para identificar el campo del enunciado
        panel.add(
                new JLabel("Pregunta:")
        );

        //se crea un campo de texto para ingresar el enunciado de la pregunta
        txtPregunta =
                new JTextField();

        //se incorpora el campo de texto al panel
        panel.add(
                txtPregunta
        );

        //se agrega una etiqueta para identificar el campo de la respuesta correcta
        panel.add(
                new JLabel("Respuesta correcta:")
        );

        //se crea un campo de texto para ingresar la respuesta correcta
        txtRespuesta =
                new JTextField();

        //se incorpora el campo de texto al panel
        panel.add(
                txtRespuesta
        );

        //se crea el botón que permite dar de alta una pregunta
        JButton btnAgregarPregunta =
                new JButton("Agregar pregunta");

        //se incorpora el botón al panel
        panel.add(
                btnAgregarPregunta
        );

        //se agrega una etiqueta vacía para mantener la distribución de dos columnas
        //sirve como espacio dentro del diseño
        panel.add(
                new JLabel("")
        );

        // =========================
        // ELIMINAR PREGUNTA
        // =========================

        //se agrega una etiqueta para identificar el campo del ID de la pregunta
        panel.add(
                new JLabel("ID pregunta:")
        );

        //se crea un campo de texto para ingresar el ID de la pregunta que se desea eliminar
        txtIdPregunta =
                new JTextField();

        //se incorpora el campo al panel
        panel.add(
                txtIdPregunta
        );

        //se crea el botón que permite dar de baja una pregunta
        JButton btnEliminarPregunta =
                new JButton("Eliminar pregunta");

        //se incorpora el botón al panel
        panel.add(
                btnEliminarPregunta
        );

        //se agrega otra etiqueta vacía para mantener el diseño de dos columnas
        panel.add(
                new JLabel("")
        );

        //se agrega el panel a la parte superior de la ventana
        //BorderLayout.NORTH indica la zona superior de la ventana
        add(
                panel,
                BorderLayout.NORTH
        );

        // =========================
        // LISTA DE PREGUNTAS
        // =========================

        //se crea un área de texto para mostrar las preguntas del cuestionario seleccionado
        areaPreguntas =
                new JTextArea();

        //se establece que el área de texto no pueda ser modificada por el usuario
        //solamente se utiliza para visualizar información
        areaPreguntas.setEditable(false);

        //se incorpora el área de texto dentro de un JScrollPane
        //permite desplazarse verticalmente si hay muchas preguntas
        //se agrega en la zona central de la ventana
        add(
                new JScrollPane(areaPreguntas),
                BorderLayout.CENTER
        );

        // =========================
        // CREAR CUESTIONARIO
        // =========================

        //se agrega un ActionListener al botón Crear cuestionario
        //cuando el docente presiona el botón se ejecuta el código correspondiente
        btnAgregarCuestionario.addActionListener(e -> {

            //se obtiene el nombre ingresado por el docente
            //getText() obtiene el contenido del campo
            //trim() elimina los espacios al principio y al final
            String nombre =
                    txtNombreCuestionario
                            .getText()
                            .trim();

            //se comprueba si el nombre está vacío
            if (nombre.isEmpty()) {

                //se muestra un mensaje indicando que debe ingresar un nombre
                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese un nombre"
                );

                //se interrumpe la ejecución para evitar crear un cuestionario vacío
                return;
            }

            //se llama al método agregarCuestionario() de SistemaFacade
            //se envía el nombre ingresado por el docente
            //la fachada crea un objeto Cuestionario y lo incorpora a su lista
            //posteriormente llama a CuestionarioDAO para guardar los datos en JSON
            facade.agregarCuestionario(
                    nombre
            );

            //se limpia el campo del nombre después de crear el cuestionario
            txtNombreCuestionario.setText("");

            //se actualiza el JComboBox para incorporar el nuevo cuestionario
            //también se actualiza la visualización de las preguntas
            actualizarCuestionarios();
        });

        // =========================
        // ELIMINAR CUESTIONARIO
        // =========================

        //se agrega un ActionListener al botón Eliminar cuestionario
        //cuando el docente presiona el botón se ejecuta este bloque de código
        btnEliminarCuestionario.addActionListener(e -> {

            //se obtiene el cuestionario seleccionado en el JComboBox
            //se convierte el elemento seleccionado al tipo Cuestionario
            Cuestionario cuestionario =
                    (Cuestionario)
                            comboCuestionarios
                                    .getSelectedItem();

            //se comprueba si existe un cuestionario seleccionado
            if (cuestionario == null) {

                //se muestra un mensaje indicando que debe seleccionar un cuestionario
                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un cuestionario"
                );

                //se interrumpe la ejecución
                return;
            }

            //se muestra una ventana de confirmación antes de eliminar el cuestionario
            //YES_NO_OPTION permite elegir entre sí y no
            //se almacena la opción seleccionada por el usuario
            int confirmar =
                    JOptionPane.showConfirmDialog(
                            this,
                            "¿Está seguro de eliminar el cuestionario?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION
                    );

            //se comprueba si el usuario no confirmó la eliminación
            //si selecciona No o cierra la ventana, no se realiza la operación
            if (confirmar != JOptionPane.YES_OPTION) {
                return;
            }

            //se llama al método eliminarCuestionario() de SistemaFacade
            //se envía el objeto Cuestionario que se desea eliminar
            //la fachada lo elimina de la lista y solicita a CuestionarioDAO
            //que guarde nuevamente la lista actualizada en cuestionarios.json
            facade.eliminarCuestionario(
                    cuestionario
            );

            //se actualiza el JComboBox para quitar el cuestionario eliminado
            //también se actualiza el área donde se muestran las preguntas
            actualizarCuestionarios();
        });

        // =========================
        // CAMBIAR CUESTIONARIO
        // =========================

        //se agrega un ActionListener al JComboBox
        //cuando el docente selecciona otro cuestionario
        //se ejecuta actualizarPreguntas()
        //permite mostrar las preguntas correspondientes al cuestionario seleccionado
        comboCuestionarios.addActionListener(e ->
                actualizarPreguntas()
        );

        // =========================
        // AGREGAR PREGUNTA
        // =========================

        //se agrega un ActionListener al botón Agregar pregunta
        //cuando el docente presiona el botón se ejecuta este bloque de código
        btnAgregarPregunta.addActionListener(e -> {

            //se obtiene el cuestionario seleccionado en el JComboBox
            Cuestionario cuestionario =
                    (Cuestionario)
                            comboCuestionarios
                                    .getSelectedItem();

            //se comprueba si existe un cuestionario seleccionado
            if (cuestionario == null) {

                //se muestra un mensaje indicando que debe seleccionar un cuestionario
                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un cuestionario"
                );

                //se interrumpe la ejecución
                return;
            }

            //se obtiene el enunciado ingresado por el docente
            //trim() elimina los espacios al principio y al final
            String pregunta =
                    txtPregunta
                            .getText()
                            .trim();

            //se obtiene la respuesta correcta ingresada por el docente
            String respuesta =
                    txtRespuesta
                            .getText()
                            .trim();

            //se comprueba si alguno de los dos campos está vacío
            if (pregunta.isEmpty()
                    || respuesta.isEmpty()) {

                //se muestra un mensaje indicando que debe completar todos los campos
                JOptionPane.showMessageDialog(
                        this,
                        "Complete todos los campos"
                );

                //se interrumpe la ejecución
                return;
            }

            //se llama al método agregarPregunta() de SistemaFacade
            //se envían tres parámetros:
            //el cuestionario al que pertenece la pregunta
            //el enunciado de la pregunta
            //la respuesta correcta
            //la fachada crea un objeto Pregunta, le asigna un ID
            //y lo incorpora al cuestionario seleccionado
            //posteriormente guarda la lista de cuestionarios mediante CuestionarioDAO
            facade.agregarPregunta(
                    cuestionario,
                    pregunta,
                    respuesta
            );

            //se limpia el campo del enunciado
            txtPregunta.setText("");

            //se limpia el campo de la respuesta correcta
            txtRespuesta.setText("");

            //se actualiza el área de texto para mostrar la nueva pregunta
            actualizarPreguntas();
        });

        // =========================
        // ELIMINAR PREGUNTA
        // =========================

        //se agrega un ActionListener al botón Eliminar pregunta
        //cuando el docente presiona el botón se ejecuta este bloque de código
        btnEliminarPregunta.addActionListener(e -> {

            //se obtiene el cuestionario actualmente seleccionado
            Cuestionario cuestionario =
                    (Cuestionario)
                            comboCuestionarios
                                    .getSelectedItem();

            //se comprueba si hay un cuestionario seleccionado
            if (cuestionario == null) {

                //se muestra un mensaje indicando que debe seleccionar un cuestionario
                JOptionPane.showMessageDialog(
                        this,
                        "Seleccione un cuestionario"
                );

                //se interrumpe la ejecución
                return;
            }

            //se obtiene el ID ingresado por el docente
            //trim() elimina los espacios al principio y al final
            String textoId =
                    txtIdPregunta
                            .getText()
                            .trim();

            //se comprueba si el campo del ID está vacío
            if (textoId.isEmpty()) {

                //se muestra un mensaje indicando que debe ingresar el ID
                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese el ID de la pregunta"
                );

                //se interrumpe la ejecución
                return;
            }

            //try permite controlar posibles errores durante la conversión del ID
            try {

                //se convierte el texto ingresado a un número entero
                //Integer.parseInt() convierte un String a int
                //si el texto no representa un número válido se produce NumberFormatException
                int id =
                        Integer.parseInt(
                                textoId
                        );

                //variable booleana que permite comprobar si existe una pregunta con ese ID
                //se inicializa en false porque todavía no se encontró ninguna
                boolean existe = false;

                //se recorren todas las preguntas del cuestionario seleccionado
                for (Pregunta pregunta :
                        cuestionario.getPreguntas()) {

                    //se compara el ID ingresado con el ID de cada pregunta
                    if (pregunta.getId() == id) {

                        //si coinciden, significa que la pregunta existe
                        existe = true;

                        //se interrumpe el recorrido porque ya se encontró la pregunta
                        break;
                    }
                }

                //se comprueba si no se encontró ninguna pregunta con ese ID
                if (!existe) {

                    //se muestra un mensaje indicando que no existe una pregunta con ese ID
                    JOptionPane.showMessageDialog(
                            this,
                            "No existe una pregunta con ese ID"
                    );

                    //se interrumpe la ejecución
                    return;
                }

                //se muestra una ventana solicitando confirmación antes de eliminar
                int confirmar =
                        JOptionPane.showConfirmDialog(
                                this,
                                "¿Está seguro de eliminar la pregunta?",
                                "Confirmar eliminación",
                                JOptionPane.YES_NO_OPTION
                        );

                //se comprueba si el usuario no confirmó la operación
                if (confirmar !=
                        JOptionPane.YES_OPTION) {

                    //si no confirma, se interrumpe la ejecución
                    return;
                }

                //se llama al método eliminarPregunta() de SistemaFacade
                //se envían el cuestionario seleccionado y el ID de la pregunta
                //la fachada solicita al objeto Cuestionario que elimine la pregunta
                //y posteriormente guarda la lista actualizada mediante CuestionarioDAO
                facade.eliminarPregunta(
                        cuestionario,
                        id
                );

                //se limpia el campo del ID después de eliminar la pregunta
                txtIdPregunta.setText("");

                //se actualiza el área de texto para reflejar la eliminación
                actualizarPreguntas();

            } catch (NumberFormatException ex) {

                //se ejecuta si el docente ingresa un ID que no puede convertirse a entero
                //por ejemplo, si ingresa letras o caracteres no numéricos
                JOptionPane.showMessageDialog(
                        this,
                        "Ingrese un ID válido"
                );
            }
        });
    }

    //método privado que actualiza la lista desplegable de cuestionarios
    //se ejecuta desde el constructor y después de crear o eliminar un cuestionario
    private void actualizarCuestionarios() {

        //se eliminan todos los elementos actuales del JComboBox
        comboCuestionarios.removeAllItems();

        //se recorren los cuestionarios almacenados en SistemaFacade
        //obtenerCuestionarios() devuelve la lista de cuestionarios disponible
        for (Cuestionario cuestionario :
                facade.obtenerCuestionarios()) {

            //se incorpora cada cuestionario al JComboBox
            //toString() de Cuestionario permite mostrar su nombre
            comboCuestionarios.addItem(
                    cuestionario
            );
        }

        //se actualiza el área de preguntas según el cuestionario seleccionado
        //si no hay ningún cuestionario seleccionado, se limpia el área
        actualizarPreguntas();
    }

    //método privado que actualiza el área de texto con las preguntas del cuestionario seleccionado
    //se ejecuta cuando cambia la selección del JComboBox
    //también se ejecuta después de agregar o eliminar una pregunta
    private void actualizarPreguntas() {

        //se limpia el contenido anterior del área de texto
        areaPreguntas.setText("");

        //se obtiene el cuestionario seleccionado en el JComboBox
        Cuestionario cuestionario =
                (Cuestionario)
                        comboCuestionarios
                                .getSelectedItem();

        //se comprueba si no hay ningún cuestionario seleccionado
        if (cuestionario == null) {

            //se interrumpe la ejecución porque no hay preguntas para mostrar
            return;
        }

        //se recorren todas las preguntas que pertenecen al cuestionario seleccionado
        for (Pregunta pregunta :
                cuestionario.getPreguntas()) {

            //se agrega al área de texto la información de cada pregunta
            //se muestra el ID, el enunciado y la respuesta correcta
            //también se agrega una línea separadora para facilitar la lectura
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
