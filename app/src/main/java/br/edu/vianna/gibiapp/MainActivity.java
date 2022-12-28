package br.edu.vianna.gibiapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.List;

import br.edu.vianna.gibiapp.config.RetrofitConfig;
import br.edu.vianna.gibiapp.databinding.ActivityMainBinding;
import br.edu.vianna.gibiapp.dto.DevolveGibisDTO;
import br.edu.vianna.gibiapp.dto.GibiDTO;
import br.edu.vianna.gibiapp.dto.UserDTO;
import br.edu.vianna.gibiapp.service.IGibisService;
import br.edu.vianna.gibiapp.ui.gallery.GalleryFragment;
import br.edu.vianna.gibiapp.ui.home.HomeFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private DrawerLayout drawer;
    NavigationView navigationView;
    UserDTO user;
    Retrofit retrofit = new RetrofitConfig().getRetrofit();
    IGibisService service = retrofit.create(IGibisService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        drawer = binding.drawerLayout;
        navigationView = binding.navView;
        Toolbar tool = binding.appBarMain.toolbar;

        navigationView.setNavigationItemSelectedListener(clickNavigator());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, tool,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );

        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }



    ActivityResultLauncher<Intent> callPageLogin = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == 20){

                        user = (UserDTO) result.getData().getExtras().getSerializable("user");

                        PrefShared();

                        habilitaDesabilitaMenu(true, R.id.nav_home, R.id.nav_gallery, R.id.nav_sair);
                        habilitaDesabilitaMenu(false, R.id.nav_login);

                         Toast.makeText(getApplicationContext(), "Login realizado com sucesso!!!", Toast.LENGTH_SHORT).show();

                         changeHeader();

                    } else if (result.getResultCode() == 0){

                        user = null;

                        habilitaDesabilitaMenu(false, R.id.nav_home, R.id.nav_gallery, R.id.nav_sair);
                        habilitaDesabilitaMenu(true, R.id.nav_login);

                        Toast.makeText(getApplicationContext(), "Deslogado!", Toast.LENGTH_SHORT).show();

                        changeHeader();

                    }

                }
            }
    );

    private NavigationView.OnNavigationItemSelectedListener clickNavigator() {
        return new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if (item.getItemId() == R.id.nav_home){
                    HomeFragment home = new HomeFragment();

                    Bundle arg = new Bundle();

                    arg.putString("accessToken", user.getAccess_token());

                    home.setArguments(arg);

                    changeFragment(home);

                } else if (item.getItemId() == R.id.nav_login) {

                    Intent itn = new Intent(getApplicationContext(), LoginActivity.class);

                    callPageLogin.launch(itn);

                } else if (item.getItemId() == R.id.nav_sair){

                    user = null;

                    Intent itn = new Intent(getApplicationContext(), LoginActivity.class);

                    callPageLogin.launch(itn);

                } else if (item.getItemId() == R.id.nav_gallery){

                    Call<List<GibiDTO>> gibis = service.getGibis(user.getAccess_token());

                    gibis.enqueue(new Callback<List<GibiDTO>>() {
                        @Override
                        public void onResponse(Call<List<GibiDTO>> call, Response<List<GibiDTO>> response) {
                            Log.i("mensagem", ""+response);
                            if (response.isSuccessful()){

                                List<GibiDTO> lgibi = response.body();

                                Bundle args = new Bundle();

                                args.putSerializable("gibis", (Serializable) lgibi);
                                args.putSerializable("accessToken", user.getAccess_token());

                                GalleryFragment gallery = new GalleryFragment();

                                gallery.setArguments(args);

                                changeFragment(gallery);

                            }

                        }

                        @Override
                        public void onFailure(Call<List<GibiDTO>> call, Throwable t) {
                            Log.i("erro",""+t.getCause());
                        }
                    });


                }

                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        };
    }

    private void PrefShared() {
        SharedPreferences preferences = getSharedPreferences("GibApp", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("accessToken", user.getAccess_token());
        editor.commit();
    }

    private void changeFragment(Fragment frag) {

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.nav_host_fragment_content_main, frag);

        transaction.commit();

    }

    private void habilitaDesabilitaMenu(boolean estado, int ...idMenu){

        for (int id_menu : idMenu){
            navigationView.getMenu().findItem(id_menu).setVisible(estado);
        }

    }

    private void changeHeader(){
        View header = navigationView.getHeaderView(0);
        TextView cpNomeHeader = header.findViewById(R.id.tvNomeHeader);
        TextView cpEmailHeader = header.findViewById(R.id.tvEmailHeader);

        if (user != null){
            cpNomeHeader.setText(user.getNome());
            cpEmailHeader.setText(user.getEmail());
        }else{
            cpNomeHeader.setText("Bem-vindo ao GibiApp!");
            cpEmailHeader.setText("Realize seu login");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}