package com.example.sgi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class inicioProfesorAlumno extends AppCompatActivity {

    Toolbar toolbar;
    AppCompatButton btnCerrarSesion, btnCrearTicket, btnEstadoTicket;
    Usuario u = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_profesor_alumno);

        toolbar = findViewById(R.id.mainToolBar);
        setToolbar(toolbar);

        btnCrearTicket = findViewById(R.id.inicio_profesor_alumno_btn_crear_ticket);
        btnEstadoTicket = findViewById(R.id.inicio_profesor_alumno_btn_estado_ticket);
        btnCerrarSesion = findViewById(R.id.inicio_profesor_alumno_btn_cerrar_sesion);

        clickCrearTicket();
        clickEstadoTicket();
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
            startActivity(new Intent(inicioProfesorAlumno.this, ajustes.class));
            return true;
        } else {
            Toast.makeText(inicioProfesorAlumno.this, "Error al acceder a Ajustes", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void clickCrearTicket() {
        btnCrearTicket.setOnClickListener((View) -> {
            if (u.getRolUsuario().equalsIgnoreCase("ROL_PROFESOR")) {
                startActivity(new Intent(inicioProfesorAlumno.this, crearTicketImg.class));
            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_ALUMNO")) {
                startActivity(new Intent(inicioProfesorAlumno.this, crearTicket.class));
            } else {
                Toast.makeText(inicioProfesorAlumno.this, "Error al acceder a Crear Ticket", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clickEstadoTicket() {
        btnEstadoTicket.setOnClickListener((View) -> {
            //startActivity(new Intent(inicioProfesorAlumno.this, estadoTicket.class));
        });
    }

    private void clickCerrarSesion() {
        btnCerrarSesion.setOnClickListener((View) -> {
            startActivity(new Intent(inicioProfesorAlumno.this, login.class));
        });
    }
}