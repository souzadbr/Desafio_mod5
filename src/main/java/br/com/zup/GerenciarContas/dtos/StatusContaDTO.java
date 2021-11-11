package br.com.zup.GerenciarContas.dtos;

import br.com.zup.GerenciarContas.enums.Status;

public class StatusContaDTO {
    private Status status;

    public StatusContaDTO() {
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
