package net.iessochoa.danielabellan.practica4.adapters;
import net.iessochoa.danielabellan.practica4.R;
import net.iessochoa.danielabellan.practica4.model.Tarea;
import net.iessochoa.danielabellan.practica4.model.TareaViewModel;

import java.util.List;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class TareasAdapter extends RecyclerView.Adapter<TareasAdapter.TareaViewHolder>{
    private List<Tarea> miListaDeTareas;
    private OnItemClickBorrarListener listenerBorrar;
    private OnItemClickEditarListener listenerEditar;

    @NonNull
    @Override
    public TareasAdapter.TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //IMPORTANTE!!! Observad cómo estamos pasando el Layout que representa cada uno de los elementos
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TareasAdapter.TareaViewHolder holder, int position) {
        if (miListaDeTareas != null) {
            //Recuperamos la tarea que deseamos mostrar
            final Tarea tarea = miListaDeTareas.get(position);

            //Procedemos a mostrar los datos
            holder.tvTareaTecnico.setText(tarea.getTecnico());
            holder.tvTareaResumen.setText(tarea.getResumen());

            //En función del estado que tenga la Tarea, mostraremos un icono correspondiente al mismo
            switch (tarea.getEstado()){
                case "Abierta":
                    holder.ivTareaEstado.setImageResource(R.drawable.ic_e_abierta_foreground);
                    break;
                case "En curso":
                    holder.ivTareaEstado.setImageResource(R.drawable.ic_e_proceso_foreground);
                    break;
                case "Terminada":
                    holder.ivTareaEstado.setImageResource(R.drawable.ic_e_terminada_foreground);
                    break;
            }

            if(tarea.getPrioridad().equals("Alta")){
                holder.ctlItem.setBackgroundResource(R.color.colorImportante);
            }else{
                holder.ctlItem.setBackgroundColor(Color.TRANSPARENT);
            }
        }
    }

    @Override
    public int getItemCount() {
        if (miListaDeTareas != null) {
            return miListaDeTareas.size();
        }
        else return 0;
    }

    //Cuando la lista de Tareas se vea modificada, actualizaremos el RecyclerView correspondiente
    public void setMiListaTareas(List<Tarea> tareas){
        miListaDeTareas = tareas;
        notifyDataSetChanged();
    }

    public class TareaViewHolder extends RecyclerView.ViewHolder {
        private ConstraintLayout ctlItem;
        private TextView tvTareaResumen;
        private TextView tvTareaTecnico;
        private ImageView ivTareaEstado;
        private ImageView ivTareaEditar;
        private ImageView ivTareaBorrar;

        public TareaViewHolder(@NonNull View itemView) {
            super(itemView);
            ctlItem = itemView.findViewById(R.id.ctlItem);
            tvTareaResumen = itemView.findViewById(R.id.tvTareaResumen);
            tvTareaTecnico = itemView.findViewById(R.id.tvTareaTecnico);
            ivTareaEstado = itemView.findViewById(R.id.ivTareaEstado);
            ivTareaEditar = itemView.findViewById(R.id.ivTareaEditar);
            ivTareaBorrar = itemView.findViewById(R.id.ivTareaBorrar);

            ivTareaBorrar.setOnClickListener(view -> {
                if (listenerBorrar != null)
                    listenerBorrar.onItemBorrarClick(miListaDeTareas.get(TareaViewHolder.this.getAdapterPosition()));
            });

            ivTareaEditar.setOnClickListener(view -> {
                if(listenerEditar != null)
                    listenerEditar.onItemEditarClick(miListaDeTareas.get(TareaViewHolder.this.getAdapterPosition()));
            });
        }
    }

    public interface OnItemClickBorrarListener {
        void onItemBorrarClick(Tarea tarea);
    }

    public interface OnItemClickEditarListener {
        void onItemEditarClick(Tarea tarea);
    }

    public void setOnClickBorrarListener(OnItemClickBorrarListener listener) {
        this.listenerBorrar = listener;
    }
    public void setOnClickEditarListener(OnItemClickEditarListener listener) {
        this.listenerEditar = listener;
    }

}
