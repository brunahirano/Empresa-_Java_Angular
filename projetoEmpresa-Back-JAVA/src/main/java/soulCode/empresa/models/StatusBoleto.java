package soulCode.empresa.models;

public enum StatusBoleto {
	//atributos do model Status boleto

	PENDENTE ("Pendente"),
	RECEBIDO ("Recebido"),
	CANCELADO ("Cancelado");
	
	private String descricao; //descrição do status do boleto 
	
	StatusBoleto(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}


}
