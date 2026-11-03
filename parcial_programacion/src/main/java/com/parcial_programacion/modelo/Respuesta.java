//id del paquete correspondiente
package com.parcial_programacion.modelo;

//representa el resultado obtenido por un alumno al completar un cuestionario
//almacena la cédula del alumno, el cuestionario realizado y la cantidad de aciertos
public class Respuesta {

    //atributo que almacena la cédula del alumno que realizó el cuestionario
    //permite identificar a qué alumno pertenece el resultado
    @SuppressWarnings("FieldMayBeFinal")
    private String cedula;

    //atributo que almacena el nombre del cuestionario realizado por el alumno
    //permite identificar a qué cuestionario corresponde el resultado
    @SuppressWarnings("FieldMayBeFinal")
    private String cuestionario;

    //atributo que almacena la cantidad de respuestas correctas obtenidas
    //representa el resultado del alumno en el cuestionario
    @SuppressWarnings("FieldMayBeFinal")
    private int aciertos;

    //constructor de la clase Respuesta
    //recibe tres parámetros: cédula, nombre del cuestionario y cantidad de aciertos
    //es utilizado desde SistemaFacade cuando se registra el resultado de un alumno
    public Respuesta(
            String cedula,
            String cuestionario,
            int aciertos) {

        //se asigna la cédula recibida al atributo de la clase
        this.cedula = cedula;

        //se asigna el nombre del cuestionario recibido al atributo correspondiente
        this.cuestionario = cuestionario;

        //se asigna la cantidad de aciertos recibida al atributo correspondiente
        this.aciertos = aciertos;
    }

    //método getter para obtener la cédula del alumno
    //permite consultar qué alumno realizó el cuestionario
    //es utilizado por otras clases para acceder a la información del resultado
    public String getCedula() {
        return cedula;
    }

    //método getter para obtener el nombre del cuestionario realizado
    //permite consultar a qué cuestionario pertenece el resultado
    public String getCuestionario() {
        return cuestionario;
    }

    //método getter para obtener la cantidad de respuestas correctas
    //permite consultar el resultado obtenido por el alumno
    public int getAciertos() {
        return aciertos;
    }
}
