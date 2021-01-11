package es.rbp.reordenar_recyclerview_arrastrando;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * @author Ricardo Bordería Pi
 * <p>
 * Adaptador del {@link RecyclerView} en el que se carga la información de las tareas
 */
public class Adaptador extends RecyclerView.Adapter<Adaptador.MyHolder> {

    /**
     * @author Ricardo Bordería Pi
     * <p>
     * {@link androidx.recyclerview.widget.RecyclerView.ViewHolder} de {@link Adaptador} que contiene las vistas del RecyclerView
     */
    public static class MyHolder extends RecyclerView.ViewHolder {

        /**
         * {@link TextView} con el nombre de la tarea
         */
        TextView lblNombreTarea;
        /**
         * {@link TextView} con la prioridad de la tarea. Siendo 1 la prioridad máxima
         */
        TextView lblPrioridad;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            lblNombreTarea = itemView.findViewById(R.id.lblNombreTarea);

            lblPrioridad = itemView.findViewById(R.id.lblPrioridad);
        }
    }

    /**
     * Lista con las tareas
     */
    private List<String> listaTareas;

    /**
     * Constructor de la clase
     *
     * @param listaTareas lista con las tareas
     */
    public Adaptador(List<String> listaTareas) {
        this.listaTareas = listaTareas;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout, parent, false);
        return new MyHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        String tarea = listaTareas.get(position);

        // Nombre de la tarea
        holder.lblNombreTarea.setText(tarea);

        // Se establece la prioridad de la tarea. Cuanto más arriba esté, más prioridad tiene
        holder.lblPrioridad.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }
}
