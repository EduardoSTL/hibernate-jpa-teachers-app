package org.eduardo.service;

import org.eduardo.entity.Student;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface GenericService <T> {
    List<T> create();
    Optional<T> findById(Integer id);
    Iterable<T> findAll();
    T save(T entidad);
    void deleteById(Integer id);
}
