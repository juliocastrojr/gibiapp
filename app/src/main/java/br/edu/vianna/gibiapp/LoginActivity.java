package br.edu.vianna.gibiapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;

import java.util.concurrent.ExecutionException;

import br.edu.vianna.gibiapp.dto.UserDTO;
import br.edu.vianna.gibiapp.service.UserService;

public class LoginActivity extends AppCompatActivity {
    private TextInputLayout cpLogin, cpPassword;
    private Button btnEntrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        binding();

        btnEntrar.setOnClickListener(realizaLogin());

    }

    private View.OnClickListener realizaLogin() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                UserService userService = new UserService();

                UserDTO user = null;

                try {
                    user = userService.logar(cpLogin.getEditText().getText().toString(), cpPassword.getEditText().getText().toString());
                }catch (InterruptedException e){
                    e.printStackTrace();
                }catch (ExecutionException e){
                    e.printStackTrace();
                }

                if (user != null){

                    Intent i = new Intent();

                    i.putExtra("user", user);

                    setResult(20, i);

                    finish();
                }

            }
        };
    }

    private void binding() {
        cpLogin = findViewById(R.id.til_login);
        cpPassword = findViewById(R.id.til_senha);
        btnEntrar = findViewById(R.id.btnLogar);
    }
}