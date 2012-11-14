package br.com.pizzaondemand.dao;

import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;
import br.com.pizzaondemand.modelo.Pizzaria;
import br.com.pizzaondemand.modelo.Produto;
import java.util.List;
import org.hibernate.LockOptions;
import org.hibernate.Transaction;

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
    
    public void exclui(Produto produto) {
        session.delete(produto);
    }
    
//    public Produto obtemPizzariaPorIdProduto(Long id) {
//        return (Produto) session.createQuery("FROM Produto where ")
//    }
    
//    public void excluiProduto(Produto produto) {
//        System.out.println("ProdutoDAO exclui: " + produto.getId() + " " + produto.getPizzaria().getId());
//        session.createQuery("DELETE FROM Produto WHERE id = " + produto.getId() + " AND pizzaria_id = " + produto.getPizzaria().getId());
//        tx = session.beginTransaction(); 
//        tx.commit();
//    }

    public List<Produto> obtemProdutoPorPizzariaId(Long pizzaria_id) {
        return (List<Produto>) session.createQuery("FROM Produto WHERE pizzaria_id = :pizzaria_id")
                .setParameter("pizzaria_id", pizzaria_id).list();
    }
}
