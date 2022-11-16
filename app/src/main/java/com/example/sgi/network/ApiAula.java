package com.example.sgi.network;

import com.example.sgi.utils.Aula;
import com.example.sgi.utils.Ticket;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiAula {
    @GET("api/Aulas/Listar")
    Call<List<Aula>> getAulas();
}
