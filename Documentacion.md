# Documentación del proyecto — Sistema de Preguntas

## 1. Descripción general

### 1.1. Nombre del proyecto

**Sistema de Preguntas**

### 1.2. Resumen

El proyecto consiste en un sistema de cuestionarios desarrollado en Java que permite trabajar con dos tipos de usuario: **Docente** y **Alumno**.

El docente puede crear y eliminar cuestionarios, agregar preguntas con sus respectivas respuestas correctas y eliminar preguntas. El alumno puede seleccionar un cuestionario, ingresar su cédula, responder las preguntas y obtener el resultado de la evaluación. El sistema comprueba automáticamente las respuestas y almacena los resultados.

La información de los cuestionarios y de las respuestas se persiste en archivos JSON mediante Gson.

### 1.3. Contexto y problema

A modo de tarea final de la materia progarmación 2 del año 2026, se pidió el desarrollo de una app de preguntas en java.

### 1.4. Objetivos

**Objetivo general**

Desarrollar un sistema de preguntas que permita a un docente administrar cuestionarios y a un alumno responderlos y obtener automáticamente su resultado.

**Objetivos específicos**

- Permitir al docente crear y eliminar cuestionarios.
- Permitir agregar y eliminar preguntas asociadas a un cuestionario, incluyendo la respuesta correcta.
- Permitir al alumno responder cuestionarios, comprobar automáticamente las respuestas y almacenar el resultado obtenido.

---

## 2. Alcance

### 2.1. Funcionalidades incluidas

- Selección del tipo de usuario entre **Docente** y **Alumno** desde el menú principal.
- Creación y eliminación de cuestionarios por parte del docente.
- Agregado de preguntas y definición de sus respuestas correctas.
- Eliminación de preguntas mediante su ID.
- Visualización de las preguntas pertenecientes al cuestionario seleccionado.
- Selección de un cuestionario por parte del alumno e ingreso de su cédula.
- Presentación secuencial de las preguntas y registro de las respuestas ingresadas.
- Corrección automática de cada respuesta y contabilización de los aciertos.
- Almacenamiento del resultado final asociado a la cédula y al cuestionario.
- Persistencia de cuestionarios y resultados mediante archivos JSON.

### 2.2. Funcionalidades no incluidas

- Autenticación o inicio de sesión de usuarios.
- Registro de usuarios.
- Modificación de resultados almacenados.
- Sistema de selección de respuestas mediante opciones múltiples.
- Reportes o estadísticas de resultados.
- Funcionalidades web o acceso remoto.

---

## 3. Usuarios y casos de uso

### 3.1. Tipos de usuario

| **Usuario** | **Descripción** | **Permisos / responsabilidades** |
| ----------- | --------------- | -------------------------------- |
| Docente | Usuario encargado de administrar los cuestionarios. | Crear y eliminar cuestionarios; agregar y eliminar preguntas; definir respuestas correctas. |
| Alumno | Usuario que responde los cuestionarios. | Seleccionar un cuestionario, ingresar su cédula, responder preguntas y obtener el resultado. |

### 3.2. Casos de uso principales

#### Crear cuestionario

- **Actor:** Docente
- **Objetivo:** Crear un nuevo cuestionario.
- **Precondiciones:** El usuario debe encontrarse en el panel docente.
- **Flujo principal:** El docente ingresa el nombre del cuestionario y selecciona la opción para crearlo. El sistema crea un objeto `Cuestionario`, lo agrega a la lista y guarda la información.
- **Flujos alternativos / errores:** Si el nombre está vacío, se muestra el mensaje `"Ingrese un nombre"` y no se crea el cuestionario.
- **Resultado esperado:** El cuestionario queda creado y almacenado.

#### Eliminar cuestionario

- **Actor:** Docente
- **Objetivo:** Eliminar un cuestionario existente.
- **Precondiciones:** Debe existir y estar seleccionado un cuestionario.
- **Flujo principal:** El docente selecciona el cuestionario, solicita su eliminación y confirma la operación.
- **Flujos alternativos / errores:** Si no se seleccionó un cuestionario, el sistema informa `"Seleccione un cuestionario"`. Si el docente cancela la confirmación, la eliminación no se realiza.
- **Resultado esperado:** El cuestionario es eliminado y se actualiza la lista.

#### Agregar pregunta

- **Actor:** Docente
- **Objetivo:** Agregar una pregunta a un cuestionario y establecer su respuesta correcta.
- **Precondiciones:** Debe existir un cuestionario seleccionado y deben completarse la pregunta y la respuesta correcta.
- **Flujo principal:** El docente selecciona un cuestionario, ingresa el enunciado y la respuesta correcta y agrega la pregunta. El sistema genera un ID para la pregunta y la incorpora al cuestionario.
- **Flujos alternativos / errores:** Si no hay cuestionario seleccionado se informa al usuario. Si la pregunta o la respuesta están vacías, se muestra `"Complete todos los campos"`.
- **Resultado esperado:** La pregunta queda asociada al cuestionario y almacenada.

#### Eliminar pregunta

- **Actor:** Docente
- **Objetivo:** Eliminar una pregunta de un cuestionario.
- **Precondiciones:** Debe existir un cuestionario seleccionado y debe proporcionarse un ID de pregunta válido.
- **Flujo principal:** El docente ingresa el ID, el sistema verifica que exista, solicita confirmación y elimina la pregunta.
- **Flujos alternativos / errores:** Si no se ingresa un ID, se informa al usuario. Si el ID no corresponde a una pregunta existente, se informa. Si el ID no es numérico, se muestra `"Ingrese un ID válido"`.
- **Resultado esperado:** La pregunta es eliminada del cuestionario.

#### Responder cuestionario

- **Actor:** Alumno
- **Objetivo:** Completar un cuestionario y obtener la cantidad de respuestas correctas.
- **Precondiciones:** Debe seleccionarse un cuestionario, ingresar una cédula y el cuestionario debe contener preguntas.
- **Flujo principal:** El alumno selecciona el cuestionario, ingresa su cédula, comienza el cuestionario, responde cada pregunta y el sistema comprueba cada respuesta. Al finalizar se almacena el resultado.
- **Flujos alternativos / errores:** Si no se selecciona cuestionario, no se ingresa la cédula, el cuestionario no contiene preguntas o se intenta enviar una respuesta vacía, el sistema muestra un mensaje y detiene esa operación.
- **Resultado esperado:** Se informa la cantidad de respuestas correctas sobre el total de preguntas y se guarda el resultado.

---

## 4. Interfaz gráfica

### 4.1. Descripción

La interfaz está desarrollada utilizando **Java Swing y Awt** y está organizada en varias ventanas (`JFrame`).

El sistema posee un menú principal para seleccionar el tipo de usuario. Desde allí se accede al panel docente o al cuestionario destinado al alumno.

### 4.2. Pantallas principales

#### Menú principal

Permite seleccionar si se desea ingresar como Docente o Alumno.

**Captura:**


#### Panel Docente

Permite crear y eliminar cuestionarios, seleccionar un cuestionario, agregar preguntas, definir sus respuestas correctas, eliminar preguntas por ID y visualizar las preguntas del cuestionario seleccionado.

**Captura:**


#### Cuestionario Alumno

Permite seleccionar el cuestionario, ingresar la cédula, comenzar la evaluación, visualizar cada pregunta, ingresar la respuesta y enviarla. Al finalizar muestra el resultado.

**Captura:**


### 4.3. Navegación

El usuario comienza en el menú principal y selecciona uno de los dos roles disponibles.

- **Docente:** accede al panel docente para administrar cuestionarios y preguntas.
- **Alumno:** accede a la pantalla de cuestionarios para seleccionar y responder una evaluación.

La navegación entre estas partes se realiza mediante los controladores `ControladorDocente` y `ControladorAlumno`.

---

## 5. Persistencia

### 5.1. Estrategia utilizada

El sistema utiliza archivos **JSON** para almacenar persistentemente la información.

Se utilizan dos clases DAO:

- `CuestionarioDAO`, que almacena los cuestionarios en `cuestionarios.json`.
- `RespuestaDAO`, que almacena los resultados en `respuestas.json`.

Para convertir los objetos Java a JSON y viceversa se utiliza la biblioteca **Gson**.

### 5.2. Datos persistidos

| **Dato / entidad** | **Descripción** | **Operaciones** |
| ------------------ | --------------- | --------------- |
| Cuestionario | Representa un cuestionario y contiene una lista de preguntas. | Crear / leer / eliminar |
| Pregunta | Contiene ID, enunciado y respuesta correcta. | Crear / leer / eliminar |
| Respuesta | Contiene cédula, nombre del cuestionario y cantidad de aciertos. | Crear / leer |

### 5.3. Carga y recuperación

Al crear `SistemaFacade`, se crean los DAO y se cargan los cuestionarios y respuestas desde los archivos JSON. Si los archivos no pueden leerse o no contienen datos, los DAO retornan listas vacías.

### 5.4. Guardado

Los cuestionarios se guardan después de crear o eliminar cuestionarios y después de agregar o eliminar preguntas.

Los resultados se guardan al finalizar un cuestionario.

---

## 6. Evaluación

### 6.1. ¿Qué se evalúa?

Se evalúa la cantidad de respuestas correctas que obtiene un alumno al responder un cuestionario.

El sistema compara cada respuesta ingresada con la respuesta correcta almacenada en la pregunta.

### 6.2. Criterios de evaluación

| **Criterio** | **Descripción** | **Resultado / escala** |
| ------------ | --------------- | ---------------------- |
| Respuesta correcta | Determina si la respuesta ingresada coincide con la respuesta correcta. | Correcta / incorrecta |
| Cantidad de aciertos | Cuenta las respuestas correctas durante el cuestionario. | Número entero de aciertos sobre el total de preguntas |

### 6.3. Método de evaluación

Por cada pregunta, el sistema obtiene la respuesta ingresada por el alumno y la compara con la respuesta correcta utilizando `equalsIgnoreCase()` después de eliminar espacios iniciales y finales mediante `trim()`.

Si la respuesta es correcta, se incrementa el contador `correctas`. Al terminar todas las preguntas, el sistema guarda la cédula, el cuestionario y la cantidad de aciertos.

No se encuentra implementado en el PDF un cálculo de porcentaje, nota numérica, promedio o escala de aprobación.

### 6.4. Ejemplo de evaluación

**Entrada:** Un alumno ingresa su cédula, selecciona un cuestionario y responde sus preguntas.

**Proceso:** Cada respuesta se compara con la respuesta correcta almacenada. Las respuestas correctas incrementan el contador de aciertos.

**Resultado:** Al finalizar, el sistema informa la cantidad de respuestas correctas sobre la cantidad total de preguntas y almacena ese resultado.

---

## 7. Diseño y arquitectura

### 7.1. Arquitectura general

El proyecto está organizado en las siguientes carpetas o paquetes:

- `controlador`
- `facade`
- `modelo`
- `persistencia`
- `vista`

El código presenta una separación entre las clases encargadas de la interfaz gráfica, los controladores, el modelo de datos y la persistencia. Además, `SistemaFacade` funciona como una interfaz simplificada entre las vistas/controladores y las operaciones del sistema.

### 7.2. Patrón MVC

El proyecto aplica el patrón **Modelo-Vista-Controlador** mediante la separación de sus clases.

- **Modelo:** Está formado por `Cuestionario`, `Pregunta` y `Respuesta`. Estas clases representan los datos principales del sistema.
- **Vista:** Está formada por `MenuView`, `DocenteView` y `AlumnoView`. Estas clases construyen las interfaces gráficas mediante Swing y permiten la interacción con el usuario.
- **Controlador:** Está formado por `ControladorAlumno` y `ControladorDocente`, que reciben el `SistemaFacade` y se encargan de abrir las vistas correspondientes.

### 7.3. Segundo patrón de diseño

**Patrón seleccionado:** Facade

**Motivo de elección:**

El patrón Facade permite proporcionar una interfaz simplificada para realizar las operaciones principales del sistema sin que las vistas deban interactuar directamente con todos los objetos del modelo y de persistencia.

**Aplicación:**

La clase `SistemaFacade` concentra operaciones como crear y eliminar cuestionarios, agregar y eliminar preguntas, comprobar respuestas, guardar respuestas y obtener cuestionarios y resultados. También mantiene las referencias a `CuestionarioDAO` y `RespuestaDAO`.

### 7.4. Diagrama de clases

**Diagrama:** Agregar al final

**Clases principales identificadas:**

- `ControladorAlumno`
- `ControladorDocente`
- `SistemaFacade`
- `Cuestionario`
- `Pregunta`
- `Respuesta`
- `CuestionarioDAO`
- `RespuestaDAO`
- `AlumnoView`
- `DocenteView`
- `MenuView`

---

## 8. Conceptos de programación utilizados

### 8.1. Herencia

Se utiliza herencia en las clases de interfaz gráfica, que extienden `JFrame`.

Por ejemplo:

- `AlumnoView extends JFrame`
- `DocenteView extends JFrame`
- `MenuView extends JFrame`

Esto permite que dichas clases hereden las funcionalidades propias de una ventana de Swing.

No se observa en el código una jerarquía de herencia propia entre las clases del modelo.

### 8.2. Polimorfismo

El PDF permite identificar principalmente el uso del método sobrescrito `toString()` en las clases `Cuestionario` y `Pregunta`, lo que constituye una redefinición de un método heredado de `Object`.

También se utilizan componentes de Swing mediante referencias a sus tipos generales, aunque el PDF no documenta explícitamente este uso como un ejemplo de polimorfismo.

### 8.3. Encapsulamiento

Las clases del modelo mantienen sus atributos como `private` y proporcionan métodos públicos para acceder o modificar determinados datos.

Por ejemplo, `Pregunta` posee atributos privados `id`, `enunciado` y `respuestaCorrecta`, y utiliza métodos `get` y `set` para acceder o modificar parte de su estado.

`Cuestionario` también mantiene sus atributos como privados y proporciona métodos para acceder a ellos y administrar sus preguntas.

### 8.4. Abstracción

La clase `SistemaFacade` constituye una abstracción de las operaciones principales del sistema. Las vistas pueden solicitar acciones como `agregarPregunta`, `eliminarPregunta`, `comprobarRespuesta` o `guardarRespuesta` sin encargarse directamente de implementar la lógica interna ni acceder directamente a los archivos JSON.

Los DAO también abstraen las operaciones de lectura y escritura de archivos JSON.

### 8.5. Otras características de Java

- Uso de clases y objetos.
- Uso de colecciones, principalmente `List` y `ArrayList`.
- Uso de excepciones, particularmente `IOException` y `NumberFormatException`.
- Uso de expresiones lambda para eventos de botones y operaciones sobre colecciones.
- Uso de Swing para construir la interfaz gráfica.
- Uso de archivos y streams mediante `FileReader` y `FileWriter`.
- Uso de genéricos, por ejemplo `List<Cuestionario>` y `List<Pregunta>`.
- Uso de la biblioteca Gson para serialización y deserialización JSON.

---

## 9. Organización del proyecto

### 9.1. Estructura general

```text
com.test_01
├── controlador
│   ├── ControladorAlumno
│   └── ControladorDocente
├── facade
│   └── SistemaFacade
├── modelo
│   ├── Cuestionario
│   ├── Pregunta
│   └── Respuesta
├── persistencia
│   ├── CuestionarioDAO
│   └── RespuestaDAO
└── vista
    ├── AlumnoView
    ├── DocenteView
    └── MenuView
```

### 9.2. Componentes principales

| **Componente** | **Responsabilidad** |
| -------------- | ------------------- |
| `ControladorAlumno` | Abrir la vista correspondiente al alumno. |
| `ControladorDocente` | Abrir el panel correspondiente al docente. |
| `SistemaFacade` | Centralizar las operaciones principales del sistema. |
| `Cuestionario` | Representar un cuestionario y sus preguntas. |
| `Pregunta` | Representar una pregunta, su enunciado y respuesta correcta. |
| `Respuesta` | Representar el resultado de un alumno en un cuestionario. |
| `CuestionarioDAO` | Guardar y cargar cuestionarios en JSON. |
| `RespuestaDAO` | Guardar y cargar respuestas/resultados en JSON. |
| `AlumnoView` | Interfaz gráfica para responder cuestionarios. |
| `DocenteView` | Interfaz gráfica para administrar cuestionarios y preguntas. |
| `MenuView` | Menú principal para seleccionar el tipo de usuario. |

---

## 10. Decisiones de diseño

### Uso de archivos JSON para persistencia

**Problema:** Persistir los cuestionarios y resultados del sistema.

**Alternativas consideradas:** responder luego.

**Decisión:** Utilizar archivos JSON (`cuestionarios.json` y `respuestas.json`) mediante Gson.

### Separación mediante MVC

**Problema:** Organizar las responsabilidades de la aplicación.

**Alternativas consideradas:** responder luego.

**Decisión:** Separar el sistema en controladores, vistas y modelo.

### Uso de Facade

**Problema:** Centralizar las operaciones principales del sistema.

**Alternativas consideradas:** responder luego.

**Decisión:** Utilizar la clase `SistemaFacade`.

---

## 11. Validaciones y manejo de errores

El sistema implementa varias validaciones:

- Verificación de que se haya seleccionado un cuestionario.
- Verificación de que se haya ingresado la cédula.
- Verificación de que el cuestionario tenga preguntas.
- Verificación de que se haya ingresado una respuesta.
- Verificación de que se haya ingresado el nombre del cuestionario.
- Verificación de que se haya ingresado una pregunta y una respuesta correcta.
- Verificación de que se haya ingresado el ID de una pregunta.
- Verificación de que el ID ingresado sea numérico.
- Verificación de que exista una pregunta con el ID indicado.
- Confirmación antes de eliminar cuestionarios o preguntas.

En la persistencia se manejan excepciones `IOException`. Cuando no se puede cargar un archivo, los DAO retornan una lista vacía. Al guardar, se imprime la excepción mediante `printStackTrace()`.

---

## 12. Requisitos para ejecutar el proyecto

### 12.1. Requisitos

- **Versión de Java:** responder luego.
- **IDE recomendado:** responder luego.
- **Dependencias:** Gson. El código utiliza `Gson`, `GsonBuilder` y `TypeToken`.
- **Otros requisitos:** El proyecto necesita poder crear y leer los archivos `cuestionarios.json` y `respuestas.json`.

### 12.2. Instalación

No corresponde

### 12.3. Ejecución

Se ejecuta desde el archivo Main.java

---

## 13. Pruebas

### 13.1. Pruebas realizadas

El PDF contiene validaciones implementadas, pero no documenta pruebas ejecutadas ni sus resultados obtenidos. Por lo tanto, no corresponde afirmar que estas pruebas hayan sido efectivamente realizadas.

| **Prueba** | **Entrada / situación** | **Resultado esperado** | **Resultado obtenido** |
| ---------- | ----------------------- | ---------------------- | ---------------------- |
| Selección sin cuestionario | Intentar comenzar sin seleccionar cuestionario | Mostrar `"Seleccione un cuestionario"` | responder luego |
| Cédula vacía | Intentar comenzar sin ingresar cédula | Mostrar `"Ingrese su cédula"` | responder luego |
| Cuestionario sin preguntas | Intentar comenzar un cuestionario vacío | Mostrar `"Este cuestionario no tiene preguntas"` | responder luego |
| Respuesta vacía | Intentar responder sin ingresar texto | Mostrar `"Ingrese una respuesta"` | responder luego |
| ID no numérico | Ingresar texto como ID de pregunta | Mostrar `"Ingrese un ID válido"` | responder luego |
| ID inexistente | Ingresar un ID que no corresponde a una pregunta | Mostrar `"No existe una pregunta con ese ID"` | responder luego |

### 13.2. Casos límite

El código contempla algunos casos límite:

- Cuestionario sin preguntas.
- Selección inexistente de cuestionario.
- Campos obligatorios vacíos.
- ID de pregunta inexistente.
- ID de pregunta con formato inválido.
- Archivos JSON inexistentes o que no pueden ser cargados.


---

## 14. Limitaciones y posibles mejoras

### Limitaciones actuales

- El sistema trabaja con respuestas escritas como texto y no se observan preguntas con opciones múltiples.
- No se observa autenticación o gestión de cuentas de docentes y alumnos.
- Los resultados almacenan la cantidad de aciertos, pero no se observa el cálculo de una nota o porcentaje.
- No se observa una función para modificar cuestionarios o preguntas ya creados.
- La persistencia se realiza mediante archivos JSON en lugar de una base de datos.

### Mejoras futuras

- No se proyectan mejoras futuras actualmente

---

## 15. Distribución del trabajo

Fue hecho de forma individual.

---

## 16. Conclusiones

El proyecto implementa un sistema de preguntas desarrollado en Java, con dos roles principales: Docente y Alumno. El docente puede administrar cuestionarios y preguntas, mientras que el alumno puede responder los cuestionarios y obtener automáticamente la cantidad de respuestas correctas.

El código aplica una separación de responsabilidades mediante las estructuras de **Modelo, Vista y Controlador**, utiliza una clase **Facade** para centralizar las operaciones del sistema y emplea clases DAO para manejar la persistencia en archivos JSON mediante Gson.

También se aplican conceptos de programación orientada a objetos como encapsulamiento, herencia y redefinición de métodos, además del uso de colecciones, excepciones, expresiones lambda y componentes gráficos de Swing.

---

## 17. Referencias

No se consultaron referencias como tal.

---

## 18. Repositorio

**link** poner al final