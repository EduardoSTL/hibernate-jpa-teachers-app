package org.eduardo.service;

import java.util.List;
import java.util.Optional;

public interface GenericService <T> {
    List<T> create();
    Optional<T> findById(Integer id);
    Iterable<T> findAll();
    T save(T entidad);
    void deleteById(Integer id);
    void edit(Integer id);
}
