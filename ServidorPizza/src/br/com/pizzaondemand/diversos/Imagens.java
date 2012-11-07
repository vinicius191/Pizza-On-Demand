package br.com.pizzaondemand.diversos;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;


import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.ioc.Component;
//import br.com.pizzaondemand.modelo.Produto;
import br.com.pizzaondemand.modelo.Pizzaria;
import org.apache.tomcat.util.http.fileupload.IOUtils;

@Component
public class Imagens {

	private File pastaImagens;
	
	public Imagens(ServletContext context) {
		String caminhoImagens = context.getRealPath("/WEB-INF/imagens");
                System.out.println("Caminho da Image: " + caminhoImagens);
		pastaImagens = new File(caminhoImagens);
		pastaImagens.mkdir();
	}
	
	public void salva(UploadedFile imagem, Pizzaria pizzaria) {
            System.out.println("Entrei no salva imagem em Imagens");
		File destino = new File(pastaImagens, pizzaria.getId() + ".imagem");
		
		try {
			IOUtils.copyLarge(imagem.getFile(), new FileOutputStream(destino));
		} catch (Exception e) {
			throw new RuntimeException("Erro ao copiar imagem", e);
		}
	}
        
        public void exclui(Pizzaria pizzaria) {
            File destino = new File(pastaImagens, pizzaria.getId() + ".imagem");
            System.out.println("Arquivo que vou deletar?: " + destino.getAbsolutePath());
            try {
                destino.delete();
            } catch (Exception e) {
                System.out.println("Erro ao deletar arquivo -> " + e.toString());
            }
        }
        
	public File mostra(Pizzaria pizzaria) {
		return new File(pastaImagens, pizzaria.getId() + ".imagem");
	}
}
