package com.example.sgi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class inicioTutoresMnt extends AppCompatActivity {

    AppCompatButton btnCerrarSesion, btnPanelControl, btnCrearTicket, btnValidarTicket, btnEstadoTicket, btnHistorialTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_tutores_mnt);

        btnPanelControl = findViewById(R.id.inicio_tutores_mnt_btn_panel);
        btnCrearTicket = findViewById(R.id.inicio_tutores_mnt_btn_crear_ticket);
        btnValidarTicket = findViewById(R.id.inicio_tutores_mnt_btn_validar_ticket);
        btnEstadoTicket = findViewById(R.id.inicio_tutores_mnt_btn_estado_ticket);
        btnHistorialTicket = findViewById(R.id.inicio_tutores_mnt_btn_historial_ticket);
        btnCerrarSesion = findViewById(R.id.inicio_tutores_mnt_btn_cerrar_sesion);

        clickPanelControl();
        clickCrearTicket();
        clickValidarTicket();
        clickEstadoTicket();
        clickHistorialTicket();
        clickCerrarSesion();
    }

    private void clickPanelControl() {
        btnPanelControl.setOnClickListener((View) -> {
            //startActivity(new Intent(inicioTutoresMnt.this, panelControl.class));
        });
    }

    private void clickCrearTicket() {
        btnCrearTicket.setOnClickListener((View) -> {
            startActivity(new Intent(inicioTutoresMnt.this, crearTicketImg.class));
        });
    }

    private void clickValidarTicket() {
        btnValidarTicket.setOnClickListener((View) -> {
            //startActivity(new Intent(inicioTutoresMnt.this, validarTicket.class));
        });
    }

    private void clickEstadoTicket() {
        btnEstadoTicket.setOnClickListener((View) -> {
            //startActivity(new Intent(inicioTutoresMnt.this, estadoTicket.class));
        });
    }

    private void clickHistorialTicket() {
        btnHistorialTicket.setOnClickListener((View) -> {
            //startActivity(new Intent(inicioTutoresMnt.this, historialTicket.class));
        });
    }

    private void clickCerrarSesion() {
        btnCerrarSesion.setOnClickListener((View) -> {
            startActivity(new Intent(inicioTutoresMnt.this, login.class));
        });
    }
}