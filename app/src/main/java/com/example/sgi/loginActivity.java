package com.example.sgi;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

public class loginActivity extends Activity {

    private View _bg__login___android___;
    private View sheet;
    private View btn_bg;
    private TextView btn_label;
    private View textarea_bg;
    private TextView textfield;
    private TextView label;
    private View textarea_bg_ek1;
    private TextView textfield_ek1;
    private ImageView vector;
    private TextView label_ek1;
    private ImageView logo_comercio;
    private TextView contrase_a_olvidada;
    private TextView introduce_tus_datos_de_acceso;
    private TextView acceso_sistema_incidencias;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        _bg__login___android___ = (View) findViewById(R.id._bg__login___android___);
        sheet = (View) findViewById(R.id.sheet);
        btn_bg = (View) findViewById(R.id.btn_bg);
        btn_label = (TextView) findViewById(R.id.btn_label);
        textarea_bg = (View) findViewById(R.id.textarea_bg);
        textfield = (TextView) findViewById(R.id.textfield);
        label = (TextView) findViewById(R.id.label);
        textarea_bg_ek1 = (View) findViewById(R.id.textarea_bg_ek1);
        textfield_ek1 = (TextView) findViewById(R.id.textfield_ek1);
        vector = (ImageView) findViewById(R.id.vector);
        label_ek1 = (TextView) findViewById(R.id.label_ek1);
        logo_comercio = (ImageView) findViewById(R.id.logo_comercio);
        contrase_a_olvidada = (TextView) findViewById(R.id.contrase_a_olvidada);
        introduce_tus_datos_de_acceso = (TextView) findViewById(R.id.login_intro_datos);
        acceso_sistema_incidencias = (TextView) findViewById(R.id.login_titulo);
    }
}