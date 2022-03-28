package soulCode.empresa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.empresa.models.Mentor;


public interface MentorRepository extends JpaRepository<Mentor, Integer>{
		
	//buscar mentor pelo id_cargo
	@Query(value ="SELECT * FROM mentor WHERE id_cargo = :id_cargo", nativeQuery = true)
	Mentor buscarMentorDoCargo(Integer id_cargo);
	
	//vai trazer todos os mentores que não tem cargo atralado
	@Query(value="SELECT * FROM mentor WHERE id_cargo is null", nativeQuery = true)
	List<Mentor> mentorSemCargo();
	
	//buscar o mentor do cargo através do id_mentor
	//join da tabela mentor com o cargo 
	//trago todos os mentores, independente sem tem cargo ou não
	//quem manda nesse select é o mentor que está a direita
	@Query(value = "SELECT mentor.id_mentor,mentor.mentor_nome,mentor.mentor_cargo,mentor.mentor_foto,mentor.mentor_cpf,cargo.id_cargo,cargo.ca_nome,cargo.car_atribuicao FROM cargo right JOIN mentor ON mentor.id_cargo = cargo.id_cargo order by mentor.mentor_nome;",nativeQuery = true)
		List<List> mentorComSeuCargo();
	
	//esse método vai buscar o cpf_mentor  para atribuir a foto para ao mentor certo
	@Query(value = "SELECT * FROM mentor WHERE mentor_cpf = :mentor_cpf", nativeQuery = true)
	Mentor fetchByCpf(String mentor_cpf);
	
	
	
	
}
