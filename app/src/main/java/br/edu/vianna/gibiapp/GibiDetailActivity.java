package br.edu.vianna.gibiapp;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.edu.vianna.gibiapp.adapter.comentario.ComentarioAdapter;
import br.edu.vianna.gibiapp.config.RetrofitConfig;
import br.edu.vianna.gibiapp.dto.ComentarioDTO;
import br.edu.vianna.gibiapp.dto.GibiDTO;
import br.edu.vianna.gibiapp.service.IGibisService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GibiDetailActivity extends AppCompatActivity {

    private GibiDTO gibiDTO;
    private int position;
    private ArrayList<ComentarioDTO> comentarios;
    private String accessToken;
    private Button btnPageCadCom;

    Retrofit ret = new RetrofitConfig().getRetrofit();
    IGibisService ser = ret.create(IGibisService.class);
    RecyclerView rv;

    private TextView cpNomeDetalhe, cpResenhaDetalhe, cpNumeroSerieDetalhe,
            cpPersonagensDetalhe, cpEditoraDetalhe, cpGeneroDetalhe, cpNacionalDetalhe,
            cpClassificacaoDetalhe, cpAnoPublicacaoDetalhe, cpNumPaginasDetalhe,
            cpTotalComentariosDetalhes, cpPrecoPagoDetalhe, cpPrecoEstimadoDetalhe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gibi_detail);

        gibiDTO = (GibiDTO) getIntent().getExtras().getSerializable("gibi");

        position = getIntent().getExtras().getInt("position");

        accessToken = getIntent().getExtras().getString("accessToken");

        binding();

        preencheDetalhe();

        Call<List<ComentarioDTO>> lcomentario = ser.getComentariosGibi(accessToken, gibiDTO.getId());

        btnPageCadCom.setOnClickListener(chamaPagCadCom());

        lcomentario.enqueue(new Callback<List<ComentarioDTO>>() {
            @Override
            public void onResponse(Call<List<ComentarioDTO>> call, Response<List<ComentarioDTO>> response) {
                // Log.i("mensagem", "onResponse: "+response);
                if (response.isSuccessful()){

                    List<ComentarioDTO> lComent = response.body();

                    ComentarioAdapter ComentAdap = new ComentarioAdapter((ArrayList<ComentarioDTO>) lComent);

                    LinearLayoutManager layout = new LinearLayoutManager(getApplicationContext());

                    rv.setLayoutManager(layout);

                    rv.setAdapter(ComentAdap);

                    for (ComentarioDTO com : lComent){
                        gibiDTO.addComentario(com);
                    }

                    cpTotalComentariosDetalhes.setText(""+gibiDTO.getComentarios().size());
                }
            }

            @Override
            public void onFailure(Call<List<ComentarioDTO>> call, Throwable t) {
                // Log.i("erro", "onResponse: "+t.getCause());
            }
        });

    }

    private View.OnClickListener chamaPagCadCom() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent itn = new Intent(getApplicationContext(), CadComentarioActivity.class);

                itn.putExtra("accessToken", accessToken);

                itn.putExtra("idGibi", gibiDTO.getId());

                viewCadastroComentario.launch(itn);
            }
        };
    }

    ActivityResultLauncher<Intent> viewCadastroComentario = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == 10){


                    }
                }
            }
    );

    private void preencheDetalhe() {
        cpNomeDetalhe.setText(gibiDTO.getNome());
        cpResenhaDetalhe.setText(gibiDTO.getResenha());
        cpNumeroSerieDetalhe.setText(gibiDTO.getNumeroSerie());
        cpPersonagensDetalhe.setText(gibiDTO.getPersonagens());
        cpEditoraDetalhe.setText(gibiDTO.getEditora());
        cpGeneroDetalhe.setText(gibiDTO.getGenero());
        cpNacionalDetalhe.setText(gibiDTO.ehNacional()?"Nacional":"Internacional");
        cpClassificacaoDetalhe.setText(""+gibiDTO.getClassificacao()+" estrela(s)");
        cpAnoPublicacaoDetalhe.setText(""+gibiDTO.getAnoPublicado());
        cpNumPaginasDetalhe.setText(""+gibiDTO.getNumPaginas());
        cpPrecoPagoDetalhe.setText("R$ "+gibiDTO.getPrecoPago());
        cpPrecoEstimadoDetalhe.setText("R$ "+gibiDTO.getPrecoEstimado());
    }

    private void binding() {
        cpNomeDetalhe = findViewById(R.id.tvGibiNomeDetalhe);
        cpResenhaDetalhe = findViewById(R.id.tvResenhaGibiDetalhe);
        cpNumeroSerieDetalhe = findViewById(R.id.tvNumeroSerieDetalhe);
        cpPersonagensDetalhe = findViewById(R.id.tvPersonagensDetalhe);
        cpEditoraDetalhe = findViewById(R.id.tvEditoraDetalhe);
        cpGeneroDetalhe = findViewById(R.id.tvGeneroDetalhe);
        cpNacionalDetalhe = findViewById(R.id.tvNacionalDetalhe);
        cpClassificacaoDetalhe = findViewById(R.id.tvClassificacaoDetalhe);
        cpAnoPublicacaoDetalhe = findViewById(R.id.tvAnoPublicacaoDetalhe);
        cpNumPaginasDetalhe = findViewById(R.id.tvNumPaginasDetalhe);
        cpTotalComentariosDetalhes = findViewById(R.id.tvTotComentariosDetalhe);
        cpPrecoPagoDetalhe = findViewById(R.id.tvPrecoPagoDetalhe);
        cpPrecoEstimadoDetalhe = findViewById(R.id.tvPrecoEstimadoDetalhe);
        btnPageCadCom = findViewById(R.id.btnCadCom);
        rv = findViewById(R.id.rvListaComentarios);
    }
}