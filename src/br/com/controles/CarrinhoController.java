package br.com.controles;

import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Resource;
import br.com.caelum.vraptor.Result;
import br.com.modelo.dao.ProdutoDAO;
import br.com.modelo.entidade.Carrinho;
import br.com.modelo.entidade.Item;

@Resource
public class CarrinhoController {
	
	private final Carrinho carrinho;
	private final ProdutoDAO dao;
	private final Result result;
	
	public CarrinhoController (Carrinho carrinho, ProdutoDAO dao, Result result){
		this.carrinho = carrinho;
		this.dao = dao;
		this.result = result;
	}
	
	@Post("/carrinho")
	public void adiciona(Item item){
		dao.recarrega(item.getProduto());
		carrinho.adiciona(item);
		result.redirectTo(this).visualiza();
	}
	
	@Get("/carrinho")
	public void visualiza(){
		
	}
	
	@Delete("/carrinho/{indiceItem}")
	public void remove(int indiceItem){
		carrinho.remove(indiceItem);
		result.redirectTo(this).visualiza();
	}
	
	
}
