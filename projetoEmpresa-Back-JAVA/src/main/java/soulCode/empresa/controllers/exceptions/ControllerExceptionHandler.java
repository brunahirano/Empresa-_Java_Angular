package soulCode.empresa.controllers.exceptions;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import soulCode.empresa.services.exceptions.ObjectNotFoundException;

import soulCode.empresa.services.exceptions.DataIntegrityViolationException;


@ControllerAdvice //Para dizer que essa clase vai fazer a configuração dos nossos tto de erros, ou seja vai controlar o lançamento das exceções
public class ControllerExceptionHandler {
	//ResponseEntity: O retorno vai ser o objeto da nossa classe StandardError (tempo, status e erro)
	//objectNotFoundException - nome da função   
	//parâmetro ObjectNotFoundException (exceção que criamos no service), com 2 parâmetros.
	//HttpStatus.NOT_FOUND.value(), o valor do status do http quando não encontra alguma requisição 
	//Servelet faz a requisição para fazer qual o erro 
	
	//PARA O GET ObjectNotFoundException e HttpStatus.NOT_FOUND
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError>  objectNotFoundException(ObjectNotFoundException e, ServletRequest request){
		
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		
		String dataFormatada = formatador.format(data);
		
		StandardError error = new StandardError(dataFormatada, HttpStatus.NOT_FOUND.value(),
				e.getMessage()); //StandarsdError tenho que passar 3 parâmetros - tempo, status e erro(e.getMessage); 
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); //retorna o body do erro
	}
	
	//PARA O DELETE DataIntegrityViolationException e HttpStatus.BAD_REQUEST
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError>  dataIntegrityViolationException(DataIntegrityViolationException e, ServletRequest request){
		
		Date data = new Date();
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		
		String dataFormatada = formatador.format(data);
		
		StandardError error = new StandardError(dataFormatada, HttpStatus.BAD_REQUEST.value(),
				e.getMessage()); //StandarsdError tenho que passar 3 parâmetros - tempo, status e erro(e.getMessage); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error); //retorna o body do erro
	}
	
	
		
}