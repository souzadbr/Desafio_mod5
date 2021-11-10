package br.com.zup.GerenciarContas;


import br.com.zup.GerenciarContas.dtos.EntradaContaDTO;
import br.com.zup.GerenciarContas.dtos.SaidaContaDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

}
