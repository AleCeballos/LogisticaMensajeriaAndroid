package com.example.logisticamensajeria.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logisticamensajeria.Entidades.Empleados;
import com.example.logisticamensajeria.Empleados.DetalleEmpleado;
import com.example.logisticamensajeria.R;

import java.util.ArrayList;

public class ListaEmpleadosAdapter extends RecyclerView.Adapter<ListaEmpleadosAdapter.EmpleadoViewHolder> {


    ArrayList<Empleados> listadoEmpleados;

    public ListaEmpleadosAdapter(ArrayList<Empleados> listadoEmpleados){

        this.listadoEmpleados = listadoEmpleados;
    }

    @NonNull
    @Override

    //diseño de cada elemento de la lista
    public EmpleadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_empleado, null, false);
      return new EmpleadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpleadoViewHolder holder, int position) {

        holder.viewNombre.setText(listadoEmpleados.get(position).getNombre());
        holder.viewCuil.setText(listadoEmpleados.get(position).getCuil());
        holder.viewDireccion.setText(listadoEmpleados.get(position).getDireccion());
        holder.viewFecha.setText(listadoEmpleados.get(position).getFecha());
        holder.viewTelefono.setText(listadoEmpleados.get(position).getTelefono());


    }

    @Override
    public int getItemCount() {
      return listadoEmpleados.size();

    }

    public class EmpleadoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre;
        TextView viewCuil;
        TextView viewDireccion;
        TextView viewFecha;
        TextView viewTelefono;

        public EmpleadoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewCuil = itemView.findViewById(R.id.viewCuil);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);
            viewFecha = itemView.findViewById(R.id.viewFecha);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);


            //ABRE LA VENTANA DE MODIFICAR


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetalleEmpleado.class);

                    intent.putExtra("ID",listadoEmpleados.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }
}
