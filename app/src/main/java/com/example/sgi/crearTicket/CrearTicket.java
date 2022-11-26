package com.example.sgi.crearTicket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import com.example.sgi.R;
import com.example.sgi.inicio.InicioProfesorAlumno;
import com.example.sgi.utils.Usuario;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

public class CrearTicket extends AppCompatActivity {

    // Declaración de Variables.
    Toolbar toolbar;
    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;
    Button botonEnviar;
    Usuario u = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ticket);

        // Inicializamos las Variables.
        toolbar = findViewById(R.id.mainToolBar);
        textInputLayout = findViewById(R.id.crear_ticket_aula_dropdown);
        autoCompleteTextView = findViewById(R.id.autocomplete_aula);
        String [] aulas = new String[]{"C01 Diurno","C01 Vespertino","C02 Diurno","C02 Vespertino","C03 Diurno","C03 Vespertino","C04 Diurno","C04 Vespertino","C05 Diurno","C05 Vespertino","C06 Diurno","C06 Vespertino","C07 Diurno","C07 Vespertino","C08 Diurno","C08 Vespertino","C09 Diurno","C09 Vespertino","C10 Diurno","C10 Vespertino","C11 Diurno","C11 Vespertino","C12 Diurno","C12 Vespertino","C13 Diurno","C13 Vespertino","Taller"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CrearTicket.this,R.layout.dropdowm_item,aulas);
        autoCompleteTextView.setAdapter(adapter);

        // Método para añadir el Toolbar a la activity.
        setToolbar(toolbar);

        //CAMBIARLO (SÓLO PRUEBAS)
        u.setRolUsuario("ROL_PROFESOR");

        // Funcionalidad de los botones del menú.
        clickBotonEnviar();
    }

    /**
     * Método que le añade la función OnClick al botón de envío.
     */
    private void clickBotonEnviar() {
        botonEnviar.setOnClickListener((View) -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Crear Ticket")
                    .setMessage("¿Desea crear el Ticket?")
                    .setPositiveButton("Crear", new DialogInterface.OnClickListener() {
                        // Dependiendo del ROL que tenga el usuario nos mandará al Activity que corresponda.
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            startActivity(new Intent(CrearTicket.this, InicioProfesorAlumno.class));
                            Toast.makeText(CrearTicket.this, "Ticket creado correctamente", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }

    /**
     * Método que añade a la activity un Toolbar.
     * @param toolbar - ToolBar que queremos añadir a la activity.
     */
    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Crear Ticket");
        // Añadimos la flecha de retroceso.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Método que le da funcionalidad a la flecha de retroceso del Toolbar.
     * @return - True.
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
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
