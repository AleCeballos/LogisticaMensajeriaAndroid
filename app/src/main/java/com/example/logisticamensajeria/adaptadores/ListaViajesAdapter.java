package com.example.logisticamensajeria.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logisticamensajeria.Entidades.Viajes;
import com.example.logisticamensajeria.R;
import com.example.logisticamensajeria.Viajes.DetalleViaje;

import java.util.ArrayList;

public class ListaViajesAdapter extends RecyclerView.Adapter<ListaViajesAdapter.ViajeViewHolder>{

    ArrayList<Viajes> listadoViajes;

    public ListaViajesAdapter(ArrayList<Viajes> listadoViajes){

        this.listadoViajes = listadoViajes;
    }

    @NonNull
    @Override

    //diseño de cada elemento de la lista
    public ListaViajesAdapter.ViajeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_viaje, null, false);
        return new ListaViajesAdapter.ViajeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaViajesAdapter.ViajeViewHolder holder, int position) {

        holder.viewDireccion.setText(listadoViajes.get(position).getDireccion());
        holder.viewLocalidad.setText(listadoViajes.get(position).getLocalidad());
        holder.viewPrecio.setText(listadoViajes.get(position).getPrecio());
        holder.viewFecha.setText(listadoViajes.get(position).getFecha());
        holder.viewCliente.setText(listadoViajes.get(position).getCliente());
        holder.viewEmpleado.setText(listadoViajes.get(position).getEmpleado());


    }

    @Override
    public int getItemCount() {
        return listadoViajes.size();

    }

    public class ViajeViewHolder extends RecyclerView.ViewHolder {

        TextView viewDireccion;
        TextView viewLocalidad;
        TextView viewPrecio;
        TextView viewFecha;
        TextView viewCliente;
        TextView viewEmpleado;

        public ViajeViewHolder(@NonNull View itemView) {
            super(itemView);

            viewDireccion = itemView.findViewById(R.id.viewDireccion);
            viewLocalidad = itemView.findViewById(R.id.viewLocalidad);
            viewPrecio = itemView.findViewById(R.id.viewPrecio);
            viewFecha = itemView.findViewById(R.id.viewFecha);
            viewCliente = itemView.findViewById(R.id.viewCliente);
            viewEmpleado = itemView.findViewById(R.id.viewEmpleado);

            //ABRE LA VENTANA DE MODIFICAR


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetalleViaje.class);

                    intent.putExtra("ID",listadoViajes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }

}
