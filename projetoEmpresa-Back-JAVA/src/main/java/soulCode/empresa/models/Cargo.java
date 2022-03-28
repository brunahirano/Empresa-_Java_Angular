package soulCode.empresa.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity //Para mostrar para o eclipse que essa é a classe de definição de tabelas
public class Cargo {
	
	//atributos do model do Cargo
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private Integer id_cargo;
	
	
	@Column(nullable = false, length = 60) //nome vai ser uma coluna, nullable = false =  esse atributo é obrigatório, não pode ser nulo. De Default o length é 265 caracteres, coloco 60 para economizar espaço
	private String ca_nome; 
	
	@Column(nullable = true, length = 60)
	private String car_atribuicao;
	
	@OneToMany(mappedBy = "cargo") //1 cargo para vários funcionarios
	private List<Funcionario> funcionario = new ArrayList<>(); //lista de funcionários
	
	@OneToOne
	@JoinColumn(name = "id_mentor", unique =true)
	private Mentor mentor;

	public Integer getId_cargo() {
		return id_cargo;
	}

	public Mentor getMentor() {
		return mentor;
	}

	public void setMentor(Mentor mentor) {
		this.mentor = mentor;
	}

	public void setId_cargo(Integer id_cargo) {
		this.id_cargo = id_cargo;
	}

	public String getCa_nome() {
		return ca_nome;
	}

	public void setCa_nome(String ca_nome) {
		this.ca_nome = ca_nome;
	}

	public String getCar_atribuicao() {
		return car_atribuicao;
	}

	public void setCar_atribuicao(String car_atribuicao) {
		this.car_atribuicao = car_atribuicao;
	}

	public List<Funcionario> getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}


}


