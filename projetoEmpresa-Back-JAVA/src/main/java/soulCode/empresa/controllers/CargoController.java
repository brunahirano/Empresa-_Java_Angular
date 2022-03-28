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
import soulCode.empresa.models.Mentor;
import soulCode.empresa.services.CargoService;



@CrossOrigin
@RestController
@RequestMapping("empresa")
public class CargoController {
	
	@Autowired
	private CargoService cargoService;
	
	//Get listas todos os cargos da empresa
	@GetMapping("/cargo")
	public List<Cargo> mostrarTodosCargos(){
		List<Cargo> cargo = cargoService.mostrarTodosCargos();
		return cargo;
	}
	
	//Get para buscar o cargo pelo seu id
	@GetMapping("/cargo/{id_cargo}")
	public ResponseEntity<Cargo>  buscarUmCargo(@PathVariable Integer id_cargo){
		Cargo cargo = cargoService.buscarUmCargo(id_cargo);
		return ResponseEntity.ok().body(cargo);
	}
	
	//Get- Mapeando service da primeira query, ou seja cargos sem mentores associados
	@GetMapping("/cargoSemMentor")
	public List<Cargo> mentorSemCargo(){
		List<Cargo> cargo = cargoService.cargoSemMentor();
		return cargo;
	}
	
	//Get- retorna um cargo baseado no id_mentor
	@GetMapping("/cargo/cargo-mentor/{id_mentor}")
	public Cargo cargoDoMentor(@PathVariable Integer id_mentor){
		
			return cargoService.cargoDoMentor(id_mentor);
	}
	
	//Get inner join, tenho dados do mentor e dados do cargo associado ao  mentor
	@GetMapping("/cargo/cargo-mentor")
	public List<List> cargosComMentor(){
		List<List> cargoMentor = cargoService.cargoComSeuMentor();
		return cargoMentor;
	}
	
	//post para cadastrar cargo
	 @PostMapping("/cargo")
	 public ResponseEntity<Cargo> cadastrarCargo(@RequestParam(value="mentor", required = false) Integer id_mentor, @RequestBody Cargo cargo){ 
		 
		 cargo = cargoService.cadastrarCargo(id_mentor,cargo);
		 
		 //URI- um identificador do serviço que vamos fazer a requisição
		 URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				 .buildAndExpand(cargo.getId_cargo()).toUri(); 
		 return ResponseEntity.created(uri).build();
	 }
	 
	 //put para editar o cargo pelo seu id
	 @PutMapping("/cargo/{id_cargo}")
	 public  ResponseEntity<Cargo> editarCargo(@PathVariable Integer id_cargo, @RequestBody Cargo cargo){
		 cargo.setId_cargo(id_cargo);
		 cargo = cargoService.editarCargo(cargo);			
		 return  ResponseEntity.noContent().build();
	}
	 
	//delete cargo pelo seu id_cargo	 
	 @DeleteMapping("/cargo/{id_cargo}")
	 public ResponseEntity<Void> deletarUmCargo(@PathVariable Integer id_cargo){
		 cargoService.deletarUmCargo(id_cargo);
		 return ResponseEntity.noContent().build(); //noContent() está falando que ele vai retornar um retorno vazio, pois é delete, ou seja o corpo do responseEntity vai ser vazio 
	 } 
	 
	 //put para atribuir um mentor para o cargo
	 @PutMapping("/cargo/definirMentor/{id_cargo}/{id_mentor}")
	 public ResponseEntity<Mentor> atribuirMentor(@PathVariable Integer id_cargo, @PathVariable Integer id_mentor){
		 cargoService.atribuirMentor(id_cargo, id_mentor);
		 return ResponseEntity.noContent().build();
	 }
	 
	 //put deixar o mentor sem cargo
	 @PutMapping("/cargo/tirarMentor/{id_cargo}/{id_mentor}")
		public ResponseEntity<Mentor> deixarMentorSemCargo(@PathVariable Integer id_cargo, @PathVariable Integer id_mentor){
			cargoService.deixarMentorSemCargo(id_cargo, id_mentor);
			return ResponseEntity.noContent().build();
		}
	 
	//put para deixar o cargo sem mentor 
	 @PutMapping("/cargo/deixarCargoSemMentor/{id_cargo}/{id_mentor}")
		public ResponseEntity<Mentor> deixarCargoSemMentor(@PathVariable Integer id_cargo, @PathVariable Integer id_mentor){
		 	Cargo cargo = cargoService.deixarCargoSemMentor(id_cargo, id_mentor);
		 	return ResponseEntity.noContent().build();
	}
	
	
	
}
