package com.example.sgi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

public class inicioProfesoresMnt extends AppCompatActivity {

    AppCompatButton btnCerrarSesion, btnPanelControl, btnCrearTicket, btnEstadoTicket, btnHistorialTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_profesores_mnt);

        btnPanelControl = findViewById(R.id.inicio_profesores_mnt_btn_panel);
        btnCrearTicket = findViewById(R.id.inicio_profesores_mnt_btn_crear_ticket);
        btnEstadoTicket = findViewById(R.id.inicio_profesores_mnt_btn_estado_ticket);
        btnHistorialTicket = findViewById(R.id.inicio_profesores_mnt_btn_historial_ticket);
        btnCerrarSesion = findViewById(R.id.inicio_profesores_mnt_btn_cerrar_sesion);

        clickPanelControl();
        clickCrearTicket();
        clickEstadoTicket();
        clickHistorialTicket();
        clickCerrarSesion();
    }

    private void clickPanelControl() {
        btnPanelControl.setOnClickListener((View) -> {
            //startActivity(new Intent(inicioProfesoresMnt.this, panelControl.class));
        });
    }

    private void clickCrearTicket() {
        btnCrearTicket.setOnClickListener((View) -> {
            startActivity(new Intent(inicioProfesoresMnt.this, crearTicketImg.class));
        });
    }
    private void clickEstadoTicket() {
        btnEstadoTicket.setOnClickListener((View) -> {
            //startActivity(new Intent(inicioProfesoresMnt.this, estadoTicket.class));
        });
    }

    private void clickHistorialTicket() {
        btnHistorialTicket.setOnClickListener((View) -> {
            //startActivity(new Intent(inicioProfesoresMnt.this, historialTicket.class));
        });
    }

    private void clickCerrarSesion() {
        btnCerrarSesion.setOnClickListener((View) -> {
            startActivity(new Intent(inicioProfesoresMnt.this, login.class));
        });
    }
}