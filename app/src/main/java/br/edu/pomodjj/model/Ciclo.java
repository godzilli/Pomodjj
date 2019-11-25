package br.edu.pomodjj.model;

import java.io.Serializable;

public class Ciclo implements Serializable {

    private String key;
    private String titulo;
    private Integer quantidade;
    private Integer tempoTrabalho;
    private Integer tempoDescanso;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getTempoTrabalho() {
        return tempoTrabalho;
    }

    public void setTempoTrabalho(Integer tempoTrabalho) {
        this.tempoTrabalho = tempoTrabalho;
    }

    public Integer getTempoDescanso() {
        return tempoDescanso;
    }

    public void setTempoDescanso(Integer tempoDescanso) {
        this.tempoDescanso = tempoDescanso;
    }
}
