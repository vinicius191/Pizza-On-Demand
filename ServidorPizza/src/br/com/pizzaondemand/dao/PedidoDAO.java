package br.com.pizzaondemand.dao;

import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;
import br.com.pizzaondemand.modelo.Pedido;
import br.com.pizzaondemand.modelo.PedidoWS;
import java.util.List;

@Component
public class PedidoDAO {
    
    private final Session session;

    public PedidoDAO(Session session) {
        this.session = session;
    }
    
    public void salva(Pedido pedido) {
        session.save(pedido);
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
}
