//id del paquete correspondiente
package com.parcial_programacion.vista;

//imports de AWT para organizar los componentes de la interfaz
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

//clase AlumnoView
//pertenece a la capa Vista
//hereda de JFrame, por lo tanto representa una ventana gráfica
//permite al alumno seleccionar un cuestionario, responder sus preguntas
//y visualizar el resultado obtenido
public class AlumnoView extends JFrame {

    //atributo que almacena la referencia a SistemaFacade
    //se recibe mediante el constructor desde ControladorAlumno
    //permite obtener cuestionarios, comprobar respuestas y guardar resultados
    private SistemaFacade facade;

    //componente JComboBox que permite mostrar y seleccionar los cuestionarios disponibles
    //cada elemento de la lista es un objeto de tipo Cuestionario
    private JComboBox<Cuestionario> comboCuestionarios;

    //campo de texto donde el alumno ingresa su cédula
    private JTextField txtCedula;

    //campo de texto donde el alumno escribe su respuesta a cada pregunta
    private JTextField txtRespuesta;

    //etiqueta que permite mostrar el enunciado de la pregunta actual
    private JLabel lblPregunta;

    //botón que permite comenzar el cuestionario seleccionado
    private JButton btnComenzar;

    //botón que permite enviar la respuesta ingresada por el alumno
    private JButton btnResponder;

    //atributo que almacena el cuestionario seleccionado por el alumno
    //se utiliza durante toda la realización del cuestionario
    private Cuestionario cuestionario;

    //variable que indica la posición de la pregunta actual dentro de la lista
    //se inicializa en cero porque las posiciones de una lista comienzan desde cero
    private int indicePregunta = 0;

    //variable que almacena la cantidad de respuestas correctas del alumno
    //se incrementa cada vez que el alumno responde correctamente
    private int correctas = 0;

    //constructor de la clase AlumnoView
    //recibe una instancia de SistemaFacade como parámetro
    //es llamado desde ControladorAlumno cuando se abre la interfaz del alumno
    public AlumnoView(SistemaFacade facade) {

        //se asigna la fachada recibida al atributo de la clase
        //permite utilizar sus métodos durante la realización del cuestionario
        this.facade = facade;

        //se establece el título que tendrá la ventana
        setTitle("Cuestionario Alumno");

        //se establece el ancho y el alto de la ventana en píxeles
        setSize(500, 300);

        //se centra la ventana en la pantalla
        setLocationRelativeTo(null);

        //se establece que al cerrar esta ventana se liberen sus recursos
        //DISPOSE_ON_CLOSE cierra esta ventana sin finalizar todo el programa
        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        //se llama al método que crea y organiza los componentes gráficos
        crearInterfaz();

        //se llama al método que carga los cuestionarios disponibles en el JComboBox
        cargarCuestionarios();
    }

    //método privado para crear y organizar los componentes de la interfaz
    //se ejecuta desde el constructor de AlumnoView
    private void crearInterfaz() {

        //se establece un diseño GridLayout de seis filas y una columna
        //los componentes se organizan uno debajo del otro
        setLayout(new GridLayout(6, 1));

        // =========================
        // CUESTIONARIO
        // =========================

        //se crea un panel para organizar la selección del cuestionario
        //FlowLayout organiza los componentes en forma horizontal
        JPanel panelCuestionario =
                new JPanel(new FlowLayout());

        //se agrega una etiqueta con el texto Cuestionario al panel
        panelCuestionario.add(
                new JLabel("Cuestionario:")
        );

        //se crea un JComboBox vacío
        //posteriormente se cargarán los cuestionarios disponibles
        comboCuestionarios =
                new JComboBox<>();

        //se incorpora el JComboBox al panel
        panelCuestionario.add(
                comboCuestionarios
        );

        //se agrega el panel a la ventana principal
        add(panelCuestionario);

        // =========================
        // CEDULA
        // =========================

        //se crea un panel para organizar el ingreso de la cédula
        JPanel panelCedula =
                new JPanel(new FlowLayout());

        //se agrega una etiqueta para identificar el campo de la cédula
        panelCedula.add(
                new JLabel("Cédula:")
        );

        //se crea un campo de texto de 15 columnas
        //permite que el alumno ingrese su cédula
        txtCedula =
                new JTextField(15);

        //se incorpora el campo de texto al panel
        panelCedula.add(txtCedula);

        //se agrega el panel a la ventana principal
        add(panelCedula);

        // =========================
        // COMENZAR
        // =========================

        //se crea el botón que permite comenzar el cuestionario
        btnComenzar =
                new JButton("Comenzar");

        //se agrega el botón a la ventana principal
        add(btnComenzar);

        // =========================
        // PREGUNTA
        // =========================

        //se crea una etiqueta inicialmente vacía
        //se utilizará para mostrar el enunciado de cada pregunta
        lblPregunta =
                new JLabel();

        //se agrega la etiqueta a la ventana principal
        add(lblPregunta);

        // =========================
        // RESPUESTA
        // =========================

        //se crea un campo de texto para que el alumno ingrese su respuesta
        txtRespuesta =
                new JTextField();

        //se agrega el campo de texto a la ventana principal
        add(txtRespuesta);

        // =========================
        // RESPONDER
        // =========================

        //se crea el botón que permite enviar la respuesta ingresada
        btnResponder =
                new JButton("Responder");

        //se agrega el botón a la ventana principal
        add(btnResponder);

        //se deshabilita inicialmente el botón responder
        //solamente se habilita cuando el alumno comienza un cuestionario
        btnResponder.setEnabled(false);

        // =========================
        // EVENTOS
        // =========================

        //se agrega un ActionListener al botón Comenzar
        //cuando el alumno presiona el botón se ejecuta comenzarCuestionario()
        //la expresión e -> representa una expresión lambda utilizada para gestionar el evento
        btnComenzar.addActionListener(e ->
                comenzarCuestionario()
        );

        //se agrega un ActionListener al botón Responder
        //cuando el alumno presiona el botón se ejecuta el método responder()
        btnResponder.addActionListener(e ->
                responder()
        );
    }

    //método privado para cargar los cuestionarios disponibles en el JComboBox
    //se ejecuta desde el constructor después de crear la interfaz
    private void cargarCuestionarios() {

        //se eliminan los elementos que pudiera tener previamente el JComboBox
        comboCuestionarios.removeAllItems();

        //se recorren todos los cuestionarios obtenidos desde SistemaFacade
        //obtenerCuestionarios() devuelve la lista de cuestionarios almacenada en la fachada
        for (Cuestionario cuestionario :
                facade.obtenerCuestionarios()) {

            //se incorpora cada cuestionario al JComboBox
            //toString() de Cuestionario permite mostrar su nombre en la lista
            comboCuestionarios.addItem(
                    cuestionario
            );
        }
    }

    //método privado que se ejecuta cuando el alumno presiona el botón Comenzar
    //se encarga de validar los datos ingresados y preparar el cuestionario
    private void comenzarCuestionario() {

        //se obtiene el cuestionario seleccionado en el JComboBox
        //se convierte el elemento seleccionado a tipo Cuestionario
        cuestionario =
                (Cuestionario)
                        comboCuestionarios
                                .getSelectedItem();

        //se comprueba si el alumno seleccionó algún cuestionario
        if (cuestionario == null) {

            //se muestra un mensaje indicando que debe seleccionar un cuestionario
            JOptionPane.showMessageDialog(
                    this,
                    "Seleccione un cuestionario"
            );

            //return finaliza la ejecución del método para evitar continuar sin selección
            return;
        }

        //se comprueba si el campo de la cédula está vacío
        //getText() obtiene el texto ingresado y trim() elimina los espacios de los extremos
        if (txtCedula.getText()
                .trim()
                .isEmpty()) {

            //se muestra un mensaje indicando que debe ingresar la cédula
            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese su cédula"
            );

            //se interrumpe la ejecución del método
            return;
        }

        //se comprueba si el cuestionario seleccionado tiene preguntas
        //getPreguntas() devuelve la lista de preguntas del cuestionario
        //isEmpty() verifica si la lista está vacía
        if (cuestionario
                .getPreguntas()
                .isEmpty()) {

            //se muestra un mensaje indicando que el cuestionario no contiene preguntas
            JOptionPane.showMessageDialog(
                    this,
                    "Este cuestionario no tiene preguntas"
            );

            //se interrumpe la ejecución del método
            return;
        }

        //se reinicia el índice de preguntas para comenzar desde la primera
        indicePregunta = 0;

        //se reinicia el contador de respuestas correctas
        correctas = 0;

        //se deshabilita la selección de cuestionarios
        //evita que el alumno cambie de cuestionario durante la realización
        comboCuestionarios
                .setEnabled(false);

        //se deshabilita el campo de la cédula
        //evita modificar la identificación durante el cuestionario
        txtCedula
                .setEnabled(false);

        //se deshabilita el botón Comenzar
        //evita iniciar nuevamente el cuestionario mientras está en curso
        btnComenzar
                .setEnabled(false);

        //se habilita el botón Responder
        //permite que el alumno comience a enviar sus respuestas
        btnResponder
                .setEnabled(true);

        //se llama al método que muestra la primera pregunta
        mostrarPregunta();
    }

    //método privado para mostrar la pregunta correspondiente al índice actual
    //se ejecuta al comenzar el cuestionario y después de cada respuesta
    private void mostrarPregunta() {

        //se comprueba si el índice actual alcanzó la cantidad total de preguntas
        //si el índice es igual o mayor al tamaño de la lista, ya no quedan preguntas
        if (indicePregunta >=
                cuestionario
                        .getPreguntas()
                        .size()) {

            //se llama al método que finaliza el cuestionario y guarda el resultado
            finalizarCuestionario();

            //se interrumpe la ejecución para evitar intentar mostrar otra pregunta
            return;
        }

        //se obtiene la pregunta correspondiente a la posición actual de la lista
        Pregunta pregunta =
                cuestionario
                        .getPreguntas()
                        .get(indicePregunta);

        //se actualiza la etiqueta para mostrar el número y el enunciado de la pregunta
        //indicePregunta + 1 permite mostrar la numeración comenzando desde uno
        //getEnunciado() obtiene el texto almacenado en el objeto Pregunta
        lblPregunta.setText(
                "Pregunta "
                + (indicePregunta + 1)
                + ": "
                + pregunta.getEnunciado()
        );

        //se limpia el campo de texto para que el alumno pueda ingresar una nueva respuesta
        txtRespuesta.setText("");

        //se coloca el cursor en el campo de respuesta
        //permite que el alumno comience a escribir directamente
        txtRespuesta.requestFocus();
    }

    //método privado que procesa la respuesta ingresada por el alumno
    //se ejecuta cuando se presiona el botón Responder
    private void responder() {

        //se obtiene el texto ingresado por el alumno
        //trim() elimina los espacios al principio y al final
        String respuesta =
                txtRespuesta
                        .getText()
                        .trim();

        //se comprueba si el alumno dejó el campo de respuesta vacío
        if (respuesta.isEmpty()) {

            //se muestra un mensaje indicando que debe ingresar una respuesta
            JOptionPane.showMessageDialog(
                    this,
                    "Ingrese una respuesta"
            );

            //se interrumpe la ejecución para no procesar una respuesta vacía
            return;
        }

        //se obtiene la pregunta que el alumno está respondiendo actualmente
        //se utiliza indicePregunta para acceder a la posición correspondiente
        Pregunta pregunta =
                cuestionario
                        .getPreguntas()
                        .get(indicePregunta);

        //se llama al método comprobarRespuesta() de SistemaFacade
        //se envían el objeto Pregunta y el texto ingresado por el alumno
        //la fachada compara la respuesta ingresada con la respuesta correcta
        //devuelve true si es correcta y false si es incorrecta
        boolean correcta =
                facade.comprobarRespuesta(
                        pregunta,
                        respuesta
                );

        //se comprueba el resultado de la comparación
        if (correcta) {

            //si la respuesta es correcta se incrementa el contador de aciertos
            correctas++;

            //se muestra un mensaje indicando que la respuesta fue correcta
            JOptionPane.showMessageDialog(
                    this,
                    "Respuesta correcta"
            );

        } else {

            //si la respuesta es incorrecta no se incrementa el contador
            //se muestra un mensaje indicando que la respuesta fue incorrecta
            JOptionPane.showMessageDialog(
                    this,
                    "Respuesta incorrecta"
            );
        }

        //se incrementa el índice para pasar a la siguiente pregunta
        indicePregunta++;

        //se llama nuevamente al método mostrarPregunta()
        //si quedan preguntas muestra la siguiente
        //si no quedan preguntas llama a finalizarCuestionario()
        mostrarPregunta();
    }

    //método privado que se ejecuta cuando el alumno termina de responder todas las preguntas
    //se encarga de registrar el resultado, mostrarlo y cerrar la ventana
    private void finalizarCuestionario() {

        // =========================
        // GUARDAR RESULTADO
        // =========================

        //se llama al método guardarRespuesta() de SistemaFacade
        //se envían tres datos:
        //la cédula ingresada por el alumno
        //el nombre del cuestionario realizado
        //la cantidad de respuestas correctas obtenidas
        //SistemaFacade crea un objeto Respuesta y lo agrega a su lista
        //posteriormente utiliza RespuestaDAO para guardar los resultados en respuestas.json
        facade.guardarRespuesta(
                txtCedula.getText().trim(),
                cuestionario.getNombre(),
                correctas
        );

        // =========================
        // MOSTRAR RESULTADO
        // =========================

        //se muestra una ventana con el resumen del cuestionario realizado
        //se informa la cédula, el nombre del cuestionario y los aciertos obtenidos
        //getPreguntas().size() permite mostrar la cantidad total de preguntas
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

        //se cierra la ventana actual y se liberan sus recursos
        //no se cierra el programa completo
        dispose();
    }
}
