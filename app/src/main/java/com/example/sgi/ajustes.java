package com.example.sgi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

public class ajustes extends AppCompatActivity {

    Usuario u = new Usuario();
    Toolbar toolbar;
    AppCompatButton btnConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        toolbar = findViewById(R.id.mainToolBar);
        setToolbar(toolbar);

        btnConfirmar = findViewById(R.id.ajustes_btn_guardar);

        //CAMBIARLO (SÓLO PRUEBAS)
        u.setRolUsuario("ROL_TUT_MANT");

        confirmarCambios();
    }

    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Ajustes");
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    private void confirmarCambios() {
        btnConfirmar.setOnClickListener((View) -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Guardar Cambios")
                    .setMessage("¿Desea Confirmar los cambios?")
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (u.getRolUsuario().equalsIgnoreCase("ROL_TUTOR")) {
                                startActivity(new Intent(ajustes.this, inicioTutores.class));
                                Toast.makeText(ajustes.this, "Cambios guardados correctamente", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_PROFESOR") || u.getRolUsuario().equalsIgnoreCase("ROL_ALUMNO")) {
                                startActivity(new Intent(ajustes.this, inicioProfesorAlumno.class));
                                Toast.makeText(ajustes.this, "Cambios guardados correctamente", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_TUT_MANT")) {
                                startActivity(new Intent(ajustes.this, inicioTutoresMnt.class));
                                Toast.makeText(ajustes.this, "Cambios guardados correctamente", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_PROF_MANT")) {
                                startActivity(new Intent(ajustes.this, inicioProfesoresMnt.class));
                                Toast.makeText(ajustes.this, "Cambios guardados correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }
}