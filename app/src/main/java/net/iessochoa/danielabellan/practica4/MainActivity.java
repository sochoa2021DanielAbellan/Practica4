package net.iessochoa.danielabellan.practica4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import net.iessochoa.danielabellan.practica4.adapters.TareasAdapter;
import net.iessochoa.danielabellan.practica4.model.Tarea;
import net.iessochoa.danielabellan.practica4.model.TareaViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvListaTareas;
    private TareaViewModel tareaViewModel;
    private TareasAdapter tareasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvListaTareas = findViewById(R.id.rvListaTareas);

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

}