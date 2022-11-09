package com.example.sgi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class registro extends AppCompatActivity{

    FirebaseAuth firebaseAuth;

    AppCompatButton btnRegistrar;
    EditText contraseñaET,correoET, nombreET;
    TextView loginTengo;
    TextInputLayout correoTV, nombreTV, contraseñaTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        final ProgressDialog progressDialog = new ProgressDialog(this);

        //inicializamos las variables
        correoTV = findViewById(R.id.registro_prompt_correo);
        nombreTV = findViewById(R.id.registro_prompt_nombre);
        contraseñaTV = findViewById(R.id.registro_prompt_contraseña);
        btnRegistrar = findViewById(R.id.registro_btn_registrar);
        correoET = findViewById(R.id.registro_prompt_correo_EditText);
        nombreET = findViewById(R.id.registro_prompt_nombre_EditText);
        contraseñaET = findViewById(R.id.registro_prompt_contraseña_EditText);
        loginTengo = findViewById(R.id.registro_login);

        //desactivamos el boton por defecto
        comprobarEstadoBoton(btnRegistrar,false);

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
                                    Toast.makeText(registro.this, "Registro Completado, Verifique su Correo", Toast.LENGTH_LONG).show();
                                    correoET.setText("");
                                    contraseñaET.setText("");
                                } else {
                                    Toast.makeText(registro.this, "Error al realizar el registro", Toast.LENGTH_SHORT).show();
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

        loginTengo.setOnClickListener((View) -> {
            startActivity(new Intent(registro.this,login.class));
        });

        //Validar Nombre y apellidos
        nombreET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.toString().isEmpty()) {
                    comprobarEstadoBoton(btnRegistrar,false);
                } else {
                    comprobarEstadoBoton(btnRegistrar,true);
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {

            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty() && editable.toString().matches("[a-zA-ZáéíóúÁÉÍÓÚS\\s]{1,35}")) {
                    if(correoET.getText().toString().isEmpty() || contraseñaET.getText().toString().isEmpty()){
                        nombreTV.setError(null);
                        comprobarEstadoBoton(btnRegistrar,false);
                    } else {
                        nombreTV.setError(null);
                        comprobarEstadoBoton(btnRegistrar,true);
                    }
                }else {
                    nombreTV.setError("Solo Caracteres Alfanuméricos");
                    comprobarEstadoBoton(btnRegistrar,false);
                }
                if(editable.length() > 35) {
                    nombreTV.setError("Maximo caracteres");
                }
            }
        });
        //Validar Correo electronico
        correoET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.toString().isEmpty()) {
                    comprobarEstadoBoton(btnRegistrar,false);
                } else {
                    comprobarEstadoBoton(btnRegistrar,true);
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if(!editable.toString().isEmpty() && editable.toString().matches("^[A-Za-z0-9]+@larioja\\.edu\\.es$")) {
                    if(nombreET.getText().toString().isEmpty() || contraseñaET.getText().toString().isEmpty()){
                        correoTV.setError(null);
                        comprobarEstadoBoton(btnRegistrar,false);
                    } else {
                        correoTV.setError(null);
                        comprobarEstadoBoton(btnRegistrar,true);
                    }
                }else {
                    correoTV.setError("Debe pertenecer al dominio @larioja.edu.es");
                    comprobarEstadoBoton(btnRegistrar,false);
                }
            }
        });
        //Validar Contraseña
        contraseñaET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.toString().isEmpty()) {
                    comprobarEstadoBoton(btnRegistrar,false);
                } else {
                    comprobarEstadoBoton(btnRegistrar,true);
                }
            }
            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                boolean condEmail = correoET.getText().toString().matches("^[A-Za-z0-9]+@larioja\\.edu\\.es$"),
                        condContraseña = contraseñaET.getText().toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$");

                if(!editable.toString().isEmpty() && editable.toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$")) {
                    if(nombreET.getText().toString().isEmpty() || correoET.getText().toString().isEmpty()){
                        contraseñaTV.setError(null);
                        comprobarEstadoBoton(btnRegistrar,false);
                    }
                    else if (!condEmail && condContraseña){
                        correoTV.setError("Debe pertenecer al dominio @larioja.edu.es");
                        contraseñaTV.setError(null);
                        comprobarEstadoBoton(btnRegistrar,false);
                    } else {
                        contraseñaTV.setError(null);
                        comprobarEstadoBoton(btnRegistrar,true);
                    }
                }else {
                    contraseñaTV.setError("Min 8 Max 15 | 1 Mayuscula | 1 Minuscula | 1 Numero | 1 Caracter especial @#$%^&+=");
                    comprobarEstadoBoton(btnRegistrar,false);
                }
                if(editable.length() > 15) {
                    contraseñaTV.setError("Maximo caracteres permitidos");
                }
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        View view = this.getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            return true;
        } else {
            return false;
        }
    }

    private void comprobarEstadoBoton(Button b,boolean estado) {
        b.setEnabled(estado);
        if(b.isEnabled() == false) {
            btnRegistrar.setBackgroundResource(R.drawable.btn_gris);
        } else {
            btnRegistrar.setBackgroundResource(R.drawable.btn_azul);
        }
    }
}
