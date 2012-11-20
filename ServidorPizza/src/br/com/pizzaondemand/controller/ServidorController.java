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
import br.com.pizzaondemand.dao.FormaPagamentoDAO;
import br.com.pizzaondemand.dao.PedidoDAO;
import br.com.pizzaondemand.diversos.Public;
import br.com.pizzaondemand.modelo.Pizzaria;
import br.com.pizzaondemand.dao.PizzariaDAO;
import br.com.pizzaondemand.dao.PizzariaFormaPagamentoDAO;
import br.com.pizzaondemand.dao.ProdutoDAO;
import br.com.pizzaondemand.dao.UsuarioAndroidDAO;
import br.com.pizzaondemand.diversos.AdministracaoEmail;
import br.com.pizzaondemand.modelo.FormaPagamento;
import br.com.pizzaondemand.modelo.Pedido;
import br.com.pizzaondemand.modelo.PizzariaFormaPagamento;
import br.com.pizzaondemand.modelo.PizzariaWS;
import br.com.pizzaondemand.modelo.Produto;
import br.com.pizzaondemand.modelo.UsuarioAndroid;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Collection;
import org.hibernate.HibernateException;
import java.lang.reflect.Type;
import java.util.Random;

@Resource
public class ServidorController {

    private final Result result;
    private final PizzariaDAO pizzariaDAO;
    private final PedidoDAO pedidoDAO;
    private final ProdutoDAO produtoDAO;
    private final UsuarioAndroidDAO ususAndroidDAO;
    private final PizzariaFormaPagamentoDAO pizzariaFormaPagamentoDAO;
    private final FormaPagamentoDAO formaPagamentoDAO;

    public ServidorController(
            PizzariaDAO pizzariaDAO,
            Result result,
            PedidoDAO pedidoDAO,
            ProdutoDAO produtoDAO,
            UsuarioAndroidDAO usuarioAndroidDAO,
            PizzariaFormaPagamentoDAO pizzariaFormaPagamentoDAO,
            FormaPagamentoDAO formaPagamentoDAO) {

        this.result = result;
        this.pizzariaDAO = pizzariaDAO;
        this.pedidoDAO = pedidoDAO;
        this.produtoDAO = produtoDAO;
        this.ususAndroidDAO = usuarioAndroidDAO;
        this.formaPagamentoDAO = formaPagamentoDAO;
        this.pizzariaFormaPagamentoDAO = pizzariaFormaPagamentoDAO;
    }

    @Public
    @Path("/servidor/listaUsuarios")
    public void listaUsuarios() {
        List<Pizzaria> listaUsuario = pizzariaDAO.lista();
//        result.use(Results.json()).withoutRoot().from(listaUsuario).serialize();
        result.use(Results.xml()).from(listaUsuario).serialize();
    }

    @Public
    @Path("/servidor/recebePedido/{id}/{status}")
    public void recebePedido(Long id, int status) {
        System.out.println("\n ============== ServidorController - reebePedido =============\n");
        if (id != 0 && status != 0) {
            String resultado = "Dados recebidos";
            System.out.println("Recebi o ID: " + id);
            System.out.println("Com status: " + status);

            try {
                Pedido p = pedidoDAO.obtemPedidoPorId(id);
                p.setStatus(status);
                pedidoDAO.atualiza(p);
                result.use(Results.json()).withoutRoot().from(resultado).serialize();
            } catch (HibernateException e) {
                resultado = "Deu erro :(";
                System.out.println("Deu erro ao obter ou salvar o pedido: " + e.toString());
                result.use(Results.json()).withoutRoot().from(resultado).serialize();
            }

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

    /*
     @Public
     @Path("/servidor/listaPedidos")
     public void listaPedidos() {
     List<Pedido> listaPedidos = pedidoDAO.listaDisponiveis();
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
     */

    /*
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
     */
    @Public
    @Get("/servidor/recebePedido/{pedido}")
    public void recebePedido(PedidoWS pedidoWS) {
//        result.use(Results.json()).withoutRoot().from(pedidoWS).serialize();
    }

    @Public
    @Consumes(value = {"application/json", "application/x-www-form-urlencoded"})
    @Post("/servidor/salvaUsuario")
    public void salvaUsuarioAndroid(UsuarioAndroid usuario) {
        System.out.println("Entrei em ======== SalvaUsuarioAndroid");
        try {

            System.out.println("Usuario que chegou (id): " + usuario.getId());
            System.out.println("Usuario que chegou (nome): " + usuario.getNome());
            System.out.println("Usuario que chegou (email): " + usuario.getEmail());

            try {

                if (ususAndroidDAO.verificaIMEI(usuario)) {
                    System.out.println("Já existe um IMEI cadastrado!!");
//                    result.use(Results.json()).from("codigo", "1").serialize();
                    result.use(Results.status()).badRequest("");
//                    
                } else {
                    System.out.println("Não existe um IMEI cadastrado.. Programa pode continuar");
                    ususAndroidDAO.salvar(usuario);
                    result.use(Results.json()).from("codigo", "2").serialize();

                    try {
                        AdministracaoEmail.EnviarEmail("Cadastro - Pizza - On Demand!", montaEmailCadastroUsuario(usuario), usuario.getEmail());
                        result.use(Results.json()).from("codigo", "3").serialize();
                    } catch (Exception e) {
                        System.out.println("Erro ao enviar emaill no cadastro do UsuarioAndroid: " + e.toString());
                        result.use(Results.json()).from("codigo", "4").serialize();
                    }

                }
            } catch (HibernateException e) {
                System.out.println("Deu erro ao salvar o usuario: " + e.toString());
                result.use(Results.json()).from("codigo", "5").serialize();
            }

        } catch (Exception e) {
            System.out.println("Erro ao salvar UsuarioAndroid: " + e.toString());
            result.use(Results.json()).from("codigo", "6").serialize();
        }

    }

    @Public
    @Get("/servidor/listaPizzariasProximas/{latitude}/{longitude}")
    public void listaPizzariaProximas(Double latitude, Double longitude) {
        System.out.println("\n ============== ServidorController - listaPizzariasProximas =============\n");
        try {
            List<Pizzaria> p = pizzariaDAO.listaPizzariasProximas(latitude, longitude);
//            List<PizzariaFormaPagamento> pFP = pizzariaFormaPagamentoDAO.obtemListaFormasPagamentoPorPizzarias(p);
            if (p == null) {
                result.use(Results.xml()).from("Não há nenhuma Pizzaria próxima a sua localização.", "mensagem").serialize();
            } else {
                PizzariaWS pWS = null;
                List<PizzariaWS> pWS2 = new ArrayList<PizzariaWS>();
                for (int i = 0; i < p.size(); i++) {
                    pWS = new PizzariaWS();

                    pWS.setId(p.get(i).getId());
                    pWS.setNomeFantasia(p.get(i).getNome_fantasia());
                    pWS.setMensagemPerfil(p.get(i).getMensagemPerfil());
                    pWS.setLatitude(p.get(i).getLatitude());
                    pWS.setLongitude(p.get(i).getLongitude());
//                    pWS.setFormaPagamento(p.get(i).getPizzariasFormasPagamento());

                    pWS2.add(pWS);
                }
//                result.use(Results.json()).withoutRoot().from(pWS2).include("formaPagamento").serialize();
                result.use(Results.json()).withoutRoot().from(pWS2).serialize();
            }
        } catch (HibernateException e) {
            System.out.println("Erro ao receber a lista de Pizzarias: " + e.toString());
//            result.use(Results.json()).from("Tivemos um problema ao pesquisar as Pizzarias. Tente novamente mais tarde.", "mensagem").serialize();
            result.use(Results.json()).from("", "").serialize();
        }

    }

    @Public
    @Get("/servidor/listaProdutosPorPizzaria/{pizzaria_id}")
    public void listaProdutosPorPizzaria(Long pizzaria_id) {
        System.out.println("\n ============== ServidorController - listaProdutosPorPizzaria =============\n");
        try {
            List<Produto> p = produtoDAO.obtemProdutoPorPizzariaId(pizzaria_id);
            if (p.size() > 0) {
                System.out.println("A Lista de Produtos não esta vazia... Enviando Lista...");
                result.use(Results.json()).from(p).include("tamanho").serialize();
            } else {
                System.out.println("A Lista de Produtos esta VAZIA... Retornando json vazio para cliente...");
                result.use(Results.json()).from("", "").serialize();
            }
        } catch (HibernateException e) {
            System.out.println("Deus erro ao lista os Produto: " + e.toString());
            result.use(Results.json()).from("", "").serialize();
        }
    }

    @Public
    @Path("/servidor/listaFormaPagamento/{pizzaria_id}")
    public void listaFormaPagamento(Long pizzaria_id) {
        System.out.println("\n ============== ServidorController - listaFormaPagamento =============\n");
        try {
            List<PizzariaFormaPagamento> lPFP = pizzariaFormaPagamentoDAO.obtemListaFormasPagamentoPorPizzarias(pizzaria_id);
            if(!lPFP.isEmpty()) {
                System.out.println("A Lista de Forma de Pagamento não esta vazia... Enviando Lista...");
                List<FormaPagamento> list = new ArrayList<FormaPagamento>();
                for(int i = 0; i < lPFP.size(); i++) {
                    FormaPagamento _fP = new FormaPagamento();
                    _fP.setId(lPFP.get(i).getId());
                    _fP.setDescricao(lPFP.get(i).getFormaPagamento().getDescricao());
                    list.add(_fP);
                }
                result.use(Results.json()).from(list).serialize();
            } else {
                System.out.println("A Lista de Forma de Pagamento esta VAZIA... Retornando json vazio para cliente...");
                result.use(Results.json()).from("", "").serialize();
            }
        } catch (HibernateException e) {
            System.out.println("Erro ao receber a lista de Formas de Pagamento : " + e.toString());
            result.use(Results.json()).from("", "").serialize();
        }
    }
    
    @Public
    @Consumes(value = {"application/json", "application/x-www-form-urlencoded"})
    @Path("/servidor/recebePedidoAndroid")
    public void recebePedidoAndroid(Pedido pedido) throws Exception {
        System.out.println("\n ============== ServidorController - recebePedidoAndroid =============\n");
        try {
            if(pedido != null) {
//                System.out.println("OK");
                pedidoDAO.salva(pedido);
                result.use(Results.json()).from("OK", "OK").serialize();
            }
            System.out.println("Pedido - Endereço Entrega: " + pedido.getEnderecoEntrega());
            System.out.println("Pedido - Endereço Entrega: " + pedido.getDescricao());
            System.out.println("Pedido - Endereço Entrega: " + pedido.getDataPedido());
            System.out.println("Pedido - Status: " + pedido.getStatus());
            System.out.println("Pedido - Forma de Pagamento: " + pedido.getFormaPagamento().getId());
            System.out.println("Pedido - Valor: " + pedido.getValor());
            System.out.println("Pedido - Troco: " + pedido.getTroco());
            System.out.println("Pedido - Pizzaria: " + pedido.getPizzaria().getId());
            System.out.println("Pedido - UsuarioAndroid: " + pedido.getUsuarioAndroid().getId());

        } catch (NullPointerException e) {
            System.out.println("Deu erro ao receber classe Pedido: " + e.toString());
        } catch (Exception e) {
            System.out.println("Deu erro quando chamou o metodo de Pedido: " + e.toString());
        }
    }
    /*  
     @Public
     @Path("/servidor/listaPizzarias")
     public void listaPizzarias() {
     try {
     List<Pizzaria> p = pizzariaDAO.lista();
     //            List<PizzariaFormaPagamento> pFP = pizzariaFormaPagamentoDAO.obtemListaFormasPagamentoPorPizzarias(p);
     List<String> descricao = 
     //            Pizzaria pizzaria = new Pizzaria();
     PizzariaWS pWS = null;
     List<PizzariaWS> pWS2 = new ArrayList<PizzariaWS>();
     if(!p.contains("<pizzaria>")) {
     for(int i = 0; i < p.size(); i++) {
     pWS = new PizzariaWS();
     pWS.setRazaoSocial(p.get(i).getRazao_social());
     pWS.setMensagemPerfil(p.get(i).getMensagemPerfil());
     pWS.setLatitude(p.get(i).getLatitude());
     pWS.setLongitude(p.get(i).getLongitude());
     pWS.setFormaPagamento(p.get(i).getPizzariasFormasPagamento());
                    
     pWS2.add(pWS);
     }
                
     result.use(Results.json()).withoutRoot().from(pWS2).include("formaPagamento").serialize();
     } else {
     result.use(Results.xml()).from("Erro", "Erro").serialize();
     }
     } catch (Exception e) {
     System.out.println("Deu erro: " + e.toString());
     }
        
        
     }
     */

    @Public
    @Path("/mensagem")
    public void mensagem() {
        result.use(Results.xml()).from("codigo", "1").serialize();
    }

    @Public
    @Get("/servidor/salvaUsuarioGet/{json}")
    public void salvaUsuarioGet(String json) {
        System.out.println("Entrei em ======== SalvaUsuarioAndroid");
        try {
            UsuarioAndroid usuarioAndroid = new UsuarioAndroid();

            Gson gson = new GsonBuilder().serializeNulls().create();
            Type collectionType = new TypeToken<Collection<UsuarioAndroid>>() {
            }.getType();

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

    public static String montaEmailRedefinicaoDeSenhaPizzaria(Pizzaria pizzaria) {
        StringBuilder sb = new StringBuilder();

        sb.append("Recuperação de senha <strong>Pizza - On Demand!</strong>.");
        sb.append("<br />");
        sb.append("<br />");
        sb.append("Abaixo você encontra a confirmação da sua nova senha de acesso ao sistema.");
        sb.append("<br />");
        sb.append("<br />");
        sb.append("<strong>Email:</strong> " + pizzaria.getEmail());
        sb.append("<br />");
        sb.append("<strong><b>Nova</b> Senha:</strong> " + pizzaria.getSenha());
        sb.append("<br />");
        sb.append("<br />");
        sb.append("Utilize esta nova senha para fazer Login.");
        sb.append("<br />");
        sb.append("Após entrar você poderá redefinir a sua senha.");
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

    @Public
    @Path("/servidor/recuperarSenhaUsuarioAndroid/{usuarioAndroid.email}")
    public void recuperarSenhaUsuarioAndroid(UsuarioAndroid usuarioAndroid) {
        System.out.println("\n========== ServidorController - recuperarSenhaAndroid ==========\n");
        try {
            if (!ususAndroidDAO.verificaIMEI(usuarioAndroid)) {
                UsuarioAndroid u = ususAndroidDAO.obtemUsuarioAndroidPorIMEI(usuarioAndroid);

                result.use(Results.json()).from(u).serialize();

            } else {
                result.use(Results.json()).from("").serialize();
            }
        } catch (HibernateException e) {
            System.out.println("Deu erro no recuperarSenhaAndroid: " + e.toString());
            result.notFound();
        }
    }

    @Public
    @Path("/servidor/verificarUsuarioAndroid/{usuarioAndroid.id}")
    public void verificarUsuarioAndroid(UsuarioAndroid usuarioAndroid) {
        System.out.println("\n========== ServidorController - verificaUsuarioAndroid ==========\n");
        try {
            if (!ususAndroidDAO.verificaIMEI(usuarioAndroid)) {
                UsuarioAndroid u = ususAndroidDAO.obtemUsuarioAndroidPorIMEI(usuarioAndroid);

                result.use(Results.json()).from(u).serialize();

            } else {
                result.use(Results.json()).from("").serialize();
            }
        } catch (HibernateException e) {
            System.out.println("Deu erro no recuperarSenhaAndroid: " + e.toString());
            result.notFound();
        }
    }

    public static String geraHashAleatorio(int qtDigitos) {
        int inicioNumeros = 48;
        int inicioMaiusculas = 65;
        int inicioMinusculas = 97;
        char[] caracteres = new char[qtDigitos];
        int valor;
        for (int i = 0; i < qtDigitos; i++) {
            Random r0 = new Random();
            int tipo = r0.nextInt(3);
            if (tipo == 0) {
                valor = inicioNumeros + r0.nextInt(10);
                caracteres[ i] = (char) (valor);
            } else if (tipo == 1) {
                valor = inicioMaiusculas + r0.nextInt(26);
                caracteres[ i] = (char) (valor);
            } else if (tipo == 2) {
                valor = inicioMinusculas + r0.nextInt(26);
                caracteres[ i] = (char) (valor);
            }
        }

        return String.valueOf(caracteres);
    }
}
