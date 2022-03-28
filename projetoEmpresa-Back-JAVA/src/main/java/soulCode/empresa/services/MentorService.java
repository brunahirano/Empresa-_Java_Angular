package soulCode.empresa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.empresa.models.Cargo;
import soulCode.empresa.models.Funcionario;
import soulCode.empresa.models.Mentor;
import soulCode.empresa.repositories.CargoRepository;
import soulCode.empresa.repositories.MentorRepository;


@Service
public class MentorService {
	@Autowired
	private MentorRepository mentorRepository;
	
	@Autowired
	private CargoService cargoService;
	
	@Autowired
	private CargoRepository cargoRepository;
	
	public List<Mentor> mostrarTodosMentores(){
		return mentorRepository.findAll();
	}
	
	//buscar uma turma por id
	public Mentor buscarUmMentor(Integer id_mentor) {
	Optional<Mentor> mentor = mentorRepository.findById(id_mentor);
			return mentor.orElseThrow(() -> new soulCode.empresa.services.exceptions.ObjectNotFoundException(
			"Objeto não cadastrado! O id procurado foi: " + id_mentor));
	}
	
	//buscar um mentor pelo seu id
	public Mentor mostrarUmMentor(Integer id_mentor) {
		Optional<Mentor> mentor = mentorRepository.findById(id_mentor);
		return mentor.orElseThrow();
	}
	
	//buscar mentor pelo id_cargo
	public  Mentor buscarMentorDoCargo(Integer id_cargo) {
		 Mentor mentor = mentorRepository.buscarMentorDoCargo(id_cargo);
		return mentor;
	}
	
	//buscar mentor sem cargo, id_cargo = null
	public List<Mentor> buscarMentorSemCargo() {
		return mentorRepository.mentorSemCargo();
	}
	
	//buscar mentor com cargo
	public List<List> mentorComSeuCargo(){
		return mentorRepository.mentorComSeuCargo();
	}
	
	//buscar o cpf do professor, usar para subir a foto 
	public Mentor buscarMentorPeloCpf(String mentor_cpf) {
		Mentor mentor = mentorRepository.fetchByCpf(mentor_cpf);
			return mentor;
	}
	
	//deixar mentor sem cargo
		public Mentor deixarMentorSemCargo(Integer id_mentor) {
			Mentor mentor = buscarUmMentor(id_mentor);
			mentor.setCargo(null);
			return mentorRepository.save(mentor);
		}

	//inserir cargo para o mentor 
	public Mentor inserirCargoParaMentor(Integer id_mentor, Cargo cargo) {
			Mentor mentor = buscarUmMentor(id_mentor);
			mentor.setCargo(cargo);
			return mentorRepository.save(mentor);
		}
	
	//inserir mentor com cargo e sem cargo
	public Mentor inserirMentor (Integer id_cargo,Mentor mentor) {
		mentor.setId_mentor(null);
		if (id_cargo != null) { //com cargo
			Cargo cargo = cargoService.buscarUmCargo(id_cargo);
			mentor.setCargo(cargo);
			cargo.setMentor(mentor);
		}
		return mentorRepository.save(mentor);
		
	}
	
	//editar mentor	
	public Mentor editarMentor(Mentor mentor) {
		buscarUmMentor(mentor.getId_mentor());
		return mentorRepository.save(mentor);
	}
	
	//deletar um mentor pelo seu id
	public void deletarUmMentor(Integer id_mentor) {
		buscarUmMentor(id_mentor);
		try {
			mentorRepository.deleteById(id_mentor);
			}catch(org.springframework.dao.DataIntegrityViolationException e) {
			throw new soulCode.empresa.services.exceptions.DataIntegrityViolationException(
					"O mentor turma não pode ser deletado, pois tem cargo associado!");
		}
	}
	
	//salvar foto do mentor
	public Mentor salvarFoto(Integer id_mentor, String caminhoFoto) {
		Mentor mentor = mostrarUmMentor(id_mentor);
		mentor.setMentor_foto(caminhoFoto);
		return mentorRepository.save(mentor);
	}
}
