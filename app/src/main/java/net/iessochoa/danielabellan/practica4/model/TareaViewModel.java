package net.iessochoa.danielabellan.practica4.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class TareaViewModel extends AndroidViewModel {
    //Si queremos que la actividad reciba un aviso cuando se modifican los datos, tenemos que crear
    //un LiveData(https://developer.android.com/topic/libraries/architecture/livedata)
    private MutableLiveData<List<Tarea>> listaTareasLiveData;

    //Creamos una lista que mantendrá los datos de las actividades mientras aplicación no sea totalmente cerrada
    private List<Tarea> listaTareas;

    public TareaViewModel(@NonNull Application application) {
        super(application);

        //El LiveData nos permitirá recibir notificaciones  en la actividad cuando se modifique la lista de los datos
        listaTareasLiveData=new MutableLiveData<List<Tarea>>();

        //Creamos unos datos a la hora de iniciar la aplicación
        crearDatos();

        //Una vez creados, avisamos de la modifiación realizada mediante el LiveData
        listaTareasLiveData.setValue(listaTareas);
    }

    /**
     * Este método nos permite recuperar el LiveData para asignarle el listener al Observador
     * en la Activity cuando los datos se vean modificados
     * @return
     */
    public LiveData<List<Tarea>> getTareaList(){
        return listaTareasLiveData;
    }

    /**
     * Este método nos permite añadir una Tarea a la lista
     * @param tarea
     */
    public void addTarea(Tarea tarea){
        /*
        Añadimos una Tarea a la lista, y si ya existe previamente (es decir, que tenga el mismo ID, tal y
        como hemos hecho al sobreescribir el método equals en la clase Tarea), la sustituiremos
        */
        int i = listaTareas.indexOf(tarea);

        if(i < 0){
            listaTareas.add(tarea);
        }
        else{
            listaTareas.remove(i);
            listaTareas.add(i,tarea);
        }

        //Tras realizar los cambios, avisaremos al LiveData para que active el Observer y así pueda la Actividad mostrar los cambios realizados
        listaTareasLiveData.setValue(listaTareas);
    }

    /**
     * Este método se encarga de borrar una Tarea mediante el paso de su ID
     */
    public void delTarea(Tarea tarea){
        if(listaTareas.size()>0){
            listaTareas.remove(tarea);

          //Avisamos al LiveData para que active el Observer
            listaTareasLiveData.setValue(listaTareas);
        }
    }

    /**
     * Método encargado de crear unos datos a la hora de iniciar la aplicación
     */
    private void crearDatos() {
        listaTareas=new ArrayList<Tarea>();
        Tarea tarea=new Tarea("Alta","Mantenimiento","Abierta","Juan Perez","Actualización de antivirus","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris laoreet aliquam sapien, quis mattis.");
        listaTareas.add(tarea);
        tarea=new Tarea("Media","Mantenimiento","Terminada","Maria Perez","Actualización de S.O.Linux","Mauris laoreet aliquam sapien, quis mattis diam pretium vel.");
        listaTareas.add(tarea);
        tarea=new Tarea("Baja","Reparación","En curso","Maria Lopez","Sustitución de ratones","Integer nec tincidunt turpis. Vestibulum interdum accumsan massa, sed blandit ex fringilla at.");
        listaTareas.add(tarea);
        tarea=new Tarea("Alta","Comercial","Abierta","Fele Martinez","Presentar presupuesto Web","Vivamus non sem vitae nisl viverra pharetra. Pellentesque pulvinar vestibulum risus sit amet tempor. Sed blandit arcu sed risus interdum fermentum.");
        listaTareas.add(tarea);
        tarea=new Tarea("Media","Comercial","Abierta","Fele Martinez","Presentar presupuesto Web","Integer ornare lorem urna, eget consequat ante lacinia et. Phasellus ut diam et diam euismod convallis");
        listaTareas.add(tarea);
    }
}
