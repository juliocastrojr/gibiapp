package br.edu.vianna.gibiapp.dto;

import java.io.Serializable;

public class GeneroDTO implements Serializable {

    public String genero;

    public GeneroDTO() {
    }

    public GeneroDTO(String genero) {
        this.genero = genero;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
