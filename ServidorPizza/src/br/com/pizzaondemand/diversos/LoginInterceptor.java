package br.com.pizzaondemand.diversos;

import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.pizzaondemand.controller.HomeController;
import br.com.pizzaondemand.modelo.UsuarioSession;

@Intercepts
public class LoginInterceptor implements Interceptor {

    private Result result;
    private UsuarioSession usuarioSession;

    public LoginInterceptor(Result result, UsuarioSession usuarioSession) {
        this.result = result;
        this.usuarioSession = usuarioSession;
    }

    @Override
    public boolean accepts(ResourceMethod method) {
        return !(method.getMethod().isAnnotationPresent(Public.class)
                || method.getResource().getType().isAnnotationPresent(Public.class));
    }

    @Override
    public void intercept(InterceptorStack stack, ResourceMethod method, Object resourceInstance) {
        if (usuarioSession.isLogged()) {
            stack.next(method, resourceInstance);
        } else {
            result.redirectTo(HomeController.class).home();
        }
    }
}
