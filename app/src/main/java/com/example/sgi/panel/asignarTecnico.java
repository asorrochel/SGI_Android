package com.example.sgi.panel;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import com.example.sgi.R;
import com.google.android.material.textfield.TextInputLayout;

public class asignarTecnico extends AppCompatActivity {

    // Declaración de Variables.
    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignar_tecnico);

        // Inicializamos las Variables.
        textInputLayout = findViewById(R.id.asignar_tecnico_tecnico_dropdown);
        autoCompleteTextView = findViewById(R.id.autocomplete_tecnico);
        String [] tecnicos = new String[]{"T","F"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(asignarTecnico.this,R.layout.dropdowm_item,tecnicos);autoCompleteTextView.setAdapter(adapter);
    }
}