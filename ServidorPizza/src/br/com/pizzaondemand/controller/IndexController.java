package br.com.pizzaondemand.controller;

import br.com.caelum.vraptor.*;
import br.com.caelum.vraptor.view.Results;
import br.com.pizzaondemand.dao.FormaPagamentoDAO;
import br.com.pizzaondemand.dao.PedidoDAO;
import br.com.pizzaondemand.dao.PizzariaDAO;
import br.com.pizzaondemand.dao.PizzariaFormaPagamentoDAO;
import br.com.pizzaondemand.dao.ProdutoDAO;
import br.com.pizzaondemand.dao.UsuarioAndroidDAO;
import br.com.pizzaondemand.diversos.Public;
import br.com.pizzaondemand.modelo.FormaPagamento;
import br.com.pizzaondemand.modelo.Pizzaria;
import br.com.pizzaondemand.modelo.PizzariaFormaPagamento;
import br.com.pizzaondemand.modelo.PizzariaFormaPagamentoWS;
import br.com.pizzaondemand.modelo.Produto;
import br.com.pizzaondemand.modelo.UsuarioSession;
import java.util.ArrayList;
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
    private final PizzariaFormaPagamentoDAO pizzariaFormaPagamentoDAO;
    private final FormaPagamentoDAO formaPagamentoDAO;

    public IndexController(
            Result result,
            PedidoDAO pedidoDAO,
            ProdutoDAO produtoDAO,
            UsuarioAndroidDAO usuarioAndroidDAO,
            UsuarioSession usuarioSession,
            PizzariaDAO pizzariaDAO,
            PizzariaFormaPagamentoDAO pizzariaFormaPagamentoDAO,
            FormaPagamentoDAO formaPagamentoDAO) {

        this.result = result;
        this.pedidoDAO = pedidoDAO;
        this.produtoDAO = produtoDAO;
        this.usuarioAndroidDAO = usuarioAndroidDAO;
        this.usuarioSession = usuarioSession;
        this.pizzariaDAO = pizzariaDAO;
        this.pizzariaFormaPagamentoDAO = pizzariaFormaPagamentoDAO;
        this.formaPagamentoDAO = formaPagamentoDAO;
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
    
    @Public
    @Get("/listaProduto")
    public void listaProduto() {
       System.out.println("\n========== IndexController - listaProduto ==========\n");
       List<Produto> p = produtoDAO.lista();
       result.include("produto", p);
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
//        List<UsuarioAndroid> listaUsuarioAndroid = usuarioAndroidDAO.lista();
//        List<Produto> lista = produtoDAO.lista();
//        result.include("listaUsuarios", listaUsuarioAndroid);
//        result.include("listaProdutos", lista);
        Pizzaria p = pizzariaDAO.obtemPizzariaPorId(1L);
        List<FormaPagamento> fP = formaPagamentoDAO.lista();
        result.include("listaFormaPagamento", fP);
        result.include("pizzaria", p);
//        Pizzaria p = new Pizzaria();
//        PizzariaFormaPagamento pFP = null;
//        p.setPizzariasFormasPagamento(null);
        
    }

    @Public
    @Post("/teste/cadastro/{pizzaria.id}")
    public void cadastro(List<PizzariaFormaPagamento> pizzariaFormaPagamento, Pizzaria pizzaria) {
        for( int i = 0; i < pizzariaFormaPagamento.size(); i++ ) {
            PizzariaFormaPagamento pFP = new PizzariaFormaPagamento();
            
            pFP.setFormaPagamento(pizzariaFormaPagamento.get(i).getFormaPagamento());
            pFP.setPizzaria(pizzaria);
            
            pizzariaFormaPagamentoDAO.salvar(pFP);
            
        }
        
        try {
            result.nothing();
        } catch (Exception e) {
            result.notFound();
        }
    }
    
    @Public
    @Post("/cadastroPedido")
    public void cadastroPedido(List<String> pedido) {
        System.out.println("Entrei com sucesso...");
        for(int i = 0; i < pedido.size(); i++) {
            System.out.println("-> " + pedido.get(i).toString());
        }
//        System.out.println("ID do produto: " + pedido.getProduto().getId());
//        if(pedido!=null) {
//            pedidoDAO.salva(pedido);         
//        }
        
//        Produto produto = produtoDAO.obtemProdutoPorId(pedido.getProduto().getId());
//        
//        StringBuilder sB = new StringBuilder();
//        sB.append(pedido.getId());
//        sB.append(produto.getDescricao());
//        sB.append(produto.getPreco1());
//        
//        String resultado = "";
//        resultado = sB.toString();
        
        result.use(Results.xml()).from(pedido).serialize();
    }
    
    @Public
    @Path("/testeJSON")
    public void testeJSON() {
    	
    }
}
