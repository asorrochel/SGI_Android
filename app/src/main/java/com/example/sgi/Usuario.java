package com.example.sgi;

public class Usuario {
    private String nombre, apellidos, correo, contraseña, rolUsuario;

    public Usuario() {
    }

    public Usuario(String correo, String contraseña) {
        this.correo = correo;
        this.contraseña = contraseña;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRolUsuario() {return rolUsuario;}

    public void setRolUsuario(String rolUsuario) {this.rolUsuario = rolUsuario;}
}
