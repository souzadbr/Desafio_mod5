package br.com.zup.GerenciarContas;

import br.com.zup.GerenciarContas.enums.Status;
import br.com.zup.GerenciarContas.enums.Tipo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContaRepository extends CrudRepository<Conta, Integer> {

    List<Conta> findAllByStatus(Status status);
    List<Conta> findAllByTipo (Tipo tipo);



}
