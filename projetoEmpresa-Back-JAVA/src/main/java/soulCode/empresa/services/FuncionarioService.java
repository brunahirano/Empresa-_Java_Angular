package soulCode.empresa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.empresa.models.Cargo;
import soulCode.empresa.models.Funcionario;
import soulCode.empresa.repositories.FuncionarioRepository;



@Service
public class FuncionarioService {
	
	@Autowired //faz injeção de dependência
	private FuncionarioRepository funcionarioRepository; //aqui instanciamos o FuncionarioRepository sem o new, no java o por padrão o objeto vem com a primeira letra em miúscula, assim tenho acesso a todos os 11 métodos do jpa repository.
	
	
	//o primeiro serviço que vamos implementar é a listagem de todos os funcionarios cadastrados
	public List<Funcionario> mostrarTodosFuncionarios(){
		return funcionarioRepository.findAll(); //o retorno é uma lista de alunos, findAll- método jpa-listar todos
	}
		
	
	//buscar um funcionario pelo id
	public Funcionario buscarUmFuncionario(Integer id_funcionario) { //passo parâmetro pelo atributo que vou buscar, no nosso caso é o ra_aluno que é o id, mas poderia ser nome etc. 
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id_funcionario);
		return funcionario.orElseThrow();
	}
	

	//get  //tabela inner join funcionario com cargo
	public List<List> funcComCargo(){
		return funcionarioRepository.funcComCargo();	
	}
		
	//buscar funcionario pelo cargo
	public List<Funcionario> buscarFuncPorCargo(Integer id_cargo){
			List<Funcionario> funcionario = funcionarioRepository.fetchByCargo(id_cargo);
			return funcionario;
	}
		
	//busca o funcionario pelon seu cpf
	public Funcionario buscarFuncPeloCpf(String func_cpf) {
		Funcionario funcionario = funcionarioRepository.fetchByCpf(func_cpf);
			return funcionario;
	}
	
	
	//inserir funcionario no cargo 
	public Funcionario inserirFuncNoCargo(Integer id_funcionario, Cargo cargo) {
		Funcionario funcionario = buscarUmFuncionario(id_funcionario);
		funcionario.setCargo(cargo);
		return funcionarioRepository.save(funcionario);
	}
	
	//deixar funcionário sem cargo
	public Funcionario deixarFuncSemCargo(Integer id_funcionario) {
		Funcionario func = buscarUmFuncionario(id_funcionario);
		func.setCargo(null);
		return funcionarioRepository.save(func);
	}
	
		
	//Inserir um funcionário 
	public Funcionario InserirFunc(Funcionario func) {
		func.setId_funcionario(null);
		return funcionarioRepository.save(func);
	}
		
	//deletar um funcionario
	public void deletarUmFuncionario(Integer id_funcionario) {
		funcionarioRepository.deleteById(id_funcionario);
	}
	
		
	//primeiro buscamos qual funcionario queremos editar e depois salvar 
	public Funcionario editarFuncionario(Funcionario funcionario) {
		buscarUmFuncionario(funcionario.getId_funcionario());
		return funcionarioRepository.save(funcionario);
		
	}
	
	//salvar foto para o funcionário, identificando o funcionário pelo seu id
	public Funcionario salvarFoto(Integer id_funcionario, String caminhoFoto) {
		Funcionario funcionario = buscarUmFuncionario(id_funcionario);
		funcionario.setFunc_foto(caminhoFoto);
		return funcionarioRepository.save(funcionario);
	}

	
	
}
