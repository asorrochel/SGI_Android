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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.sgi.R;
import com.example.sgi.inicio.inicioProfesorAlumno;
import com.example.sgi.inicio.inicioProfesoresMnt;
import com.example.sgi.inicio.inicioTutores;
import com.example.sgi.inicio.inicioTutoresMnt;
import com.example.sgi.network.ApiAula;
import com.example.sgi.network.ApiClient;
import com.example.sgi.network.ApiTicket;
import com.example.sgi.utils.Aula;
import com.example.sgi.utils.Ticket;
import com.example.sgi.utils.Usuario;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class crearTicketImg extends AppCompatActivity {

    // Declaración de Variables.
    EditText titulo, equipo, comentario;
    List<Aula> aulaList;
    AdapterAulas adapter;
    Map<Integer ,String> aulas = new HashMap<Integer, String>();
    String[] aulaValor;
    List<Pair> parejas = new ArrayList<>();
    int idAula;

    Toolbar toolbar;
    TextInputLayout textInputLayout;
    AutoCompleteTextView autoCompleteTextView;
    Button botonEnviar, botonImagen;
    Usuario u = new Usuario();
    Uri uri;
    TextView textViewMensajeImagen;
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
        setContentView(R.layout.activity_crear_ticket_img);

        // Inicializamos las Variables.
        titulo = findViewById(R.id.crear_ticket_prompt_titulo_EditText);
        equipo = findViewById(R.id.crear_ticket_img_prompt_correo_EditText);
        comentario = findViewById(R.id.crear_ticket_img_prompt_comentario_EditText);
        toolbar = findViewById(R.id.mainToolBar);
        textInputLayout = findViewById(R.id.crear_ticket_aula_img_dropdown);
        autoCompleteTextView = findViewById(R.id.autocomplete_aula_img);
        botonEnviar = findViewById(R.id.crear_ticket_img_enviar);
        botonImagen = findViewById(R.id.crear_ticket_img_btn_imagen);
        textViewMensajeImagen = findViewById(R.id.mensajeImagen);
        cameraPermissions = new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        storagePermissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE};
        textViewMensajeImagen.setText("");
        //String [] aulas = new String[]{"C01 Diurno","C01 Vespertino","C02 Diurno","C02 Vespertino","C03 Diurno","C03 Vespertino","C04 Diurno","C04 Vespertino","C05 Diurno","C05 Vespertino","C06 Diurno","C06 Vespertino","C07 Diurno","C07 Vespertino","C08 Diurno","C08 Vespertino","C09 Diurno","C09 Vespertino","C10 Diurno","C10 Vespertino","C11 Diurno","C11 Vespertino","C12 Diurno","C12 Vespertino","C13 Diurno","C13 Vespertino","Taller"};
        rellenarAulasAPI();

        //CAMBIARLO (SÓLO PRUEBAS)
        u.setRolUsuario("ROL_TUT_MANT");

        // Método para añadir el Toolabr a la activity.
        setToolbar(toolbar);

        // Funcionalidad de los botones del menú.
        clickBotonEnviar();
        clickBotonImagen();
    }

    /**
     * Método para añadir la imagen y mostramos las opciones a elegir.
     */
    private void clickBotonImagen() {
        botonImagen.setOnClickListener(new View.OnClickListener() {
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
        if(resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_CAMERA_CODE) {
                textViewMensajeImagen.setText("Imagen de cámara añadida");
            } else if (requestCode == PICK_GALLERY_CODE) {
                uri = data.getData();
                textViewMensajeImagen.setText("Imagen de galería añadida");
            }
            Toast.makeText(this, "Imagen Añadida", Toast.LENGTH_SHORT).show();
        // Si hay un error mostramos un mensaje al usuario.
        } else {
            Toast.makeText(this, "Error al añadir imagen", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método que le añade la función OnClick al botón de envío.
     */
    private void clickBotonEnviar() {
        botonEnviar.setOnClickListener((View) -> {

            String valorAula = autoCompleteTextView.getText().toString();
            for (Pair pair: parejas) {
                if (pair.getValor().equals(valorAula)) {
                    idAula = pair.getClave();
                }
            }
            guardarTicket(crearTicket());
            new MaterialAlertDialogBuilder(this)
                    .setTitle("Crear Ticket")
                    .setMessage("¿Desea crear el Ticket?")
                    .setPositiveButton("Crear", new DialogInterface.OnClickListener() {

                        // Dependiendo del ROL que tenga el usuario nos mandará al Activity que corresponda.
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
                                startActivity(new Intent(crearTicketImg.this, inicioProfesorAlumno.class));
                                Toast.makeText(crearTicketImg.this, "Ticket creado correctamente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .setNegativeButton("Cancelar", null)
                    .show();
        });
    }

    /**
     * Método que añade a la activity un Toolbar.
     * @param toolbar - ToolBar que queremos añadir a la activity.
     */
    private void setToolbar(Toolbar toolbar) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Crear Ticket");
        // Añadimos la flecha de retroceso.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    /**
     * Método que le da funcionalidad a la flecha de retroceso del Toolbar.
     * @return - True.
     */
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    /**
     * Método usado para cerrar el teclado al pulsar sobre otro lado de la pantalla.
     * @param event - Objeto utilizado para informar eventos de movimiento.
     * @return - True, si la vista es distinta de null, False si la View es null.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // Guardamos la vista seleccionada.
        View view = this.getCurrentFocus();

        // Si no es null (Tenemos una vista seleccionada), cerramos el teclado.
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
            return true;
        } else {
            return false;
        }
    }

    public Ticket crearTicket(){
        Ticket ticket = new Ticket();
        ticket.setTitulo(titulo.getText().toString());
        ticket.setAula(idAula);
        ticket.setDetalles(comentario.getText().toString());
        ticket.setFechaEmision("2013-03-12T00:00:00");
        ticket.setFechaResolucion("2013-03-12T00:00:00");
        ticket.setFoto(null);
        ticket.setIdEstado(1);
        ticket.setIdProfesor(1);
        ticket.setIdAlumno(1);
        return ticket;
    }

    public void guardarTicket(Ticket ticket){
        Call<Ticket> guardarTicket = ApiClient.getClient().create(ApiTicket.class).addUser(ticket);
        guardarTicket.enqueue(new Callback<Ticket>() {
            @Override
            public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                if(response.isSuccessful()){
                    Toast.makeText(crearTicketImg.this,"Creado correctamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(crearTicketImg.this,"Error gato", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Ticket> call, Throwable t) {
                Toast.makeText(crearTicketImg.this,"Error gati", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void rellenarAulasAPI() {
        Call<List<Aula>> call = ApiClient.getClient().create(ApiAula.class).getAulas();
        call.enqueue(new Callback<List<Aula>>() {
            @Override
            public void onResponse(Call<List<Aula>> call, Response<List<Aula>> response) {
                if(response.isSuccessful()){
                    aulaList = response.body();
                    aulaValor = new String[aulaList.size()];
                    int indice = 0;

                    for (Aula a : aulaList) {
                        parejas.add(new Pair(a.getIdAula(), a.getAula()));
                        //aulas.put((Integer) a.getIdAula(), a.getAula());
                        aulaValor[indice] = a.getAula();
                        indice++;
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(crearTicketImg.this,R.layout.dropdowm_item, aulaValor);
                    autoCompleteTextView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<List<Aula>> call, Throwable t) {
                Toast.makeText(crearTicketImg.this,"ERROR DE CONEXION",Toast.LENGTH_SHORT).show();
            }
        });
    }

    class Pair<Integer, String>{
        int clave;
        String valor;

        public Pair(){
        }

        public Pair(Integer x, String z){
            clave = (int) x;
            valor = z;
        }

        public int getClave() {
            return clave;
        }

        public void setIdAula(int idAula) {
            this.clave = idAula;
        }

        public String getValor() {
            return valor;
        }

        public void setAula(String valor) {
            this.valor = valor;
        }

    }

}