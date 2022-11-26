package com.example.sgi.panel.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.sgi.R;
import com.example.sgi.network.ApiClient;
import com.example.sgi.network.ApiUsuario;
import com.example.sgi.utils.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class asignarTecnico extends AppCompatActivity {

    private List<Usuario> userList;
    private RecyclerView recyclerView;
    AdapterTecnicosMnt adapter;
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

    }

    private void mostrarUsuariosMnt(){
        Call<List<Usuario>> call = ApiClient.getClient().create(ApiUsuario.class).getUsuariosMnt();
        call.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                if(response.isSuccessful()){
                    userList = response.body();
                    adapter = new AdapterTecnicosMnt(userList,getApplicationContext());
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