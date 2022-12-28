package br.edu.vianna.gibiapp.holder.gibi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.edu.vianna.gibiapp.GibiDetailActivity;
import br.edu.vianna.gibiapp.R;
import br.edu.vianna.gibiapp.config.RetrofitConfig;
import br.edu.vianna.gibiapp.dto.GibiDTO;
import br.edu.vianna.gibiapp.service.IGibisService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GibiHolder extends RecyclerView.ViewHolder {

    private TextView cpNomeGibiLista;
    private TextView cpAnoGibiLista;
    private TextView cpEditoraGibiLista;
    private TextView cpGeneroGibiLista;
    private GibiDTO gibiDTO;
    private ActivityResultLauncher<Intent> result;
    private int position;
    private String accessToken;
    Retrofit r = new RetrofitConfig().getRetrofit();
    IGibisService ser = r.create(IGibisService.class);

    public GibiHolder(@NonNull View itemView, ActivityResultLauncher<Intent> result, String accessToken) {
        super(itemView);
        this.result = result;
        this.accessToken = accessToken;

        cpNomeGibiLista = itemView.findViewById(R.id.tvNomeGibiLista);
        cpAnoGibiLista = itemView.findViewById(R.id.tvAnoGibiLista);
        cpEditoraGibiLista = itemView.findViewById(R.id.tvEditora);
        cpGeneroGibiLista = itemView.findViewById(R.id.tvGeneroGibiLista);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<GibiDTO> callGibiDto = ser.getGibiId(accessToken, gibiDTO.getId());

                callGibiDto.enqueue(new Callback<GibiDTO>() {
                    @Override
                    public void onResponse(Call<GibiDTO> call, Response<GibiDTO> response) {
                        Log.i("mensagem", ""+response);

                        GibiDTO gibiDTO = response.body();

                        Intent i = new Intent(view.getContext(), GibiDetailActivity.class);

                        i.putExtra("gibi", gibiDTO);
                        i.putExtra("position", position);
                        i.putExtra("accessToken", accessToken);

                        result.launch(i);

                    }

                    @Override
                    public void onFailure(Call<GibiDTO> call, Throwable t) {
                        Log.i("erro",""+t.getCause());
                    }
                });



            }
        });
    }

    public TextView getCpNomeGibiLista() {
        return cpNomeGibiLista;
    }

    public TextView getCpAnoGibiLista() {
        return cpAnoGibiLista;
    }

    public TextView getCpEditoraGibiLista() {
        return cpEditoraGibiLista;
    }

    public TextView getCpGeneroGibiLista() {
        return cpGeneroGibiLista;
    }

    public void preenche (GibiDTO gibiDTO, int position){
        this.gibiDTO = gibiDTO;
        this.position = position;

        getCpNomeGibiLista().setText(gibiDTO.getNome());
        getCpAnoGibiLista().setText(""+gibiDTO.getAnoPublicado());
        getCpEditoraGibiLista().setText(gibiDTO.getEditora());
        getCpGeneroGibiLista().setText(gibiDTO.getGenero());
    }
}
