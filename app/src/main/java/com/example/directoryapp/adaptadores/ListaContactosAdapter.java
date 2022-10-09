package com.example.directoryapp.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.directoryapp.R;
import com.example.directoryapp.VerActivity;
import com.example.directoryapp.entidades.Contactos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {

    ArrayList<Contactos> listaContactos;
    ArrayList<Contactos> listaOriginal;

    public ListaContactosAdapter(ArrayList<Contactos> listaContactos){
        this.listaContactos = listaContactos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaContactos);
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


    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if(longitud==0){
            listaContactos.clear();
            listaContactos.addAll(listaOriginal);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Contactos> coleccion = listaContactos.stream().filter(i -> i.getNombre().toLowerCase()
                        .contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                listaContactos.clear();
                listaContactos.addAll(coleccion);
            } else {
                for (Contactos c:listaOriginal){
                    if(c.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                        listaContactos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    public void filtradoPorClas(int clas){
        if(clas==0){
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Contactos> coleccion = listaContactos.stream().filter(i -> i.isConsultoria() == 1).collect(Collectors.toList());
                listaContactos.clear();
                listaContactos.addAll(coleccion);
            }else{
                for (Contactos c:listaOriginal){
                    if (c.isConsultoria()==1){
                        listaContactos.add(c);
                    }
                }
            }
        } else if (clas==1) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Contactos> coleccion = listaContactos.stream().filter(i -> i.isDesarrollo() == 1).collect(Collectors.toList());
                listaContactos.clear();
                listaContactos.addAll(coleccion);
            }else{
                for (Contactos c:listaOriginal){
                    if (c.isDesarrollo()==1){
                        listaContactos.add(c);
                    }
                }
            }
        }else if (clas==2) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Contactos> coleccion = listaContactos.stream().filter(i -> i.isFabrica() == 1).collect(Collectors.toList());
                listaContactos.clear();
                listaContactos.addAll(coleccion);
            }else{
                for (Contactos c:listaOriginal){
                    if (c.isFabrica()==1){
                        listaContactos.add(c);
                    }
                }
            }
        }else  {
            listaContactos.clear();
            listaContactos.addAll(listaOriginal);
        }

        notifyDataSetChanged();
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

            itemView.setOnClickListener(view -> {
                Context context = view.getContext();
                Intent intent = new Intent(context, VerActivity.class);
                intent.putExtra("ID", listaContactos.get(getAdapterPosition()).getId());
                context.startActivity(intent);
            });
        }
    }
}
