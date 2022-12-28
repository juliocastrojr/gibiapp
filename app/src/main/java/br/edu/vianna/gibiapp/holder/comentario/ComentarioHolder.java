package br.edu.vianna.gibiapp.holder.comentario;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.edu.vianna.gibiapp.R;
import br.edu.vianna.gibiapp.dto.ComentarioDTO;

public class ComentarioHolder extends RecyclerView.ViewHolder {

    private TextView cpComentarioLista;
    private TextView cpDataComentarioLista;
    private ComentarioDTO comentarioDTO;
    private int position;

    public ComentarioHolder(@NonNull View itemView) {
        super(itemView);
        cpComentarioLista = itemView.findViewById(R.id.tvComentarioGibiLista);
        cpDataComentarioLista = itemView.findViewById(R.id.tvDataComentarioLista);
    }

    public TextView getCpComentarioLista() {
        return cpComentarioLista;
    }

    public TextView getCpDataComentarioLista() {
        return cpDataComentarioLista;
    }

    public void preenche(ComentarioDTO comentarioDTO, int position){
        this.comentarioDTO = comentarioDTO;
        this.position = position;

        getCpComentarioLista().setText(comentarioDTO.getComentario());
        getCpDataComentarioLista().setText(comentarioDTO.getData());
    }
}
