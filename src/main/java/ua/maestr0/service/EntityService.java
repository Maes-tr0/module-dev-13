package ua.maestr0.service;

import ua.maestr0.dao.CRUDRepository;

public abstract class EntityService<T, K> {
    private final CRUDRepository<T, K> repository;

    protected EntityService(CRUDRepository<T, K> repository) {
        this.repository = repository;
    }

    public T get(K id) {
        return repository.get(id);
    }

    public void save(T entity) {
        repository.save(entity);
    }

    public void update(T entity) {
        repository.update(entity);
    }

    public void delete(K id) {
        repository.delete(id);
    }
}
