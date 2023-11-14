package com.cydeo.service;

import java.util.List;

public interface CrudService<E,ID> {
    List<E> findAll();
    E findById(ID id);
    void save(E entity);
    void update(E entity);
    void delete(ID id);
}
