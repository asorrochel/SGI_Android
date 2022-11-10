package com.example.sgi.crearTicket;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sgi.R;
import com.example.sgi.ajustes;
import com.example.sgi.inicio.inicioProfesorAlumno;
import com.example.sgi.inicio.inicioProfesoresMnt;
import com.example.sgi.inicio.inicioTutores;
import com.example.sgi.inicio.inicioTutoresMnt;
import com.example.sgi.utils.Usuario;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

public class crearTicketImg extends AppCompatActivity {

    Toolbar toolbar;
    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;
    Button botonEnviar, botonImagen;
    Usuario u = new Usuario();
    Uri uri;
    TextView textViewMensajeImagen;

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
        setContentView(R.layout.activity_crear_ticket_img);

        toolbar = findViewById(R.id.mainToolBar);
        textInputLayout = findViewById(R.id.crear_ticket_aula_img_dropdown);
        autoCompleteTextView = findViewById(R.id.autocomplete_aula_img);
        botonEnviar = findViewById(R.id.crear_ticket_img_enviar);
        botonImagen = findViewById(R.id.crear_ticket_img_btn_imagen);
        textViewMensajeImagen = findViewById(R.id.mensajeImagen);

        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        textViewMensajeImagen.setText("");

        u.setRolUsuario("ROL_TUT_MANT");

        String [] aulas = new String[]{"C01 Diurno","C01 Vespertino","C02 Diurno","C02 Vespertino","C03 Diurno","C03 Vespertino","C04 Diurno","C04 Vespertino","C05 Diurno","C05 Vespertino","C06 Diurno","C06 Vespertino","C07 Diurno","C07 Vespertino","C08 Diurno","C08 Vespertino","C09 Diurno","C09 Vespertino","C10 Diurno","C10 Vespertino","C11 Diurno","C11 Vespertino","C12 Diurno","C12 Vespertino","C13 Diurno","C13 Vespertino","Taller"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                crearTicketImg.this,R.layout.dropdowm_item,aulas);

        autoCompleteTextView.setAdapter(adapter);

        setToolbar(toolbar);
        clickBotonEnviar();
        clickBotonImagen();
    }

    private void clickBotonImagen() {
        botonImagen.setOnClickListener(new View.OnClickListener() {
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
        ContentValues valores = new ContentValues();
        valores.put(MediaStore.Images.Media.TITLE, "Título de la imagen");
        valores.put(MediaStore.Images.Media.DESCRIPTION, "Descripción de la imagen");

        //En la uri correpsondiente a la imagen escogida insertamos los valores de título y descripción
        uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, valores);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
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
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_CAMERA_CODE) {
                textViewMensajeImagen.setText("Imagen de cámara añadida");
            } else if (requestCode == PICK_GALLERY_CODE) {
                uri = data.getData();
                textViewMensajeImagen.setText("Imagen de galería añadida");
            }
            Toast.makeText(this, "Imagen Añadida", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al añadir imagen", Toast.LENGTH_SHORT).show();
        }
    }

    private void clickBotonEnviar() {
        botonEnviar.setOnClickListener((View) -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Crear Ticket")
                    .setMessage("¿Desea crear el Ticket?")
                    .setPositiveButton("Crear", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (u.getRolUsuario().equalsIgnoreCase("ROL_TUTOR")) {
                                startActivity(new Intent(crearTicketImg.this, inicioTutores.class));
                                Toast.makeText(crearTicketImg.this, "Ticket creado correctamente", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_TUT_MANT")) {
                                startActivity(new Intent(crearTicketImg.this, inicioTutoresMnt.class));
                                Toast.makeText(crearTicketImg.this, "Ticket creado correctamente", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_PROF_MANT")) {
                                startActivity(new Intent(crearTicketImg.this, inicioProfesoresMnt.class));
                                Toast.makeText(crearTicketImg.this, "Ticket creado correctamente", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_PROFESOR")) {
                                startActivity(new Intent(crearTicketImg.this, inicioProfesoresMnt.class));
                                Toast.makeText(crearTicketImg.this, "Ticket creado correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
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