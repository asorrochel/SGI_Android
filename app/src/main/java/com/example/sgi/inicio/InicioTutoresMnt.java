package com.example.sgi.inicio;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.example.sgi.R;
import com.example.sgi.Ajustes;
import com.example.sgi.crearTicket.CrearTicketImg;
import com.example.sgi.Login;
import com.example.sgi.panel.PanelControl;
import com.example.sgi.utils.Usuario;
import de.hdodenhof.circleimageview.CircleImageView;

public class InicioTutoresMnt extends AppCompatActivity {

    // Declaración de Variables.
    Usuario u = new Usuario();
    CircleImageView imagenUsuario;
    Toolbar toolbar;
    AppCompatButton btnCerrarSesion, btnPanelControl, btnCrearTicket, btnValidarTicket, btnEstadoTicket, btnHistorialTicket;
    Uri uri;
        // Códigos de Permisos de Cámara y Almacenamiento.
    private static final int REQUEST_CAMERA_CODE = 1;
    private static final int REQUEST_STORAGE_CODE = 2;
        // Códigos de Comprobación, si la imagen viene de cámara o almacenamiento.
    private static final int PICK_CAMERA_CODE = 3;
    private static final int PICK_GALLERY_CODE = 4;
        // Arrays de permisos (el primero para cámara y almacenamiento y el segundo sólo para almacenamiento).
    private String[] cameraPermissions;
    private String[] storagePermissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_tutores_mnt);

        // Inicializamos las Variables.
        toolbar = findViewById(R.id.mainToolBar);
        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        btnPanelControl = findViewById(R.id.inicio_tutores_mnt_btn_panel);
        btnCrearTicket = findViewById(R.id.inicio_tutores_mnt_btn_crear_ticket);
        btnValidarTicket = findViewById(R.id.inicio_tutores_mnt_btn_validar_ticket);
        btnEstadoTicket = findViewById(R.id.inicio_tutores_mnt_btn_estado_ticket);
        btnHistorialTicket = findViewById(R.id.inicio_tutores_mnt_btn_historial_ticket);
        btnCerrarSesion = findViewById(R.id.inicio_tutores_mnt_btn_cerrar_sesion);
        imagenUsuario = findViewById(R.id.inicio_tutores_mnt_btn_add_foto_perfil);

        // Método para añadir el Toolabr a la activity.
        setToolbar(toolbar);

        // Método para añadir la Imagen.
        añadirImagen();

        // Funcionalidad de los botones del menú.
        clickPanelControl();
        clickCrearTicket();
        clickValidarTicket();
        clickEstadoTicket();
        clickHistorialTicket();
        clickCerrarSesion();
    }

    /**
     * Le añadimos al CircleImage el OnClick y mostramos las opciones a elegir.
     */
    private void añadirImagen() {
        imagenUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {mostrarOpcionesImagen();}
        });
    }

    /**
     * Método que le muestra al usuario un AlertDialog con las opciones desde las que se puede añadir la imagen.
     */
    private void mostrarOpcionesImagen() {
        String[] opciones = {"Cámara", "Galería"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Añadir Imagen");

        builder.setItems(opciones, new DialogInterface.OnClickListener() {
            // Le añadimos el OnClick a las opciones y comprobamos permisos.
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

    /**
     * Método que comprueba los permisos de cámara.
     */
    private boolean comprobarPermisosCamara() {
        boolean permisosCamara, permisosAlmacenamiento;
        permisosCamara = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
        permisosAlmacenamiento = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        return permisosCamara && permisosAlmacenamiento;
    }

    /**
     * Método que comprueba los permisos de almacenamiento.
     */
    private boolean comprobarPermisosAlmacenamiento() {
        boolean permisos;
        permisos = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;

        return permisos;
    }

    /**
     * Método que le pide los permisos de cámara al usuario.
     */
    private void pedirPermisosCamara() {
        ActivityCompat.requestPermissions(this, cameraPermissions, REQUEST_CAMERA_CODE);
    }

    /**
     * Método que le pide los permisos de almacenamiento al usuario.
     */
    private void pedirPermisosAlmacenamiento() {
        ActivityCompat.requestPermissions(this, storagePermissions, REQUEST_STORAGE_CODE);
    }

    /**
     * Método que lleva al usuario a la Cámara.
     */
    private void irCamara() {
        ContentValues valores = new ContentValues();
        valores.put(MediaStore.Images.Media.TITLE, "Título de la imagen");
        valores.put(MediaStore.Images.Media.DESCRIPTION, "Descripción de la imagen");

        // En la uri correspondiente a la imagen escogida insertamos los valores de título y descripción.
        uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, valores);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(intent, PICK_CAMERA_CODE);
    }

    /**
     * Método que lleva al usuario a la Galería.
     */
    private void irGaleria() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_GALLERY_CODE);
    }

    /**
     * Método con el que pedimos los permisos al usuario.
     * @param requestCode - Código que mandamos al método, indica si la opción seleccionada es la cámara o la galería.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CAMERA_CODE){
            // Si el usuario permite los permisos, vamos a la cámara.
            if(permissions.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                irCamara();
            }
            // Si no, le mostramos un mensaje para que habilite los permisos.
            else{
                Toast.makeText(this, "Es necesario que habilites los permisos", Toast.LENGTH_SHORT).show();
            }

        }
        if(requestCode == REQUEST_STORAGE_CODE){
            // Si el usuario permite esos permisos, vamos a la galería.
            if(permissions.length>0 && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                irGaleria();
            }
            // Si no, le mostramos un mensaje para que habilite los permisos.
            else{
                Toast.makeText(this, "Es necesario que habilites los permisos", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Método que nos añade en el CircleImage la imagen que ha seleccionado el usuario.
     * @param requestCode - Código que mandamos al método, indica si la opción seleccionada es la cámara o la galería.
     * @param resultCode - Código que devuelve el resultado del proceso.
     * @param data - Datos de la imagen que recibimos.
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Si el usuario no tiene imagen asociada, la ponemos desde cámara o galeería, dependiendo de lo que haya seleccionado el usuario.
        if (u.getImagen() == null) {
            if(resultCode == Activity.RESULT_OK) {
                if (requestCode == PICK_CAMERA_CODE) {
                    imagenUsuario.setImageURI(uri);
                } else if (requestCode == PICK_GALLERY_CODE) {
                    uri = data.getData();
                    imagenUsuario.setImageURI(uri);
                }
                Toast.makeText(this, "Imagen actualizada correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al cambiar la imagen", Toast.LENGTH_SHORT).show();
            }
        // Si ya tiene una imagen asociada, la ponemos.
        } else {
            imagenUsuario.setImageURI(u.getImagen());
        }
    }

    /**
     * Método que añade a la activity un Toolbar.
     * @param toolbar - ToolBar que queremos añadir a la activity.
     */
    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Inicio");
    }

    /**
     * Método en el que añadimos al Toolbar el Menú de ajustes.
     * @param menu - Menú que vamos a crear.
     * @return - True.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    /**
     * Método que le da la funcionalidad a los items que están dentro del menú.
     * @param item -  Item del menú que queremos seleccionar.
     * @return - True, si se ejecuta sin fallos, False si salta algún error
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Si la opción seleccionada es Ajustes, iniciamos la actividad de ajustes.
        if (item.getItemId() == R.id.ajustes) {
            startActivity(new Intent(InicioTutoresMnt.this, Ajustes.class));
            return true;
        } else {
            Toast.makeText(InicioTutoresMnt.this, "Error al acceder a Ajustes", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    /**
     * Método que le añade la funcionalidad OnClick al botón de Panel de Control.
     */
    private void clickPanelControl() {
        btnPanelControl.setOnClickListener((View) -> {
            startActivity(new Intent(InicioTutoresMnt.this, PanelControl.class));
        });
    }

    /**
     * Método que le añade la funcionalidad OnClick al botón de Crear Ticket.
     */
    private void clickCrearTicket() {
        btnCrearTicket.setOnClickListener((View) -> {
            startActivity(new Intent(InicioTutoresMnt.this, CrearTicketImg.class));
        });
    }

    /**
     * Método que le añade la funcionalidad OnClick al botón de Validar Ticket.
     */
    private void clickValidarTicket() {
        btnValidarTicket.setOnClickListener((View) -> {
            //startActivity(new Intent(inicioTutoresMnt.this, validarTicket.class));
        });
    }

    /**
     * Método que le añade la funcionalidad OnClick al botón de Estado Ticket.
     */
    private void clickEstadoTicket() {
        btnEstadoTicket.setOnClickListener((View) -> {
            //startActivity(new Intent(inicioTutoresMnt.this, estadoTicket.class));
        });
    }

    /**
     * Método que le añade la funcionalidad OnClick al botón de Historial Ticket.
     */
    private void clickHistorialTicket() {
        btnHistorialTicket.setOnClickListener((View) -> {
            //startActivity(new Intent(inicioTutoresMnt.this, historialTicket.class));
        });
    }

    /**
     * Método que le añade la funcionalidad OnClick al botón de Cerrar Sesión.
     */
    private void clickCerrarSesion() {
        btnCerrarSesion.setOnClickListener((View) -> {
            Login.cambiarEstadoCheckbox(InicioTutoresMnt.this, false);
            startActivity(new Intent(InicioTutoresMnt.this, Login.class));
        });
    }
}