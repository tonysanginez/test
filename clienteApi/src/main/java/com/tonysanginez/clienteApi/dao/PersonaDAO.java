package com.tonysanginez.clienteApi.dao;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Service;

import com.tonysanginez.clienteApi.model.Cliente;
import com.tonysanginez.clienteApi.model.Persona;
@Service
public class PersonaDAO extends BaseDAO<Persona, Long>{
	
	@PersistenceContext
	private EntityManager em;
	
	protected PersonaDAO() {
		super(Persona.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
    public void persist(Persona t) throws PersistenceException {
        super.persist(t);
    }
}
