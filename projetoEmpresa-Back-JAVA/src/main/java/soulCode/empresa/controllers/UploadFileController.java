package soulCode.empresa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import soulCode.empresa.models.Funcionario;
import soulCode.empresa.models.Mentor;
import soulCode.empresa.services.FuncionarioService;
import soulCode.empresa.services.MentorService;
import soulCode.empresa.utils.uploadFileUtil;


@RestController
@RequestMapping("empresa")
@CrossOrigin
public class UploadFileController {
	
	@Autowired
	private MentorService mentorService;
	
	@Autowired
	private FuncionarioService funcService;
	
	//Post para envio de foto do mentor
	@PostMapping("/envio/{id_mentor}")
	public ResponseEntity<String> enviarDados(@PathVariable Integer id_mentor, MultipartFile foto, @RequestParam (value="nomeDoArquivo") String nomeArquivo) { // do tipo String, pois vai armazenar o caminho da foto/arquivo //requestParam eu dou o o nome que eu quiser, é aquele que fica na url ?nome=
		
		String fileName = nomeArquivo;
		String uploadDir = "/Users/bruna/OneDrive/empresaFront2/src/assets/imagens";
		String nomeMaisCaminho = "assets/imagens/" + nomeArquivo;
		
		Mentor mentor = mentorService.salvarFoto(id_mentor, nomeMaisCaminho);
		
			
		try {
			uploadFileUtil.salvarArquivo(uploadDir, fileName, foto);
		}catch (Exception e) {
			
			
			System.out.println("O arquivo não foi enviado" + e);
		}
		
		System.out.println("Deu certo: " + nomeMaisCaminho);
		return ResponseEntity.ok("Arquivo enviado");
	}
	
	//Post para envio de foto do funcionário 
	@PostMapping("/envioFotoFunc/{id_funcionario}")
	public ResponseEntity<String> enviarDadosFunc(@PathVariable Integer id_funcionario, MultipartFile foto, @RequestParam (value="nomeDoArquivo") String nomeArquivo) { // do tipo String, pois vai armazenar o caminho da foto/arquivo //requestParam eu dou o o nome que eu quiser, é aquele que fica na url ?nome=
		
		String fileName = nomeArquivo;
		String uploadDir = "/Users/bruna/OneDrive/empresaFront/src/assets/imagens";
		String nomeMaisCaminho = "assets/imagens/" + nomeArquivo;
		
		Funcionario funcionario = funcService.salvarFoto(id_funcionario, nomeMaisCaminho);
		
			
		try {
			uploadFileUtil.salvarArquivo(uploadDir, fileName, foto);
		}catch (Exception e) {
			System.out.println("O arquivo não foi enviado" + e);
		}
		
		System.out.println("Deu certo: " + nomeMaisCaminho);
		return ResponseEntity.ok("Arquivo enviado");
	}
	
}
