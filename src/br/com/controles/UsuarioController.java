package br.com.controles;

import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.modelo.dao.UsuarioDAO;
import br.com.modelo.entidade.Usuario;
import br.com.modelo.entidade.UsuarioWeb;

@Resource
public class UsuarioController {

	private final UsuarioDAO dao;
	private final Result result;
	private final Validator validator;
	private final UsuarioWeb usuarioWeb;

	public UsuarioController(UsuarioDAO dao, Result result,
			Validator validador, UsuarioWeb usuarioWeb) {
		this.dao = dao;
		this.result = result;
		this.validator = validador;
		this.usuarioWeb = usuarioWeb;
	}

	@Get
	@Path("/usuarios/login")
	public void loginForm() {
	}

	@Get("/usuarios/novo")
	public void novo() {
	}

	@Post("/usuarios")
	public void adiciona(Usuario usuario) {
		if (dao.existeUsuario(usuario)) {
			validator.add(new ValidationMessage("Login já existe",
					"usuario.login"));
		}

		validator.onErrorUsePageOf(UsuarioController.class).novo();
		dao.adiciona(usuario);
		result.redirectTo(ProdutosController.class).lista();
	}
	
	@Post("/login")
	public void login(Usuario usuario){
		Usuario carregado = dao.carrega(usuario);
		if(carregado == null){
			validator.add(new ValidationMessage("Login e/ou Senha inválidos", "usuario.login"));
		}
		
		validator.onErrorUsePageOf(UsuarioController.class).loginForm();
		
		usuarioWeb.login(carregado);
		result.redirectTo(ProdutosController.class).lista();
	}
	
	@Path("/logout")
	public void logout(){
		usuarioWeb.logout();
		result.redirectTo(ProdutosController.class).lista();
	}
}
