package com.matheussilas.listadetarefas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matheussilas.listadetarefas.model.Assignment;
import com.matheussilas.listadetarefas.R;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Assignment> listAssignment;

    public Adapter(List<Assignment> list) {
        this.listAssignment = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_adapter, parent, false);
        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Assignment assignment = listAssignment.get(position);
        holder.assignment.setText(assignment.getNameAssignment());
    }

    @Override
    public int getItemCount() {
        return this.listAssignment.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView assignment;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            assignment = itemView.findViewById(R.id.txtAssignment);
        }
    }
}
