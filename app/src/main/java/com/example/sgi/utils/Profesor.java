package com.example.sgi.utils;

import java.io.Serializable;

public class Profesor implements Serializable {
    private int idProfesor;
    private String nombre, apellidos;

    public Profesor(){
    }

    public Profesor(String nombre, String apellidos){
        this.nombre = nombre;
        this.apellidos = apellidos;
    }
}
