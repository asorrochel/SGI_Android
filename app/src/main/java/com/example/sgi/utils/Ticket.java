package com.example.sgi.utils;

import android.net.Uri;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {
    private int idTicket;
    private String titulo;
    private String detalles;
    private Aula aula;
    private int idAula;
    private String equipo;
    private String fechaEmision;
    private String fechaResolucion;
    private Uri foto;
    private Profesor profesor;
    private Alumno alumno;
    private int idEstado;

    public Ticket(String titulo, Aula aula, String equipo) {
        this.titulo = titulo;
        this.aula = aula;
        this.equipo = equipo;
    }

    public Ticket(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public int getIdAula() {
        return idAula;
    }
    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }
    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public String getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(String fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(String fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public Uri getFoto() {
        return foto;
    }

    public void setFoto(Uri foto) {
        this.foto = foto;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
}


