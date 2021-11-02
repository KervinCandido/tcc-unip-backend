package br.unip.cc.tcc.config.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.unip.cc.tcc.config.validation.exception.FormErrorException;

@RestControllerAdvice
public class ErrorValidationHandler {
	
	@Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<FormErrorDTO> handle(MethodArgumentNotValidException exception){
    	return handleFormValidationException(exception.getBindingResult());
    }
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(FormErrorException.class)
    public List<FormErrorDTO> handle(FormErrorException exception){
        return List.of(new FormErrorDTO(exception.getField(), exception.getMessage()));
    }
    
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public List<FormErrorDTO> handle(BindException exception){
        return handleFormValidationException(exception.getBindingResult());
    }

	private List<FormErrorDTO> handleFormValidationException(BindingResult exception) {
		List<FormErrorDTO> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getFieldErrors();
        
        fieldErrors.forEach(erro -> {
            String mensagem = messageSource.getMessage(erro, LocaleContextHolder.getLocale());
            dto.add(new FormErrorDTO(erro.getField(), mensagem));
        });

        return dto;
	}
    
}
