package com.example.sgi.panel.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.sgi.R;
import com.example.sgi.inicio.inicioProfesorAlumno;
import com.example.sgi.inicio.inicioProfesoresMnt;
import com.example.sgi.inicio.inicioTutores;
import com.example.sgi.inicio.inicioTutoresMnt;
import com.example.sgi.network.ApiClient;
import com.example.sgi.network.ApiUsuario;
import com.example.sgi.utils.Usuario;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class asignarTecnico extends AppCompatActivity {

    private List<Usuario> userList;
    private RecyclerView recyclerView;
    ListAdapterMnt adapter;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_tecnico);

        //setToolbar(toolbar);
        recyclerView = findViewById(R.id.at_rv);
        //toolbar = findViewById(R.id.atToolBar);

        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        //recyclerView.setItemAnimator(new DefaultItemAnimator());


        mostrarUsuariosMnt();
        /*
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
         */
    }

    private void mostrarUsuariosMnt(){
        Call<List<Usuario>> call = ApiClient.getClient().create(ApiUsuario.class).getUsuariosMnt();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()){
                    userList = response.body();
                    adapter = new ListAdapterMnt(userList,getApplicationContext());
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Usuario>> call, Throwable t) {
                Toast.makeText(asignarTecnico.this,"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*
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
    */
}