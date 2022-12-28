package br.edu.vianna.gibiapp.dto;

import java.io.Serializable;

public class InputComentarioDTO implements Serializable {

    private int classificacao;
    private String comentario;

    public InputComentarioDTO() {
    }

    public InputComentarioDTO(int classificacao, String comentario) {
        this.classificacao = classificacao;
        this.comentario = comentario;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
