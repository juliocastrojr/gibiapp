package br.edu.vianna.gibiapp.dto;

import java.io.Serializable;

public class ComentarioDTO implements Serializable {

    private String comentario;
    private String data;

    public ComentarioDTO() {
    }

    public ComentarioDTO(String comentario, String data) {
        this.comentario = comentario;
        this.data = data;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
