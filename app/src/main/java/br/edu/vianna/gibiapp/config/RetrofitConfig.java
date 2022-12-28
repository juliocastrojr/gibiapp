package br.edu.vianna.gibiapp.config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private Retrofit retrofit;

    public RetrofitConfig(){

        retrofit = new Retrofit.Builder().baseUrl("https://api.gibi.davesmartins.com.br/").addConverterFactory(GsonConverterFactory.create()).build();
    }

    public Retrofit getRetrofit(){
        return retrofit;
    }

}
