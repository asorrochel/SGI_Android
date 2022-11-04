package com.example.sgi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class inicioProfesoresMnt extends AppCompatActivity {

    Toolbar toolbar;
    AppCompatButton btnCerrarSesion, btnPanelControl, btnCrearTicket, btnEstadoTicket, btnHistorialTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_profesores_mnt);

        toolbar = findViewById(R.id.mainToolBar);
        setToolbar(toolbar);

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

    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
            startActivity(new Intent(inicioProfesoresMnt.this, ajustes.class));
            return true;
        } else {
            Toast.makeText(inicioProfesoresMnt.this, "Error al acceder a Ajustes", Toast.LENGTH_SHORT).show();
            return false;
        }
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