package br.edu.imepac.model;

public class Usuario {
    private long id;
    private String nome;
    private String email;
    private Boolean ativo;

    public void setId(long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
