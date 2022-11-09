package com.example.sgi.panel.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.example.sgi.R;
import com.example.sgi.utils.Ticket;

public class panelControlTicketAbierto extends AppCompatActivity {
    Toolbar toolbar;
    TextView titulo, aula, equipo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_control_ticket_abierto);

        Ticket element = (Ticket) getIntent().getSerializableExtra("Ticket");
        toolbar = findViewById(R.id.pc_ta_ToolBar);
        titulo = findViewById(R.id.pc_ta_titulo);
        aula = findViewById(R.id.pc_ta_aula);
        equipo = findViewById(R.id.pc_ta_equipo);

        titulo.setText(element.getTitulo());
        aula.setText(element.getAula());
        equipo.setText(element.getEquipo());
        setToolbar(toolbar);
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
}