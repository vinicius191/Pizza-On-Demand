package br.com.pizzaondemand.modelo;

import java.util.ResourceBundle;

import br.com.caelum.vraptor.Convert;
import br.com.caelum.vraptor.Converter;
import br.com.caelum.vraptor.ioc.ApplicationScoped;

@Convert(UsuarioAndroidWS.class)
@ApplicationScoped 
public class UsuarioAndroidConverter implements Converter<UsuarioAndroidWS> {

	@Override
	public UsuarioAndroidWS convert(String arg0,
			Class<? extends UsuarioAndroidWS> arg1, ResourceBundle arg2) {
		// TODO Auto-generated method stub
		return null;
	}

}