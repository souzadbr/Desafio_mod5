package br.com.zup.GerenciarContas;

import br.com.zup.GerenciarContas.enums.Status;
import br.com.zup.GerenciarContas.enums.Tipo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ContaRepository extends CrudRepository<Conta, Integer> {

    List<Conta> findAllByStatus(Status status);
    List<Conta> findAllByTipo (Tipo tipo);
    @Query (value = "SELECT * FROM contas WHERE VALOR BETWEEN :valor*0.9 AND :valor*1.1", nativeQuery = true)
    List<Conta> findAllByValorBetween(double valor);

}
