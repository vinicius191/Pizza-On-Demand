package br.com.pizzaondemand.controller;

import br.com.pizzaondemand.diversos.Public;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
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

    @Get("/logout")
    public void logout() {
        usuarioSession.logout();
        result.redirectTo(HomeController.class).home();
    }
    
    
}
