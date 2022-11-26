package com.example.sgi.utils;

import java.io.Serializable;

public class Aula implements Serializable {
    private int idAula;
    private String aula;

    public Aula(int idAula, String aula) {
        this.idAula = idAula;
        this.aula = aula;
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }
}
