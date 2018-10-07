package com.complex.server.persistence.repositories;

import com.complex.server.persistence.domain.User;
import java.util.List;

public interface UserRepository {

  void save(User user);

  User findById(long id);

  void remove(User user);

  List<User> getAll();

  User getUserByName(String name);

}
