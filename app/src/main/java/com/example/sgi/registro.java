package com.example.sgi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sgi.databinding.ActivityRegistroBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.regex.Pattern;

public class registro extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    AppCompatButton btnRegistrar;
    EditText contraseñaET,correoET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        final ProgressDialog progressDialog = new ProgressDialog(this);

        btnRegistrar = findViewById(R.id.registro_btn_registrar);
        correoET = findViewById(R.id.registro_prompt_correo_EditText);
        contraseñaET = findViewById(R.id.registro_prompt_contraseña_EditText);

        firebaseAuth=FirebaseAuth.getInstance();

        btnRegistrar.setOnClickListener((View) -> {
            progressDialog.show();
            String correo = correoET.getText().toString();
            String contraseña = contraseñaET.getText().toString();

                firebaseAuth.createUserWithEmailAndPassword(correo, contraseña).addOnCompleteListener((task) -> {
                    progressDialog.hide();
                    if (task.isSuccessful()) {
                        firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(registro.this, "Registro Completado, Verifique su Correo", Toast.LENGTH_SHORT).show();
                                    correoET.setText("");
                                    contraseñaET.setText("");
                                } else {
                                    Toast.makeText(registro.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                        correoET.setText("");
                        contraseñaET.setText("");
                        startActivity(new Intent(registro.this, login.class));
                    } else {
                        Toast.makeText(registro.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        });
    }
}
