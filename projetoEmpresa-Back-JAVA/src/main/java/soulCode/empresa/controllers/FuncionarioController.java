package soulCode.empresa.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import soulCode.empresa.models.Cargo;
import soulCode.empresa.models.Funcionario;
import soulCode.empresa.models.Mentor;
import soulCode.empresa.services.FuncionarioService;

@CrossOrigin //tira todos os nossos problemas de cors
@RestController //para dizer que essa classe é um controller
@RequestMapping("empresa") //indica que todos os nossos end points vai ser depois do /empresa. Rota principal
public class FuncionarioController {
	
	@Autowired
	private FuncionarioService funcionarioService;
	
	//faço o mapeamento do método get- mostrar todos os funcionários
	@GetMapping("/funcionario") 
	public List<Funcionario> mostrarTodosFuncionarios(){
		List<Funcionario> funcionario = funcionarioService.mostrarTodosFuncionarios();
		return funcionario;
	}
	
	 //Get - tabela inner join de funcionários com seu cargo
	 @GetMapping("/funcionario/func-cargo")
	 	public List<List> funcComCargo(){
		 List<List> funcCargo = funcionarioService.funcComCargo();
		 return funcCargo;
	 }
	 
	//Get- end point buscar o funcionário pelo seu cpf
	@GetMapping("/func-cpf/{func_cpf}")
		public ResponseEntity<Funcionario> buscarFuncPeloCpf(@PathVariable String func_cpf) {
			Funcionario funcionario = funcionarioService.buscarFuncPeloCpf(func_cpf);
			return ResponseEntity.ok().body(funcionario);
	}
	
	//buscar funcionario por id_cargo	 	 
	@GetMapping("/funcionario/busca-cargo/{id_cargo}")
		public List<Funcionario> buscarFuncPorCargo(@PathVariable Integer id_cargo){
			 List<Funcionario> funcionario = funcionarioService.buscarFuncPorCargo(id_cargo);
			 return funcionario;
	}
	 		
	//Get buscar funcionário pelo seu id
	@GetMapping("/funcionario/{id_funcionario}")
		public ResponseEntity<?> buscarUmFuncionario(@PathVariable Integer id_funcionario){
		 Funcionario funcionario = funcionarioService.buscarUmFuncionario(id_funcionario);
			return ResponseEntity.ok().body(funcionario);
	}
	
 	 //Put, deixar o funcionário sem cargo, utilizando o id_funcionario
	 @PutMapping("/funcionario/deixarSemCargo/{id_funcionario}")
	 public ResponseEntity<Funcionario> deixarFuncSemCargo(@PathVariable Integer id_funcionario){
		 Funcionario func = funcionarioService.deixarFuncSemCargo(id_funcionario);
		 return ResponseEntity.noContent().build();
	 }
	 
	
	 //Post inserir funcionário	 	 
	 //@RequestBody especificar que vai ser passado para o nosso método pelo corpo da nossa requisição 
	 @PostMapping("/funcionario")
	 public ResponseEntity<Funcionario> inserirFuncionario(@RequestBody Funcionario funcionario){
		funcionario = funcionarioService.InserirFunc(funcionario);
				 
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				 .buildAndExpand(funcionario.getId_funcionario()).toUri(); 
		 return ResponseEntity.created(uri).build();
	 }
	 
	//Put inserir cargo para o funcionário	 
	 @PutMapping("funcionario/inserirCargo/{id_funcionario}")
	 public ResponseEntity<Funcionario> inserirFuncNoCargo(@PathVariable Integer id_funcionario, @RequestBody Cargo cargo){
		 	Funcionario funcionario = funcionarioService.inserirFuncNoCargo(id_funcionario, cargo);
		 	return ResponseEntity.noContent().build();
	}
	 
	 //Delete do funcionário pelo seu id
	 @DeleteMapping("/funcionario/{id_funcionario}")
	 public ResponseEntity<Void> deletarUmFuncionario(@PathVariable Integer id_funcionario){
		 funcionarioService.deletarUmFuncionario(id_funcionario);
		 return ResponseEntity.noContent().build(); //noContent() está falando que ele vai retornar um retorno vazio, pois é delete, ou seja o corpo do responseEntity vai ser vazio 
	 }
	
	//put editar funcionário com cargo associado
	@PutMapping("/funcionario/{id_funcionario}") 
	public ResponseEntity<Void> editarFuncComCargo(@RequestParam(value = "cargo")Cargo cargo, @PathVariable Integer id_funcionario, @RequestBody Funcionario func){
		func.setId_funcionario(id_funcionario);
		func.setCargo(cargo);
		func = funcionarioService.editarFuncionario(func);
		return ResponseEntity.noContent().build();
	}
	 
	//put editar funcionário sem cargo associado
	 @PutMapping("/funcionario-sem-cargo/{id_funcionario}")
	 public  ResponseEntity<Void> editarFuncSemCargo(@PathVariable 	Integer id_funcionario, @RequestBody Funcionario funcionario){
		 funcionario.setId_funcionario(id_funcionario);
		 funcionario = funcionarioService.editarFuncionario(funcionario);
		 return  ResponseEntity.noContent().build();
	}
	 
	
	 
	 
}








