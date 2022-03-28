package soulCode.empresa.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


//Para mostrar para o eclipse que essa é a classe de definição de tabelas
@Entity
public class Funcionario {
	
	//atributos do model do Funcionário
	
	@Id //aqui é a anotação que eu informo que id_funcionario vai ser o id 
	@GeneratedValue(strategy=GenerationType.IDENTITY) //e que o Id vai ser autoincremento e a estratégia vai ser definido pelo springboot(automático), sequencial 
	private Integer id_funcionario; //primeiro campo da tabela, do tipo Integer(inteiro não primitivo)
	
	@Column(nullable = false, length = 60) //nome vai ser uma coluna, nullable = false =  esse atributo é obrigatório, não pode ser nulo. De Default o length é 265 caracteres, coloco 60 para economizar espaço
	private String func_nome; 
	
	@Column(nullable = true, length = 60)
	private String func_cidade;
	
	@Column(nullable = true)
	private String func_foto;
	
	@Column(nullable = true, length = 60)
	private String func_cpf;
	
	
	@JsonIgnore // essa anotação serve para não entrar em loop de ficar mostrando o aluno quando testa a alicação
	@ManyToOne //muito alunoa para 1 turma 
	@JoinColumn(name = "id_cargo") // vai armazemar o id do cargo que esse funcionario está relacioado
	private Cargo cargo;

	public Integer getId_funcionario() {
		return id_funcionario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public void setId_funcionario(Integer id_funcionario) {
		this.id_funcionario = id_funcionario;
	}

	public String getFunc_nome() {
		return func_nome;
	}

	public void setFunc_nome(String func_nome) {
		this.func_nome = func_nome;
	}

	public String getFunc_cidade() {
		return func_cidade;
	}

	public void setFunc_cidade(String func_cidade) {
		this.func_cidade = func_cidade;
	}

	public String getFunc_foto() {
		return func_foto;
	}

	public void setFunc_foto(String func_foto) {
		this.func_foto = func_foto;
	}

	public String getFunc_cpf() {
		return func_cpf;
	}

	public void setFunc_cpf(String func_cpf) {
		this.func_cpf = func_cpf;
	}
	
	
}
