package com.example.sgi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.google.android.material.textfield.TextInputLayout;

public class crearTicket extends AppCompatActivity {

    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ticket);

        textInputLayout = findViewById(R.id.crear_ticket_aula_dropdown);
        autoCompleteTextView = findViewById(R.id.autocomplete_aula);

        toolbar = findViewById(R.id.mainToolBar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        String [] aulas = new String[]{"C01 Diurno","C01 Vespertino","C02 Diurno","C02 Vespertino","C03 Diurno","C03 Vespertino","C04 Diurno","C04 Vespertino","C05 Diurno","C05 Vespertino","C06 Diurno","C06 Vespertino","C07 Diurno","C07 Vespertino","C08 Diurno","C08 Vespertino","C09 Diurno","C09 Vespertino","C10 Diurno","C10 Vespertino","C11 Diurno","C11 Vespertino","C12 Diurno","C12 Vespertino","C13 Diurno","C13 Vespertino","Taller"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                crearTicket.this,R.layout.dropdowm_item,aulas);

        autoCompleteTextView.setAdapter(adapter);
    }
}
