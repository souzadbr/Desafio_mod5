package br.com.zup.GerenciarContas;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/conta")
public class ContaController {
    @Autowired
    private ContaService contaService;

    @Autowired
    private ModelMapper modelMapper;

}
