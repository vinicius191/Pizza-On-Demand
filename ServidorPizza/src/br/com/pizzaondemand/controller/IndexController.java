package br.com.pizzaondemand.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.view.Results;
import br.com.pizzaondemand.dao.PedidoDAO;
import br.com.pizzaondemand.dao.PizzariaDAO;
import br.com.pizzaondemand.dao.ProdutoDAO;
import br.com.pizzaondemand.dao.UsuarioAndroidDAO;
import br.com.pizzaondemand.diversos.Public;
import br.com.pizzaondemand.modelo.Pedido;
import br.com.pizzaondemand.modelo.Pizzaria;
import br.com.pizzaondemand.modelo.Produto;
import br.com.pizzaondemand.modelo.UsuarioAndroid;
import br.com.pizzaondemand.modelo.UsuarioSession;
import java.util.List;

@Resource
public class IndexController {

    @SuppressWarnings("unused")
    private final Result result;
    private final PedidoDAO pedidoDAO;
    private final ProdutoDAO produtoDAO;
    private final UsuarioAndroidDAO usuarioAndroidDAO;
    private UsuarioSession usuarioSession;
    private final PizzariaDAO pizzariaDAO;

    public IndexController(
            Result result,
            PedidoDAO pedidoDAO,
            ProdutoDAO produtoDAO,
            UsuarioAndroidDAO usuarioAndroidDAO,
            UsuarioSession usuarioSession,
            PizzariaDAO pizzariaDAO) {

        this.result = result;
        this.pedidoDAO = pedidoDAO;
        this.produtoDAO = produtoDAO;
        this.usuarioAndroidDAO = usuarioAndroidDAO;
        this.usuarioSession = usuarioSession;
        this.pizzariaDAO = pizzariaDAO;
    }

    @Path("/index")
    public void index() {        
        Pizzaria pizzaria = pizzariaDAO.obtemPizzariaPorId(usuarioSession.getUser().getId());
//        List<Pizzaria> pizzaria = pizzariaDAO.lista();
        System.out.println("Entrei em Index - Imagem do Perfil: " + pizzaria.getImagemPerfil());
        System.out.println("Entrei em Index - Mensagem do Perfil: " + pizzaria.getMensagemPerfil());
        result.include("pizzaria", pizzaria);

    }
   
    @Public
    @Get("/perfil")
    public void perfil() {
        Pizzaria pizzaria = pizzariaDAO.obtemPizzariaPorId(usuarioSession.getUser().getId());
        System.out.println("Entrei em Perfil - Imagem do Perfil: " + pizzaria.getImagemPerfil());
        System.out.println("Entrei em Perfil - Mensagem do Perfil: " + pizzaria.getMensagemPerfil());
      
        System.out.println("Cheguei em Perfil!");
        result.include("pizzaria", pizzaria);
    }
    
    @Get("/edita")
    public void edita() {
        System.out.println("\n========== IndexController - edita ==========\n");
        Pizzaria pizzaria = pizzariaDAO.obtemPizzariaPorId(usuarioSession.getUser().getId());
        System.out.println("A senha que esta sendo enviada para o Edita Cadastro: " + pizzaria.getSenha());
        result.include("pizzaria", pizzaria);
    }

    @Get("/produto")
    public void produto() {
        System.out.println("\n========== IndexController - produto ==========\n");
        Pizzaria pizzaria = pizzariaDAO.obtemPizzariaPorId(usuarioSession.getUser().getId());
        result.include("pizzaria", pizzaria);
    }
    
//    @Public
    @Get("/mensagemPerfil")
    public void mensagemPerfil() {
        System.out.println("\n========== IndexController - mensagemPerfil ==========\n");
        Pizzaria pizzaria = pizzariaDAO.obtemPizzariaPorId(usuarioSession.getUser().getId());
        result.include("pizzaria", pizzaria);
    }
    
//    @Public
    @Get("/imagemPerfil")
    public void imagemPerfil() {
        System.out.println("\n========== IndexController - imagemPerfil ==========\n");
        Pizzaria pizzaria = pizzariaDAO.obtemPizzariaPorId(usuarioSession.getUser().getId());
        result.include("pizzaria", pizzaria);
    }

    @Public
    @Path("/teste")
    public void teste() {
        List<UsuarioAndroid> listaUsuarioAndroid = usuarioAndroidDAO.lista();
        List<Produto> lista = produtoDAO.lista();
        result.include("listaUsuarios", listaUsuarioAndroid);
        result.include("listaProdutos", lista);
        
    }
    
    @Public
    @Post("/cadastroPedido")
    public void cadastroPedido(Pedido pedido) {
        System.out.println("Entrei com sucesso...");
        System.out.println("ID do produto: " + pedido.getProduto().getId());
        if(pedido!=null) {
            pedidoDAO.salva(pedido);         
        }
        
        Produto produto = produtoDAO.obtemProdutoPorId(pedido.getProduto().getId());
        
        StringBuilder sB = new StringBuilder();
        sB.append(pedido.getId());
        sB.append(produto.getDescricao());
        sB.append(produto.getPreco1());
        
        String resultado = "";
        resultado = sB.toString();
        
        result.use(Results.xml()).from(sB.toString()).serialize();
    }
    
    @Public
    @Path("/testeJSON")
    public void testeJSON() {
    	
    }
}
