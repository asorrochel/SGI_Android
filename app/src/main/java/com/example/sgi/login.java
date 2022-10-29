package com.example.sgi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.example.sgi.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class login extends Activity {

    FirebaseAuth firebaseAuth;

    AppCompatButton btn_Acceder;
    EditText contraseñaET,correoET;
    TextView registrarse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ProgressDialog progressDialog = new ProgressDialog(this);

        btn_Acceder = findViewById(R.id.login_btn_acceder);
        correoET = findViewById(R.id.login_prompt_correo_EditText);
        contraseñaET = findViewById(R.id.login_prompt_contraseña_EditText);
        registrarse = findViewById(R.id.login_registrarse);

        firebaseAuth=FirebaseAuth.getInstance();

        btn_Acceder.setOnClickListener((View) -> {
                    progressDialog.show();
                    String correo = correoET.getText().toString();
                    String contraseña = contraseñaET.getText().toString();
                    if (correo.length() == 0 || contraseña.length() == 0) {
                        Toast.makeText(login.this, "Correo o contraseña no validos", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    firebaseAuth.signInWithEmailAndPassword(correo, contraseña).addOnCompleteListener((task) -> {
                        progressDialog.hide();
                        if (task.isSuccessful()) {
                            if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                startActivity(new Intent(login.this, inicioTutoresMnt.class));
                            } else {
                                Toast.makeText(login.this, "Correo o contraseña no validos", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(login.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
            });
        /*
        binding.loginContraseAOlvidada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String correo=binding.loginPromptCorreoEditText.getText().toString();
                progressDialog.setTitle("Enviando");
                progressDialog.show();
                firebaseAuth.sendPasswordResetEmail(correo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                progressDialog.cancel();
                                Toast.makeText(login.this,"Restablecimiento de contraseña enviado",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.cancel();
                                Toast.makeText(login.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        */
        registrarse.setOnClickListener((View) -> {
                startActivity(new Intent(login.this,registro.class));
        });
    }
}