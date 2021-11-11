package br.com.zup.GerenciarContas.exception;

public class ContaNãoEcontradaPorIdException extends RuntimeException{
    public ContaNãoEcontradaPorIdException(String message) {
        super(message);
    }
}
