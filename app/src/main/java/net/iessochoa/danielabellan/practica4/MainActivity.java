package net.iessochoa.danielabellan.practica4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.iessochoa.danielabellan.practica4.adapters.TareasAdapter;
import net.iessochoa.danielabellan.practica4.model.Tarea;
import net.iessochoa.danielabellan.practica4.model.TareaViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public final static int OPTION_REQUEST_NUEVO = 1;
    public final static int OPTION_REQUEST_EDITAR = 2;

    private RecyclerView rvListaTareas;
    private TareaViewModel tareaViewModel;
    private TareasAdapter tareasAdapter;
    private FloatingActionButton fabAnyadirTarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListaTareas = findViewById(R.id.rvListaTareas);
        fabAnyadirTarea = findViewById(R.id.fabAnyadirTarea);
        fabAnyadirTarea.setOnClickListener(this);

        //***********RecyclerView*******************
        tareasAdapter = new TareasAdapter();
        rvListaTareas.setLayoutManager(new LinearLayoutManager(this));
        rvListaTareas.setAdapter(tareasAdapter);

        //*********ViewModel**************************
        //Recuperamos el ViewModel. Si fuese la primera vez que se ejecuta al abrir la aplicación, se crearía uno nuevo.
        tareaViewModel = new ViewModelProvider(this).get(TareaViewModel.class);

        //Hemos creado la lista como un LiveData para que si hay cambios en la lista, éstos se vayan mostrando de forma automática
        tareaViewModel.getTareaList().observe(this, new Observer<List<Tarea>>() {
            //@Override
            public void onChanged(List<Tarea> tareas) {
                //actualizamos el recyclerView si hay cambios en la lista de Notas
                tareasAdapter.setMiListaTareas(tareas);
            }
        });

        //Para borrar la nota en cuestión que sea llamada
        tareasAdapter.setOnClickBorrarListener(tarea -> {
            borrarTarea(tarea);
        });

        tareasAdapter.setOnClickEditarListener(tarea -> {
            Intent intent = new Intent(this, TareaActivity.class);
            intent.putExtra(TareaActivity.EXTRA_TAREA, tarea);
            startActivityForResult(intent, OPTION_REQUEST_EDITAR);
        });
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.fabAnyadirTarea:
                Intent intent = new Intent(this, TareaActivity.class);
                startActivityForResult(intent, OPTION_REQUEST_NUEVO);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opcion_ordena:
                muestraToast("La lista ha sido ordenada");
                break;
            case R.id.opcion_acercaDe:
                muestraAcercaDe();
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            Tarea tareaRecibida = data.getParcelableExtra(TareaActivity.EXTRA_TAREA);

            if (requestCode == OPTION_REQUEST_NUEVO) {
                tareaViewModel.addTarea(tareaRecibida);

                //Si es una tarea ya creada se sobrescriben los datos
            } else if (requestCode == OPTION_REQUEST_EDITAR) {
                tareaViewModel.addTarea(tareaRecibida);
            }
        }
    }

    private void muestraToast(String mensaje){
        Toast.makeText(getApplicationContext(), mensaje,Toast.LENGTH_SHORT).show();
    }

    private void muestraAcercaDe(){
        AlertDialog.Builder ventanaEmergente = new AlertDialog.Builder(this);
        ventanaEmergente.setTitle("Acerca de...");
        ventanaEmergente.setMessage("Práctica 4\nVersión 1.0\nDaniel Abellán Zárate\nLicencia CC\n(Creative Commons)");
        ventanaEmergente.setPositiveButton(android.R.string.ok, null);
        ventanaEmergente.show();
    }

    private void borrarTarea(final Tarea tarea) {
        android.app.AlertDialog.Builder ventanaAvisoBorrado = new android.app.AlertDialog.Builder(MainActivity.this);
        ventanaAvisoBorrado.setTitle("Borrando tarea...");
        ventanaAvisoBorrado.setMessage("¿Está seguro de que desea borrar esta tarea?");
        ventanaAvisoBorrado.setPositiveButton(android.R.string.yes, (dialogInterface, i) -> tareaViewModel.delTarea(tarea));
        ventanaAvisoBorrado.setNegativeButton(android.R.string.no, (dialogInterface, i) -> {
        });
        ventanaAvisoBorrado.show();
    }
}