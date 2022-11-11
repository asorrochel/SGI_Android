package com.example.sgi.panel.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sgi.R;
import com.example.sgi.inicio.inicioProfesoresMnt;
import com.example.sgi.inicio.inicioTutoresMnt;
import com.example.sgi.utils.Ticket;
import com.example.sgi.utils.Usuario;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class panelControlTicketAbierto extends AppCompatActivity {

    // Declaración de Variables.
    Toolbar toolbar;
    Usuario u;
    TextView titulo, aula, equipo;
    AppCompatButton pc_ta_btn_cerrarTicket,pc_ta_btn_Urgentes,pc_ta_btn_EnEspera,pc_ta_btn_AsignarTecnico;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_control_ticket_abierto);

        // Inicializamos las Variables.
        Ticket element = (Ticket) getIntent().getSerializableExtra("Ticket");
        toolbar = findViewById(R.id.pc_ta_ToolBar);
        titulo = findViewById(R.id.pc_ta_titulo);
        aula = findViewById(R.id.pc_ta_aula);
        equipo = findViewById(R.id.pc_ta_equipo);
        pc_ta_btn_cerrarTicket = findViewById(R.id.pc_ta_btn_cerrarTicket);
        pc_ta_btn_Urgentes = findViewById(R.id.pc_ta_btn_Urgentes);
        pc_ta_btn_EnEspera = findViewById(R.id.pc_ta_btn_EnEspera);
        pc_ta_btn_AsignarTecnico = findViewById(R.id.pc_ta_btn_AsignarTecnico);

        //CAMBIARLO (SÓLO PRUEBAS)
        u.setRolUsuario("ROL_PROFESOR");

        // Asignamos valores a los campos
        titulo.setText(element.getTitulo());
        aula.setText(element.getAula());
        equipo.setText(element.getEquipo());

        // Método para añadir el Toolbar a la activity.
        setToolbar(toolbar);

        // Funcionalidad de los botones del menú.
        cerrarTicket();
        asignarTecnico();
        moverUrgentes();
        ponerEspera();
    }

    /**
     * Método que añade a la activity un Toolbar.
     * @param toolbar - ToolBar que queremos añadir a la activity.
     */
    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tickets");
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

    //METER FUNCIONALIDAD DE MOVER ENTRE TABLAS BD
    /**
     * Método que implementa el OnClick de cerrar Ticket.
     */
    private void cerrarTicket() {
        pc_ta_btn_cerrarTicket.setOnClickListener((View) -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Cerrar Ticket")
                    .setMessage("¿Desea cerrar el ticket?")
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        // Comprobamos el Rol que tiene el usuario para mandarle al menú de inicio que corresponde.
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (u.getRolUsuario().equalsIgnoreCase("ROL_TUT_MANT")) {
                                startActivity(new Intent(panelControlTicketAbierto.this, inicioTutoresMnt.class));
                                Toast.makeText(panelControlTicketAbierto.this, "Ticket Cerrado Correctamente", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_PROF_MANT")) {
                                startActivity(new Intent(panelControlTicketAbierto.this, inicioProfesoresMnt.class));
                                Toast.makeText(panelControlTicketAbierto.this, "Ticket Cerrado Correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }


    //METER FUNCIONALIDAD DE MOVER ENTRE TABLAS BD
    /**
     * Método que implementa el OnClick de Poner en espera el Ticket.
     */
    private void ponerEspera() {
        pc_ta_btn_EnEspera.setOnClickListener((View) -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Poner Ticket en Espera")
                    .setMessage("¿Desea poner el ticket en espera?")
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        // Comprobamos el Rol que tiene el usuario para mandarle al menú de inicio que corresponde.
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (u.getRolUsuario().equalsIgnoreCase("ROL_TUT_MANT")) {
                                startActivity(new Intent(panelControlTicketAbierto.this, inicioTutoresMnt.class));
                                Toast.makeText(panelControlTicketAbierto.this, "Ticket movido a Espera", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_PROF_MANT")) {
                                startActivity(new Intent(panelControlTicketAbierto.this, inicioProfesoresMnt.class));
                                Toast.makeText(panelControlTicketAbierto.this, "Ticket movido a Espera", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }


    //METER FUNCIONALIDAD DE MOVER ENTRE TABLAS BD
    /**
     * Método que implementa el OnClick de mover el Ticket a urgentes.
     */
    private void moverUrgentes() {
        pc_ta_btn_Urgentes.setOnClickListener((View) -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Mover a Urgentes")
                    .setMessage("¿Desea mover el Ticket a urgentes?")
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        // Comprobamos el Rol que tiene el usuario para mandarle al menú de inicio que corresponde.
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (u.getRolUsuario().equalsIgnoreCase("ROL_TUT_MANT")) {
                                startActivity(new Intent(panelControlTicketAbierto.this, inicioTutoresMnt.class));
                                Toast.makeText(panelControlTicketAbierto.this, "Ticket movido a Urgentes", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_PROF_MANT")) {
                                startActivity(new Intent(panelControlTicketAbierto.this, inicioProfesoresMnt.class));
                                Toast.makeText(panelControlTicketAbierto.this, "Ticket movido a Urgentes", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }

    //METER FUNCIONALIDAD DE MOVER ENTRE TABLAS BD
    /**
     * Método que implementa el OnClick de asignar técnico al Ticket.
     */
    private void asignarTecnico() {
        pc_ta_btn_AsignarTecnico.setOnClickListener((View) -> {
            View v = LayoutInflater.from(panelControlTicketAbierto.this).inflate(R.layout.activity_asignar_tecnico,null);
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Asignar Tecnico")
                    .setView(v)
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        // Comprobamos el Rol que tiene el usuario para mandarle al menú de inicio que corresponde.
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (u.getRolUsuario().equalsIgnoreCase("ROL_TUT_MANT")) {
                                startActivity(new Intent(panelControlTicketAbierto.this, inicioTutoresMnt.class));
                                Toast.makeText(panelControlTicketAbierto.this, "Ticket Cerrado Correctamente", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_PROF_MANT")) {
                                startActivity(new Intent(panelControlTicketAbierto.this, inicioProfesoresMnt.class));
                                Toast.makeText(panelControlTicketAbierto.this, "Ticket Cerrado Correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }
}