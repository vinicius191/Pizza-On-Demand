package br.com.pizzaondemand.dao;

import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;
import br.com.pizzaondemand.modelo.Produto;
import java.util.List;

@Component
public class ProdutoDAO {
    
    private final Session session;

    public ProdutoDAO(Session session) {
        this.session = session;
    }
    
    public Produto obtemProdutoPorId(Long id) {
        return (Produto) session.createQuery("FROM Produto WHERE id = :id").setParameter("id", id).uniqueResult();
    }
    
    public List<Produto> lista() {
        return (List<Produto>) session.createQuery("FROM Produto").list();
    }
    
    public void salva(Produto produto) {
        session.save(produto);
    }
}
