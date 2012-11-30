package br.com.pizzaondemand.controller;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import java.util.List;
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
import java.math.BigDecimal;
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
        result.use(Results.xml()).from(listaUsuario).serialize();
    }

     /**
     * Método que recebe o ID do Pedido e o seu novo Status.
     *
     * @author Vinicius
     * @param id Long - Número do ID do Pedido
     * @param status int - Novo número do Status do Pedido
     * @return email - Envia um Email para o UsuarioAndroid confirmando que o
     * Pedido foi Aceito
     * @version 1.0
     * @since 26/11/2012
     */
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
                
                try {
                    AdministracaoEmail.EnviarEmail("Pedido Aceito - Pizza - On Demand!", ServidorController.montaEmailPeidoAceito(p), p.getUsuarioAndroid().getEmail());
                } catch (Exception e) {
                    System.out.println("Erro ao enviar emaill de Recuperação de Senha: " + e.toString());
                }
                
            } catch (HibernateException e) {
                resultado = "Deu erro :(";
                System.out.println("Deu erro ao obter ou salvar o pedido: " + e.toString());
                result.use(Results.json()).withoutRoot().from(resultado).serialize();
            }

            result.use(Results.json()).withoutRoot().from(resultado).serialize();

        }
    }
    
    /**
     * Método que recebe o ID do Pedido e o seu novo Status.
     *
     * @author Vinicius
     * @param id Long - Número do ID do Pedido
     * @param status int - Novo número do Status do Pedido
     * @return email - Envia um Email para o UsuarioAndroid confirmando que o
     * Pedido esta em Rota de Entrega.
     * @version 1.0
     * @since 26/11/2012
     */
    @Public
    @Path("/servidor/recebePedidoEntega/{id}/{status}")
    public void recebePedidoEntega(Long id, int status) {
        System.out.println("\n ============== ServidorController - recebePedidoEntega =============\n");
        if (id != 0 && status != 0) {
            String resultado = "Dados recebidos";
            System.out.println("Recebi o ID: " + id);
            System.out.println("Com status: " + status);

            try {
                Pedido p = pedidoDAO.obtemPedidoPorId(id);
                p.setStatus(status);
                pedidoDAO.atualiza(p);
                result.use(Results.json()).withoutRoot().from(resultado).serialize();
                
                if(status == 3) {
                    System.out.println("Status é 3 - Enviando email...");
                    try {
                        AdministracaoEmail.EnviarEmail("Pedido Aceito - Pizza - On Demand!", ServidorController.montaEmailPeidoEmRotaDeEntrega(p), p.getUsuarioAndroid().getEmail());
                    } catch (Exception e) {
                        System.out.println("Erro ao enviar emaill de Recuperação de Senha: " + e.toString());
                    }
                } else {
                    System.out.println("Status diferente de 3 - Email NÃO enviado!");
                }
            } catch (HibernateException e) {
                resultado = "Deu erro :(";
                System.out.println("Deu erro ao obter ou salvar o pedido: " + e.toString());
                result.use(Results.json()).withoutRoot().from(resultado).serialize();
            }

            result.use(Results.json()).withoutRoot().from(resultado).serialize();

        }
    }

    /**
     * Método que recebe o ID da Pizzaria e lista os Pedidos com Status = -1 da
     * Pizzaria.
     *
     * @author Vinicius
     * @param id Long - Número do ID da Pizzaria
     * @return listaPedido - Lista com os Pedidos com campo Status = -1
     * @version 1.0
     * @since 20/11/2012
     */
    @Public
    @Path("/servidor/listaPedidos/{id}")
    public void listaPedidos(Long id) {
        System.out.println("\n ============== ServidorController - listaPedidos =============\n");
        try {
            List<Pedido> listaPedidos = pedidoDAO.listaDisponiveisPorPizzaria(id);
            result.use(Results.json()).withoutRoot().from(listaPedidos).include("usuarioAndroid").include("formaPagamento").serialize();

            if (listaPedidos.isEmpty()) {
                System.out.println("Não existem pedidos disponpiveis..");
                result.use(Results.json()).from("").serialize();
            }
        } catch (Exception e) {
            System.out.println("Deu erro no listaPedidos: " + e.toString());
        }

    }

    /**
     * Método que recebe o ID da Pizzaria e lista os Pedidos com Status = 1 da
     * Pizzaria.
     *
     * @author Vinicius
     * @param id Long - Número do ID da Pizzaria
     * @return listaPedido - Lista com os Pedidos com campo Status = 1
     * @version 1.0
     * @since 26/11/2012
     */
    @Public
    @Path("/servidor/listaPedidosAceitos/{id}")
    public void listaPedidosAceitos(Long id) {
        System.out.println("\n ============== ServidorController - listaPedidosAceitos =============\n");
        try {
            List<Pedido> listaPedidos = pedidoDAO.listaAceitosPorPizzaria(id);
            result.use(Results.json()).withoutRoot().from(listaPedidos).include("usuarioAndroid").include("formaPagamento").serialize();

            if (listaPedidos.isEmpty()) {
                System.out.println("Não existem pedidos disponpiveis..");
                result.use(Results.json()).from("").serialize();
            }
        } catch (Exception e) {
            System.out.println("Deu erro no listaPedidos: " + e.toString());
        }

    }

    /**
     * Método que recebe via POST um JSON com os dados da Classe UsuarioAndroid.
     * É Criado um Objeto a partir do JSON e esse Objeto é salvo no BD. Em 
     * seguida é enviado um email para o UsuarioAndroid informando o cadastro.
     *
     * @author Vinicius
     * @param usuario UsuarioAndroid - Classe UsuarioAndroid em formato de JSON
     * @return email - Envia um Email para o UsuarioAndroid
     * @version 1.0
     * @since 26/11/2012
     */
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
                    result.use(Results.status()).badRequest("");
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

    /**
     * Método que recebe via GET a Latitude e a Longitude. Com esses dados é
     * feito o calculo e é listado as Pizzarias próximo em um raio de 6 KM
     *
     * @author Vinicius
     * @param latitude Double - Latitude do Usuario no Android
     * @param longitude Double - Longitude do Usuario no Android
     * @return pWS2 - Lista de Pizzarias num raio de 6KM
     * @version 1.0
     * @since 26/11/2012
     */  
    @Public
    @Get("/servidor/listaPizzariasProximas/{latitude}/{longitude}")
    public void listaPizzariaProximas(Double latitude, Double longitude) {
        System.out.println("\n ============== ServidorController - listaPizzariasProximas =============\n");
        try {
            List<Pizzaria> p = pizzariaDAO.listaPizzariasProximas(latitude, longitude);
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

                    pWS2.add(pWS);
                }
                result.use(Results.json()).withoutRoot().from(pWS2).serialize();
            }
        } catch (HibernateException e) {
            System.out.println("Erro ao receber a lista de Pizzarias: " + e.toString());
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
            if (!lPFP.isEmpty()) {
                System.out.println("A Lista de Forma de Pagamento não esta vazia... Enviando Lista...");
                List<FormaPagamento> list = new ArrayList<FormaPagamento>();
                for (int i = 0; i < lPFP.size(); i++) {
                    FormaPagamento _fP = new FormaPagamento();
                    _fP.setId(lPFP.get(i).getFormaPagamento().getId());
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
    public void recebePedidoAndroid(Pedido pedido) {
        System.out.println("\n ============== ServidorController - recebePedidoAndroid =============\n");
        try {
            if (pedido != null) {
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

    @Public
    @Path("/servidor/listaPedidosPorUsuarioAndroid/{id}")
    public void listaPedidosPorUsuarioAndroid(Long id) {
        System.out.println("\n ============== ServidorController - listaPedidosPorUsuarioAndroid =============\n");
        try {
            List<Pedido> lista = pedidoDAO.listaPedidosPorUsuarioAndroid(id);

            if (!lista.isEmpty()) {
                System.out.println("A lista de Pedidos (por UsuarioAndroid) não esta vazia. Enviando...");
                result.use(Results.json()).withoutRoot().from(lista).serialize();
            } else {
                System.out.println("A lista de Pedidos (por UsuarioAndroi) esta vazia!");
                result.use(Results.json()).from("", "").serialize();
            }
        } catch (Exception e) {
            System.out.println("Deu erro no listaPedidosPorUsuarioAndroid : " + e.toString());
            result.use(Results.json()).from("", "").serialize();
        }
    }

    @Public
    @Path("/servidor/mostraMediaAvaliacaoPositivaPedido/{id}")
    public void mostraMediaAvaliacaoPositivaPedido(Long id) {
        System.out.println("\n ============== ServidorController - mostraMediaAvaliacaoPositivaPedido =============\n");
        try {
            BigDecimal vlrFator = new BigDecimal(pedidoDAO.mostraMediaAvaliacoesPositivas(id));
            BigDecimal numFormatado = vlrFator.setScale(2, BigDecimal.ROUND_UP);
            System.out.println("Média que vai ser enviada: " + numFormatado);
            result.use(Results.json()).withoutRoot().from(numFormatado.doubleValue()).serialize();
        } catch (HibernateException e) {
            System.out.println("Deu erro ao receber a média em mostraMediaAvaliacaoPositivaPedido: " + e.toString());
            result.use(Results.json()).from("", "").serialize();
        }
    }

    @Public
    @Path("/servidor/mostraMediaAvaliacaoNegativaPedido/{id}")
    public void mostraMediaAvaliacaoNegativaPedido(Long id) {
        System.out.println("\n ============== ServidorController - mostraMediaAvaliacaoNegativaPedido =============\n");
        try {
            BigDecimal vlrFator = new BigDecimal(pedidoDAO.mostraMediaAvaliacoesNegativas(id));
            BigDecimal numFormatado = vlrFator.setScale(2, BigDecimal.ROUND_UP);
            System.out.println("Média que vai ser enviada: " + numFormatado);
            result.use(Results.json()).withoutRoot().from(numFormatado.doubleValue()).serialize();
        } catch (HibernateException e) {
            System.out.println("Deu erro ao receber a média em mostraMediaAvaliacaoNegativaPedido: " + e.toString());
            result.use(Results.json()).from("", "").serialize();
        }
    }

    @Public
    @Path("/servidor/mostraMediaGeralAvaliacaoPedido/{id}")
    public void mostraMediaGeralAvaliacaoPedido(Long id) {
        System.out.println("\n ============== ServidorController - mostraMediaGeralAvaliacaoPedido =============\n");
        try {
            BigDecimal vlrFator = new BigDecimal(pedidoDAO.mostraMediaGeralAvaliacoes(id));
            BigDecimal numFormatado = vlrFator.setScale(2, BigDecimal.ROUND_UP);
            System.out.println("Média que vai ser enviada: " + numFormatado);
            result.use(Results.json()).withoutRoot().from(numFormatado.doubleValue()).serialize();
        } catch (HibernateException e) {
            System.out.println("Deu erro ao receber a média em mostraMediaGeralAvaliacaoPedido: " + e.toString());
            result.use(Results.json()).from("", "").serialize();
        }
    }

    @Public
    @Path("/servidor/mostraQuantidadeAvaliacoesPositivasPedido/{id}")
    public void mostraQuantidadeAvaliacoesPositivasPedido(Long id) {
        System.out.println("\n ============== ServidorController - mostraQuantidadeAvaliacoesPositivasPedido =============\n");
        try {
            Long resultado = pedidoDAO.quantidadeAvaliacoesPositivas(id);
            System.out.println("Quantidade de Avaliações Positivas: " + resultado);
            result.use(Results.json()).withoutRoot().from(resultado).serialize();
        } catch (HibernateException e) {
            System.out.println("Deu erro ao receber a média em mostraQuantidadeAvaliacoesPositivasPedido: " + e.toString());
            result.use(Results.json()).from("", "").serialize();
        }
    }

    @Public
    @Path("/servidor/mostraQuantidadeAvaliacoesNegativasPedido/{id}")
    public void mostraQuantidadeAvaliacoesNegativasPedido(Long id) {
        System.out.println("\n ============== ServidorController - mostraQuantidadeAvaliacoesNegativasPedido =============\n");
        try {
            Long resultado = pedidoDAO.quantidadeAvaliacoesNegativas(id);
            System.out.println("Quantidade de Avaliações Negativas: " + resultado);
            result.use(Results.json()).withoutRoot().from(resultado).serialize();
        } catch (HibernateException e) {
            System.out.println("Deu erro ao receber a média em mostraQuantidadeAvaliacoesNegativasPedido: " + e.toString());
            result.use(Results.json()).from("", "").serialize();
        }
    }

    @Public
    @Path("/servidor/mostraQuantidadeGeralAvaliacoesPedido/{id}")
    public void mostraQuantidadeGeralAvaliacoesPedido(Long id) {
        System.out.println("\n ============== ServidorController - mostraQuantidadeGeralAvaliacoesPedido =============\n");
        try {
            Long resultado = pedidoDAO.quantidadeGeralAvaliacoes(id);
            System.out.println("Quantidade de Avaliações Negativas: " + resultado);
            result.use(Results.json()).withoutRoot().from(resultado).serialize();
        } catch (HibernateException e) {
            System.out.println("Deu erro ao receber a média em mostraQuantidadeGeralAvaliacoesPedido: " + e.toString());
            result.use(Results.json()).from("", "").serialize();
        }
    }

    @Public
    @Consumes(value = {"application/json", "application/x-www-form-urlencoded"})
    @Path("/servidor/salvaAvalicaoPositivaPedido")
    public void salvaAvalicaoPositivaPedido(Pedido pedido) {
        System.out.println("\n ============== ServidorController - salvaAvalicaoPositivaPedido =============\n");
        try {

            if (pedido.getId() != null) {
                System.out.println("Pedido não esta vazio. Salvando...");
                Pedido p = pedidoDAO.obtemPedidoPorId(pedido.getId());
                p.setAvaliacao(pedido.getAvaliacao());
                p.setMensagemAvaliacao(pedido.getMensagemAvaliacao());
                p.setStatusAvaliacao(1);
                System.out.println("Nota da avaliação do pedido [" + pedido.getId() + "] = " + p.getAvaliacao());
                System.out.println("Mensagem da avaliação do pedido [" + pedido.getId() + "] = " + p.getMensagemAvaliacao());
                pedidoDAO.atualiza(p);
                result.use(Results.json()).from("", "").serialize();
            } else {
                System.out.println("Pedido chegou com ID vazio em salvaAvalicaoPositivaPedido.");
                result.use(Results.json()).from("", "").serialize();
            }

        } catch (Exception e) {
            System.out.println("Deu erro salvaAvaliacaoPedido: " + e.toString());
            result.use(Results.json()).from("", "").serialize();
        }
    }

    @Public
    @Consumes(value = {"application/json", "application/x-www-form-urlencoded"})
    @Path("/servidor/salvaAvalicaoNegativaPedido")
    public void salvaAvalicaoNegativaPedido(Pedido pedido) {
        System.out.println("\n ============== ServidorController - salvaAvalicaoNegativaPedido =============\n");
        try {

            if (pedido.getId() != null) {
                System.out.println("Pedido não esta vazio. Salvando...");
                Pedido p = pedidoDAO.obtemPedidoPorId(pedido.getId());
                p.setAvaliacao(pedido.getAvaliacao());
                p.setMensagemAvaliacao(pedido.getMensagemAvaliacao());
                p.setStatusAvaliacao(2);
                System.out.println("Nota da avaliação do pedido [" + pedido.getId() + "] = " + p.getAvaliacao());
                System.out.println("Mensagem da avaliação do pedido [" + pedido.getId() + "] = " + p.getMensagemAvaliacao());
                pedidoDAO.atualiza(p);
                result.use(Results.json()).from("", "").serialize();
            } else {
                System.out.println("Pedido chegou com ID vazio em salvaAvalicaoNegativaPedido.");
                result.use(Results.json()).from("", "").serialize();
            }

        } catch (Exception e) {
            System.out.println("Deu erro salvaAvaliacaoPedido: " + e.toString());
            result.use(Results.json()).from("", "").serialize();
        }
    }

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
            result.use(Results.json()).from("mensagem", "Dados salvos com sucesso!").serialize();

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
    
    public static String montaEmailCadastroPizzaria(Pizzaria pizzaria) {
        StringBuilder sb = new StringBuilder();

        sb.append("Seja bem vindo a <strong>Pizza - On Demand!</strong>.");
        sb.append("<br />");
        sb.append("<br />");
        sb.append("Este é um email de confirmação do seu Cadastro.");
        sb.append("<br />");
        sb.append("Abaixo você encontra a confirmação dos seus dados de acesso ao Sistema Web:");
        sb.append("<br />");
        sb.append("<strong>Login:</strong> " + pizzaria.getEmail());
        sb.append("<br />");
        sb.append("<strong>Senha:</strong> " + pizzaria.getSenha());
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

    public static String montaEmailPeidoAceito(Pedido pedido) {
        StringBuilder sb = new StringBuilder();

        sb.append("Seu Pedido <strong>Pizza - On Demand!</strong>.");
        sb.append("<br />");
        sb.append("<br />");
        sb.append("Informamos que o seu Pedido: " + pedido.getDescricao() + " foi <strong>aceito<strong>.");
        sb.append("<br />");
        sb.append("<br />");
        sb.append("Em breve você receberá uma nova mensagem informando sobre a entrega do Pedido");
        sb.append("<br />");
        sb.append("<br />");
        sb.append("<br />");
        sb.append("<strong>Equipe Pizza - On Demand!</strong>");
        sb.append("<br />");

        String conteudo = sb.toString();

        return conteudo;
    }
    
    public static String montaEmailPeidoEmRotaDeEntrega(Pedido pedido) {
        StringBuilder sb = new StringBuilder();

        sb.append("Seu Pedido <strong>Pizza - On Demand!</strong>.");
        sb.append("<br />");
        sb.append("<br />");
        sb.append("O Pedido: " + pedido.getDescricao() + " está neste momento em rota de entrega, com tempo estimado de entrega de 35 minutos.");
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
            boolean u = ususAndroidDAO.verificaIMEI(usuarioAndroid);
            System.out.println("uuuuu  => " + u);
            if (u != false) {
                UsuarioAndroid uA = ususAndroidDAO.obtemUsuarioAndroidPorIMEI(usuarioAndroid);
                System.out.println("Email: " + uA.getEmail());
                String email = uA.getEmail();
                result.use(Results.json()).from(uA).serialize();

            } else {
                result.use(Results.json()).from("", "").serialize();
            }
        } catch (HibernateException e) {
            System.out.println("Deu erro no recuperarSenhaAndroid: " + e.toString());
            result.use(Results.json()).from("", "").serialize();
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
