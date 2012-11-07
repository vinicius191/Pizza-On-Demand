package br.com.pizzaondemand.controller;

import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.pizzaondemand.diversos.Public;

@Resource
public class HomeController {
	
	@SuppressWarnings("unused")
	private final Result result;

	public HomeController(
			Result result) {
		
		this.result = result;
	}
	
	@Public
	@Path( "/" )
	public void home() {
            System.out.println("Entrei em index.jsp");
	}
}
