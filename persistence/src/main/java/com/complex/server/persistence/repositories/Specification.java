package com.complex.server.persistence.repositories;

public interface Specification<T> {

    Class<T> getType();

}
