package com.example.sgi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;


public class login extends Activity {

    FirebaseAuth firebaseAuth;
    boolean btnEnabled;
    AppCompatButton btn_Acceder;
    EditText contraseñaET,correoET;
    TextView registrarse, contraseña_olvidada;
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
        contraseña_olvidada = findViewById(R.id.login_contraseña_olvidada);

        firebaseAuth=FirebaseAuth.getInstance();
        btnEnabled = false;

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

        registrarse.setOnClickListener((View) -> {
                startActivity(new Intent(login.this,registro.class));
        });

        contraseña_olvidada.setOnClickListener((View) -> {
            View v = LayoutInflater.from(login.this).inflate(R.layout.activity_recordar_password,null);
            EditText correoRecovery = (EditText) v.findViewById(R.id.alert_rp_prompt_correo_EditText);

            new MaterialAlertDialogBuilder(login.this,R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                    .setTitle("Recuperar Contraseña")
                    .setView(v)
                    .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                        String correoRecuperacion = correoRecovery.getText().toString();

                        public boolean pulsarBoton() {
                            return correoRecuperacion.matches("^[A-Za-z0-9]+@larioja\\.edu\\.es$");
                        }

                        public boolean emailVerificado() {
                            return firebaseAuth.getCurrentUser().isEmailVerified();
                        }

                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            progressDialog.show();

                            if (pulsarBoton()) {
                                btnEnabled = true;
                                if (emailVerificado()) {
                                    firebaseAuth.sendPasswordResetEmail(correoRecuperacion).addOnCompleteListener((task) -> {
                                        progressDialog.hide();
                                        if (task.isSuccessful()) {
                                            Toast.makeText(login.this, "Correo de recuperación enviado", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(login.this, "Cuenta no registrada", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                } else {
                                    Toast.makeText(login.this, "Correo no verificado", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(login.this, "Error al enviar petición, no pertenecer al dominio @larioja.edu.es", Toast.LENGTH_LONG).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show().getButton(DialogInterface.BUTTON_POSITIVE).setEnabled(btnEnabled);
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

    private void comprobarEstadoBoton(Button b, boolean estado) {
        b.setEnabled(estado);
        if(b.isEnabled() == false) {
            btn_Acceder.setBackgroundResource(R.drawable.btn_gris);
        } else {
            btn_Acceder.setBackgroundResource(R.drawable.btn_azul);
        }
    }
}