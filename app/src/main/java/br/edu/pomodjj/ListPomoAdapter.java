package br.edu.pomodjj;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

import br.edu.pomodjj.model.Ciclo;

public class ListPomoAdapter extends RecyclerView.Adapter<ListPomoAdapter.MyViewHolder> {
    private List<Ciclo> ciclos;
    private Ciclo ciclo;
    private Activity activity;
    private Bundle bundle = new Bundle();

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtTitulo;
        public TextView txtTempoDescanso;
        public TextView txtTempoTrabalho;
        public Button btnIniciar;
        public Button btnEditar;
        public MyViewHolder(View c) {
            super(c);
            txtTitulo = c.findViewById(R.id.txtTitulo);
            txtTempoDescanso = c.findViewById(R.id.txtTempoDescanso);
            txtTempoTrabalho = c.findViewById(R.id.txtTempoTrabalho);
            btnIniciar = c.findViewById(R.id.btnIniciar);
            btnEditar = c.findViewById(R.id.btnEditar);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ListPomoAdapter() {
    }

    public void setCiclos(List<Ciclo> ciclos) {
        this.ciclos = ciclos;
    }



    // Create new views (invoked by the layout manager)
    @Override
    public ListPomoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        // create a new view
        View c = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_ciclo, parent, false);

        MyViewHolder vh = new MyViewHolder(c);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.txtTitulo.setText(this.ciclos.get(position).getTitulo());
        holder.txtTempoDescanso.setText(this.ciclos.get(position).getTempoDescanso().toString());
        holder.txtTempoTrabalho.setText(this.ciclos.get(position).getTempoTrabalho().toString());

        holder.btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ciclo = ciclos.get(position);
                Intent it = new Intent(activity, Pomodoro_Tarefa.class);

                bundle.putSerializable("ciclo", ciclo);
                it.putExtras(bundle);

                activity.startActivity(it);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return this.ciclos.size();
    }
}
