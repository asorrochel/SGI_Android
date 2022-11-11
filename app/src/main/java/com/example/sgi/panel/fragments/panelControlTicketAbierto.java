package com.example.sgi.panel.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sgi.R;
import com.example.sgi.inicio.inicioProfesorAlumno;
import com.example.sgi.inicio.inicioProfesoresMnt;
import com.example.sgi.inicio.inicioTutores;
import com.example.sgi.inicio.inicioTutoresMnt;
import com.example.sgi.utils.Ticket;
import com.example.sgi.utils.Usuario;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class panelControlTicketAbierto extends AppCompatActivity {
    Toolbar toolbar;
    Usuario u;
    TextView titulo, aula, equipo;

    AppCompatButton pc_ta_btn_cerrarTicket,pc_ta_btn_Urgentes,pc_ta_btn_EnEspera,pc_ta_btn_AsignarTecnico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_control_ticket_abierto);

        Ticket element = (Ticket) getIntent().getSerializableExtra("Ticket");
        toolbar = findViewById(R.id.pc_ta_ToolBar);
        titulo = findViewById(R.id.pc_ta_titulo);
        aula = findViewById(R.id.pc_ta_aula);
        equipo = findViewById(R.id.pc_ta_equipo);

        u = new Usuario();

        //u.setRolUsuario("ROL_PROFESOR");

        pc_ta_btn_cerrarTicket = findViewById(R.id.pc_ta_btn_cerrarTicket);
        pc_ta_btn_Urgentes = findViewById(R.id.pc_ta_btn_Urgentes);
        pc_ta_btn_EnEspera = findViewById(R.id.pc_ta_btn_EnEspera);
        pc_ta_btn_AsignarTecnico = findViewById(R.id.pc_ta_btn_AsignarTecnico);

        titulo.setText(element.getTitulo());
        aula.setText(element.getAula());
        equipo.setText(element.getEquipo());
        setToolbar(toolbar);

        cerrarTicket();
        asignarTecnico();
        moverUrgentes();
        ponerEspera();
    }
    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tickets");
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    private void cerrarTicket() {
        pc_ta_btn_cerrarTicket.setOnClickListener((View) -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Cerrar Ticket")
                    .setMessage("¿Desea cerrar el ticket?")
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (u.getRolUsuario().equalsIgnoreCase("ROL_TUTOR")) {
                                startActivity(new Intent(panelControlTicketAbierto.this, inicioTutores.class));
                                Toast.makeText(panelControlTicketAbierto.this, "Ticket Cerrado Correctamente", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_PROFESOR") || u.getRolUsuario().equalsIgnoreCase("ROL_ALUMNO")) {
                                startActivity(new Intent(panelControlTicketAbierto.this, inicioProfesorAlumno.class));
                                Toast.makeText(panelControlTicketAbierto.this, "Ticket Cerrado Correctamente", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_TUT_MANT")) {
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
    private void ponerEspera() {
        pc_ta_btn_EnEspera.setOnClickListener((View) -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Poner Ticket en Espera")
                    .setMessage("¿Desea poner el ticket en espera?")
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (u.getRolUsuario().equalsIgnoreCase("ROL_TUTOR")) {
                                startActivity(new Intent(panelControlTicketAbierto.this, inicioTutores.class));
                                Toast.makeText(panelControlTicketAbierto.this, "Ticket movido a Espera", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_PROFESOR") || u.getRolUsuario().equalsIgnoreCase("ROL_ALUMNO")) {
                                startActivity(new Intent(panelControlTicketAbierto.this, inicioProfesorAlumno.class));
                                Toast.makeText(panelControlTicketAbierto.this, "Ticket movido a Espera", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_TUT_MANT")) {
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
    private void moverUrgentes() {
        pc_ta_btn_Urgentes.setOnClickListener((View) -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Mover a Urgentes")
                    .setMessage("¿Desea mover el Ticket a urgentes?")
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (u.getRolUsuario().equalsIgnoreCase("ROL_TUTOR")) {
                                startActivity(new Intent(panelControlTicketAbierto.this, inicioTutores.class));
                                Toast.makeText(panelControlTicketAbierto.this, "Ticket movido a Urgentes", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_PROFESOR") || u.getRolUsuario().equalsIgnoreCase("ROL_ALUMNO")) {
                                startActivity(new Intent(panelControlTicketAbierto.this, inicioProfesorAlumno.class));
                                Toast.makeText(panelControlTicketAbierto.this, "Ticket movido a Urgentes", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_TUT_MANT")) {
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

    private void asignarTecnico() {
        pc_ta_btn_AsignarTecnico.setOnClickListener((View) -> {
            startActivity(new Intent(this, asignarTecnico.class));
        });

    }


}