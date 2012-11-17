package br.com.pizzaondemand.dao;

import java.util.List;

import org.hibernate.Session;
import br.com.caelum.vraptor.ioc.Component;
import br.com.pizzaondemand.modelo.UsuarioAndroid;
import br.com.pizzaondemand.modelo.UsuarioAndroid;
import br.com.pizzaondemand.modelo.UsuarioAndroidWS;

@Component
public class UsuarioAndroidDAO {

    private final Session session;

    public UsuarioAndroidDAO(Session session) {
        this.session = session;
    }

    @SuppressWarnings("unchecked")
    public List<UsuarioAndroid> lista() {
        return (List<UsuarioAndroid>) session.createQuery("FROM UsuarioAndroid").list();
    }

    public void salvar(UsuarioAndroid usuarioAndroid) {
        session.save(usuarioAndroid);
    }

    public UsuarioAndroid autenticar(String email, String senha) {

        System.out.println("UsuarioDAO email: " + email);
        System.out.println("UsuarioDAO senha: " + senha);

        return (UsuarioAndroid) session.createQuery(
                "FROM Usuario WHERE email = :email AND senha = :senha").setParameter("email", email).setParameter("senha", senha).uniqueResult();

    }
    
    public boolean verificaIMEI(UsuarioAndroid usuarioAndroid) {
        UsuarioAndroid u = (UsuarioAndroid) session.createQuery("FROM UsuarioAndroid WHERE id = :id").setParameter("id", usuarioAndroid.getId()).uniqueResult();

        return u != null;
    }
    
    public UsuarioAndroid obtemUsuarioAndroidPorIMEI(UsuarioAndroid usuarioAndroid) {
        return (UsuarioAndroid) session.createQuery("FROM UsuarioAndroid WHERE id = :id").setParameter("id", usuarioAndroid.getId()).uniqueResult();
    }
}
