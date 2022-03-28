package soulCode.empresa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import soulCode.empresa.models.Cargo;
import soulCode.empresa.models.Mentor;
import soulCode.empresa.repositories.CargoRepository;
import soulCode.empresa.services.exceptions.ObjectNotFoundException;



@Service
public class CargoService {

	@Autowired
	private CargoRepository cargoRepository;
	
	@Lazy 
	@Autowired
	private MentorService mentorService;

	//método para mostrar uma lista de cargos,ou seja todos os cargos cadastrados
	public List<Cargo> mostrarTodosCargos() {
		return cargoRepository.findAll();
	}

	//implemento a exceção ObjectNotFoundException 
	//get PARA O GET USO ObjectNotFoundException
	public Cargo buscarUmCargo(Integer id_cargo) { // passo parâmetro pelo atributo que vou buscar.
		Optional<Cargo> cargo = cargoRepository.findById(id_cargo);
		return cargo.orElseThrow(() -> new ObjectNotFoundException("Objeto não cadastrado! O id procurado foi " + id_cargo));
	}

	//servico da primeira query, trazer cargo sem mentor
	public List<Cargo> cargoSemMentor(){
			return cargoRepository.cargoSemMentor();
	}
	
	//serviço da segunda query, trazer cargo do mentor
	public Cargo cargoDoMentor(Integer id_mentor) {
			Cargo cargo = cargoRepository.cargoDoMentor(id_mentor);
			return cargo;
	}
	
	//tabela inner join, terceira query, trazer cargo com mentor
	public List<List> cargoComSeuMentor(){
			return cargoRepository.cargoComSeuMentor();
	}
	
	//cadastrar cargo para o mentor
	public Cargo cadastrarCargo(Integer id_mentor, Cargo cargo) {
		// é uma forma de segurança para não setarmos o id
		cargo.setId_cargo(null);
		if (id_mentor != null) {
			Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
			cargo.setMentor(mentor);	
		}
		
		return cargoRepository.save(cargo);
	}

	// Put editar cargo
	public Cargo editarCargo(Cargo cargo) {
		buscarUmCargo(cargo.getId_cargo());
		return cargoRepository.save(cargo);
	}

	//delete PARA O DELETE USO O //DataIntegrityViolationException
	public void deletarUmCargo (Integer id_cargo) {
		buscarUmCargo(id_cargo);
		try {
			cargoRepository.deleteById(id_cargo);
		} catch (org.springframework.dao.DataIntegrityViolationException e) { //esse primeiro é o import do spring
					throw new soulCode.empresa.services.exceptions.DataIntegrityViolationException("O cargo não pode ser deletado, porque possui funcionários relacionados!"); // o segundo é uma uma instância do nosso (soulCode.empresa.services.exceptions)
				}
			}

	//atribuir mentor para o cargo
	public Cargo atribuirMentor(Integer id_cargo, Integer id_mentor) {
		Cargo cargo = buscarUmCargo(id_cargo);
		Mentor mentorAnterior = mentorService.buscarMentorDoCargo(id_cargo); //consigo através do id_cargo, buscar o mentor
		Mentor mentor = mentorService.mostrarUmMentor(id_mentor); // consigo pegar o id do novo mentor  no select
		if(cargo.getMentor()!=null) { //significa que escolhi um novo mentor no select
		cargo.setMentor(null); //seto mentor antigo para null
		mentorAnterior.setCargo(null); //seto cargo antigo para null
		}
		cargo.setMentor(mentor);
		mentor.setCargo(cargo);
		return cargoRepository.save(cargo); //vai fazer de qualquer forma 
	}

	//deixar cargo sem mentor
	public Cargo deixarCargoSemMentor(Integer id_cargo, Integer id_mentor) {
		Cargo cargo = buscarUmCargo(id_cargo);
		cargo.setMentor(null);
		Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
		mentor.setCargo(null);
		return cargoRepository.save(cargo);
	}
	
	//deixar mentor sem cargo
	public Cargo deixarMentorSemCargo(Integer id_cargo, Integer id_mentor) {
		Cargo cargo = buscarUmCargo(id_cargo);
		cargo.setMentor(null);
		Mentor mentor = mentorService.mostrarUmMentor(id_mentor);
		mentor.setCargo(null);
		return cargoRepository.save(cargo);
	}
			
	
}
