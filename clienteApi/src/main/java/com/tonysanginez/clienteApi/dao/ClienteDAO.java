package com.tonysanginez.clienteApi.dao;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Service;

import com.tonysanginez.clienteApi.model.Cliente;
@Service
public class ClienteDAO extends BaseDAO<Cliente, Long>{
	
	@PersistenceContext
	private EntityManager em;
	
	protected ClienteDAO() {
		super(Cliente.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
    public void persist(Cliente t) throws PersistenceException {
        super.persist(t);
    }
}
