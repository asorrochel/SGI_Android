package com.example.sgi.utils;

import java.io.Serializable;

public class Pair <Integer, String> implements Serializable {
    int clave;
    String valor;

    public Pair(){
    }

    public Pair(Integer x, String z){
        clave = (int) x;
        valor = z;
    }

    public int getClave() {
        return clave;
    }

    public void setIdAula(int idAula) {
        this.clave = idAula;
    }

    public String getValor() {
        return valor;
    }

    public void setAula(String valor) {
        this.valor = valor;
    }
}
