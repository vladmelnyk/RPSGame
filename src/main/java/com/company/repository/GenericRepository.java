package com.company.repository;

import java.util.Optional;

public interface GenericRepository<T> {

    Integer create(T entity);

    void update(Integer id, T entity);

    Optional<T> findById(Integer id);
}
