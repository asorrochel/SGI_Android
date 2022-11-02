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

        btnCerrarSesion = findViewById(R.id.inicio_tutores_mnt_btn_cerrar_sesion);

        btnCerrarSesion.setOnClickListener((View) -> {
            startActivity(new Intent(inicioTutoresMnt.this, login.class));
        });
    }
}