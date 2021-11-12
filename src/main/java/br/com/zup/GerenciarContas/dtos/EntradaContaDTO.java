package br.com.zup.GerenciarContas.dtos;

import br.com.zup.GerenciarContas.enums.Tipo;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class EntradaContaDTO {

    @Size( min = 2, max = 30, message = "Quantidade de caracteres inválida!")
    @NotBlank
    private String nome;
    @DecimalMin(value = "0.01")
    private double valor;
    @NotNull
    private Tipo tipo;
    @NotNull(message = "Campo vazio, por favor preencher!")
    private LocalDate dataDeVencimento;

    public EntradaContaDTO() {
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public LocalDate getDataDeVencimento() {
        return dataDeVencimento;
    }

    public void setDataDeVencimento(LocalDate dataDeVencimento) {
        this.dataDeVencimento = dataDeVencimento;
    }
}
