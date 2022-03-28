package soulCode.empresa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.empresa.models.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
	//selecionar todos os campos da tabela cargo, onde o id do mentor é null, ou seja crago sem mentor
	@Query(value="SELECT * FROM cargo WHERE id_mentor is null", nativeQuery = true)
	List<Cargo> cargoSemMentor();
	
	//todos os dados do cargo que tem id_mentor, ou seja retorna cargo do mentor
	@Query(value="SELECT * FROM cargo where id_mentor =:id_mentor",nativeQuery = true)
	Cargo cargoDoMentor(Integer id_mentor);
	
	//Tabela inner join de cargo com mentor, vai "percorre por todas os cargos (left), tendo mentor ou não e traz os metores do cargo"
	@Query(value="SELECT cargo.id_cargo,cargo.ca_nome,cargo.car_atribuicao,mentor.id_mentor,mentor.mentor_nome,mentor.mentor_cargo FROM cargo left JOIN mentor ON mentor.id_cargo = cargo.id_cargo order by cargo.ca_nome;",nativeQuery = true)
	List<List> cargoComSeuMentor();
}
