package com.academia.modelo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// Anotación Entity para indicar que esta clase es una entidad JPA.
@Entity(name = "Alumno")
// Anotación Table para especificar el nombre de la tabla en la base de datos.
@Table(name = "alumnos")
public class Alumno {

    // Anotaciones para marcar 'id' como la clave primaria y autoincrementada.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    // Anotación Column para especificar la columna correspondiente en la base de datos.
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellidos;

    @Column(name = "edad")
    private int edad;

    // Constructor con parámetros para crear una instancia de Alumno.
    public Alumno(String nombre, String apellidos, int edad) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
    }

    // Constructor vacío 
    public Alumno() {
    }

    // Métodos getter y setter para cada campo.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    // Método toString para representar el objeto Alumno como una cadena.
    @Override
    public String toString() {
        return "Alumno{" +
               "id=" + id +
               ", nombre='" + nombre + '\'' +
               ", apellidos='" + apellidos + '\'' +
               ", edad=" + edad +
               '}';
    }
}
