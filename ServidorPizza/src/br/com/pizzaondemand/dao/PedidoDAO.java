package br.com.pizzaondemand.dao;

import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;
import br.com.pizzaondemand.modelo.Pedido;
import br.com.pizzaondemand.modelo.PedidoWS;
import java.util.List;
import org.hibernate.HibernateException;

@Component
public class PedidoDAO {
    
    private final Session session;

    public PedidoDAO(Session session) {
        this.session = session;
    }
    
    public void salva(Pedido pedido) {
        try {
           session.save(pedido); 
        } catch (HibernateException e) {
            System.out.println("Deu erro ao tentar salvar o Pedido : " + e.toString());
        }
        
    }
    
    public List<Pedido> lista() {
        return (List<Pedido>) session.createQuery("FROM Pedido").list();
    }
    
    public List<Pedido> listaDisponiveis() {
        return (List<Pedido>) session.createQuery("FROM Pedido WHERE status = -1").list();
    }
    
    public List<Pedido> listaDisponiveisPorPizzaria(Long pizzaria_id) {
        return (List<Pedido>) session.createQuery("FROM Pedido WHERE status = -1 AND pizzaria_id = :pizzaria_id")
                .setParameter("pizzaria_id", pizzaria_id).list();
    }
    
    public List<Pedido> listaAceitosPorPizzaria(Long pizzaria_id) {
        return (List<Pedido>) session.createQuery("FROM Pedido WHERE status = 1 AND pizzaria_id = :pizzaria_id")
                .setParameter("pizzaria_id", pizzaria_id).list();
    }
    
    public List<PedidoWS> listaWS() {
        return (List<PedidoWS>) session.createQuery("FROM Pedido").list();
    }
    
    public Pedido obtemPedidoPorId(Long id) {
        return (Pedido) session.createQuery("FROM Pedido WHERE id = :id").setParameter("id", id).uniqueResult();
    }

    public void atualiza(Pedido pedido) {
        session.merge(pedido);
    }

    public void salva(List<Pedido> pedido) {
        session.save(pedido);
    }
    
    public List<Pedido> listaPedidosPorUsuarioAndroid(Long usuarioAndroid_id) {
        return (List<Pedido>) session.createQuery("FROM Pedido WHERE usuarioAndroid_id = :usuarioAndroid_id "
                + "AND statusAvaliacao = 0 AND status >= 1")
                .setParameter("usuarioAndroid_id", usuarioAndroid_id)
                .list();
    }
    
    public Double mostraMediaAvaliacoesPositivas(Long usuarioAndroid_id) {
        return (Double) session.createQuery("SELECT AVG(avaliacao) FROM Pedido WHERE usuarioAndroid_id = :usuarioAndroid_id "
                + "AND statusAvaliacao = 1")
                .setParameter("usuarioAndroid_id", usuarioAndroid_id).uniqueResult();
    }
    
    public Double mostraMediaAvaliacoesNegativas(Long usuarioAndroid_id) {
        return (Double) session.createQuery("SELECT AVG(avaliacao) FROM Pedido WHERE usuarioAndroid_id = :usuarioAndroid_id "
                + "AND statusAvaliacao = 2")
                .setParameter("usuarioAndroid_id", usuarioAndroid_id).uniqueResult();
    }
    
    public Double mostraMediaGeralAvaliacoes(Long usuarioAndroid_id) {
        return (Double) session.createQuery("SELECT AVG(avaliacao) FROM Pedido WHERE usuarioAndroid_id = :usuarioAndroid_id "
                + "AND statusAvaliacao >= 1")
                .setParameter("usuarioAndroid_id", usuarioAndroid_id).uniqueResult();
    }
    
    public Long quantidadeAvaliacoesPositivas(Long usuarioAndroid_id) {
        return (Long) session.createQuery("SELECT COUNT(id) FROM Pedido WHERE usuarioAndroid_id = :usuarioAndroid_id "
                + "AND statusAvaliacao = 1")
                .setParameter("usuarioAndroid_id", usuarioAndroid_id).uniqueResult();
    }
    
    public Long quantidadeAvaliacoesNegativas(Long usuarioAndroid_id) {
        return (Long) session.createQuery("SELECT COUNT(id) FROM Pedido WHERE usuarioAndroid_id = :usuarioAndroid_id "
                + "AND statusAvaliacao = 2")
                .setParameter("usuarioAndroid_id", usuarioAndroid_id).uniqueResult();
    }
    
    public Long quantidadeGeralAvaliacoes(Long usuarioAndroid_id) {
        return (Long) session.createQuery("SELECT COUNT(id) FROM Pedido WHERE usuarioAndroid_id = :usuarioAndroid_id "
                + "AND statusAvaliacao >= 1")
                .setParameter("usuarioAndroid_id", usuarioAndroid_id).uniqueResult();
    }
}
