package com.tonysanginez.cuentasApi.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Service;

import com.tonysanginez.cuentasApi.model.Cuenta;

import lombok.NonNull;
@Service
public class CuentaDAO extends BaseDAO<Cuenta,Long>{
	@PersistenceContext
	private EntityManager em;
	
	protected CuentaDAO() {
		super(Cuenta.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
    public void persist(Cuenta t) throws PersistenceException {
        super.persist(t);
    }
	
}
