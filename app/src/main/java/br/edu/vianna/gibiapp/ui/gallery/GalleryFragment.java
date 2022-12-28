package br.edu.vianna.gibiapp.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.edu.vianna.gibiapp.GibiDetailActivity;
import br.edu.vianna.gibiapp.R;
import br.edu.vianna.gibiapp.adapter.gibi.GibiAdapter;
import br.edu.vianna.gibiapp.databinding.FragmentGalleryBinding;
import br.edu.vianna.gibiapp.dto.GibiDTO;

public class GalleryFragment extends Fragment {

    private RecyclerView rvListaGibis;
    private ArrayList<GibiDTO> gibis;
    private String accessToken;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        gibis = (ArrayList<GibiDTO>) getArguments().getSerializable("gibis");

        accessToken = getArguments().getString("accessToken");

        GibiAdapter gibiAdapter = new GibiAdapter(gibis,viewGibiDetail, accessToken);

        rvListaGibis = getView().findViewById(R.id.rvListaGibis);

        rvListaGibis.setLayoutManager(new LinearLayoutManager(getContext()));

        rvListaGibis.setAdapter(gibiAdapter);

    }

    ActivityResultLauncher<Intent> viewGibiDetail = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == 10){



                    }
                }
            }
    );

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}