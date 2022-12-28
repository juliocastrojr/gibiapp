package br.edu.vianna.gibiapp.service.task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import br.edu.vianna.gibiapp.dto.UserDTO;
import br.edu.vianna.gibiapp.dto.inputLoginDTO;

public class LoginTask extends AsyncTask<inputLoginDTO, Void, UserDTO> {
    @Override
    protected UserDTO doInBackground(inputLoginDTO... inputLoginDTOS) {
        //Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        //String data = "";

        StringBuilder result = new StringBuilder();

        HttpsURLConnection httpsUrlConnection = null;

        try {
            httpsUrlConnection = (HttpsURLConnection) new URL("https://api.gibi.davesmartins.com.br/oauth/token").openConnection();

            // Sets the request method for the URL
            httpsUrlConnection.setRequestMethod("POST");
            //Login e senha da aplicação
            httpsUrlConnection.setRequestProperty("Authorization","Basic cHJvdmE6cHJvdmE=");


            // Tells the URL that I am sending a POST request body
            httpsUrlConnection.setDoOutput(true);
            // Tells the URL that I want to read the response data
            httpsUrlConnection.setDoInput(true);

            // JSON object for the REST API

            String param = "grant_type=password&username="+inputLoginDTOS[0].getLogin()+"&password="+inputLoginDTOS[0].getSenha()+"";

            JSONObject jsonParam = new JSONObject();

            Log.i("parametro", param);

            // To write primitive Java data types to an output stream in a portable way
            DataOutputStream wr = new DataOutputStream(httpsUrlConnection.getOutputStream());
            // Writes out a byte to the underlying output stream of the data posted from .execute function
            wr.writeBytes(param);
            // Flushes the jsonParam to the output stream
            wr.flush();
            wr.close();

            // // Representing the input stream
            InputStream in = new BufferedInputStream(httpsUrlConnection.getInputStream());

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            // reading the input stream / response from the url
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Disconnects socket after using
            if (httpsUrlConnection != null) {
                httpsUrlConnection.disconnect();
            }
        }

        Log.i("TAG", result.toString());

        //Transforma String JSON para objeto -- importar biblioteca GSON
        Gson g = new Gson();
        UserDTO obj = g.fromJson(result.toString(), UserDTO.class);

        return obj;
    }
}
