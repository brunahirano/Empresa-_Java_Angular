package soulCode.empresa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import soulCode.empresa.models.Funcionario;
import soulCode.empresa.models.Mentor;


//aqui já temos acesso a todos os 11 métodos do jpsRepository, exemplo (find, get, put, delete)
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{ //coloco o Entity(Funcionario.java) e o tipo da chave primária, no nosso caso o Id que é Interger
	

	//Esse método vai buscar/listar os funcionarios da empresa pelo id do cargo
	@Query(value = "SELECT * FROM funcionario WHERE id_cargo = :id_cargo", nativeQuery = true)
	List<Funcionario> fetchByCargo(Integer id_cargo); //fetch = buscar funcionario pelo cargo
	
	//vai trazer todos os mentores que não tem cargo atralado
	@Query(value="SELECT * FROM funcionario WHERE id_cargo is null", nativeQuery = true)
		List<Mentor> funcSemCargo();
	
	
	//tabela inner join de funcionário com cargo
	@Query(value = "SELECT funcionario.id_funcionario, funcionario.func_nome, funcionario.func_cidade, funcionario.func_foto, funcionario.func_cpf, cargo.id_cargo, cargo.ca_nome, cargo.car_atribuicao FROM cargo right JOIN funcionario ON funcionario.id_cargo = cargo.id_cargo order by funcionario.func_nome", nativeQuery = true)
	List<List> funcComCargo();
	
	//esse método vai buscar o funcionário pelo seu cpf, para atribuir a foto para ao funcionpario certo
	@Query(value = "SELECT * FROM funcionario WHERE func_cpf = :func_cpf", nativeQuery = true)
	Funcionario fetchByCpf(String func_cpf);
	
}

	

