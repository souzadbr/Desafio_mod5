package br.com.zup.GerenciarContas;

import br.com.zup.GerenciarContas.dtos.EntradaContaDTO;
import br.com.zup.GerenciarContas.enums.Status;
import br.com.zup.GerenciarContas.exception.ContaNãoEcontradaPorIdException;
import br.com.zup.GerenciarContas.exception.ValorDeContaInvalidoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        verificarValorConta(conta);
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
    public List<Conta> exibirTodosOsCadastros() {
        List<Conta> conta = (List<Conta>) contaRepository.findAll();
        return conta;
    }

    //método buscarConta
    public Conta buscarConta(int codigo) {
        Optional<Conta> conta = contaRepository.findById(codigo);
        if (conta.isEmpty()) {
            throw new ContaNãoEcontradaPorIdException();
        }
        return conta.get();
    }

    //método atualizar pagamento de conta
    public Conta atualizarStatusConta(int codigo) {
        Conta conta = buscarConta(codigo);
        conta.setDataDePagamento(LocalDateTime.now());
        conta.setStatus(Status.PAGO);
        contaRepository.save(conta);

        return conta;
    }

    //método verificar valor conta
    private void verificarValorConta (Conta conta){
        if (conta.getValor() <= 0){
            throw new ValorDeContaInvalidoException("Valor inválido");
        }
    }


}
