package soulCode.empresa.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import soulCode.empresa.models.Boleto;
import soulCode.empresa.models.Funcionario;
import soulCode.empresa.repositories.BoletoRepository;
import soulCode.empresa.models.StatusBoleto;

@Service
public class BoletoService {

	@Autowired
	private BoletoRepository boletoRepository;
	
	@Autowired
	private FuncionarioService funcionarioService;

	//método para mostrar uma lista de boletos,ou seja todos os boletos cadastrados
	public List<Boleto> buscarTodosBoletos() {
		return boletoRepository.findAll();
	}
	
	//buscar o boleto pelo id
	public Boleto buscarUmBoleto(Integer codigo) {
		Optional<Boleto> boleto = boletoRepository.findById(codigo);
		return boleto.orElseThrow();
	}
	
	//esse método vai trazer os boletos cadastrados pra o funcionário
	public List<Boleto> buscarBoletosDoFuncionario(Integer id_funcionario) {
		List<Boleto> boleto = boletoRepository.buscarBoletosDoFuncionario(id_funcionario);
		return boleto;
	}

	// criar Boleto
	public Boleto criarBoleto(Boleto boleto, Integer id_funcionario) {
		boleto.setCodigo(null);
		Funcionario funcionario = funcionarioService.buscarUmFuncionario(id_funcionario);
		boleto.setFuncionario(funcionario);
		return boletoRepository.save(boleto);
	}

	// pagar um boleto
	public Boleto pagarBoleto(Integer codigo) {
		Boleto boleto = buscarUmBoleto(codigo);
		StatusBoleto st1 = StatusBoleto.RECEBIDO;
		boleto.setBo_status(st1);
		return boletoRepository.save(boleto);
	}

	// cancelar um boleto
	public Boleto cancelarBoleto(Integer codigo) {
		Boleto boleto = buscarUmBoleto(codigo);
		StatusBoleto st1 = StatusBoleto.CANCELADO;
		boleto.setBo_status(st1);
		return boletoRepository.save(boleto);
	}

	// editar um boleto
	public Boleto editarBoleto(Boleto boleto, Integer codigo, Integer id_funcionario) {
		buscarUmBoleto(codigo);
		Funcionario funcionario = funcionarioService.buscarUmFuncionario(id_funcionario);
		boleto.setFuncionario(funcionario);
		return boletoRepository.save(boleto);
	}
	
	//excluir boleto
	public void deletarUmBoleto(Integer codigo) {
		boletoRepository.deleteById(codigo);
	}


}
