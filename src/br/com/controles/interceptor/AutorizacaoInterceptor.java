package br.com.controles.interceptor;

import br.com.caelum.vraptor.InterceptionException;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.core.InterceptorStack;
import br.com.caelum.vraptor.interceptor.Interceptor;
import br.com.caelum.vraptor.resource.ResourceMethod;
import br.com.controles.UsuarioController;
import br.com.modelo.entidade.UsuarioWeb;

@Intercepts
public class AutorizacaoInterceptor implements Interceptor{
	
	private final UsuarioWeb usuarioWeb;
	private final Result result;
	
	public AutorizacaoInterceptor(UsuarioWeb usuarioWeb, Result result){
		this.usuarioWeb = usuarioWeb;
		this.result = result;
	}
	
	public boolean accepts(ResourceMethod method) {
		return !usuarioWeb.isLogado() && method.containsAnnotation(Restrito.class);
	}

	public void intercept(InterceptorStack stack, ResourceMethod method,
			Object obj) throws InterceptionException {
		result.redirectTo(UsuarioController.class).loginForm();
		
	}

}
