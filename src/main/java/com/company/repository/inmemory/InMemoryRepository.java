package com.company.repository.inmemory;

import com.company.repository.GenericRepository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class InMemoryRepository<T> implements GenericRepository<T> {

    private Map<Integer, T> storage = new ConcurrentHashMap<>();
    private AtomicInteger idCounter = new AtomicInteger(-1);

    public void update(Integer id, T entity) {
        if (storage.containsKey(id)) {
            storage.put(id, entity);
        }
    }

    public Integer create(T entity) {
        storage.putIfAbsent(idCounter.incrementAndGet(), entity);
        return idCounter.get();
    }

    public Optional<T> findById(Integer id) {
        return Optional.ofNullable(storage.get(id));
    }
}
