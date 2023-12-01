package com.tonysanginez.clienteApi.dao;

import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import lombok.NonNull;

public abstract class BaseDAO<T, K> {

	private final Class<T> clazz;

	protected BaseDAO(Class<T> clazz) {
		this.clazz = clazz;
	}

	protected abstract EntityManager getEntityManager();

	public void persist(T t) throws PersistenceException {
		EntityManager em = getEntityManager();
		em.persist(t);
	}

	public void update(T t) throws PersistenceException {
		EntityManager em = getEntityManager();
		em.merge(t);
	}
        
        public void remove(T t) throws PersistenceException {
            EntityManager em = getEntityManager();
            em.remove(t);
        }

	public Optional<T> find(@NonNull K id) {
		EntityManager em = getEntityManager();
		T t = em.find(clazz, id);
		return Optional.ofNullable(t);
	}

}