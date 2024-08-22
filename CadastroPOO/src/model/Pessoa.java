package model;

import java.io.Serializable;

public class Pessoa implements Serializable {

    protected int id;
    protected String nome;

    public Pessoa(int id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    protected String exibir() {
        return id + " " + nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

}
