package net.iessochoa.danielabellan.practica4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                muestraAcerca();
                break;
        }
        return true;
    }

    private void muestraToast(String mensaje){
        Toast.makeText(getApplicationContext(), mensaje,Toast.LENGTH_SHORT).show();
    }

    private void muestraAcerca(){
        AlertDialog.Builder ventanaEmergente = new AlertDialog.Builder(this);
        ventanaEmergente.setTitle("Acerca de...");
        ventanaEmergente.setMessage("Práctica 4\nVersión 1.0\nDaniel Abellán Zárate\nLicencia CC\n(Creative Commons)");
        ventanaEmergente.setPositiveButton(android.R.string.ok, null);
        ventanaEmergente.show();
    }

}