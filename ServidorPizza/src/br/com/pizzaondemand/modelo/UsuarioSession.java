package br.com.pizzaondemand.modelo;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.SessionScoped;
import org.omg.PortableInterceptor.USER_EXCEPTION;

@Component
@SessionScoped
public class UsuarioSession implements java.io.Serializable {
	
	private Pizzaria user;
        
	public boolean isLogged() {
		return user != null;
	}
	
	public void logout() {
		user = null;
	}

	public Pizzaria getUser() {
		return user;
	}

	public void setUser(Pizzaria user) {
		this.user = user;
	}
	

}
