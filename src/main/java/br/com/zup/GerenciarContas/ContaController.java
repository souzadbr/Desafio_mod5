package br.com.zup.GerenciarContas;


import br.com.zup.GerenciarContas.dtos.EntradaContaDTO;
import br.com.zup.GerenciarContas.dtos.ResumoContaDTO;
import br.com.zup.GerenciarContas.dtos.SaidaContaDTO;
import br.com.zup.GerenciarContas.dtos.StatusContaDTO;
import br.com.zup.GerenciarContas.enums.Status;
import br.com.zup.GerenciarContas.enums.Tipo;
import br.com.zup.GerenciarContas.exception.StatusInválidoSelecionarPagoException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/contas")
@Api(value = "Gerenciador de Contas")
@CrossOrigin (origins = "*")
public class ContaController {

    @Autowired
    private ContaService contaService;

    @Autowired
    private ModelMapper modelMapper;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "Método que cadastra uma conta.")
    public SaidaContaDTO cadastrarConta(@RequestBody @Valid EntradaContaDTO entradaContaDTO) {
        Conta conta = modelMapper.map(entradaContaDTO, Conta.class);

        return modelMapper.map(contaService.cadastrarConta(conta), SaidaContaDTO.class);
    }

    @GetMapping
    @ApiOperation(value = "Método que exibi lista de contas cadastradas com parâmetro apra filtro ou não.")
    public List<ResumoContaDTO> exibirListaDeContas(@RequestParam(required = false)Status status,
                                                    @RequestParam(required = false) Tipo tipo,
                                                    @RequestParam(required = false)Double valor) {
        List<ResumoContaDTO> listaContaDTOS = new ArrayList<>();
        for (Conta conta : contaService.exibirTodosOsCadastros(status, tipo, valor)) {
            ResumoContaDTO resumoContaDTO = modelMapper.map(conta, ResumoContaDTO.class);
            listaContaDTOS.add(resumoContaDTO);
        }
        return listaContaDTOS;
    }

    @GetMapping ("/{id}")
    @ApiOperation(value = "Método que exibi uma conta via id(código de identificação da conta).")
    public SaidaContaDTO mostrarContaPorId(@PathVariable int id){
        return modelMapper.map(contaService.buscarConta(id), SaidaContaDTO.class);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Método que atualiza o status da conta de AGUARDADO/VENCIDA para PAGO.")
    public SaidaContaDTO atualizarStatus(@PathVariable int id, @RequestBody StatusContaDTO statusContaDTO) {
        if(statusContaDTO.getStatus() == Status.PAGO){
            return modelMapper.map(contaService.atualizarStatusConta(id,statusContaDTO.getStatus()),
                    SaidaContaDTO.class);
        }else {
            throw new StatusInválidoSelecionarPagoException("Status inválido, Selecionar PAGO!");
        }
    }

    @DeleteMapping ("/{id}")
    @ApiOperation(value = "Método que deleta uma conta via id(código de identificação de uma conta).")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarConta(@PathVariable int id){
        contaService.deletarConta(id);
    }


}
