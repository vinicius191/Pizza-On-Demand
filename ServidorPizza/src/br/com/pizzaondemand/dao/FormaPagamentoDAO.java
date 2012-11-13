/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzaondemand.dao;

import br.com.caelum.vraptor.ioc.Component;
import br.com.pizzaondemand.modelo.FormaPagamento;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author VINICIUS
 */
@Component
public class FormaPagamentoDAO {
    
    private final Session session;

    public FormaPagamentoDAO(Session session) {
        this.session = session;
    }
    
    public List<FormaPagamento> lista() {
        return (List<FormaPagamento>) session.createQuery("FROM FormaPagamento").list();
    }
}
