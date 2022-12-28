package br.edu.vianna.gibiapp.adapter.gibi;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.edu.vianna.gibiapp.R;
import br.edu.vianna.gibiapp.dto.GibiDTO;
import br.edu.vianna.gibiapp.holder.gibi.GibiHolder;

public class GibiAdapter extends RecyclerView.Adapter<GibiHolder> {

    private ArrayList<GibiDTO> gibis;
    private ActivityResultLauncher<Intent> result;
    private String accessToken;

    public GibiAdapter(ArrayList<GibiDTO> gibis, ActivityResultLauncher<Intent> result, String accessToken) {
        this.gibis = gibis;
        this.result = result;
        this.accessToken = accessToken;
    }

    @NonNull
    @Override
    public GibiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GibiHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.linha_gibis, parent, false), result, accessToken);
    }

    @Override
    public void onBindViewHolder(@NonNull GibiHolder holder, int position) {
        holder.preenche(gibis.get(position), position);
    }

    @Override
    public int getItemCount() {
        return gibis.size();
    }
}
