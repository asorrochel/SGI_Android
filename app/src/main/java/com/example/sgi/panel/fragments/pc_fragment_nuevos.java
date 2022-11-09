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

import com.example.sgi.R;
import com.example.sgi.panel.ListAdapterTicketsNuevos;
import com.example.sgi.utils.Ticket;
import java.util.ArrayList;
import java.util.List;

public class pc_fragment_nuevos extends Fragment {

    List<Ticket> elements;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        elements = new ArrayList<>();
        elements.add(new Ticket("Veyon no funciona","C.12 Vesp","PC 01"));
        elements.add(new Ticket("Cable HDMI roto","C.02 Vesp","PC 05"));
        elements.add(new Ticket("Proyector no da señal","Taller","Proyector"));
        elements.add(new Ticket("Patilla derecha teclado rota","C07 Diurno","PC 08"));
        elements.add(new Ticket("Excell no abre","C.05 Vesp","PC 18"));
        elements.add(new Ticket("VDI no inicia sesion","Biblioteca","PC 01"));
        elements.add(new Ticket("Veyon Desinstalado","C.07 Diurno","PC 27"));
        elements.add(new Ticket("SSD borrado","C.09 Diurno","PC 03"));
        elements.add(new Ticket("Equipo no arranca","C.06 Vesp","PC 11"));

        ListAdapterTicketsNuevos listAdapter = new ListAdapterTicketsNuevos(elements, this.getContext(), new ListAdapterTicketsNuevos.OnItemClickListener() {
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
    }

    public void entrarEnTicket(Ticket item) {
        Intent intent = new Intent(getActivity(), panelControlTicketAbierto.class);
        intent.putExtra("Ticket",item);
        startActivity(intent);
    }
}