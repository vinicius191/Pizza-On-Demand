package br.com.pizzaondemand.dao;

import java.util.List;

import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;
import br.com.pizzaondemand.modelo.Pizzaria;

@Component
public class PizzariaDAO {

    private final Session session;

    public PizzariaDAO(Session session) {
        this.session = session;
    }

    @SuppressWarnings("unchecked")
    public List<Pizzaria> lista() {
        return (List<Pizzaria>) session.createQuery("FROM Pizzaria").list();
    }

    public Pizzaria autenticar(String email, String senha) {

        System.out.println("PizzariaDAO email: " + email);
        System.out.println("PizzariaDAO senha: " + senha);

        return (Pizzaria) session.createQuery(
                "FROM Pizzaria WHERE email = :email AND senha = :senha").setParameter("email", email).setParameter("senha", senha).uniqueResult();

    }
    
    public Pizzaria obtemPizzariaPorId(Long id) {
        return (Pizzaria) session.createQuery("FROM Pizzaria WHERE id = :id").setParameter("id", id).uniqueResult();
    }

    public void salvar(Pizzaria pizzaria) {
        System.out.println("PizzariaDAO: " + pizzaria.getEmail());
        session.save(pizzaria);
    }
    
    public void atualizar(Pizzaria pizzaria) {
        System.out.println("PizzariaDAO - Mensagem que vai ser salva: " + pizzaria.getMensagemPerfil());
        session.merge(pizzaria);
    }
    
    public boolean verificaEmail(Pizzaria pizzaria) {
        Pizzaria p = (Pizzaria) session.createQuery("FROM Pizzaria WHERE email = :email").setParameter("email", pizzaria.getEmail()).uniqueResult();
    
        return p == null;
    }
}