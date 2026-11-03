//id del paquete correspondiente
package com.parcial_programacion.modelo;

//representa una pregunta individual que forma parte de un cuestionario
//almacena su identificador, enunciado y respuesta correcta
public class Pregunta {

    //atributo que almacena el identificador de la pregunta
    //se asigna mediante el constructor y permite identificar cada pregunta
    //se utiliza para localizar preguntas cuando se desea eliminarlas
    @SuppressWarnings("FieldMayBeFinal")
    private int id;

    //atributo que almacena el enunciado o texto de la pregunta
    //puede modificarse posteriormente mediante su setter
    private String enunciado;

    //atributo que almacena la respuesta correcta de la pregunta
    //se utiliza desde SistemaFacade para comprobar las respuestas de los alumnos
    //puede modificarse posteriormente mediante su setter
    private String respuestaCorrecta;

    //constructor de la clase Pregunta
    //recibe tres parámetros: identificador, enunciado y respuesta correcta
    //es utilizado desde SistemaFacade cuando se da de alta una nueva pregunta
    public Pregunta(int id, String enunciado, String respuestaCorrecta) {

        //se asigna el identificador recibido al atributo de la clase
        this.id = id;

        //se asigna el enunciado recibido al atributo correspondiente
        this.enunciado = enunciado;

        //se asigna la respuesta correcta recibida al atributo correspondiente
        this.respuestaCorrecta = respuestaCorrecta;
    }

    //método getter para obtener el identificador de la pregunta
    //permite que otras clases consulten el ID sin acceder directamente al atributo privado
    //es utilizado, por ejemplo, desde Cuestionario para identificar una pregunta al eliminarla
    public int getId() {
        return id;
    }

    //método getter para obtener el enunciado de la pregunta
    //permite que la interfaz del alumno pueda mostrar el texto de la pregunta
    public String getEnunciado() {
        return enunciado;
    }

    //método getter para obtener la respuesta correcta
    //es utilizado desde SistemaFacade para comparar la respuesta del alumno
    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    //método setter para modificar el enunciado de la pregunta
    //recibe el nuevo enunciado como parámetro
    //permite actualizar el texto de una pregunta existente
    public void setEnunciado(String enunciado) {

        //se reemplaza el enunciado anterior por el nuevo valor recibido
        this.enunciado = enunciado;
    }

    //método setter para modificar la respuesta correcta
    //recibe la nueva respuesta correcta como parámetro
    //permite actualizar la respuesta asociada a una pregunta existente
    public void setRespuestaCorrecta(String respuestaCorrecta) {

        //se reemplaza la respuesta correcta anterior por el nuevo valor recibido
        this.respuestaCorrecta = respuestaCorrecta;
    }

    //sobrescritura del método toString() heredado de la clase Object
    //permite representar la pregunta mediante un texto
    //devuelve el ID seguido del enunciado
    //resulta útil para mostrar preguntas en listas o componentes de la interfaz
    @Override
    public String toString() {
        return id + " - " + enunciado;
    }
}
