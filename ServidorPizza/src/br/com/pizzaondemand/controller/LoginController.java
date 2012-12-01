package br.com.pizzaondemand.controller;

import br.com.pizzaondemand.diversos.Public;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;
import br.com.pizzaondemand.modelo.Pizzaria;
import br.com.pizzaondemand.dao.PizzariaDAO;
import br.com.pizzaondemand.modelo.UsuarioSession;

@Resource
public class LoginController {

    private Result result;
    private PizzariaDAO pizzariaDAO;
    private UsuarioSession usuarioSession;

    public LoginController(
            Result result,
            PizzariaDAO pizzariaDAO,
            UsuarioSession usuarioSession) {

        this.result = result;
        this.pizzariaDAO = pizzariaDAO;
        this.usuarioSession = usuarioSession;
    }

    @Public
    @Get("/login")
    public void login() {
    }

    @Public
    @Post("/autenticar")
    public void autenticar(Pizzaria pizzaria) {
        System.out.println("Usuario email: " + pizzaria.getEmail());
        System.out.println("Usuario senha: " + pizzaria.getSenha());

        Pizzaria user = pizzariaDAO.autenticar(pizzaria.getEmail(), pizzaria.getSenha());

        if (user != null) {
            System.out.println("Usuario email: " + user.getEmail());
            System.out.println("Usuario senha: " + user.getSenha());
            
            usuarioSession.setUser(user);

            result.redirectTo(IndexController.class).index();
            result.include("usuario", user);
        } else {
            result.include("error", "E-mail ou senha incorreta!").redirectTo(HomeController.class).home();
        }
        
    }

    @Public
    @Get("/autenticar/{email}/{senha}")
    public void autenticar(String email, String senha) {
        System.out.println("Entrei ====");
        try {
            System.out.println("Usuario email: " + email);
            System.out.println("Usuario senha: " + senha);

            Pizzaria user = pizzariaDAO.autenticar(email, senha);

            if (user != null) {
                System.out.println("Não é nulo");
                result.use(Results.json()).withoutRoot().from(user.getId()).serialize();
            } else {
                
                result.use(Results.json()).withoutRoot().from(0).serialize();
            } 
        } catch (Exception e) {
            System.out.println("Erro: " + e.toString());
        }
        
        
    }
    

    @Get("/logout")
    public void logout() {
        usuarioSession.logout();
        result.redirectTo(HomeController.class).home();
    }
    
    
}
