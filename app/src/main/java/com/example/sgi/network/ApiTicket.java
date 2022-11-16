package com.example.sgi.network;

import com.example.sgi.utils.Ticket;
import com.example.sgi.utils.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiTicket {

    @GET("api/Tickets/Listar")
    Call<List<Ticket>> getTicketsNuevos();
    @GET("api/Tickets/ListarUrgentes")
    Call<List<Ticket>> getTicketsUrgentes();
    @GET("api/Tickets/ListarEspera")
    Call<List<Ticket>> getTicketsEspera();
    @GET("api/Tickets/ListarProceso")
    Call<List<Ticket>> getTicketsProceso();
    @POST("api/Tickets/Insertar")
    Call<Ticket> addUser(@Body Ticket ticket);

}
