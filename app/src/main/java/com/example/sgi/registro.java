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

    // Declaración de Variables.
    FirebaseAuth firebaseAuth;
    AppCompatButton btnRegistrar;
    EditText contraseñaET,correoET, nombreET, apellidoET;
    TextView loginTengo;
    TextInputLayout correoTV, nombreTV, contraseñaTV, apellidoTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        final ProgressDialog progressDialog = new ProgressDialog(this);

        // Inicializamos las Variables.
        correoTV = findViewById(R.id.registro_prompt_correo);
        nombreTV = findViewById(R.id.registro_prompt_nombre);
        contraseñaTV = findViewById(R.id.registro_prompt_contraseña);
        btnRegistrar = findViewById(R.id.registro_btn_registrar);
        correoET = findViewById(R.id.registro_prompt_correo_EditText);
        nombreET = findViewById(R.id.registro_prompt_nombre_EditText);
        apellidoET = findViewById(R.id.registro_prompt_apellido_EditText);
        apellidoTV = findViewById(R.id.registro_prompt_apellido);
        contraseñaET = findViewById(R.id.registro_prompt_contraseña_EditText);
        loginTengo = findViewById(R.id.registro_login);
        firebaseAuth=FirebaseAuth.getInstance();

        // Funcionalidad de los Botones y TextView.
        clickBtnRegistro(progressDialog);
        clcikTengoSesion();

        // Métodos para la validación de los datos introducidos por el usuario.
        validarCampos();
    }

    private void validarCampos() {
        validarNombre();
        validarApellido();
        validarCorreo();
        validarContraseña();
    }

    /**
     * Método que comprueba que el nombre introducido por el usuario cumple con las restricciones impuestas.
     */
    private void validarNombre() {
        nombreET.addTextChangedListener(new TextWatcher() {
            // Este método comprueba el EditText antes de que cambie su valor.
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                // - Si el campo está vacío, desactiva el botón de registro y, si no lo está, activa el botón.
                if(s.toString().isEmpty()) {
                    cambiarEstadoBoton(btnRegistrar,false);
                } else {
                    cambiarEstadoBoton(btnRegistrar,true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            // Este método comprueba el EditText después de que cambie su valor.
            @Override
            public void afterTextChanged(Editable editable) {
                /*
                * - Si el campo no está vacío y cumple con la expersión regular (Sólo caracteres alfanuméricos), comprobamos el estado de los otros campos,
                * si no cumple la condición, mostramos el error y desactivamos el botón.
                */
                if(!editable.toString().isEmpty() && editable.toString().matches("[a-zA-ZáéíóúÁÉÍÓÚS\\s]{1,35}")) {
                    if(correoET.getText().toString().isEmpty() || contraseñaET.getText().toString().isEmpty()){
                        nombreTV.setError(null);
                        cambiarEstadoBoton(btnRegistrar,false);
                    } else {
                        nombreTV.setError(null);
                        cambiarEstadoBoton(btnRegistrar,true);
                    }
                }else {
                    nombreTV.setError("Solo Caracteres Alfanuméricos");
                    cambiarEstadoBoton(btnRegistrar,false);
                }
                // - Si el campo excede el número máximo de caracteres, mostramos el error al usuario.
                if(editable.length() > 35) {
                    nombreTV.setError("Maximo caracteres");
                }
            }
        });
    }

    /**
     * Método que comprueba que el apellido introducido por el usuario cumple con las restricciones impuestas.
     */
    private void validarApellido() {
        apellidoET.addTextChangedListener(new TextWatcher() {
            // Este método comprueba el EditText antes de que cambie su valor.
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                // - Si el campo está vacío, desactiva el botón de registro y, si no lo está, activa el botón.
                if(s.toString().isEmpty()) {
                    cambiarEstadoBoton(btnRegistrar,false);
                } else {
                    cambiarEstadoBoton(btnRegistrar,true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            // Este método comprueba el EditText después de que cambie su valor.
            @Override
            public void afterTextChanged(Editable editable) {
                /*
                 * - Si el campo no está vacío y cumple con la expersión regular (Sólo caracteres alfanuméricos), comprobamos el estado de los otros campos,
                 * si no cumple la condición, mostramos el error y desactivamos el botón.
                 */
                if(!editable.toString().isEmpty() && editable.toString().matches("[a-zA-ZáéíóúÁÉÍÓÚS\\s]{1,35}")) {
                    if(apellidoET.getText().toString().isEmpty() || contraseñaET.getText().toString().isEmpty()){
                        apellidoTV.setError(null);
                        cambiarEstadoBoton(btnRegistrar,false);
                    } else {
                        apellidoTV.setError(null);
                        cambiarEstadoBoton(btnRegistrar,true);
                    }
                }else {
                    apellidoTV.setError("Solo Caracteres Alfanuméricos");
                    cambiarEstadoBoton(btnRegistrar,false);
                }
                // - Si el campo excede el número máximo de caracteres, mostramos el error al usuario.
                if(editable.length() > 35) {
                    apellidoTV.setError("Maximo caracteres");
                }
            }
        });
    }

    /**
     * Método que comprueba que el correo introducido cumple con las restricciones marcadas.
     */
    private void validarCorreo() {
        correoET.addTextChangedListener(new TextWatcher() {
            // Este método comprueba el EditText antes de que cambie su valor.
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                // - Si el campo está vacío, desactiva el botón de registro y, si no lo está, activa el botón.
                if(s.toString().isEmpty()) {
                    cambiarEstadoBoton(btnRegistrar,false);
                } else {
                    cambiarEstadoBoton(btnRegistrar,true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            // Este método comprueba el EditText después de que cambie su valor.
            @Override
            public void afterTextChanged(Editable editable) {
                /*
                 * - Si el campo no está vacío y cumple con la expersión regular (Dominio @larioja.edu.es), comprobamos el estado de los otros campos,
                 * si no cumple la condición, mostramos el error y desactivamos el botón.
                 */
                if(!editable.toString().isEmpty() && editable.toString().matches("^[A-Za-z0-9]+@larioja\\.edu\\.es$")) {
                    if(nombreET.getText().toString().isEmpty() || contraseñaET.getText().toString().isEmpty()){
                        correoTV.setError(null);
                        cambiarEstadoBoton(btnRegistrar,false);
                    } else {
                        correoTV.setError(null);
                        cambiarEstadoBoton(btnRegistrar,true);
                    }
                }else {
                    correoTV.setError("Debe pertenecer al dominio @larioja.edu.es");
                    cambiarEstadoBoton(btnRegistrar,false);
                }
            }
        });
    }

    /**
     * Método que comprueba que la contraseña introducida cumple con las restricciones marcadas.
     */
    private void validarContraseña() {
        contraseñaET.addTextChangedListener(new TextWatcher() {
            // Declaramos unas variables para comprobación de restricciones.
            boolean condEmail = correoET.getText().toString().matches("^[A-Za-z0-9]+@larioja\\.edu\\.es$");
            boolean condContraseña = contraseñaET.getText().toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$");

            // Este método comprueba el EditText antes de que cambie su valor.
            @Override
            public void beforeTextChanged(CharSequence s, int i, int i1, int i2) {
                // - Si el campo está vacío, desactiva el botón de registro y, si no lo está, activa el botón.
                if(s.toString().isEmpty()) {
                    cambiarEstadoBoton(btnRegistrar,false);
                } else {
                    cambiarEstadoBoton(btnRegistrar,true);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
            }

            // Este método comprueba el EditText después de que cambie su valor.
            @Override
            public void afterTextChanged(Editable editable) {
                /*
                 * - Si el campo no está vacío y cumple con la expersión regular, comprobamos el estado de los otros campos,
                 * si no cumple la condición, mostramos el error y desactivamos el botón.
                 */
                if(!editable.toString().isEmpty() && editable.toString().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$")) {
                    if(nombreET.getText().toString().isEmpty() || correoET.getText().toString().isEmpty()){
                        contraseñaTV.setError(null);
                        cambiarEstadoBoton(btnRegistrar,false);
                    }
                    else if (!condEmail && condContraseña){
                        correoTV.setError("Debe pertenecer al dominio @larioja.edu.es");
                        contraseñaTV.setError(null);
                        cambiarEstadoBoton(btnRegistrar,false);
                    } else {
                        contraseñaTV.setError(null);
                        cambiarEstadoBoton(btnRegistrar,true);
                    }
                }else {
                    contraseñaTV.setError("Min 8 Max 15 | 1 Mayuscula | 1 Minuscula | 1 Numero | 1 Caracter especial @#$%^&+=");
                    cambiarEstadoBoton(btnRegistrar,false);
                }
                // - Si el campo excede el número máximo de caracteres, mostramos el error al usuario.
                if(editable.length() > 15) {
                    contraseñaTV.setError("Maximo caracteres permitidos");
                }
            }
        });
    }

    /**
     * Método que al pulsar el TextView ("¿Ya tienes una cuenta? Iniciar Sesión") inicia la actividad del login.
     */
    private void clcikTengoSesion() {
        loginTengo.setOnClickListener((View) -> {
            startActivity(new Intent(registro.this,login.class));
        });
    }

    /**
     * Método que le da la funcionalidad OnClick al botón de registrar.
     * @param progressDialog - Muestra un indicador de progreso o de carga.
     */
    private void clickBtnRegistro(ProgressDialog progressDialog) {
        btnRegistrar.setOnClickListener((View) -> {
            progressDialog.show();
            String correo = correoET.getText().toString();
            String contraseña = contraseñaET.getText().toString();

            // Al pulsar el botón de registro llamamos a firebase para que nos cree el usuario con el correo y la contraseña.
            firebaseAuth.createUserWithEmailAndPassword(correo, contraseña).addOnCompleteListener((task) -> {
                progressDialog.hide();
                if (task.isSuccessful()) {
                    // Si no hay errores, mandará un correo al usuario para que verifique su correo.
                    firebaseAuth.getCurrentUser().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        // Una vez creado el usuario sin errores nos muestra un Toast indicándole al usuario que debe verificar el correo.
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
                    // Le mandamos a la pantalla de Login.
                    startActivity(new Intent(registro.this, login.class));
                } else {
                    Toast.makeText(registro.this, "Error al realizar el registro", Toast.LENGTH_SHORT).show();
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
        // Guardamos la vista seleccionada
        View view = this.getCurrentFocus();

        // Si no es null (Tenemos una vista seleccionada), cerramos el teclado
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Método que comprueba el estado del botón de registrarse.
     * @param b - Botón al que queremos
     * @param estado - Estado en el que queremos poner el botón (True o False).
     */
    private void cambiarEstadoBoton(Button b,boolean estado) {
        b.setEnabled(estado);
        if(b.isEnabled() == false) {
            btnRegistrar.setBackgroundResource(R.drawable.btn_gris);
        } else {
            btnRegistrar.setBackgroundResource(R.drawable.btn_azul);
        }
    }
}