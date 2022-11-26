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
import android.widget.Toast;

import com.example.sgi.R;
import com.example.sgi.network.ApiClient;
import com.example.sgi.network.IApiTicket;
import com.example.sgi.panel.AdapterTickets;
import com.example.sgi.utils.Ticket;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pc_frament_proceso extends Fragment {

    // Declaración de Variables.
    List<Ticket> ticketNuevosList;
    private RecyclerView recyclerView;
    AdapterTickets adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getContext()).inflate(R.layout.frament_pc_proceso,container,false);
        recyclerView=view.findViewById(R.id.fr_pc_nuevos_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        mostrarUsuariosMnt();
        return view;
    }

    /**
     * Método que nos abre la activity del Ticket Abierto.
     * @param item - Ticket que hemos pulsado.
     */
    public void entrarEnTicket(Ticket item) {
        Intent intent = new Intent(getActivity(), PanelControlTicketAbierto.class);
        intent.putExtra("Ticket",item);
        startActivity(intent);
    }

    private void mostrarUsuariosMnt(){
        Call<List<Ticket>> call = ApiClient.getClient().create(IApiTicket.class).getTicketsProceso();
        call.enqueue(new Callback<List<Ticket>>() {

            @Override
            public void onResponse(Call<List<Ticket>> call, Response<List<Ticket>> response) {
                if(response.isSuccessful()){
                    ticketNuevosList = response.body();
                    adapter = new AdapterTickets(ticketNuevosList, Pc_frament_proceso.this.getContext(), new AdapterTickets.OnItemClickListener() {
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
                Toast.makeText(Pc_frament_proceso.this.getContext(),"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        });
    }
}