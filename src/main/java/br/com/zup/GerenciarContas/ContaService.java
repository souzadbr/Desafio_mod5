package br.com.zup.GerenciarContas;

import br.com.zup.GerenciarContas.dtos.ResumoContaDTO;
import br.com.zup.GerenciarContas.enuns.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    public void verificarStatusConta(Conta conta){
        LocalDate dataAtual = LocalDate.now();
        if(conta.getDataDeVencimento().isAfter(dataAtual)){
            conta.setStatus(Status.AGUARDANDO);
        }else if (conta.getDataDeVencimento().isBefore(dataAtual)){
            conta.setStatus(Status.VENCIDA);
        }
    }

    //método exibir lista de cadastro usando ResumoContaDTO
    public List<Conta> exibirTodosOsCadastros (){
        List<Conta> conta = (List<Conta>) contaRepository.findAll();
        return conta;
    }

}
