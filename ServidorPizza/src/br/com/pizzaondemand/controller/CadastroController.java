package br.com.pizzaondemand.controller;

import br.com.caelum.vraptor.Get;
import br.com.pizzaondemand.diversos.Public;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.multipart.UploadedFile;
import br.com.caelum.vraptor.view.Results;
import br.com.pizzaondemand.modelo.Pizzaria;
import br.com.pizzaondemand.dao.PizzariaDAO;
import br.com.pizzaondemand.diversos.Imagens;
import br.com.pizzaondemand.modelo.UsuarioSession;
import com.google.common.io.ByteStreams;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Resource
public class CadastroController {

    private Result result;
    private PizzariaDAO pizzariaDAO;
    private Imagens imagens;
    private UsuarioSession usuarioSession;

    public CadastroController(
            Result result,
            PizzariaDAO pizzariaDAO,
            UsuarioSession usuarioSession,
            Imagens imagens) {

        this.result = result;
        this.pizzariaDAO = pizzariaDAO;
        this.imagens = imagens;
        this.usuarioSession = usuarioSession;

    }

    @Public
    @Path("/cadastroPizzaria")
    public void cadastroPizzaria() {
//    	System.out.println("### Entrei em cadastroPizzaria ###");
//    	System.out.println("Nome da Pizzaria: " + pizzaria.getNomePizzaria());
//    	System.out.println("Email da Pizzaria: " + pizzaria.getEmailPizzaria());
//    	System.out.println("Senha: " + pizzaria.getSenha());
//    	System.out.println("CNPJ: " + pizzaria.getCnpj());
//    	System.out.println("Telefone 1: " + pizzaria.getTelefone1());
//    	System.out.println("Endere�o: " + pizzaria.getEndereco());
//    	System.out.println("Latitude: " + pizzaria.getLatitude());
//    	System.out.println("Longitude: " + pizzaria.getLongitude());
    }

    @Public
    @Post("/cadastrarPizzaria")
    public void cadastrarPizzaria(Pizzaria pizzaria) {
        if (pizzaria != null) {
            System.out.println("email: " + pizzaria.getEmail());
            
            pizzariaDAO.salvar(pizzaria);

            result.redirectTo(HomeController.class).home();
        } else {
            result.include("error", "Dados não foram salvos.").redirectTo(this).cadastroPizzaria();
        }
    }

    @Post("/atualizarPizzaria/{pizzaria.id}")
    public void atualizarPizzaria(Pizzaria pizzaria) {
        System.out.println("\n========== CadastroController - atualizaCadastro ==========\n");
        try {
            if(pizzaria != null) {
                System.out.println("Vou atualizar a pizzaria com email: " + pizzaria.getId());
                
                Pizzaria p = pizzariaDAO.obtemPizzariaPorId(pizzaria.getId());

                pizzariaDAO.atualizar(montaPizzariaParaAtualizar(pizzaria, p));
                
                result.redirectTo(IndexController.class).index();
            } else {
                result.use(Results.http()).body("Erro ao atualizar Pizzaria").sendError(403);
            }
        } catch (Exception e) {
            System.out.println("Erro ao atualizar Pizzaria: " + e.toString());
        }
    }
 
    public Pizzaria montaPizzariaParaAtualizar(Pizzaria antiga, Pizzaria nova) {
        if(antiga.getRazao_social()!=null) {
            nova.setRazao_social(antiga.getRazao_social());
        }
        if(antiga.getNome_fantasia()!=null) {
            nova.setNome_fantasia(antiga.getNome_fantasia());
        }
        if(antiga.getEmail()!=null) {
            nova.setEmail(antiga.getEmail());
        }
        if(antiga.getSenha()!=null) {
            nova.setSenha(antiga.getSenha());
        }
        if(antiga.getCnpj()!=null) {
            nova.setCnpj(antiga.getCnpj());
        }
        if(antiga.getTelefone1()!=null) {
            nova.setTelefone1(antiga.getTelefone1());
        }
        if(antiga.getTelefone2()!=null) {
            nova.setTelefone2(antiga.getTelefone2());
        }
        if(antiga.getTelefone3()!=null) {
            nova.setTelefone3(antiga.getTelefone3());
        }
        if(antiga.getComplemento()!=null) {
            nova.setComplemento(antiga.getComplemento());
        }
        if(antiga.getBairro()!=null) {
            nova.setBairro(antiga.getBairro());
        }
        if(antiga.getCep()!=null) {
            nova.setCep(antiga.getCep());
        }
        if(antiga.getCidade()!=null) {
            nova.setCidade(antiga.getCidade());
        }
        if(antiga.getEstado()!=null) {
            nova.setEstado(antiga.getEstado());
        }
        if(antiga.getLatitude()!=null) {
            nova.setLatitude(antiga.getLatitude());
        }
        if(antiga.getLongitude()!=null) {
            nova.setLongitude(antiga.getLongitude());
        }
        if(antiga.getMensagemPerfil()!=null) {
            nova.setMensagemPerfil(antiga.getMensagemPerfil());
        }
        if(antiga.getImagemPerfil()!=null) {
            nova.setImagemPerfil(antiga.getImagemPerfil());
        }
        
        return nova;
    }
    
    @Get("/editarCadastro")
    public void editarCadastro() {
       System.out.println("\n========== CadastroController - editarCadastro ==========\n");
       Pizzaria pizzaria = pizzariaDAO.obtemPizzariaPorId(usuarioSession.getUser().getId()); 
       result.include("pizzaria", pizzaria);
    }
    
    @Post("/atualizaMensagemPerfil/{pizzaria.id}")
    public void atualizaMensagemPerfil(Pizzaria pizzaria) {
        System.out.println("\n========== CadastroController - atualizaMensagemPerfil ==========\n");
        try {
            System.out.println("========== " + pizzaria.getMensagemPerfil());
            Pizzaria p = pizzariaDAO.obtemPizzariaPorId(pizzaria.getId());
            p.setMensagemPerfil(pizzaria.getMensagemPerfil());
            pizzariaDAO.atualizar(p);

            result.redirectTo(IndexController.class).index();
        } catch (NullPointerException e) {
            System.out.println("Erro: " + e.toString());
        }
    }

    @Post("/removeImagemPerfil/{pizzaria.id}")
    public void removeImagemPerfil(Pizzaria pizzaria) {
        System.out.println("\n========== CadastroController - removeImagemPerfil ==========\n");
        try {
            Pizzaria p = pizzariaDAO.obtemPizzariaPorId(pizzaria.getId());
            p.setImagemPerfil(null);
            pizzariaDAO.atualizar(p);
            
            imagens.exclui(pizzaria);
            
            result.redirectTo(IndexController.class).index();
        } catch (NullPointerException e) {
            System.out.println("Erro: " + e.toString());
        }
    }
    
    @Post("/atualizaImagemPerfil/{pizzaria.id}/imagem")
    public void atualizaImagemPerfil(Pizzaria pizzaria, final UploadedFile imagem) throws IOException {
        imagens.salva(imagem, pizzaria);
        
        InputStream is = imagem.getFile();
        System.out.println("Input stream da foto: " + is);
        byte[] fotoBytes = ByteStreams.toByteArray(is);        
        
        Pizzaria p = pizzariaDAO.obtemPizzariaPorId(pizzaria.getId());        
        p.setImagemPerfil(fotoBytes);
        
        System.out.println("Imagem: " + p.getImagemPerfil());
        
        pizzariaDAO.atualizar(p);    
        
        result.redirectTo(IndexController.class).index();
    }
    
    @Get
    @Path("/mostraImagem/{pizzaria.id}/imagem")
    public File mostraImagem(Pizzaria pizzaria) {
        System.out.println("Mostra imagem: " + pizzaria.getImagemPerfil());
        return imagens.mostra(pizzaria);
    }
    
    @Public
    @Post("/verificaEmail")
    public void verificaEmail(Pizzaria pizzaria) {
        System.out.println("Entrei em verifica email");
        result.use(Results.json()).withoutRoot().from(pizzariaDAO.verificaEmail(pizzaria)).serialize();
    }
}
