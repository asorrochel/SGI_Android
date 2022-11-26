package com.example.sgi.network;

import com.example.sgi.utils.Alumno;
import com.example.sgi.utils.Profesor;
import com.example.sgi.utils.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiUsuario {
    @GET("api/Profesores/Listar")
    Call<List<Usuario>> getUsuariosMnt();
    @GET("api/Alumnos/Listar")
    Call<List<Alumno>> getAlumnos();
    @GET("api/Profesores/Listar")
    Call<List<Profesor>> getProfesores();
}
