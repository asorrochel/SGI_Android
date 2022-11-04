package com.example.sgi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class panelControl extends AppCompatActivity {

    AppCompatButton pc_btn_nuevos, pc_btn_espera, pc_btn_proceso, pc_btn_urgentes ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_control);

        pc_btn_nuevos = findViewById(R.id.pc_btn_nuevos);
        pc_btn_espera = findViewById(R.id.pc_btn_espera);
        pc_btn_proceso = findViewById(R.id.pc_btn_proceso);
        pc_btn_urgentes = findViewById(R.id.pc_btn_urgentes);

        pc_btn_nuevos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pc_btn_nuevos.setBackgroundResource(R.drawable.btn_verde);
                pc_btn_espera.setBackgroundResource(R.drawable.btn_azul_oscuro_borde);
                pc_btn_proceso.setBackgroundResource(R.drawable.btn_amarillo_borde);
                pc_btn_urgentes.setBackgroundResource(R.drawable.btn_rojo_borde);

                replaceFragment(new pc_fragment_nuevos());
            }
        });

        pc_btn_espera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pc_btn_espera.setBackgroundResource(R.drawable.btn_azul_oscuro);
                pc_btn_nuevos.setBackgroundResource(R.drawable.btn_verde_borde);
                pc_btn_proceso.setBackgroundResource(R.drawable.btn_amarillo_borde);
                pc_btn_urgentes.setBackgroundResource(R.drawable.btn_rojo_borde);

                replaceFragment(new pc_fragment_espera());
            }
        });
        pc_btn_proceso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pc_btn_proceso.setBackgroundResource(R.drawable.btn_amarillo);
                pc_btn_nuevos.setBackgroundResource(R.drawable.btn_verde_borde);
                pc_btn_urgentes.setBackgroundResource(R.drawable.btn_rojo_borde);
                pc_btn_espera.setBackgroundResource(R.drawable.btn_azul_oscuro_borde);

                replaceFragment(new pc_frament_proceso());
            }
        });
        pc_btn_urgentes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pc_btn_urgentes.setBackgroundResource(R.drawable.btn_rojo);
                pc_btn_nuevos.setBackgroundResource(R.drawable.btn_verde_borde);
                pc_btn_proceso.setBackgroundResource(R.drawable.btn_amarillo_borde);
                pc_btn_espera.setBackgroundResource(R.drawable.btn_azul_oscuro_borde);
                replaceFragment(new pc_fragment_urgentes());
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.panel_control_frame, fragment);
        fragmentTransaction.commit();

    }
}