/*
* 
*   Teste de envio do arquivo 2
* 
*/
package br.com.pizzaondemand.controller;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.pizzaondemand.modelo.PedidoWS;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.reflect.TypeToken;

import br.com.caelum.vraptor.view.Results;
import br.com.pizzaondemand.dao.PedidoDAO;
import br.com.pizzaondemand.diversos.Public;
import br.com.pizzaondemand.modelo.Pizzaria;
import br.com.pizzaondemand.dao.PizzariaDAO;
import br.com.pizzaondemand.dao.ProdutoDAO;
import br.com.pizzaondemand.dao.UsuarioAndroidDAO;
import br.com.pizzaondemand.diversos.AdministracaoEmail;
import br.com.pizzaondemand.modelo.Pedido;
import br.com.pizzaondemand.modelo.UsuarioAndroid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.HibernateException;
import java.lang.reflect.Type;

@Resource
public class ServidorController {

    private final Result result;
    private final PizzariaDAO pizzariaDAO;
    private final PedidoDAO pedidoDAO;
    private final ProdutoDAO produtoDAO;
    private final UsuarioAndroidDAO ususAndroidDAO;

    public ServidorController(
            PizzariaDAO pizzariaDAO,
            Result result,
            PedidoDAO pedidoDAO,
            ProdutoDAO produtoDAO,
            UsuarioAndroidDAO usuarioAndroidDAO) {

        this.result = result;
        this.pizzariaDAO = pizzariaDAO;
        this.pedidoDAO = pedidoDAO;
        this.produtoDAO = produtoDAO;
        this.ususAndroidDAO = usuarioAndroidDAO;
    }

    @Public
    @Path("/servidor/listaUsuarios")
    public void listaUsuarios() {
        List<Pizzaria> listaUsuario = pizzariaDAO.lista();
        result.use(Results.json()).withoutRoot().from(listaUsuario).serialize();
    }

    @Public
    @Path("/servidor/recebePedido/{id}/{status}")
    public void recebePedido(Long id, int status) {
        System.out.println("\n ============== ServidorController - reebePedido =============\n");
        if (id != 0 && status != 0) {
            String resultado = "Dados recebidos";
            System.out.println("Recebi o ID: " + id);
            System.out.println("Com status: " + status);
            
            Pedido p = pedidoDAO.obtemPedidoPorId(id);
            p.setStatus(status);
            pedidoDAO.atualiza(p);
            
            result.use(Results.json()).withoutRoot().from(resultado).serialize();
        }
    }
    
    @Public
    @Path("/servidor/testeRecebimentoParametros/{id}/")
    public void testeRecebimentoParametros(String data) {
        String recebido = "";
        if (data != null) {
            recebido = "Dados recebidos";
            System.out.println("\n\n===== Dados recebidos!: " + data + "");
            result.use(Results.json()).withoutRoot().from(recebido).serialize();
        } else {
            recebido = "Não recebi nada!";
            System.out.println("Não recebi nada!");
            result.nothing();
        }
    }

    @Public
    @Path("/servidor/testeJSON/{json}")
    public void testeJSON(String json) {

        String s = "{\"name\":\"MyNode\", \"width\":200, \"height\":100}";
        Object obj = JSONValue.parse(s);
        JSONObject jsonObj = (JSONObject) obj;
        System.out.println("======the 2nd element of array======");
        System.out.println("name=" + jsonObj.get("name"));
        System.out.println("width=" + jsonObj.get("width"));

        result.nothing();
    }

    @Public
    @Path("/servidor/listaPedidos")
    public void listaPedidos() {
        List<Pedido> listaPedidos = pedidoDAO.lista();
        List<PedidoWS> pWS2 = new ArrayList<PedidoWS>();
        PedidoWS pWS = null;
        StringBuilder sB = new StringBuilder();
        List<String> descricao = new ArrayList<String>();
        
        for(int i = 0; i < listaPedidos.size(); i++) {
            pWS = new PedidoWS();
            pWS.setId(listaPedidos.get(i).getId());
            pWS.setEnderecoEntrega(listaPedidos.get(i).getEnderecoEntrega());
            pWS.setQuantidade(listaPedidos.get(i).getQuantidade());
            pWS.setProduto(listaPedidos.get(i).getProduto().getDescricao());
            pWS.setStatus(listaPedidos.get(i).getStatus());
            pWS.setUsuarioAndroid(listaPedidos.get(i).getUsuarioAndroid().getNome());
            
            pWS2.add(pWS);
        }
        
//        result.use(Results.json()).withoutRoot().from(listaPedidos).include("produto.detalhe").serialize();
        result.use(Results.json()).withoutRoot().from(pWS2).serialize();
    }
    
    @Public
    @Path("/servidor/listaPedidosJSON")
    public void listaPedidosJSON() {
        List<Pedido> listaPedidos = pedidoDAO.lista();
        
        JSONObject obj = new JSONObject();
        
        for(int i = 0; i < listaPedidos.size(); i++) {
            obj.put("id", listaPedidos.get(i).getId());
            obj.put("descricao", listaPedidos.get(i).getProduto().getDescricao());
        }
        
        String teste = obj.toString();
        result.use(Results.json()).from(teste).serialize();
    }
    
    @Public
    @Get("/servidor/recebePedido/{pedido}")
    public void recebePedido(PedidoWS pedidoWS) {
//        result.use(Results.json()).withoutRoot().from(pedidoWS).serialize();
    }

    @Public
    @Consumes(value={"application/json", "application/x-www-form-urlencoded"})
    @Post("/servidor/salvaUsuario")
    public void salvaUsuarioAndroid(UsuarioAndroid usuario) {
    	System.out.println("Entrei em ======== SalvaUsuarioAndroid");
        try {
            
            System.out.println("Usuario que chegou (id): " + usuario.getId());
            System.out.println("Usuario que chegou (nome): " + usuario.getNome());
            System.out.println("Usuario que chegou (email): " + usuario.getEmail());
            
            try {
                
                if(ususAndroidDAO.verificaIMEI(usuario)) {
                    System.out.println("Já existe um IMEI cadastrado!!");                    
                    result.use(Results.json()).from("codigo", "1");                    
//                    result.include(1);
                    
                } else {
                    System.out.println("Não existe um IMEI cadastrado.. Programa pode continuar");
//                    result.use(Results.http()).body("O IMEI utilizado não esta cadastrado. Seu cadastro esta sendo realizado...").setStatusCode(200);
                    ususAndroidDAO.salvar(usuario);
                    result.use(Results.json()).from("codigo", "2");                   
//                    result.include(2);
 
                    try {
                        AdministracaoEmail.EnviarEmail("Cadastro - Pizza - On Demand!", montaEmailCadastroUsuario(usuario), usuario.getEmail());
                        result.use(Results.json()).from("codigo", "3");
//                        result.include(3);
                    } catch (Exception e) {
                        System.out.println("Erro ao enviar emaill no cadastro do UsuarioAndroid: " + e.toString());
                        result.use(Results.json()).from("codigo", "4");
//                        result.include(4);
                    }
                
                }
            } catch (HibernateException e) {
                System.out.println("Deu erro ao salvar o usuario: " + e.toString());
                result.include(5);
            }
            
        } catch (Exception e) {
            System.out.println("Erro ao salvar UsuarioAndroid: " + e.toString());
        }

    }

    @Public
    @Get("/mensagem")
    public void mensagem() {
        result.use(Results.http()).body("Parabens! Dados salvos.");
    }
    
    @Public
    @Get("/servidor/salvaUsuarioGet/{json}")
    public void salvaUsuarioGet(String json) {
        System.out.println("Entrei em ======== SalvaUsuarioAndroid");
        try {
            UsuarioAndroid usuarioAndroid = new UsuarioAndroid();

            Gson gson = new GsonBuilder().serializeNulls().create();
            Type collectionType = new TypeToken<Collection<UsuarioAndroid>>() {}.getType();

            usuarioAndroid = gson.fromJson(json, UsuarioAndroid.class);

            System.out.println("Usuario que eu montei: " + usuarioAndroid.getNome());

            ususAndroidDAO.salvar(usuarioAndroid);
            
            try {
                AdministracaoEmail.EnviarEmail("Cadastro - Pizza - On Demand!", montaEmailCadastroUsuario(usuarioAndroid), usuarioAndroid.getEmail());
            } catch (Exception e) {
                System.out.println("Erro ao enviar emaill no cadastro do UsuarioAndroid: " + e.toString());
            }
//            
            result.use(Results.json()).from("mensagem", "Dados salvos com sucesso!").serialize();
//            result.include("mensagem", "Dados salvas com sucesso!");
           
        } catch (Exception e) {
            System.out.println("Erro ao salvar UsuarioAndroid: " + e.toString());
        }

    }
    
    public static String montaEmailCadastroUsuario(UsuarioAndroid usuario) {
        StringBuilder sb = new StringBuilder();

        sb.append("Seja bem vindo a <strong>Pizza - On Demand!</strong>.");
        sb.append("<br />");
        sb.append("<br />");
        sb.append("Abaixo você encontra a confirmação dos seus dados de acesso ao aplicativo:");
        sb.append("<br />");
        sb.append("<br />");
        sb.append("<strong>Email:</strong> " + usuario.getEmail());
        sb.append("<br />");
        sb.append("<strong>Senha:</strong> " + usuario.getSenha());
        sb.append("<br />");
        sb.append("<br />");
        sb.append("<br />");
        sb.append("<br />");
        sb.append("<strong>Equipe Pizza - On Demand!</strong>");
        sb.append("<br />");

        String conteudo = sb.toString();
        
        return conteudo;
    }
    
    @Public
    @Path("/servidor/listaUsuarioAndroid")
    public void listaUsuarioAndroid() {
    	List<UsuarioAndroid> lista = ususAndroidDAO.lista();
    	
    	result.use(Results.xml()).from(lista).serialize();
    }
}
