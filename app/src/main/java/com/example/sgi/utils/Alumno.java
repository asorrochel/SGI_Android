package com.example.sgi.utils;

import java.io.Serializable;

public class Alumno implements Serializable {
    private int idAlumno;
    private String nombre, apellidos;

    public Alumno(){
    }

    public Alumno(String nombre, String apellidos){
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
}
