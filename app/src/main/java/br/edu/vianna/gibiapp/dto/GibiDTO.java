package br.edu.vianna.gibiapp.dto;

import java.io.Serializable;
import java.util.ArrayList;

public class GibiDTO implements Serializable {

    private int id;
    private String nome;
    private String resenha;
    private String numeroSerie;
    private String personagens;
    private String editora;
    private String genero;
    private boolean nacional;
    private int classificacao;
    private int anoPublicado;
    private int numPaginas;
    private int totalComentarios;
    private double precoPago;
    private double precoEstimado;
    private ArrayList<ComentarioDTO> comentarios;

    public GibiDTO() {
        comentarios = new ArrayList<>();
    }

    public GibiDTO(int id, String nome, String resenha, String numeroSerie, String personagens, String editora, String genero, boolean nacional, int classificacao, int anoPublicado, int numPaginas, int totalComentarios, double precoPago, double precoEstimado) {
        this.id = id;
        this.nome = nome;
        this.resenha = resenha;
        this.numeroSerie = numeroSerie;
        this.personagens = personagens;
        this.editora = editora;
        this.genero = genero;
        this.nacional = nacional;
        this.classificacao = classificacao;
        this.anoPublicado = anoPublicado;
        this.numPaginas = numPaginas;
        this.totalComentarios = totalComentarios;
        this.precoPago = precoPago;
        this.precoEstimado = precoEstimado;
        comentarios = new ArrayList<>();
    }

    public ArrayList<ComentarioDTO> getComentarios() {
        return comentarios;
    }

    public void addComentario(ComentarioDTO coment) {
        if (coment != null){
            this.comentarios.add(coment);
        }
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

    public String getResenha() {
        return resenha;
    }

    public void setResenha(String resenha) {
        this.resenha = resenha;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getPersonagens() {
        return personagens;
    }

    public void setPersonagens(String personagens) {
        this.personagens = personagens;
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

    public boolean ehNacional() {
        return nacional;
    }

    public void setNacional(boolean nacional) {
        this.nacional = nacional;
    }

    public int getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(int classificacao) {
        this.classificacao = classificacao;
    }

    public int getAnoPublicado() {
        return anoPublicado;
    }

    public void setAnoPublicado(int anoPublicado) {
        this.anoPublicado = anoPublicado;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public int getTotalComentarios() {
        return totalComentarios;
    }

    public void setTotalComentarios(int totalComentarios) {
        this.totalComentarios = totalComentarios;
    }

    public double getPrecoPago() {
        return precoPago;
    }

    public void setPrecoPago(double precoPago) {
        this.precoPago = precoPago;
    }

    public double getPrecoEstimado() {
        return precoEstimado;
    }

    public void setPrecoEstimado(double precoEstimado) {
        this.precoEstimado = precoEstimado;
    }
}
