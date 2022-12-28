package br.edu.vianna.gibiapp.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

import java.util.List;

import br.edu.vianna.gibiapp.R;
import br.edu.vianna.gibiapp.config.RetrofitConfig;
import br.edu.vianna.gibiapp.databinding.FragmentHomeBinding;
import br.edu.vianna.gibiapp.dto.GibiDTO;
import br.edu.vianna.gibiapp.dto.InputGibiDTO;
import br.edu.vianna.gibiapp.service.IGibisService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {

    private String accessToken;
    private TextInputLayout cpNomeGibi, cpResenha, cpPersonagens, cpNumeroSerie, cpEditora;
    private TextInputLayout cpAnoPublicado, cpNumeroPaginas, cpDataCompra, cpPrecoPago,cpPrecoEstimado;
    private CheckBox cpEhNacional;
    private Spinner cpGenero;
    private Button btnSalvar;

    Retrofit re = new RetrofitConfig().getRetrofit();
    IGibisService s = re.create(IGibisService.class);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        accessToken = getArguments().getString("accessToken");

        binding();

        Call<List<String>> generos = s.getGeneros(accessToken);

        generos.enqueue(buscarGeneros());

        btnSalvar.setOnClickListener(salvarGibi());

    }

    private View.OnClickListener salvarGibi() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                InputGibiDTO inputGibiDTO = new InputGibiDTO(cpNomeGibi.getEditText().getText().toString(), cpResenha.getEditText().getText().toString(),
                        cpNumeroSerie.getEditText().getText().toString(), cpPersonagens.getEditText().getText().toString(),
                        cpEditora.getEditText().getText().toString(),cpGenero.getSelectedItem().toString(),cpEhNacional.isChecked()?"true":"false",
                        cpAnoPublicado.getEditText().getText().toString(),cpNumeroPaginas.getEditText().getText().toString(),
                        cpDataCompra.getEditText().getText().toString(),cpPrecoPago.getEditText().getText().toString(),
                        cpPrecoEstimado.getEditText().getText().toString());

                Call<GibiDTO> salvarGibiApi = s.salvarGibi(accessToken, inputGibiDTO);

                salvarGibiApi.enqueue(saveGibi());
            }
        };
    }

    private Callback<GibiDTO> saveGibi() {
        return new Callback<GibiDTO>() {
            @Override
            public void onResponse(Call<GibiDTO> call, Response<GibiDTO> response) {
                Log.i("msg", ""+response);

                Toast.makeText(getContext(), "Gibi cadastrado com sucesso!!!", Toast.LENGTH_SHORT).show();
                
                cpNomeGibi.getEditText().setText("");
                cpResenha.getEditText().setText("");
                cpPersonagens.getEditText().setText("");
                cpNumeroSerie.getEditText().setText("");
                cpEditora.getEditText().setText("");
                cpAnoPublicado.getEditText().setText("");
                cpNumeroPaginas.getEditText().setText("");
                cpDataCompra.getEditText().setText("");
                cpPrecoPago.getEditText().setText("");
                cpPrecoEstimado.getEditText().setText("");
            }

            @Override
            public void onFailure(Call<GibiDTO> call, Throwable t) {
                Log.i("msg", ""+t.getCause());
            }
        };
    }

    private void binding() {
        cpGenero = getView().findViewById(R.id.spGenerosGibi);
        cpNomeGibi = getView().findViewById(R.id.tilNomeGibi);
        cpResenha = getView().findViewById(R.id.tilResenhaGibi);
        cpPersonagens = getView().findViewById(R.id.tilPersonagensGibi);
        cpNumeroSerie = getView().findViewById(R.id.tilNumSerieGibi);
        cpEditora = getView().findViewById(R.id.tilEditoraGibi);
        cpAnoPublicado = getView().findViewById(R.id.tilAnoPublicadoGibi);
        cpNumeroPaginas = getView().findViewById(R.id.tilNumPaginasGibi);
        cpDataCompra = getView().findViewById(R.id.tilDataCompraGibi);
        cpPrecoPago = getView().findViewById(R.id.tilPrecoPagoGibi);
        cpPrecoEstimado = getView().findViewById(R.id.tilPrecoEstimadoGibi);
        cpEhNacional = getView().findViewById(R.id.cbNacional);
        btnSalvar = getView().findViewById(R.id.btnSalvarGibi);
    }

    private Callback<List<String>> buscarGeneros() {
        return new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                Log.i("msg", ""+response);
                if (response.isSuccessful()){

                    List<String> lgeneros = response.body();

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_list_item_1, lgeneros);

                    cpGenero.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.i("msg", ""+t.getCause());

            }
        };
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}