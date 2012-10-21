package br.com.modelo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.caelum.vraptor.ioc.Component;
import br.com.modelo.entidade.Produto;
import br.com.util.CriadorDeSession;

@Component
public class ProdutoDAO {
	private Session session;
	
	public ProdutoDAO(Session session){
		this.session = session;
	}
	
	public void salvar(Produto produto){
		Transaction tx = this.session.beginTransaction();
		this.session.save(produto);
		tx.commit();
	}
	
	@SuppressWarnings("unchecked")
	public List<Produto> listarTudo(){
		return this.session.createCriteria(Produto.class).list();
	}

	public Produto carrega(Long id) {
		return (Produto) this.session.load(Produto.class, id);
	}

	public void atualiza(Produto produto) {
		Transaction tx = this.session.beginTransaction();
		this.session.update(produto);
		tx.commit();
	}

	public void remove(Produto produto) {
		Transaction tx = this.session.beginTransaction();
		this.session.delete(produto);
		tx.commit();
		
	}

	@SuppressWarnings("unchecked")
	public List<Produto> busca(String nome) {
		return this.session.createCriteria(Produto.class).add(Restrictions.like("nome", nome, MatchMode.ANYWHERE)).list();
	}

	public void recarrega(Produto produto) {
		this.session.refresh(produto);
	}
}
