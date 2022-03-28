package soulCode.empresa.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Boleto {
	
	//atributos do model do Boleto
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer codigo;
	
	@Column(nullable = false)
	private String bo_descricao;
	
	@DateTimeFormat(pattern = "dd-MM-yyyy") //espeficicar o padrão
	//@Temporal(TemporalType.DATE) //para tirar o horário da data
	@Column(columnDefinition = "date", nullable = false )
	private Date bo_dataVencimento;
	
	@NumberFormat(pattern = "#.##0,00") //formato brasileiro
	@Column(nullable = false)
	private Double bo_valor;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusBoleto bo_status;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_funcionario")
	private Funcionario funcionario;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getBo_descricao() {
		return bo_descricao;
	}

	public void setBo_descricao(String bo_descricao) {
		this.bo_descricao = bo_descricao;
	}

	public Date getBo_dataVencimento() {
		return bo_dataVencimento;
	}

	public void setBo_dataVencimento(Date bo_dataVencimento) {
		this.bo_dataVencimento = bo_dataVencimento;
	}

	public Double getBo_valor() {
		return bo_valor;
	}

	public void setBo_valor(Double bo_valor) {
		this.bo_valor = bo_valor;
	}

	public StatusBoleto getBo_status() {
		return bo_status;
	}

	public void setBo_status(StatusBoleto bo_status) {
		this.bo_status = bo_status;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}
	
}
