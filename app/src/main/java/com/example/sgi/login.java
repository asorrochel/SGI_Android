package com.example.sgi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import androidx.appcompat.widget.AppCompatButton;
import com.example.sgi.inicio.inicioTutoresMnt;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;

public class login extends Activity {

    // Declaración de Variables.
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

        // Inicializamos las Variables.
        btn_Acceder = findViewById(R.id.login_btn_acceder);
        correoET = findViewById(R.id.login_prompt_correo_EditText);
        contraseñaET = findViewById(R.id.login_prompt_contraseña_EditText);
        registrarse = findViewById(R.id.login_registrarse);
        correoTV = findViewById(R.id.login_prompt_correo);
        contraseñaTV = findViewById(R.id.login_prompt_contraseña);
        contraseña_olvidada = findViewById(R.id.login_contraseña_olvidada);
        recuperarContraseñaTV = findViewById(R.id.alert_rp_prompt_correo);
        checkBoxLogin = findViewById(R.id.checkBoxLoginAuto);
        firebaseAuth = FirebaseAuth.getInstance();
        estaActivado = checkBoxLogin.isChecked();

        // Funcionalidad de Inicio Automático y checkbox
        inicioAuto();
        chbxLogin();

        // Métodos para meterles valor a los campos Correo y Contraseña.
        comprobarCorreo();
        comprobarContraseña();

        // Funcionalidad de los botones y TextView.
        crearCuenta();
        restaurarContraseña(progressDialog);
        clickBtnAcceder(progressDialog);
    }

    /**
     * Método que comprueba que el correo introducido por el usuario cumple con las restricciones impuestas.
     */
    private void comprobarCorreo() {
        correoET.addTextChangedListener(new TextWatcher() {
            // Este método comprueba el EditText antes de que cambie su valor.
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                // - Si el campo está vacío, desactiva el botón de registro y, si no lo está, activa el botón.
                if(s.toString().isEmpty()) {
                    cambiarEstadoBoton(btn_Acceder,false);
                } else {
                    cambiarEstadoBoton(btn_Acceder,true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            // Este método comprueba el EditText después de que cambie su valor.
            @Override
            public void afterTextChanged(Editable editable) {
                // - Si el campo no está vacío, comprobamos el estado de los otros campos, si no cumple las condiciones, mostramos el error y desactivamos el botón.
                if(!editable.toString().isEmpty()) {
                    if(correoET.getText().toString().isEmpty() || contraseñaET.getText().toString().isEmpty()){
                        correoTV.setError(null);
                        cambiarEstadoBoton(btn_Acceder,false);
                    } else {
                        correoTV.setError(null);
                        cambiarEstadoBoton(btn_Acceder,true);
                    }
                }else {
                    correoTV.setError("");
                    cambiarEstadoBoton(btn_Acceder,false);
                }
            }
        });
    }

    /**
     * Método que comprueba que la contraseña introducida por el usuario cumple con las restricciones impuestas.
     */
    private void comprobarContraseña() {
        contraseñaET.addTextChangedListener(new TextWatcher() {
            // Este método comprueba el EditText antes de que cambie su valor.
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                if(s.toString().isEmpty()) {
                    cambiarEstadoBoton(btn_Acceder,false);
                } else {
                    cambiarEstadoBoton(btn_Acceder,true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            // Este método comprueba el EditText después de que cambie su valor.
            @Override
            public void afterTextChanged(Editable editable) {
                // - Si el campo no está vacío, comprobamos el estado de los otros campos, si no cumple las condiciones, mostramos el error y desactivamos el botón.
                if(!editable.toString().isEmpty()) {
                    if(contraseñaET.getText().toString().isEmpty() || correoET.getText().toString().isEmpty()){
                        contraseñaTV.setError(null);
                        cambiarEstadoBoton(btn_Acceder,false);
                    }
                    else {
                        contraseñaTV.setError(null);
                        cambiarEstadoBoton(btn_Acceder,true);
                    }
                }else {
                    contraseñaTV.setError("");
                    cambiarEstadoBoton(btn_Acceder,false);
                }
                if(editable.length() > 15) {
                    contraseñaTV.setError("Maximo caracteres permitidos");
                }
            }
        });
    }

    /**
     * Método que nos envía a la pantalla de inicio si está marcado el checkbox.
     */
    private void inicioAuto() {
        if (obtenerEstadoCheckBox()) {
            startActivity(new Intent(login.this, inicioTutoresMnt.class));
        }
    }

    /**
     * Método que le da la funcionalidad al CheckBox y guarda su valor.
     */
    private void chbxLogin() {
        checkBoxLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (estaActivado) {
                    checkBoxLogin.setChecked(false);
                }
                estaActivado = checkBoxLogin.isChecked();
                guardarEstadoCheckBox();
            }
        });
    }

    /**
     * Método que cambia el estado del checkbox de inicio de sesión automático.
     * @param c - Interfaz a la información global sobre un entorno de aplicación.
     * @param b - Boolean que recibe el valor del checkbox.
     */
    public static void cambiarEstadoCheckbox(Context c, boolean b) {
        SharedPreferences sharedPreferences = c.getSharedPreferences("Correo", MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("BotonChecked", b).apply();
    }

    /**
     * Método que guarda el valor del Checkbox.
     */
    public void guardarEstadoCheckBox() {
        SharedPreferences sharedPreferences = getSharedPreferences("Correo", MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("BotonChecked", checkBoxLogin.isChecked()).apply();
    }

    /**
     * Método que obtiene el valor del Checkbox.
     */
    public boolean obtenerEstadoCheckBox() {
        SharedPreferences sharedPreferences = getSharedPreferences("Correo", MODE_PRIVATE);
        return sharedPreferences.getBoolean("BotonChecked", false);
    }

    /**
     * Método que comprueba el estado del botón de registrarse.
     * @param b - Botón al que queremos.
     * @param estado - Estado en el que queremos poner el botón (True o False).
     */
    private void cambiarEstadoBoton(Button b, boolean estado) {
        b.setEnabled(estado);
        if(b.isEnabled() == false) {
            btn_Acceder.setBackgroundResource(R.drawable.btn_gris);
        } else {
            btn_Acceder.setBackgroundResource(R.drawable.btn_azul);
        }
    }

    /**
     * Método que al pulsar el TextView ("Crear Cuenta") inicia la actividad de registro.
     */
    private void crearCuenta() {
        registrarse.setOnClickListener((View) -> {
            startActivity(new Intent(login.this,registro.class));
        });
    }

    /**
     * Método que le da la funcionalidad OnClick de restaurar contraseña al TextView ("He olvidado la contraseña").
     * @param progressDialog - Muestra un indicador de progreso o de carga.
     */
    private void restaurarContraseña(ProgressDialog progressDialog) {
        contraseña_olvidada.setOnClickListener((View) -> {
            // Mostramos co Inflate la actvidad de Recordar Password.
            View v = LayoutInflater.from(login.this).inflate(R.layout.activity_recordar_password, null);

            // Creamos un AlertDialog, con título, los botones de enviar y cancelar y la vista de la activity recordar password.
            new MaterialAlertDialogBuilder(login.this, R.style.MyThemeOverlay_MaterialComponents_MaterialAlertDialog)
                    .setTitle("Recuperar Contraseña")
                    .setView(v)
                    .setPositiveButton("Enviar", new DialogInterface.OnClickListener() {
                        // Le añadimos la funcionalidad OnClick al botón de enviar.
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            progressDialog.show();

                            EditText correoRecovery = v.findViewById(R.id.alert_rp_prompt_correo_EditText);
                            String correoRecuperacion = correoRecovery.getText().toString();

                            // Si cumple alguna de las condicione, cierra el Alert muestra un mensaje de error, si no las cumple, envía el correo de recuperación.
                            if (correoRecuperacion.isEmpty() || !correoRecuperacion.matches("^[A-Za-z0-9]+@larioja\\.edu\\.es$")) {
                                progressDialog.hide();
                                Toast.makeText(login.this, "El correo debe pertenecer al dominio @larioja.edu.es", Toast.LENGTH_LONG).show();
                            } /*else if () {
                                progressDialog.hide();
                                Toast.makeText(login.this, "Correo no validado", Toast.LENGTH_SHORT).show();
                            }*/ else {
                                // Enviamos el correo de recuperación a través de firebase.
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
                        // Le añadimos la funcionalidad OnClick al botón de cancelar, que nos cerrará el Alert.
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show();
        });
    }

    /**
     * Método que le da la funcionalidad OnClick al botón de login.
     * @param progressDialog - Muestra un indicador de progreso o de carga.
     */
    private void clickBtnAcceder(ProgressDialog progressDialog) {
        btn_Acceder.setOnClickListener((View) -> {
            progressDialog.show();
            String correo = correoET.getText().toString();
            String contraseña = contraseñaET.getText().toString();
            // Si lgunos de los campos está acío nos salta un Toast mensaje de error.
            if (correo.length() == 0 || contraseña.length() == 0) {
                Toast.makeText(login.this, "Correo o contraseña no válidos", Toast.LENGTH_SHORT).show();
                return;
            }
            // Iniciamos sesión con correo y contraseña a través de firebase.
            firebaseAuth.signInWithEmailAndPassword(correo, contraseña).addOnCompleteListener((task) -> {
                progressDialog.hide();
                if (task.isSuccessful()) {
                    // Comprobamos que el usuario ha verificado su correo electrónico.
                    if(firebaseAuth.getCurrentUser().isEmailVerified()){
                        startActivity(new Intent(login.this, inicioTutoresMnt.class));
                    } else {
                        cambiarEstadoCheckbox(this, false);
                        Toast.makeText(login.this, "Correo no validado", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    cambiarEstadoCheckbox(this, false);
                    Toast.makeText(login.this, "Correo o contraseña no válidos", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    /**
     * Método usado para cerrar el teclado al pulsar sobre otro lado de la pantalla.
     * @param event - Objeto utilizado para informar eventos de movimiento.
     * @return - True, si la vista es distinta de null, False si la View es null.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Guardamos la vista seleccionada.
        View view = this.getCurrentFocus();

        // Si no es null (Tenemos una vista seleccionada), cerramos el teclado.
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            return true;
        } else {
            return false;
        }
    }
}