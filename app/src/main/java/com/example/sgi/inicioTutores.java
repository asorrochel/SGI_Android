package com.example.sgi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class inicioTutores extends AppCompatActivity {

    Toolbar toolbar;
    AppCompatButton btnCerrarSesion, btnCrearTicket, btnValidarTicket, btnEstadoTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_tutores);

        toolbar = findViewById(R.id.mainToolBar);
        setToolbar(toolbar);

        btnCrearTicket = findViewById(R.id.inicio_tutores_btn_crear_ticket);
        btnValidarTicket = findViewById(R.id.inicio_tutores_btn_validar_ticket);
        btnEstadoTicket = findViewById(R.id.inicio_tutores_btn_estado_ticket);
        btnCerrarSesion = findViewById(R.id.inicio_tutores_btn_cerrar_sesion);

        clickCrearTicket();
        clickValidarTicket();
        clickEstadoTicket();
        clickCerrarSesion();
    }

    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inicio");
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ajustes) {
            startActivity(new Intent(inicioTutores.this, ajustes.class));
            return true;
        } else {
            Toast.makeText(inicioTutores.this, "Error al acceder a Ajustes", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void clickCrearTicket() {
        btnCrearTicket.setOnClickListener((View) -> {
            startActivity(new Intent(inicioTutores.this, crearTicketImg.class));
        });
    }

    private void clickValidarTicket() {
        btnValidarTicket.setOnClickListener((View) -> {
            //startActivity(new Intent(inicioTutores.this, validarTicket.class));
        });
    }

    private void clickEstadoTicket() {
        btnEstadoTicket.setOnClickListener((View) -> {
            //startActivity(new Intent(inicioTutores.this, estadoTicket.class));
        });
    }

    private void clickCerrarSesion() {
        btnCerrarSesion.setOnClickListener((View) -> {
            startActivity(new Intent(inicioTutores.this, login.class));
        });
    }
}