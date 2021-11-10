package br.com.zup.GerenciarContas;

public enum Status {
    AGUARDANDO ("Aguardando"),
    PAGO ("Pago"),
    VENCIDA("Vencida");

    private String descricaoStatus;

    Status (String descricaoStatus){
        this.descricaoStatus = descricaoStatus;
    }
    public String getDescricaoStatus(){
        return descricaoStatus;
    }
}
