package com.example.sgi.panel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import com.example.sgi.R;
import com.example.sgi.panel.fragments.Pc_fragment_espera;
import com.example.sgi.panel.fragments.Pc_fragment_nuevos;
import com.example.sgi.panel.fragments.Pc_fragment_urgentes;
import com.example.sgi.panel.fragments.Pc_frament_proceso;

public class PanelControl extends AppCompatActivity {

    // Declaración de Variables.
    Toolbar toolbar;
    AppCompatButton pc_btn_nuevos, pc_btn_espera, pc_btn_proceso, pc_btn_urgentes ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_control);

        // Inicializamos las Variables.
        toolbar = findViewById(R.id.mainToolBar);
        pc_btn_nuevos = findViewById(R.id.pc_btn_nuevos);
        pc_btn_espera = findViewById(R.id.pc_btn_espera);
        pc_btn_proceso = findViewById(R.id.pc_btn_proceso);
        pc_btn_urgentes = findViewById(R.id.pc_btn_urgentes);

        // Método para añadir el Toolbar a la activity.
        setToolbar(toolbar);

        // Funcionalidad de los botones.
        clickNuevos();
        clickEspera();
        clickEnProceso();
        clickUrgentes();
    }

    /**
     * Método que cambia el Fragment a Nuevos al pulsar sobre él.
     */
    private void clickNuevos() {
        pc_btn_nuevos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ponemos los colores que correspondan al botón.
                pc_btn_nuevos.setBackgroundResource(R.drawable.btn_verde);
                pc_btn_espera.setBackgroundResource(R.drawable.btn_azul_oscuro_borde);
                pc_btn_proceso.setBackgroundResource(R.drawable.btn_amarillo_borde);
                pc_btn_urgentes.setBackgroundResource(R.drawable.btn_rojo_borde);

                // Llamamos al método para reemplazar el fragment.
                replaceFragment(new Pc_fragment_nuevos());
            }
        });
    }

    /**
     * Método que cambia el Fragment a Espera al pulsar sobre él.
     */
    private void clickEspera() {
        pc_btn_espera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ponemos los colores que correspondan al botón.
                pc_btn_espera.setBackgroundResource(R.drawable.btn_azul_oscuro);
                pc_btn_nuevos.setBackgroundResource(R.drawable.btn_verde_borde);
                pc_btn_proceso.setBackgroundResource(R.drawable.btn_amarillo_borde);
                pc_btn_urgentes.setBackgroundResource(R.drawable.btn_rojo_borde);

                // Llamamos al método para reemplazar el fragment.
                replaceFragment(new Pc_fragment_espera());
            }
        });
    }

    /**
     * Método que cambia el Fragment a En Proceso al pulsar sobre él.
     */
    private void clickEnProceso() {
        pc_btn_proceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ponemos los colores que correspondan al botón.
                pc_btn_proceso.setBackgroundResource(R.drawable.btn_amarillo);
                pc_btn_nuevos.setBackgroundResource(R.drawable.btn_verde_borde);
                pc_btn_urgentes.setBackgroundResource(R.drawable.btn_rojo_borde);
                pc_btn_espera.setBackgroundResource(R.drawable.btn_azul_oscuro_borde);

                // Llamamos al método para reemplazar el fragment.
                replaceFragment(new Pc_frament_proceso());
            }
        });
    }

    /**
     * Método que cambia el Fragment a Urgentes al pulsar sobre él.
     */
    private void clickUrgentes() {
        pc_btn_urgentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ponemos los colores que correspondan al botón.
                pc_btn_urgentes.setBackgroundResource(R.drawable.btn_rojo);
                pc_btn_nuevos.setBackgroundResource(R.drawable.btn_verde_borde);
                pc_btn_proceso.setBackgroundResource(R.drawable.btn_amarillo_borde);
                pc_btn_espera.setBackgroundResource(R.drawable.btn_azul_oscuro_borde);

                // Llamamos al método para reemplazar el fragment.
                replaceFragment(new Pc_fragment_urgentes());
            }
        });
    }

    /**
     * Método que reemplaza el frgament por otro
     * @param fragment - Fragment que queremos que reemplaze al actual.
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.panel_control_frame, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Método que añade a la activity un Toolbar.
     * @param toolbar - ToolBar que queremos añadir a la activity.
     */
    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Panel de Control");
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
}