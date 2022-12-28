package br.edu.vianna.gibiapp.dto;

import java.io.Serializable;

public class InputGibiDTO implements Serializable {

    private String nome;
    private String resenha;
    private String numeroSerie;
    private String personagens;
    private String editora;
    private String genero;
    private String nacional;
    private String anoPublicado;
    private String numPaginas;
    private String dataCompra;
    private String precoPago;
    private String precoEstimado;

    public InputGibiDTO() {
    }

    public InputGibiDTO(String nome,
                        String resenha,
                        String numeroSerie,
                        String personagens,
                        String editora,
                        String genero,
                        String nacional,
                        String anoPublicado,
                        String numPaginas,
                        String dataCompra,
                        String precoPago,
                        String precoEstimado) {
        this.nome = nome;
        this.resenha = resenha;
        this.numeroSerie = numeroSerie;
        this.personagens = personagens;
        this.editora = editora;
        this.genero = genero;
        this.nacional = nacional;
        this.anoPublicado = anoPublicado;
        this.numPaginas = numPaginas;
        this.dataCompra = dataCompra;
        this.precoPago = precoPago;
        this.precoEstimado = precoEstimado;
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

    public String getNacional() {
        return nacional;
    }

    public void setNacional(String nacional) {
        this.nacional = nacional;
    }

    public String getAnoPublicado() {
        return anoPublicado;
    }

    public void setAnoPublicado(String anoPublicado) {
        this.anoPublicado = anoPublicado;
    }

    public String getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(String numPaginas) {
        this.numPaginas = numPaginas;
    }

    public String getDataCompra() {
        return dataCompra;
    }

    public void setDataCompra(String dataCompra) {
        this.dataCompra = dataCompra;
    }

    public String getPrecoPago() {
        return precoPago;
    }

    public void setPrecoPago(String precoPago) {
        this.precoPago = precoPago;
    }

    public String getPrecoEstimado() {
        return precoEstimado;
    }

    public void setPrecoEstimado(String precoEstimado) {
        this.precoEstimado = precoEstimado;
    }
}
