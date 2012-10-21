package br.com.controles;

import java.util.List;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.ValidationMessage;
import br.com.caelum.vraptor.view.Results;
import br.com.controles.interceptor.Restrito;
import br.com.modelo.dao.ProdutoDAO;
import br.com.modelo.entidade.Produto;

@Resource
public class ProdutosController {
	private final ProdutoDAO dao;
	private Result result;
	private Validator validator;

	public ProdutosController(ProdutoDAO dao, Result result, Validator validator) {
		this.dao = dao;
		this.result = result;
		this.validator = validator;
	}

	@Get("/produtos")
	public List<Produto> lista() {
		return dao.listarTudo();
	}
	
	@Post("/produtos")
	@Restrito
	public void adiciona(Produto produto) {
		validator.validate(produto);
		this.validator.onErrorUsePageOf(ProdutosController.class).formulario();
		
		this.dao.salvar(produto);
		this.result.redirectTo(this).lista();
	}
	
	@Get("/produtos/{id}")
	@Restrito
	public Produto edita(Long id) {
		return this.dao.carrega(id);
	}
	
	@Put("/produtos/{produto.id}")
	@Restrito
	public void altera(Produto produto) {
		validator.validate(produto);
		this.validator.onErrorUsePageOf(ProdutosController.class).edita(produto.getId());
		
		this.dao.atualiza(produto);
		this.result.redirectTo(this).lista();
	}
	
	@Delete("/produtos/{id}")
	@Restrito
	public void remove(Long id) {
		Produto produto = this.dao.carrega(id);
		this.dao.remove(produto);
		this.result.redirectTo(this).lista();
	}
	
	@Get("/produtos/novo")
	@Restrito
	public void formulario() {
	}
	
	public List<Produto> busca(String nome){
		result.include("nome",nome);
		return dao.busca(nome);
	}
	
	@Get("/produtos/busca.json")
	public void buscaJson(String q){
		result.use(Results.json()).withoutRoot().from(dao.busca(q)).exclude("id","descricao").serialize();
	}
}
