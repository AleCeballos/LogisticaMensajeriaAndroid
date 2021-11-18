package com.example.logisticamensajeria.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.logisticamensajeria.Clientes.DetalleCliente;
import com.example.logisticamensajeria.Entidades.Clientes;
import com.example.logisticamensajeria.R;

import java.util.ArrayList;

public class ListaClientesAdapter extends RecyclerView.Adapter<ListaClientesAdapter.ClienteViewHolder>{

    ArrayList<Clientes> listadoClientes;

    public ListaClientesAdapter(ArrayList<Clientes> listadoClientes){

        this.listadoClientes = listadoClientes;
    }

    @NonNull
    @Override

    //diseño de cada elemento de la lista
    public ClienteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_cliente, null, false);
        return new ClienteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaClientesAdapter.ClienteViewHolder holder, int position) {

        holder.viewNombre.setText(listadoClientes.get(position).getNombre());
        holder.viewCuit.setText(listadoClientes.get(position).getCuit());
        holder.viewDireccion.setText(listadoClientes.get(position).getDireccion());
        holder.viewFecha.setText(listadoClientes.get(position).getFecha());
        holder.viewTelefono.setText(listadoClientes.get(position).getTelefono());


    }

    @Override
    public int getItemCount() {
        return listadoClientes.size();

    }

    public class ClienteViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre;
        TextView viewCuit;
        TextView viewDireccion;
        TextView viewFecha;
        TextView viewTelefono;

        public ClienteViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewCuit = itemView.findViewById(R.id.viewCuit);
            viewDireccion = itemView.findViewById(R.id.viewDireccion);
            viewFecha = itemView.findViewById(R.id.viewFecha);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);


            //ABRE LA VENTANA DE MODIFICAR


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetalleCliente.class);

                    intent.putExtra("ID",listadoClientes.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });

        }
    }

}
