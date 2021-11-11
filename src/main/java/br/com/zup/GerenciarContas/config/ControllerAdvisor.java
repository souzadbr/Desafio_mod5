package br.com.zup.GerenciarContas.config;

import br.com.zup.GerenciarContas.exception.ContaNãoEcontradaPorIdException;
import br.com.zup.GerenciarContas.exception.StatusInválidoSelecionarPagoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public List<ErroDeValidacao> manipularErrosDeValidacao(MethodArgumentNotValidException exception) {
        List<ErroDeValidacao> erros = new ArrayList<>();
        for (FieldError fieldError : exception.getFieldErrors()) {
            ErroDeValidacao erroDeValidacao = new ErroDeValidacao(
                    fieldError.getDefaultMessage(),fieldError.getField());
            erros.add(erroDeValidacao);
        }
        return erros;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public MensagemDeErro manipularExcecaoEnumInvalido(HttpMessageNotReadableException exception){
        if(exception.getLocalizedMessage().contains("br.com.zup.GerenciarContas.enums.Tipo")){
            return new MensagemDeErro("Opção não encontrada");
        }
      return new MensagemDeErro(exception.getLocalizedMessage());
    }

    @ExceptionHandler(ContaNãoEcontradaPorIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public MensagemDeErro manipularExcecaoDEContaNãoEcontrada(ContaNãoEcontradaPorIdException exception){
        return new MensagemDeErro("Conta não encontrada");
    }

    @ExceptionHandler(StatusInválidoSelecionarPagoException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public MensagemDeErro manipularExcecaoSelecionarStatusPago(StatusInválidoSelecionarPagoException exception){
        return new MensagemDeErro("Selecionar Status PAGO");
    }

}
