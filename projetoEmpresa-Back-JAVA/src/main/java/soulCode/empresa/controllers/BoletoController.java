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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import soulCode.empresa.models.Boleto;
import soulCode.empresa.services.BoletoService;


@CrossOrigin
@RestController
@RequestMapping("empresa")
public class BoletoController {
	
	@Autowired
	private BoletoService boletoService;
	
	//get todos os boletos
	@GetMapping("/funcionario/boleto")
	public List<Boleto> buscarTodosBoletos(){
		List<Boleto> boleto = boletoService.buscarTodosBoletos();
		return  boleto;
	}
	
	//get buscar boleto pelo seu código
	@GetMapping("/funcionario/boleto/{codigo}")
	public ResponseEntity<Boleto> buscarUmBoleto(@PathVariable Integer codigo){
		Boleto boleto = boletoService.buscarUmBoleto(codigo);
		return ResponseEntity.ok().body(boleto);
	}
	
	//get listar boleto pelo id-funcionario
	@GetMapping("/funcionario/boleto-func/{id_funcionario}")
	public List<Boleto> buscarBoletosDosFuncionarios(@PathVariable Integer id_funcionario){
		List<Boleto>  boleto= boletoService.buscarBoletosDoFuncionario(id_funcionario);
		return boleto;
	}
	
	
	//post para criar um boleto para 1 funciário, utilizando id_funcionario
	@PostMapping("/funcionario/boleto/{id_funcionario}")
	public ResponseEntity<Boleto> criarBoleto(@RequestBody Boleto boleto, @PathVariable Integer id_funcionario){
		boleto = boletoService.criarBoleto(boleto, id_funcionario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(boleto.getCodigo()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
	//put para pagar o boleto utilizando o seu código, quando pago o boleto edito o status do mesmo, por isso put
	@PutMapping("/funcionario/pagar-boleto/{codigo}")
	public ResponseEntity<Boleto> pagarBoleto(@PathVariable Integer codigo){
		boletoService.pagarBoleto(codigo);
		return ResponseEntity.noContent().build();
	}
	
	//put para cancelar o boleto utilizando o seu código, quando cancelo o boleto, edito o status do mesmo, por isso put
	@PutMapping("/funcionario/cancelar-boleto/{codigo}")
	public ResponseEntity<Boleto> cancelarBoleto(@PathVariable Integer codigo){
		boletoService.cancelarBoleto(codigo);
		return ResponseEntity.noContent().build();
	}
	
	
	//put editar o boleto, utilizando o código do boleto e o id_funcionário associado a boleto
	@PutMapping("/funcionario/editar-boleto/{codigo}/{id_funcionario}")
	public ResponseEntity<Boleto> editarBoleto(@RequestBody Boleto boleto, @PathVariable Integer codigo, @PathVariable Integer id_funcionario){
		boleto = boletoService.editarBoleto(boleto, codigo,id_funcionario);
		return ResponseEntity.noContent().build();
	}
	
	
	//deletar um boleto utilizando o seu código
	@DeleteMapping("/funcionario/deletar-boleto/{codigo}")
	public ResponseEntity<Void> deletarUmBoleto(@PathVariable Integer codigo){
		boletoService.deletarUmBoleto(codigo);
		return ResponseEntity.noContent().build();
	}

}
