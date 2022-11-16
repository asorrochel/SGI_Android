package com.example.sgi.utils;

import android.net.Uri;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Ticket implements Serializable {
    private int idTicket;
    private String titulo;
    private int aula;
    private String equipo;
    private String comentario;
    private Date fechaEmision;
    private Date fechaResolucion;
    private Uri foto;
    private int idAlumno;
    private int idProfesor;
    private int idEstado;

    public Ticket(String titulo, int aula, String equipo, String comentario, Date fechaEmision, Date fechaResolucion, Uri imagen, int idAlumno, int idProfesor, int idEstado ) {
        this.titulo = titulo;
        this.aula = aula;
        this.equipo = equipo;
        this.comentario = comentario;
        this.fechaEmision = fechaEmision;
        this.fechaResolucion = fechaResolucion;
        this.foto = imagen;
        this.idAlumno = idAlumno;
        this.idEstado = idEstado;
        this.idProfesor = idProfesor;
    }
    public Ticket(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAula() {
        return aula;
    }

    public void setAula(int aula) {
        this.aula = aula;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public Uri getFoto() {return foto;}

    public void setFoto(Uri foto) {this.foto = foto;}

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }
}
