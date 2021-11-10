package br.com.zup.GerenciarContas;

public enum Tipo {

    LUZ("Luz"),
    AGUA("Agua"),
    COMIDA("Comida"),
    LAZER("Lazer"),
    OUTROS("Outros");

    private String descricao;
    Tipo (String descricao){
        this.descricao = descricao;
    }
    public String getDescricao(){
        return descricao;
    }

}
