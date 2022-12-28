package br.edu.vianna.gibiapp.dto;

import java.io.Serializable;

public class DevolveGibisDTO implements Serializable {


    private int id;
    private String nome;
    private int numeroSerie;
    private String editora;
    private String genero;
    private int anoPublicado;
    private int totalComentarios;

    public DevolveGibisDTO() {
    }

    public DevolveGibisDTO(int id, String nome, int numeroSerie, String editora, String genero, int anoPublicado, int totalComentarios) {
        this.id = id;
        this.nome = nome;
        this.numeroSerie = numeroSerie;
        this.editora = editora;
        this.genero = genero;
        this.anoPublicado = anoPublicado;
        this.totalComentarios = totalComentarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(int numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getAnoPublicado() {
        return anoPublicado;
    }

    public void setAnoPublicado(int anoPublicado) {
        this.anoPublicado = anoPublicado;
    }

    public int getTotalComentarios() {
        return totalComentarios;
    }

    public void setTotalComentarios(int totalComentarios) {
        this.totalComentarios = totalComentarios;
    }
}
