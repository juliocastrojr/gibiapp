package br.edu.vianna.gibiapp.adapter.comentario;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.edu.vianna.gibiapp.R;
import br.edu.vianna.gibiapp.dto.ComentarioDTO;
import br.edu.vianna.gibiapp.holder.comentario.ComentarioHolder;

public class ComentarioAdapter extends RecyclerView.Adapter<ComentarioHolder> {

    private ArrayList<ComentarioDTO> comentarios;

    public ComentarioAdapter(ArrayList<ComentarioDTO> comentarios) {
        this.comentarios = comentarios;
    }

    @NonNull
    @Override
    public ComentarioHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ComentarioHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.linha_comentario, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioHolder holder, int position) {
        holder.preenche(comentarios.get(position), position);
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }
}
