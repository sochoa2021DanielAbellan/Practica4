package net.iessochoa.danielabellan.practica4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class TareaActivity extends AppCompatActivity implements View.OnClickListener{

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

        tvSpinnerCategoria.findViewById(R.id.tvSpinnerCategoria);
        spTareaCategoria.findViewById(R.id.spTareaCategoria);
        final ArrayAdapter<CharSequence> adaptadorCategoria = ArrayAdapter.createFromResource(this, R.array.categoriaTarea, android.R.layout.simple_spinner_item);
        spTareaCategoria.setAdapter(adaptadorCategoria);

        tvSpinnerPrioridad.findViewById(R.id.tvSpinnerPrioridad);
        spTareaPrioridad.findViewById(R.id.spTareaPrioridad);

        ivSpinnerEstado.findViewById(R.id.ivSpinnerEstado);
        tvSpinnerEstado.findViewById(R.id.tvSpinnerEstado);
        spTareaEstado.findViewById(R.id.spTareaEstado);

        tvActivityTareaTecnico.findViewById(R.id.tvActivityTareaTecnico);
        tilTareaActivityTecnico.findViewById(R.id.tilTareaActivityTecnico);
        tietTareaActivityTecnico.findViewById(R.id.tietTareaActivityTecnico);

        tvActivityTareaDescripcion.findViewById(R.id.tvActivityTareaDescripcion);
        tilTareaActivityTecnico.findViewById(R.id.tilTareaActivityDescripcion);
        tietTareaActivityDescripcion.findViewById(R.id.tietActivityTareaDescripcion);

        tvTareaActivityResumen.findViewById(R.id.tvTareaActivityResumen);
        etTareaActivityResumen.findViewById(R.id.etTareaActivityResumen);

        fabTareaActivityGuardar.findViewById(R.id.fabTareaActivityGuardar);
    }

    @Override
    public void onClick(View view) {

    }
}