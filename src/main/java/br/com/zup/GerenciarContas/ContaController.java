package br.com.zup.GerenciarContas;


import br.com.zup.GerenciarContas.dtos.EntradaContaDTO;
import br.com.zup.GerenciarContas.dtos.ResumoContaDTO;
import br.com.zup.GerenciarContas.dtos.SaidaContaDTO;
import br.com.zup.GerenciarContas.dtos.StatusContaDTO;
import br.com.zup.GerenciarContas.enums.Status;
import br.com.zup.GerenciarContas.exception.StatusInválidoSelecionarPagoException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/conta")
public class ContaController {
    @Autowired
    private ContaService contaService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SaidaContaDTO cadastrarConta(@RequestBody @Valid EntradaContaDTO entradaContaDTO) {
        Conta conta = modelMapper.map(entradaContaDTO, Conta.class);

        return modelMapper.map(contaService.cadastrarConta(conta), SaidaContaDTO.class);
    }

    @GetMapping
    public List<ResumoContaDTO> exibirListaDeContas() {
        List<ResumoContaDTO> listaContaDTOS = new ArrayList<>();
        for (Conta conta : contaService.exibirTodosOsCadastros()) {
            ResumoContaDTO resumoContaDTO = modelMapper.map(conta, ResumoContaDTO.class);
            listaContaDTOS.add(resumoContaDTO);
        }
        return listaContaDTOS;
    }
    @GetMapping ("/{codigo}")
    public SaidaContaDTO mostrarContaPorId(@PathVariable int codigo){
        return modelMapper.map(contaService.buscarConta(codigo), SaidaContaDTO.class);
    }

    @PutMapping("/{codigo}")
    public SaidaContaDTO atualizarStatus(@PathVariable int codigo, @RequestBody StatusContaDTO statusContaDTO) {
        if(statusContaDTO.getStatus() == Status.PAGO){
            return modelMapper.map(contaService.atualizarStatusConta(codigo,statusContaDTO.getStatus()), SaidaContaDTO.class);
        }else {
            throw new StatusInválidoSelecionarPagoException("Status inválido, Selecionar PAGO!");
        }


    }


}
