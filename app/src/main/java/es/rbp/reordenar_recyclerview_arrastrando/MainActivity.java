package es.rbp.reordenar_recyclerview_arrastrando;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Ricardo Bordería Pi
 * <p>
 * Esta clase tiene un {@link RecyclerView} con una lista de tareas ordenada por el nivel de prioridad.
 * Cuanto más arriba estén más prioridad tienen.
 * Para cambiar la posición de los elementos en el {@link RecyclerView} hay que mantener pulsado y deslizar el elemento
 * deseado hasta su nueva posición.
 * <p>
 * Para detectar este cambio se implementa la clase abstracta {@link androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback}.
 * Con el método {@link androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback#onMove(RecyclerView, RecyclerView.ViewHolder, RecyclerView.ViewHolder)}
 * se cambia el orden de los elementos.
 * Y con el método {@link androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback#canDropOver(RecyclerView, RecyclerView.ViewHolder, RecyclerView.ViewHolder)}
 * se actualizan los datos del {@link RecyclerView}
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Lista con las tareas
     */
    private List<String> listaTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaTareas = new ArrayList<>();
        listaTareas.add("Fregar la Cocina");
        listaTareas.add("Fregar los Cacharros");
        listaTareas.add("Fregar el Baño");
        listaTareas.add("Sacar la Basura");
        listaTareas.add("Barrer la Cocina");
        listaTareas.add("Limpiar el Suelo");
        listaTareas.add("Fregar los Platos");
        listaTareas.add("Ordenar el Cuarto");
        listaTareas.add("Hacer la Comida");
        listaTareas.add("Hacer la Compra");

        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        Adaptador adaptador = new Adaptador(listaTareas);

        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN, 0) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            // Posición inicial del elemento del RecyclerView
            int posicionInicial = viewHolder.getAdapterPosition();
            // Posición a la que se ha desplazado el elemento del RecyclerView
            int posicionFinal = target.getAdapterPosition();

            // Coloca el elemento en la posición a la que se ha desplazado
            Collections.swap(listaTareas, posicionInicial, posicionFinal);

            // Avisa al adaptador que se ha trasladado el elemenro
            recyclerView.getAdapter().notifyItemMoved(posicionInicial, posicionFinal);

            return true;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        }

        @Override
        public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
            // Una vez se ha colocado el elemento en la posición deseada, el adaptador actualiza la prioridad de la tarea
            recyclerView.getAdapter().notifyDataSetChanged();
        }
    };
}