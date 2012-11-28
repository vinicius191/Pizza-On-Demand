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
    
    public Pizzaria obtemPizzariaPorEmail(String email) {
        return (Pizzaria) session.createQuery("FROM Pizzaria WHERE email = :email").setParameter("email", email).uniqueResult();
    }

    public void salvar(Pizzaria pizzaria) {
        System.out.println("PizzariaDAO: " + pizzaria.getEmail());
        session.save(pizzaria);
    }
    
    public void atualizar(Pizzaria pizzaria) {
        System.out.println("PizzariaDAO - Vou atualizar o usuario: " + pizzaria.getEmail());
        session.merge(pizzaria);
    }
    
    public boolean verificaEmail(Pizzaria pizzaria) {
        Pizzaria p = (Pizzaria) session.createQuery("FROM Pizzaria WHERE email = :email").setParameter("email", pizzaria.getEmail()).uniqueResult();
    
        return p != null;
    }
    
    public boolean verificaEmailCadastro(Pizzaria pizzaria) {
        Pizzaria p = (Pizzaria) session.createQuery("FROM Pizzaria WHERE email = :email").setParameter("email", pizzaria.getEmail()).uniqueResult();
    
        return p == null;
    }
    
    public Pizzaria recuperaSenha(Pizzaria pizzaria) {
        return (Pizzaria) session.createQuery("FROM Pizzaria WHERE email = :email").setParameter("email", pizzaria.getEmail()).uniqueResult();
    }
    
    public List<Pizzaria> listaPizzariasProximas(Double lat_disp, Double lon_disp) {
        List<Pizzaria> p = (List<Pizzaria>) session.createQuery(
                "FROM Pizzaria WHERE"
                + " latitude <= ' " + lat_disp + " ' + 0.02699784"
                + " AND latitude >= ' " + lat_disp + " ' - 0.02699784"
                + " AND longitude <= ' " + lon_disp + " ' + 0.02699784"
                + " AND longitude >= ' " + lon_disp + " ' - 0.02699784")
                .list();
                
        return p;
    }
}