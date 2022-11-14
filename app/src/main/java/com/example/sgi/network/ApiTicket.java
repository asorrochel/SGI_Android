package com.example.sgi.network;

import com.example.sgi.utils.Ticket;
import com.example.sgi.utils.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiTicket {

    @GET("api/Tickets/ListarNuevos")
    Call<List<Ticket>> getTicketsNuevos();
    @GET("api/Tickets/ListarUrgentes")
    Call<List<Ticket>> getTicketsUrgentes();
    @GET("api/Tickets/ListarEspera")
    Call<List<Ticket>> getTicketsEspera();
    @GET("api/Tickets/ListarProceso")
    Call<List<Ticket>> getTicketsProceso();

}
