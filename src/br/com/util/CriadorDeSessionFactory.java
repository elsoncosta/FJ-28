package br.com.util;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.caelum.vraptor.ioc.ApplicationScoped;
import br.com.caelum.vraptor.ioc.Component;
import br.com.caelum.vraptor.ioc.ComponentFactory;

@Component
@ApplicationScoped
public class CriadorDeSessionFactory implements
		ComponentFactory<SessionFactory> {

	private SessionFactory sf;

	public CriadorDeSessionFactory() {
		super();
	}

	@PostConstruct
	public void abre() {
		AnnotationConfiguration configuration = new AnnotationConfiguration();
		configuration.configure("br/com/util/hibernate.cfg.xml");
		
		this.sf = configuration.buildSessionFactory();
		System.out.println("Configurando o SessionFactory");
	}
	
	public SessionFactory getInstance() {
		System.out.println("Retornando o SessionFactory");
		return this.sf;
	}
	
	public void fechar(){
		this.sf.close();
		System.out.println("Fechando o SessionFactory");
	}

}
