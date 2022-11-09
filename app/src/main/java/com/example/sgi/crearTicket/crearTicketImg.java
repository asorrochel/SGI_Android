package com.example.sgi.crearTicket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.sgi.R;
import com.google.android.material.textfield.TextInputLayout;

public class crearTicketImg extends AppCompatActivity {

    Toolbar toolbar;
    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_ticket_img);

        toolbar = findViewById(R.id.mainToolBar);
        setToolbar(toolbar);

        textInputLayout = findViewById(R.id.crear_ticket_aula_img_dropdown);
        autoCompleteTextView = findViewById(R.id.autocomplete_aula_img);

        String [] aulas = new String[]{"C01 Diurno","C01 Vespertino","C02 Diurno","C02 Vespertino","C03 Diurno","C03 Vespertino","C04 Diurno","C04 Vespertino","C05 Diurno","C05 Vespertino","C06 Diurno","C06 Vespertino","C07 Diurno","C07 Vespertino","C08 Diurno","C08 Vespertino","C09 Diurno","C09 Vespertino","C10 Diurno","C10 Vespertino","C11 Diurno","C11 Vespertino","C12 Diurno","C12 Vespertino","C13 Diurno","C13 Vespertino","Taller"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                crearTicketImg.this,R.layout.dropdowm_item,aulas);

        autoCompleteTextView.setAdapter(adapter);
    }

    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Crear Ticket");
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        View view = this.getCurrentFocus();

        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            return true;
        } else {
            return false;
        }
    }
}