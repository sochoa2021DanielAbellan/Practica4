package net.iessochoa.danielabellan.practica4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import net.iessochoa.danielabellan.practica4.model.Tarea;

public class TareaActivity extends AppCompatActivity implements View.OnClickListener{
    public final static String EXTRA_TAREA = "TareaActivity.EXTRA_TAREA";

    private TextView tvSpinnerCategoria;
    private Spinner spTareaCategoria;
    private TextView tvSpinnerPrioridad;
    private Spinner spTareaPrioridad;
    private ImageView ivSpinnerEstado;
    private TextView tvSpinnerEstado;
    private Spinner spTareaEstado;
    private TextView tvActivityTareaTecnico;
    private TextInputLayout tilTareaActivityTecnico;
    private TextInputEditText tietTareaActivityTecnico;
    private TextView tvActivityTareaDescripcion;
    private TextInputLayout getTilTareaActivityDescripcion;
    private TextInputEditText tietTareaActivityDescripcion;
    private TextView tvTareaActivityResumen;
    private EditText etTareaActivityResumen;
    private FloatingActionButton fabTareaActivityGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarea);

        tvSpinnerCategoria = findViewById(R.id.tvSpinnerCategoria);
        spTareaCategoria = findViewById(R.id.spTareaCategoria);

        tvSpinnerPrioridad = findViewById(R.id.tvSpinnerPrioridad);
        spTareaPrioridad = findViewById(R.id.spTareaPrioridad);

        ivSpinnerEstado = findViewById(R.id.ivSpinnerEstado);
        tvSpinnerEstado = findViewById(R.id.tvSpinnerEstado);
        spTareaEstado = findViewById(R.id.spTareaEstado);

        spTareaEstado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String seleccionActual = (String) adapterView.getItemAtPosition(i);

                switch (seleccionActual) {
                    case "Abierta":
                        ivSpinnerEstado.setImageResource(R.drawable.ic_e_abierta_foreground);
                        break;
                    case "En curso":
                        ivSpinnerEstado.setImageResource(R.drawable.ic_e_proceso_foreground);
                        break;
                    case "Terminada":
                        ivSpinnerEstado.setImageResource(R.drawable.ic_e_terminada_foreground);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        tilTareaActivityTecnico = findViewById(R.id.tilTareaActivityTecnico);
        tietTareaActivityTecnico = findViewById(R.id.tietTareaActivityTecnico);

        tilTareaActivityTecnico = findViewById(R.id.tilTareaActivityDescripcion);
        tietTareaActivityDescripcion = findViewById(R.id.tietActivityTareaDescripcion);

        tvTareaActivityResumen = findViewById(R.id.tvTareaActivityResumen);
        etTareaActivityResumen = findViewById(R.id.etTareaActivityResumen);

        fabTareaActivityGuardar = findViewById(R.id.fabTareaActivityGuardar);
        fabTareaActivityGuardar.setOnClickListener(view -> {
            if(comprobarCamposVacios(tietTareaActivityTecnico, tietTareaActivityDescripcion, etTareaActivityResumen)){
                avisoRellenarCampos();
            }
            else{
                Intent intentRetorno = new Intent();
                Tarea tareaRetorno = new Tarea(spTareaPrioridad.getSelectedItem().toString(), spTareaCategoria.getSelectedItem().toString(), spTareaEstado.getSelectedItem().toString(), tietTareaActivityTecnico.getText().toString(), tietTareaActivityDescripcion.getText().toString(), etTareaActivityResumen.getText().toString());
                intentRetorno.putExtra(EXTRA_TAREA, tareaRetorno);
                setResult(RESULT_OK, intentRetorno);
                finish();
            }
        });

        //A partir de aquí, comprobamos si la Tarea en cuestión es nueva o no dependiendo de si hemos recibido una Tarea como extra a la hora de iniciar TareaActivity
        Tarea tareaExistente = getIntent().getParcelableExtra(EXTRA_TAREA);
        if(tareaExistente != null){
            this.setTitle("Tarea Número " + tareaExistente.getId());
            spTareaCategoria.setSelection(obtenerSeleccion(spTareaCategoria, tareaExistente.getCategoria()));
            spTareaEstado.setSelection(obtenerSeleccion(spTareaEstado, tareaExistente.getEstado()));
            spTareaPrioridad.setSelection(obtenerSeleccion(spTareaPrioridad, tareaExistente.getPrioridad()));
            tietTareaActivityTecnico.setText(tareaExistente.getTecnico());
            tietTareaActivityDescripcion.setText(tareaExistente.getDescripcion());
            etTareaActivityResumen.setText(tareaExistente.getResumen());
        }
        else{
            this.setTitle("Nueva tarea");
        }
    }

    @Override
    public void onClick(View view) {

    }

    public int obtenerSeleccion(Spinner sp, String opcion){
        int resultado = -1;
        boolean encontrado = false;
        for(int i=0; i < sp.getCount() && !encontrado; i++){
            if(sp.getItemAtPosition(i).toString().equalsIgnoreCase(opcion)){
                resultado = i;
                encontrado = true;
            }
        }

        return resultado;
    }

    public boolean comprobarCamposVacios(TextInputEditText tecnico, TextInputEditText descripcion, EditText resumen){
        boolean resultado = false;

        if(tecnico.getText().toString().equals("") || descripcion.getText().toString().equals("") || resumen.getText().toString().equals("")){
            resultado = true;
        }

        return resultado;
    }

    public void avisoRellenarCampos(){
        AlertDialog.Builder aviso = new AlertDialog.Builder(this);
        aviso.setTitle("Campos vacíos");
        aviso.setMessage("Se han detectado uno o más campos sin rellenar. Por favor, rellénelos todos antes de guardar la tarea.");
        aviso.setPositiveButton(android.R.string.ok, null);
        aviso.show();
    }
}