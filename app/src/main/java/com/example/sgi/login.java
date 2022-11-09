package com.example.sgi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import com.example.sgi.inicio.inicioTutoresMnt;
import com.google.android.gms.internal.firebase_auth.zzff;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.auth.zzy;
import com.google.firebase.auth.zzz;

import java.util.List;


public class login extends Activity {

    FirebaseAuth firebaseAuth;
    AppCompatButton btn_Acceder;
    EditText contraseñaET,correoET;
    TextView registrarse, contraseña_olvidada;
    TextInputLayout correoTV, contraseñaTV, recuperarContraseñaTV;
    CheckBox checkBoxLogin;
    boolean estaActivado;

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
        recuperarContraseñaTV = findViewById(R.id.alert_rp_prompt_correo);
        checkBoxLogin = findViewById(R.id.checkBoxLoginAuto);

        firebaseAuth=FirebaseAuth.getInstance();

        comprobarEstadoBoton(btn_Acceder,false);

        estaActivado = checkBoxLogin.isChecked();

        setCorreo();
        setContraseña();

        crearCuenta();
        restaurarContraseña(progressDialog);

        inicioAuto();

        botonLogin();
        btnAcceder(progressDialog);
    }

    public static void cambiarEstadoCambiarCheckbox(Context c, boolean b) {
        SharedPreferences sharedPreferences = c.getSharedPreferences("Correo", MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("BotonChecked", b).apply();
    }

    private void inicioAuto() {
        if (obtenerEstadoBoton()) {
            startActivity(new Intent(login.this, inicioTutoresMnt.class));
        }
    }

    private void botonLogin() {
        checkBoxLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (estaActivado) {
                    checkBoxLogin.setChecked(false);
                }
                estaActivado = checkBoxLogin.isChecked();
                guardarEstadoBoton();
            }
        });
    }

    public void guardarEstadoBoton() {
        SharedPreferences sharedPreferences = getSharedPreferences("Correo", MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("BotonChecked", checkBoxLogin.isChecked()).apply();
    }

    public boolean obtenerEstadoBoton() {
        SharedPreferences sharedPreferences = getSharedPreferences("Correo", MODE_PRIVATE);
        return sharedPreferences.getBoolean("BotonChecked", false);
    }

    private void comprobarEstadoBoton(Button b, boolean estado) {
        b.setEnabled(estado);
        if(b.isEnabled() == false) {
            btn_Acceder.setBackgroundResource(R.drawable.btn_gris);
        } else {
            btn_Acceder.setBackgroundResource(R.drawable.btn_azul);
        }
    }

    private void setContraseña() {
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
    }

    private void setCorreo() {
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
    }

    private void crearCuenta() {
        registrarse.setOnClickListener((View) -> {
            startActivity(new Intent(login.this,registro.class));
        });
    }

    private void restaurarContraseña(ProgressDialog progressDialog) {
        contraseña_olvidada.setOnClickListener((View) -> {
            View v = LayoutInflater.from(login.this).inflate(R.layout.activity_recordar_password, null);

            new MaterialAlertDialogBuilder(login.this, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                    .setTitle("Recuperar Contraseña")
                    .setView(v)
                    .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            progressDialog.show();

                            EditText correoRecovery = v.findViewById(R.id.alert_rp_prompt_correo_EditText);
                            String correoRecuperacion = correoRecovery.getText().toString();

                            if (correoRecuperacion.isEmpty() || !correoRecuperacion.matches("^[A-Za-z0-9]+@larioja\\.edu\\.es$")) {
                                progressDialog.hide();
                                Toast.makeText(login.this, "El correo debe pertenecer al dominio @larioja.edu.es", Toast.LENGTH_LONG).show();
                            } /*else if () {
                                progressDialog.hide();
                                Toast.makeText(login.this, "Correo no validado", Toast.LENGTH_SHORT).show();
                            }*/ else {
                                firebaseAuth.sendPasswordResetEmail(correoRecuperacion).addOnCompleteListener((task) -> {
                                    progressDialog.hide();
                                    if (task.isSuccessful()) {
                                        Toast.makeText(login.this, "Correo de recuperación enviado", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(login.this, "Cuenta no registrada", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        }
                    })
                    .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show();
        });
    }

    private void btnAcceder(ProgressDialog progressDialog) {

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
                        cambiarEstadoCambiarCheckbox(this, false);
                        Toast.makeText(login.this, "Correo no validado", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    cambiarEstadoCambiarCheckbox(this, false);
                    Toast.makeText(login.this, "Correo o contraseña no válidos", Toast.LENGTH_SHORT).show();
                }
            });
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
}