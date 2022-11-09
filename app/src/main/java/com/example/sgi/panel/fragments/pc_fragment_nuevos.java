package com.example.sgi.panel.fragments;

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
        elements.add(new Ticket("Pato","1","Gallo"));
        elements.add(new Ticket("Casa","2","Gallo"));
        elements.add(new Ticket("Perro","3","Gallo"));
        elements.add(new Ticket("Gallina","4","Gallo"));
        elements.add(new Ticket("Alacran","5","Gallo"));
        elements.add(new Ticket("Leon","6","Gallo"));
        elements.add(new Ticket("Gato","7","Gallo"));
        elements.add(new Ticket("Trucha","8","Gallo"));
        elements.add(new Ticket("Jaguar","9","Gallo"));
        elements.add(new Ticket("Dinosaurio","10","Gallo"));
        elements.add(new Ticket("Gonzalo","11","Gallo"));
        elements.add(new Ticket("Pato","12","Gallo"));
        elements.add(new Ticket("Pato","13","Gallo"));
        elements.add(new Ticket("Pato","14","Gallo"));
        elements.add(new Ticket("Pato","15","Gallo"));
        elements.add(new Ticket("Pato","16","Gallo"));

        ListAdapterTicketsNuevos listAdapter = new ListAdapterTicketsNuevos(elements,this.getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_pc_nuevos,container,false);

        RecyclerView recyclerView=view.findViewById(R.id.fr_pc_nuevos_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(listAdapter);
        return view;
    }
}