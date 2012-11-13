/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaondemand.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.pizzaondemand.modelo.Pizzaria;
import br.com.pizzaondemand.modelo.PizzariaFormaPagamento;
import br.com.pizzaondemand.modelo.PizzariaFormaPagamentoWS;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author VINICIUS
 */
@Component
public class PizzariaFormaPagamentoDAO {
    
    private final Session session;

    public PizzariaFormaPagamentoDAO(Session session) {
        this.session = session;
    }
    
    public List<PizzariaFormaPagamento> lista() {
        return (List<PizzariaFormaPagamento>) session.createQuery("FROM PizzariaFormaPagamento").list();
    }
    
    public void salvar(PizzariaFormaPagamentoWS pizzariaFormaPagamentoWS) {
        Transaction tx = session.beginTransaction();
        session.createSQLQuery("INSERT INTO PizzariaFormaPagamento (formaPagamento_id, pizzaria_id) "
                + " VALUES (" + pizzariaFormaPagamentoWS.getFormaPagamento_id() + ", " 
                + pizzariaFormaPagamentoWS.getPizzaria_id() + ")" );
        tx.commit();
    }
    
    public void salvar(PizzariaFormaPagamento pizzariaFormaPagamento) {
        session.save(pizzariaFormaPagamento);
    }

    public void salvar(List<PizzariaFormaPagamentoWS> pizzariaFormaPagamentoWS) {
        session.save(pizzariaFormaPagamentoWS);
    }

    public List<PizzariaFormaPagamento> obtemListaFormasPagamentoPorPizzarias(List<Pizzaria> p) {
        return (List<PizzariaFormaPagamento>) session.createQuery("FROM PizzariaFormaPagamento WHERE pizzaria_id = :p")
                .setParameter("pizzaria_id", p);
    }
    
}
