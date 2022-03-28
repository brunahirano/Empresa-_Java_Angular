package soulCode.empresa.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

//essa rotina é como se fosse o serviço separado como util para fazer um upload de arquivo
public class uploadFileUtil {
	//multipartfile do spring para trabalhar com arquivo 
		public static void salvarArquivo(String uploadDir, String fileName, MultipartFile file) throws IOException {
			
			Path uploadPath = Paths.get(uploadDir);
			
			if(!Files.exists(uploadPath)) { //se o caminho(uploadPath) não existir, nós vamos criar 
				Files.createDirectories(uploadPath);
			}
			
			try(InputStream inputStreamm = file.getInputStream()) {//identifica o arquivo e tras em forma de byte, faz a leitura do arquivo que selecionamos
				Path filePath = uploadPath.resolve(fileName);
				Files.copy(inputStreamm, filePath, StandardCopyOption.REPLACE_EXISTING);
			}
			catch(IOException e){
				throw new IOException("Não foi possível enviar o seu arquivo" + fileName, e);
			}
			
		}
}
