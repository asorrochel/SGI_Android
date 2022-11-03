package com.example.sgi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

public class inicioTutores extends AppCompatActivity {

    Toolbar toolbar;
    AppCompatButton btnCerrarSesion, btnCrearTicket, btnValidarTicket, btnEstadoTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_tutores);

        toolbar = findViewById(R.id.mainToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        btnCrearTicket = findViewById(R.id.inicio_tutores_btn_crear_ticket);
        btnValidarTicket = findViewById(R.id.inicio_tutores_btn_validar_ticket);
        btnEstadoTicket = findViewById(R.id.inicio_tutores_btn_estado_ticket);
        btnCerrarSesion = findViewById(R.id.inicio_tutores_btn_cerrar_sesion);

        clickCrearTicket();
        clickValidarTicket();
        clickEstadoTicket();
        clickCerrarSesion();
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