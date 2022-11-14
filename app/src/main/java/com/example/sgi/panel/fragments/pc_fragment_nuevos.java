package com.example.sgi.panel.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Toast;

import com.example.sgi.R;
import com.example.sgi.network.ApiClient;
import com.example.sgi.network.ApiTicket;
import com.example.sgi.network.ApiUsuario;
import com.example.sgi.panel.AdapterTicketsNuevos;
import com.example.sgi.utils.Ticket;
import com.example.sgi.utils.Usuario;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class pc_fragment_nuevos extends Fragment {

    // Declaración de Variables.
    List<Ticket> ticketNuevosList;
    private RecyclerView recyclerView;
    AdapterTicketsNuevos adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_pc_nuevos,container,false);
        recyclerView=view.findViewById(R.id.fr_pc_nuevos_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        /*
        // Inicializamos Variables.
        ticketNuevosList = new ArrayList<>();
        ticketNuevosList.add(new Ticket("Veyon no funciona","C.12 Vesp","PC 01"));
        ticketNuevosList.add(new Ticket("Cable HDMI roto","C.02 Vesp","PC 05"));
        ticketNuevosList.add(new Ticket("Proyector no da señal","Taller","Proyector"));
        ticketNuevosList.add(new Ticket("Patilla derecha teclado rota","C07 Diurno","PC 08"));
        ticketNuevosList.add(new Ticket("Excell no abre","C.05 Vesp","PC 18"));
        ticketNuevosList.add(new Ticket("VDI no inicia sesion","Biblioteca","PC 01"));
        ticketNuevosList.add(new Ticket("Veyon Desinstalado","C.07 Diurno","PC 27"));
        ticketNuevosList.add(new Ticket("SSD borrado","C.09 Diurno","PC 03"));
        ticketNuevosList.add(new Ticket("Equipo no arranca","C.06 Vesp","PC 11"));
        AdapterTicketsNuevos listAdapter = new AdapterTicketsNuevos(ticketNuevosList, this.getContext(), new AdapterTicketsNuevos.OnItemClickListener() {
            @Override
            public void onItemClick(Ticket item) {
                entrarEnTicket(item);
            }
        });
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_pc_nuevos,container,false);
        RecyclerView recyclerView=view.findViewById(R.id.fr_pc_nuevos_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(listAdapter);
        return view;
         */
        mostrarUsuariosMnt();
        return view;
    }

    /**
     * Método que nos abre la activity del Ticket Abierto.
     * @param item - Ticket que hemos pulsado.
     */
    public void entrarEnTicket(Ticket item) {
        Intent intent = new Intent(getActivity(), panelControlTicketAbierto.class);
        intent.putExtra("Ticket",item);
        startActivity(intent);
    }

    private void mostrarUsuariosMnt(){
        Call<List<Ticket>> call = ApiClient.getClient().create(ApiTicket.class).getTicketsNuevos();
        call.enqueue(new Callback<List<Ticket>>() {

            @Override
            public void onResponse(Call<List<Ticket>> call, Response<List<Ticket>> response) {
                if(response.isSuccessful()){
                    ticketNuevosList = response.body();
                    adapter = new AdapterTicketsNuevos(ticketNuevosList, pc_fragment_nuevos.this.getContext(), new AdapterTicketsNuevos.OnItemClickListener() {
                        @Override
                        public void onItemClick(Ticket item) {
                            entrarEnTicket(item);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Ticket>> call, Throwable t) {
                Toast.makeText(pc_fragment_nuevos.this.getContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        });
    }
}