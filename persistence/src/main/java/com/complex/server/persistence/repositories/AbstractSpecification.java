package com.complex.server.persistence.repositories;

import java.lang.reflect.ParameterizedType;

abstract public class AbstractSpecification<T> implements Specification<T> {

    @Override
    public Class<T> getType() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) type.getActualTypeArguments()[0];
    }
}
