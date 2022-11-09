package com.example.sgi.inicio;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.sgi.R;
import com.example.sgi.ajustes;
import com.example.sgi.crearTicket.crearTicketImg;
import com.example.sgi.login;
import com.example.sgi.panel.panelControl;
import com.example.sgi.utils.Usuario;

import de.hdodenhof.circleimageview.CircleImageView;

public class inicioProfesoresMnt extends AppCompatActivity {

    Usuario u = new Usuario();
    CircleImageView imagenUsuario;
    Toolbar toolbar;
    AppCompatButton btnCerrarSesion, btnPanelControl, btnCrearTicket, btnEstadoTicket, btnHistorialTicket;
    Bitmap bmp;

    //Códigos de Permisos de Cámara y Almacenamiento
    private static final int REQUEST_CAMERA_CODE = 1;
    private static final int REQUEST_STORAGE_CODE = 2;

    //Códifos de Comprobación, si la imagen viene de cámara o almacenamiento
    private static final int PICK_CAMERA_CODE = 3;
    private static final int PICK_GALLERY_CODE = 4;

    //Arrays de permisos (el primero para cámara y almacenamiento y el segundo sólo para almacenamiento)
    private String[] cameraPermissions;
    private String[] storagePermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_profesores_mnt);

        toolbar = findViewById(R.id.mainToolBar);
        setToolbar(toolbar);

        //Inicializamos los arrays de permisos
        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};

        btnPanelControl = findViewById(R.id.inicio_profesores_mnt_btn_panel);
        btnCrearTicket = findViewById(R.id.inicio_profesores_mnt_btn_crear_ticket);
        btnEstadoTicket = findViewById(R.id.inicio_profesores_mnt_btn_estado_ticket);
        btnHistorialTicket = findViewById(R.id.inicio_profesores_mnt_btn_historial_ticket);
        btnCerrarSesion = findViewById(R.id.inicio_profesores_mnt_btn_cerrar_sesion);
        imagenUsuario = findViewById(R.id.inicio_profesores_mnt_btn_add_foto_perfil);

        clickPanelControl();
        clickCrearTicket();
        clickEstadoTicket();
        clickHistorialTicket();
        clickCerrarSesion();

        añadirImagen();
    }

    private void añadirImagen() {
        imagenUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //vamos al método mostrarOpcionesImagen para que nos aparezca el cuadro de diálogo con las opciones a escoger
                mostrarOpcionesImagen();
            }
        });
    }

    private void mostrarOpcionesImagen() {
        String[] opciones = {"Cámara", "Galería"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Añadir Imagen");

        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0) {
                    if(!comprobarPermisosCamara()) pedirPermisosCamara();
                    else irCamara();
                } else if(which==1) {
                    if(!comprobarPermisosAlmacenamiento()) pedirPermisosAlmacenamiento();
                    else irGaleria();
                }
            }
        });

        builder.create().show();
    }

    private boolean comprobarPermisosCamara() {
        boolean permisosCamara, permisosAlmacenamiento;
        permisosCamara = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        permisosAlmacenamiento = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        return permisosCamara && permisosAlmacenamiento;
    }

    private boolean comprobarPermisosAlmacenamiento() {
        boolean permisos;
        permisos = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        return permisos;
    }

    private void pedirPermisosCamara() {
        ActivityCompat.requestPermissions(this, cameraPermissions, REQUEST_CAMERA_CODE);
    }

    private void pedirPermisosAlmacenamiento() {
        ActivityCompat.requestPermissions(this, storagePermissions, REQUEST_STORAGE_CODE);
    }

    private void irCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, PICK_CAMERA_CODE);
    }

    private void irGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_GALLERY_CODE);
    }

    //Con este método pedimos los permisos, el requestCode será el REQUEST_CAMERA_CODE o el REQUEST_STORAGE_CODE
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CAMERA_CODE){
            //Si el usuario permite esos permisos, vamos a la cámara
            if(permissions.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                irCamara();
            }
            //si no le mostramos un mensaje para que habilite los permisos
            else{
                Toast.makeText(this, "Es necesario que habilites los permisos", Toast.LENGTH_SHORT).show();
            }

        }
        if(requestCode == REQUEST_STORAGE_CODE){
            //Si el usuario permite esos permisos, vamos a la galería
            if(permissions.length>0 && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                irGaleria();
            }
            //si no le mostramos un mensaje para que habilite los permisos
            else{
                Toast.makeText(this, "Es necesario que habilites los permisos", Toast.LENGTH_SHORT).show();
            }

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (u.getImagen() == null) {
            if(resultCode == Activity.RESULT_OK) {
                if (requestCode == PICK_CAMERA_CODE) {
                    Bundle ext = data.getExtras();
                    bmp = (Bitmap) ext.get("data");
                    imagenUsuario.setImageBitmap(bmp);
                } else if (requestCode == PICK_GALLERY_CODE) {
                    Uri path = data.getData();
                    imagenUsuario.setImageURI(path);
                }
                Toast.makeText(this, "Imagen actualizada correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al cambiar la imagen", Toast.LENGTH_SHORT).show();
            }
        } else {
            imagenUsuario.setImageURI(u.getImagen());
        }
    }

    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inicio");
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.ajustes) {
            startActivity(new Intent(inicioProfesoresMnt.this, ajustes.class));
            return true;
        } else {
            Toast.makeText(inicioProfesoresMnt.this, "Error al acceder a Ajustes", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void clickPanelControl() {
        btnPanelControl.setOnClickListener((View) -> {
            startActivity(new Intent(inicioProfesoresMnt.this, panelControl.class));
        });
    }

    private void clickCrearTicket() {
        btnCrearTicket.setOnClickListener((View) -> {
            startActivity(new Intent(inicioProfesoresMnt.this, crearTicketImg.class));
        });
    }
    private void clickEstadoTicket() {
        btnEstadoTicket.setOnClickListener((View) -> {
            //startActivity(new Intent(inicioProfesoresMnt.this, estadoTicket.class));
        });
    }

    private void clickHistorialTicket() {
        btnHistorialTicket.setOnClickListener((View) -> {
            //startActivity(new Intent(inicioProfesoresMnt.this, historialTicket.class));
        });
    }

    private void clickCerrarSesion() {
        btnCerrarSesion.setOnClickListener((View) -> {
            login.cambiarEstadoCambiarCheckbox(inicioProfesoresMnt.this, false);
            finish();
        });
    }
}