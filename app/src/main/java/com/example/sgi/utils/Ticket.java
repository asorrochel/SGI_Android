package com.example.sgi.utils;

public class Ticket {
    private String titulo;
    private String aula;
    private String fecha;
    private String equipo;

    public Ticket(String titulo, String aula, String equipo) {
        this.titulo = titulo;
        this.aula = aula;
        this.equipo = equipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
}
