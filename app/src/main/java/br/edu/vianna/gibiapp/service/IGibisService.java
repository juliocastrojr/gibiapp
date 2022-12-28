package br.edu.vianna.gibiapp.service;

import java.util.List;

import br.edu.vianna.gibiapp.dto.ComentarioDTO;
import br.edu.vianna.gibiapp.dto.DevolveGibisDTO;
import br.edu.vianna.gibiapp.dto.GeneroDTO;
import br.edu.vianna.gibiapp.dto.GibiDTO;
import br.edu.vianna.gibiapp.dto.InputComentarioDTO;
import br.edu.vianna.gibiapp.dto.InputGibiDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IGibisService {

    @GET("gibi")
    public Call<List<GibiDTO>> getGibis(@Header("Authorization") String token);

    @GET("gibi/{id}")
    public Call<GibiDTO> getGibiId(@Header("Authorization") String token, @Path("id") int idGibi);

    @GET("gibi/{id}/comentarios")
    public Call<List<ComentarioDTO>> getComentariosGibi(@Header("Authorization") String token, @Path("id") int idGibi);

    @GET("gibi/genero")
    public Call<List<String>> getGeneros(@Header("Authorization") String token);

    @POST("gibi")
    public Call<GibiDTO> salvarGibi(@Header("Authorization") String token, @Body InputGibiDTO gibi);

    @PUT("gibi/{id}/comentario")
    public Call<GibiDTO> classificacaoComentarioGibi(@Header("Authorization") String token, @Path("id") int idGibi, @Body InputComentarioDTO comentarioClassificacao);
}
