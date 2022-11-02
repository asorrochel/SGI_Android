package com.example.sgi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.widget.AppCompatButton;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;


public class login extends Activity {

    FirebaseAuth firebaseAuth;

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

        contraseña_olvidada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(login.this,R.style.MyAlertDialogTheme);
                builder.setTitle("Resetear Contraseña");
                final EditText et = new EditText(login.this);
                et.setHint("Correo electrónico");
                builder.setView(et);
                builder.setPositiveButton("Resetear", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
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