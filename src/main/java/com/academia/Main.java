package com.academia;

import com.academia.controlador.Acceso;
import com.academia.modelo.Alumno;

public class Main {
    public static void main(String[] args) {
        // Crear una instancia de Acceso para manejar la conexión a la base de datos
        Acceso conexion = new Acceso();
        conexion.conectar(); // Conectar con la base de datos

        // Datos del nuevo alumno
        String nombre = "Pepe";
        String apellido = "Gierre";
        int edad = 33;
        // Crear una instancia de Alumno
        Alumno alumno1 = new Alumno(nombre, apellido, edad);

        try {
            // Añadir el nuevo alumno a la base de datos
            conexion.añadirAlumno(alumno1);
            System.out.println("--------------------Datos alumno recien añadido-------------");
            // Mostrar todos los alumnos para verificar la inserción
            conexion.mostrarAlumnos();

            // Actualizar un alumno existente
            int idAlumno = 1; // ID del alumno a actualizar
            nombre = "Ramon";
            apellido = "Martin";
            edad = 71;
            // Leer el alumno existente con el ID especificado
            Alumno alumnoExistente = conexion.leerAlumno(idAlumno);
            if (alumnoExistente != null) {
                // Si el alumno existe, actualizar sus datos
                System.out.println("-----------------Datos actualizadas----------------------");
                alumno1.setId(idAlumno);
                alumno1.setNombre(nombre);
                alumno1.setApellidos(apellido);
                alumno1.setEdad(edad);
                // Realizar la actualización en la base de datos
                conexion.actualizarAlumno(alumno1);
                // Mostrar todos los alumnos para verificar la actualización
                conexion.mostrarAlumnos();
            }
            System.out.println("-------------Cantidad de filas en la base de datos-----------");
            // Mostrar la cantidad total de alumnos
            conexion.cantidadAlumnos();

        } catch (Exception e) {
            // Capturar y mostrar cualquier excepción que ocurra
            e.printStackTrace();
        } finally {
            // Asegurarse de cerrar la conexión a la base de datos
            conexion.desconectar();
        }
        // Mensaje final indicando que todo el proceso se ejecutó correctamente
        System.out.println("Todo fue correctamente");
    }
}
