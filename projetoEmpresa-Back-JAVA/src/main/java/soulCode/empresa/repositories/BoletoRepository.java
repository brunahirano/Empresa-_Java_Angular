package soulCode.empresa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import soulCode.empresa.models.Boleto;


public interface BoletoRepository extends JpaRepository<Boleto, Integer>{
	//Encontrar o boleto pelo id_funcionario
	@Query(value = "SELECT * FROM boleto WHERE id_funcionario = :id_funcionario", nativeQuery = true)
	List<Boleto> buscarBoletosDoFuncionario(Integer id_funcionario);
}

