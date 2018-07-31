package com.complex.server.persistence.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class SimpleRepositoryImpl<T> {

    @PersistenceContext private EntityManager entityManager;

    protected T get(Class<T> cLassType, long id) {
        return entityManager.find(cLassType, id);
    }

    protected void save(T item) {
        entityManager.persist(item);
    }

    protected void remove(T item) {
        entityManager.remove(entityManager.contains(item) ? item : entityManager.merge(item));
    }
}
