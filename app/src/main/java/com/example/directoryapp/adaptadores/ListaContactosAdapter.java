package com.example.directoryapp.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.directoryapp.R;
import com.example.directoryapp.entidades.Contactos;

import java.util.ArrayList;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {

    ArrayList<Contactos> listaContactos;

    public ListaContactosAdapter(ArrayList<Contactos> listaContactos){
        this.listaContactos = listaContactos;
    }
    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto, null,false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.viewNombre.setText(listaContactos.get(position).getNombre());
        holder.viewTelefono.setText(listaContactos.get(position).getTelefono());
        holder.viewEmail.setText(listaContactos.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewTelefono, viewEmail;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre=itemView.findViewById(R.id.viewNombre);
            viewTelefono=itemView.findViewById(R.id.viewTelefono);
            viewEmail=itemView.findViewById(R.id.viewEmail);
        }
    }
}
