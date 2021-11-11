package br.com.zup.GerenciarContas;

import br.com.zup.GerenciarContas.enums.Status;
import br.com.zup.GerenciarContas.enums.Tipo;
import br.com.zup.GerenciarContas.exception.ContaNãoEcontradaPorIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ContaService {
    @Autowired
    private ContaRepository contaRepository;

    //método cadastrarConta(ContaDTO):ContaSaidaDTO
    public Conta cadastrarConta(Conta conta) {

        verificarStatusConta(conta);
        conta.setDataDePagamento(null);
        return contaRepository.save(conta);
    }

    public void verificarStatusConta(Conta conta) {
        LocalDate dataAtual = LocalDate.now();
        if (conta.getDataDeVencimento().isAfter(dataAtual)) {
            conta.setStatus(Status.AGUARDANDO);
        } else if (conta.getDataDeVencimento().isBefore(dataAtual)) {
            conta.setStatus(Status.VENCIDA);
        }
    }

    //método exibir lista de cadastro usando ResumoContaDTO
    public List<Conta> exibirTodosOsCadastros(Status status, Tipo tipo, Double valor) {
        if (status != null) {
            return (List<Conta>) contaRepository.findAllByStatus(status);
        }
        else if (tipo != null) {
            return (List<Conta>) contaRepository.findAllByTipo(tipo);
        }
        else if(valor != null){
            return (List<Conta>) contaRepository.findAllByValorBetween(valor);
        }

        List<Conta> conta = (List<Conta>) contaRepository.findAll();
        return conta;
    }

    //método buscarConta
    public Conta buscarConta(int codigo) {
        Optional<Conta> conta = contaRepository.findById(codigo);
        if (conta.isEmpty()) {
            throw new ContaNãoEcontradaPorIdException("Conta não encontrada");
        }
        return conta.get();
    }

    //método atualizar pagamento de conta
    public Conta atualizarStatusConta(int codigo, Status status) {
        Conta conta = buscarConta(codigo);
        conta.setDataDePagamento(LocalDateTime.now());
        conta.setStatus(status);
        contaRepository.save(conta);

        return conta;
    }

    //Método mostrar 1 conta por ID
    public Conta mostrarContaPorId(int codigo) {
        Conta conta = buscarConta(codigo);

        return conta;
    }

    //Método deletar conta via id
    public void deletarConta(int codigo){
        if(contaRepository.existsById(codigo)){
            contaRepository.deleteById(codigo);
        }else {
            throw new ContaNãoEcontradaPorIdException("Contra não encontrada!");
        }
    }

}



