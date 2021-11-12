package br.com.zup.GerenciarContas.dtos;

import br.com.zup.GerenciarContas.enums.Status;

public class ResumoContaDTO {

    private int id;
    private String nome;
    private double valor;
    private Status status;

    public ResumoContaDTO() {
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
