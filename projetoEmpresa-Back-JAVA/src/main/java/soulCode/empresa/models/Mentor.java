package soulCode.empresa.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Mentor {
	
	//atributos do model do mentor

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_mentor; 
	
	@Column(nullable = false, length = 60)
	private String mentor_nome;
	
	@Column(nullable = false, length = 60)
	private String mentor_cargo;
	
	@Column(nullable = true)
	private String mentor_foto;
	
	@Column(nullable = true,  length = 60) 
	private String mentor_cpf;
	
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name= "id_cargo", unique = true) //fk_id_cargo
	
	private Cargo cargo;

	public Integer getId_mentor() {
		return id_mentor;
	}

	public void setId_mentor(Integer id_mentor) {
		this.id_mentor = id_mentor;
	}

	public String getMentor_nome() {
		return mentor_nome;
	}

	public void setMentor_nome(String mentor_nome) {
		this.mentor_nome = mentor_nome;
	}

	public String getMentor_cargo() {
		return mentor_cargo;
	}

	public void setMentor_cargo(String mentor_cargo) {
		this.mentor_cargo = mentor_cargo;
	}

	public String getMentor_foto() {
		return mentor_foto;
	}

	public void setMentor_foto(String mentor_foto) {
		this.mentor_foto = mentor_foto;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	public String getMentor_cpf() {
		return mentor_cpf;
	}

	public void setMentor_cpf(String mentor_cpf) {
		this.mentor_cpf = mentor_cpf;
	}
}
