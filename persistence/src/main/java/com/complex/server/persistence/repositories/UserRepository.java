package com.complex.server.persistence.repositories;

import com.complex.server.persistence.domain.User;

public interface UserRepository {

    void save(User user);
    User findById(long id);
}
