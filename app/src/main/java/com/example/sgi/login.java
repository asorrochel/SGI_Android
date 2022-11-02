package com.example.sgi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;


public class login extends Activity {

    FirebaseAuth firebaseAuth;

    AppCompatButton btn_Acceder;
    EditText contraseñaET,correoET;
    TextView registrarse;
    TextInputLayout correoTV, contraseñaTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final ProgressDialog progressDialog = new ProgressDialog(this);

        btn_Acceder = findViewById(R.id.login_btn_acceder);
        correoET = findViewById(R.id.login_prompt_correo_EditText);
        contraseñaET = findViewById(R.id.login_prompt_contraseña_EditText);
        registrarse = findViewById(R.id.login_registrarse);
        correoTV = findViewById(R.id.login_prompt_correo);
        contraseñaTV = findViewById(R.id.login_prompt_contraseña);

        firebaseAuth=FirebaseAuth.getInstance();

        comprobarEstadoBoton(btn_Acceder,false);

        btn_Acceder.setOnClickListener((View) -> {
                    progressDialog.show();
                    String correo = correoET.getText().toString();
                    String contraseña = contraseñaET.getText().toString();
                    if (correo.length() == 0 || contraseña.length() == 0) {
                        Toast.makeText(login.this, "Correo o contraseña no válidos", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    firebaseAuth.signInWithEmailAndPassword(correo, contraseña).addOnCompleteListener((task) -> {
                        progressDialog.hide();
                        if (task.isSuccessful()) {
                            if(firebaseAuth.getCurrentUser().isEmailVerified()){
                                startActivity(new Intent(login.this, inicioTutoresMnt.class));
                            } else {
                                Toast.makeText(login.this, "Correo no validado", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(login.this, "Correo o contraseña no válidos", Toast.LENGTH_SHORT).show();
                        }
                    });
            });

        correoET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.toString().isEmpty()) {
                    comprobarEstadoBoton(btn_Acceder,false);
                } else {
                    comprobarEstadoBoton(btn_Acceder,true);
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty()) {
                    if(correoET.getText().toString().isEmpty() || contraseñaET.getText().toString().isEmpty()){
                        correoTV.setError(null);
                        comprobarEstadoBoton(btn_Acceder,false);
                    } else {
                        correoTV.setError(null);
                        comprobarEstadoBoton(btn_Acceder,true);
                    }
                }else {
                    correoTV.setError("");
                    comprobarEstadoBoton(btn_Acceder,false);
                }
            }
        });

        contraseñaET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.toString().isEmpty()) {
                    comprobarEstadoBoton(btn_Acceder,false);
                } else {
                    comprobarEstadoBoton(btn_Acceder,true);
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty()) {
                    if(contraseñaET.getText().toString().isEmpty() || correoET.getText().toString().isEmpty()){
                        contraseñaTV.setError(null);
                        comprobarEstadoBoton(btn_Acceder,false);
                    }
                    else {
                        contraseñaTV.setError(null);
                        comprobarEstadoBoton(btn_Acceder,true);
                    }
                }else {
                    contraseñaTV.setError("");
                    comprobarEstadoBoton(btn_Acceder,false);
                }
                if(editable.length() > 15) {
                    contraseñaTV.setError("Maximo caracteres permitidos");
                }
            }
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

    private void comprobarEstadoBoton(Button b, boolean estado) {
        b.setEnabled(estado);
        if(b.isEnabled() == false) {
            btn_Acceder.setBackgroundResource(R.drawable.btn_gris);
        } else {
            btn_Acceder.setBackgroundResource(R.drawable.btn_azul);
        }
    }
}