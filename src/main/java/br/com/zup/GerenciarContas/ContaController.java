package br.com.zup.GerenciarContas;


import br.com.zup.GerenciarContas.dtos.EntradaContaDTO;
import br.com.zup.GerenciarContas.dtos.ResumoContaDTO;
import br.com.zup.GerenciarContas.dtos.SaidaContaDTO;
import br.com.zup.GerenciarContas.dtos.StatusContaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping ("/conta")
public class ContaController {
    @Autowired
    private ContaService contaService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public SaidaContaDTO cadastrarConta (@RequestBody EntradaContaDTO entradaContaDTO){
        Conta conta = modelMapper.map (entradaContaDTO, Conta.class);

       return modelMapper.map(contaService.cadastrarConta(conta), SaidaContaDTO.class);
    }

    @GetMapping
    public List<ResumoContaDTO>exibirListaDeContas(){
        List<ResumoContaDTO>listaContaDTOS = new ArrayList<>();
        for (Conta conta: contaService.exibirTodosOsCadastros()) {
            ResumoContaDTO resumoContaDTO = modelMapper.map(conta, ResumoContaDTO.class);
            listaContaDTOS.add(resumoContaDTO);
        }
        return listaContaDTOS;
    }

    @PutMapping("/{codigo}")
    public SaidaContaDTO atualizarStatus (@PathVariable int codigo, @RequestBody StatusContaDTO statusContaDTO){
        return modelMapper.map(contaService.atualizarStatusConta(codigo), SaidaContaDTO.class);

    }


}
