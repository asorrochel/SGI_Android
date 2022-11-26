package com.example.sgi.network;

import com.example.sgi.utils.Ticket;
import com.example.sgi.utils.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IApiTicket {

    @GET("api/Tickets/Listar?idEstado=1")
    Call<List<Ticket>> getTicketsNuevos();
    @GET("api/Tickets/Listar?idEstado=2")
    Call<List<Ticket>> getTicketsUrgentes();
    @GET("api/Tickets/Listar?idEstado=3")
    Call<List<Ticket>> getTicketsEspera();
    @GET("api/Tickets/Listar?idEstado=4")
    Call<List<Ticket>> getTicketsProceso();
    @POST("api/Tickets/Insertar")
    Call<Ticket> addTicket(@Body Ticket ticket);
}
