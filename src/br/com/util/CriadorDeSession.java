package br.com.util;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

/*
 * @author  Igor Pereira Dutra	
 * Classe de configuração do Hibernate.
 */

@Component
public class CriadorDeSession implements ComponentFactory<Session> {
	private final SessionFactory factory;
	private Session session;
	
	
	public CriadorDeSession(SessionFactory factory) {
		this.factory = factory;

	}
	
	@PostConstruct
	public void abre(){
		System.out.println(">>> ABRINDO SESSÃO");
		this.session = this.factory.openSession();
	}
	
	public Session getInstance() {
		return this.session;
	}
	
	@PreDestroy
	public void fecha(){
		System.out.println(">>> FECHANDO SESSÃO");
		this.session.close();
	}
}
