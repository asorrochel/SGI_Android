package com.example.sgi.panel.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.example.sgi.R;
import com.example.sgi.utils.Usuario;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import java.util.ArrayList;

public class asignarTecnico extends AppCompatActivity {

    private ArrayList<Usuario> userList;
    private RecyclerView recyclerView;
    ListAdapterMnt adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_tecnico);
        recyclerView = findViewById(R.id.at_rv);
        userList = new ArrayList<>();
        adapter = new ListAdapterMnt(userList);
        toolbar = findViewById(R.id.atToolBar);

        setToolbar(toolbar);

        setUserinfo();
        setAdapter();


        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //IF: SI EL TICKET YA TIENE UN TECNICO ASIGNADO, QUE MUESTRE QUE YA ESTA ASIGNADO Y ELSE: SI NO LO TIENE ASIGNADO QUE LE DEJE ASIGNAR UNO CON EL
                new MaterialAlertDialogBuilder(view.getContext())
                        .setTitle("Asignar a "+userList.get(recyclerView.getChildAdapterPosition(view)).getNombre()+" al ticket")
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                startActivity(new Intent(asignarTecnico.this, panelControlTicketAbierto.class));
                                Toast.makeText(asignarTecnico.this, userList.get(recyclerView.getChildAdapterPosition(view)).getNombre()+ " asignado correctamente", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancelar", null)
                        .show();
            }
        });
    }

    private void setAdapter() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setUserinfo() {
        userList.add(new Usuario("Jose","Perez","ROL_MNT"));
        userList.add(new Usuario("Mioa","Perez","ROL_MNT"));
        userList.add(new Usuario("Pedro","Perez","ROL_MNT"));
        userList.add(new Usuario("Mateo","Perez","ROL_MNT"));
    }

    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ticket");
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }


}