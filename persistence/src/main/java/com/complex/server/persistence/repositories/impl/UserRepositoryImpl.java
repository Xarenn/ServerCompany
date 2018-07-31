package com.complex.server.persistence.repositories.impl;

import com.complex.server.persistence.domain.User;
import com.complex.server.persistence.repositories.SimpleRepositoryImpl;
import com.complex.server.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl extends SimpleRepositoryImpl<User> implements UserRepository {

    @Autowired
    private EntityManager entityManager;

    @Override public void save(User user) {
        super.save(user);
    }

    @Override public User findById(long id) {
        return super.get(User.class, id);
    }

    @Override public void remove(User user) {
        super.remove(user);
    }

    public List<User> getAll() {
        TypedQuery<User> query = entityManager.createQuery("SELECT usr FROM Users usr", User.class);
        return query.getResultList();
    }

    public User getUserByName(String name) {
        TypedQuery<User> query = entityManager.createQuery(
            "SELECT usr FROM Users usr WHERE usr.name = :name", User.class);
        return query.setParameter("name", name).getSingleResult();
    }

}
