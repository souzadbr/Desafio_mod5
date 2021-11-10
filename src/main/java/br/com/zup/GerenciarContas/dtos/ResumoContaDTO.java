package br.com.zup.GerenciarContas.dtos;

import br.com.zup.GerenciarContas.enuns.Status;
import br.com.zup.GerenciarContas.enuns.Tipo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ResumoContaDTO {

    private int codigo;
    private String nome;
    private double valor;
    private Status status;

    public ResumoContaDTO() {
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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
