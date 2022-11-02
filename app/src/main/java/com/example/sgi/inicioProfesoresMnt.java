package com.example.sgi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;

public class inicioProfesoresMnt extends AppCompatActivity {

    AppCompatButton btnCerrarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_profesores_mnt);

        btnCerrarSesion = findViewById(R.id.inicio_profesores_mnt_btn_cerrar_sesion);

        btnCerrarSesion.setOnClickListener((View) -> {
            startActivity(new Intent(inicioProfesoresMnt.this, login.class));
        });
    }
}