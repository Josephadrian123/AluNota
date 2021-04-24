package br.edu.ifpb.jaas.dao;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProducer implements Serializable {
	private static final long serialVersionUID = 1L;
	private static EntityManagerFactory factory = Persistence.createEntityManagerFactory("alunota");
	
	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		return factory.createEntityManager();
	}
	
	public void closeEntityManager(@Disposes EntityManager manager) {
		manager.close();
	}
	
	public static EntityManagerFactory getEntityManagerFactory() {
		return factory;
	}

}
