package com.example.sgi;

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
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.sgi.inicio.inicioProfesorAlumno;
import com.example.sgi.inicio.inicioProfesoresMnt;
import com.example.sgi.inicio.inicioTutores;
import com.example.sgi.inicio.inicioTutoresMnt;
import com.example.sgi.utils.Usuario;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import de.hdodenhof.circleimageview.CircleImageView;

public class ajustes extends AppCompatActivity {

    Usuario u = new Usuario();
    Toolbar toolbar;
    AppCompatButton btnConfirmar;
    CircleImageView imagenUsuario;
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
        setContentView(R.layout.activity_ajustes);

        toolbar = findViewById(R.id.mainToolBar);
        setToolbar(toolbar);

        btnConfirmar = findViewById(R.id.ajustes_btn_guardar);
        imagenUsuario = findViewById(R.id.ajustes_btn_add_foto_perfil);

        //CAMBIARLO (SÓLO PRUEBAS)
        u.setRolUsuario("ROL_TUT_MANT");

        confirmarCambios();

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
        getSupportActionBar().setTitle("Ajustes");
        //getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }

    private void confirmarCambios() {
        btnConfirmar.setOnClickListener((View) -> {
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Guardar Cambios")
                    .setMessage("¿Desea Confirmar los cambios?")
                    .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if (u.getRolUsuario().equalsIgnoreCase("ROL_TUTOR")) {
                                startActivity(new Intent(ajustes.this, inicioTutores.class));
                                Toast.makeText(ajustes.this, "Cambios guardados correctamente", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_PROFESOR") || u.getRolUsuario().equalsIgnoreCase("ROL_ALUMNO")) {
                                startActivity(new Intent(ajustes.this, inicioProfesorAlumno.class));
                                Toast.makeText(ajustes.this, "Cambios guardados correctamente", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_TUT_MANT")) {
                                startActivity(new Intent(ajustes.this, inicioTutoresMnt.class));
                                Toast.makeText(ajustes.this, "Cambios guardados correctamente", Toast.LENGTH_SHORT).show();
                            } else if (u.getRolUsuario().equalsIgnoreCase("ROL_PROF_MANT")) {
                                startActivity(new Intent(ajustes.this, inicioProfesoresMnt.class));
                                Toast.makeText(ajustes.this, "Cambios guardados correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
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