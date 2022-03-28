package soulCode.empresa.controllers.exceptions;

//modelo que vou vou lançar a nossa exceção, todos os objetos das exceções terão essa estrutura: data, status e error 
public class StandardError {
	
	private String data; 
	private Integer status; //qual erro, ex 404, 500, 400, por isso Integer
	private String error;
	
	public StandardError(String data, Integer status, String error) {
		super();
		this.setData(data);
		this.status = status;
		this.error = error;
	}
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}


	public String getData() {
		return data;
	}


	public void setData(String data) {
		this.data = data;
	}
	
	
}