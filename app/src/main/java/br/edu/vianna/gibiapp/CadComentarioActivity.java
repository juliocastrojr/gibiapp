package br.edu.vianna.gibiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import br.edu.vianna.gibiapp.config.RetrofitConfig;
import br.edu.vianna.gibiapp.dto.GibiDTO;
import br.edu.vianna.gibiapp.dto.InputComentarioDTO;
import br.edu.vianna.gibiapp.service.IGibisService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CadComentarioActivity extends AppCompatActivity {
    private TextInputLayout cpClassificacao, cpComentarioGibi;
    private String accessToken;
    private int idGibi;

    Button btnCadComent;
    Retrofit ret = new RetrofitConfig().getRetrofit();
    IGibisService ser = ret.create(IGibisService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_comentario);

        binding();

        accessToken = getIntent().getExtras().getString("accessToken");

        idGibi = getIntent().getExtras().getInt("idGibi");

        btnCadComent.setOnClickListener(salvarComentario());
    }

    private View.OnClickListener salvarComentario() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputComentarioDTO inputComentarioDTO = new InputComentarioDTO(Integer.parseInt(cpClassificacao.getEditText().getText().toString()),
                        cpComentarioGibi.getEditText().getText().toString());

                Call<GibiDTO> saveComentarioClassificacao = ser.classificacaoComentarioGibi(accessToken, idGibi, inputComentarioDTO);

                saveComentarioClassificacao.enqueue(new Callback<GibiDTO>() {
                    @Override
                    public void onResponse(Call<GibiDTO> call, Response<GibiDTO> response) {
                        Log.i("msg", ""+response);
                        if (response.isSuccessful()){

                            GibiDTO gibi = response.body();

                            Intent i = new Intent();

                            i.putExtra("gibiDTO", gibi);

                            setResult(10, i);

                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<GibiDTO> call, Throwable t) {
                        Log.i("msg", ""+t.getCause());
                    }
                });

            }
        };
    }

    private void binding() {
        cpClassificacao = findViewById(R.id.tilClassificacaoCad);
        cpComentarioGibi = findViewById(R.id.tilComentarioCad);
        btnCadComent = findViewById(R.id.btnCadComentario);
    }
}