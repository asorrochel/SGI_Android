package com.example.sgi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

public class inicioProfesorAlumno extends AppCompatActivity {

    AppCompatButton btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_profesor_alumno);

        btnCerrarSesion = findViewById(R.id.inicio_profesor_alumno_cerrar_sesion);

        btnCerrarSesion.setOnClickListener((View) -> {
            startActivity(new Intent(inicioProfesorAlumno.this, login.class));
        });
    }
}