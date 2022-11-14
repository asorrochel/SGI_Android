package com.example.sgi.network;

import com.example.sgi.utils.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiUsuario {
    @GET("api/Profesores/Listar")
    Call<List<Usuario>> getUsuariosMnt();
}
