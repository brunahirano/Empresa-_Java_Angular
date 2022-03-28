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

import soulCode.empresa.services.MentorService;


@CrossOrigin
@RestController
@RequestMapping("empresa")
public class MentorController {

	@Autowired
	private MentorService mentorService;

	//get mostrar todos os mentores
	@GetMapping("/mentor")
	public List<Mentor> mostrarTodosMentores() {
		List<Mentor> mentor = mentorService.mostrarTodosMentores();
		return mentor;
	}

	//get mostrar mentor através do seu id
	@GetMapping("/mentor/{id_mentor}")
	public ResponseEntity<Mentor> mostrarUmMentor(@PathVariable Integer id_mentor) {
		Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
		return ResponseEntity.ok().body(mentor);
	}

	//Get buscar mentor do cargo, através do id_cargo
	@GetMapping("/mentor-cargo/{id_cargo}")
	public ResponseEntity<Mentor> buscarMentorDoCargo(@PathVariable Integer id_cargo) {
		Mentor mentor = mentorService.buscarMentorDoCargo(id_cargo);
		return ResponseEntity.ok().body(mentor);
	}

	//Get buscar mentor sem o cargo associado
	@GetMapping("/mentorSemCargo")
	public List<Mentor> mentorSemCargo() {
		List<Mentor> mentor = mentorService.buscarMentorSemCargo();
		return mentor;
	}
	
	//Get buscar mentor com cargo associado
	@GetMapping("/mentor/mentor-cargo")
	public List<List> mentorComCargo() {
		List<List> mentorComCargo = mentorService.mentorComSeuCargo();
		return mentorComCargo;
	}

	//Get- end point buscar o prof pelo cpf
	@GetMapping("/mentor-cpf/{mentor_cpf}")
	public ResponseEntity<Mentor> buscarMentorPeloCpf(@PathVariable String mentor_cpf) {
		Mentor mentor = mentorService.buscarMentorPeloCpf(mentor_cpf);
		return ResponseEntity.ok().body(mentor);
	}
	
	//Post para inserir um mentor com cargo
	@PostMapping("/mentor")
	public ResponseEntity<Mentor> inserirMentorComCargo(
			@RequestParam(value = "cargo", required = false) Integer id_cargo, @RequestBody Mentor mentor) {
		mentor = mentorService.inserirMentor(id_cargo, mentor);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}") 
				.buildAndExpand(mentor.getId_mentor()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	//Delete - deletar o mentor através do seu id
	@DeleteMapping("/mentor/{id_mentor}")
	public ResponseEntity<Mentor> deletarUmMentor(@PathVariable Integer id_mentor){
		mentorService.deletarUmMentor(id_mentor);
		return ResponseEntity.noContent().build();
	}
	

	//Put inserir cargo para o funcionário	 
	@PutMapping("mentor/inserirCargo/{id_mentor}")
	 public ResponseEntity<Mentor> inserirCargoParaMentor(@PathVariable Integer id_mentor, @RequestBody Cargo cargo){
			 	Mentor mentor = mentorService.inserirCargoParaMentor(id_mentor, cargo);
			 	return ResponseEntity.noContent().build();
	}
	
	 //Put, deixar o mentor sem cargo, utilizando o id_funcionario
	 @PutMapping("/mentor/deixarSemCargo/{id_mentor}")
	 public ResponseEntity<Mentor> deixarMentorSemCargo(@PathVariable Integer id_mentor){
		 Mentor mentor = mentorService.deixarMentorSemCargo(id_mentor);
		 return ResponseEntity.noContent().build();
	 }
	
	//Put editar mentor com cargo através do seu id
	@PutMapping("mentor/{id_mentor}")
	public ResponseEntity<Void> editarMentor(@RequestParam(value = "cargo")Cargo cargo, @PathVariable Integer id_mentor, @RequestBody Mentor mentor){
		mentor.setId_mentor(id_mentor);
		mentor.setCargo(cargo);
		mentor = mentorService.editarMentor(mentor);
		return ResponseEntity.noContent().build();
	}
	
	//Put editar mentor sem cargo através do seu id
	@PutMapping("/mentorSemCargo/{id_mentor}")
	public ResponseEntity<Void> editarMentorSemCargo(@PathVariable Integer id_mentor, @RequestBody Mentor mentor) {
		mentor.setId_mentor(id_mentor);
		mentor = mentorService.editarMentor(mentor);
		return ResponseEntity.noContent().build();
	}
}
