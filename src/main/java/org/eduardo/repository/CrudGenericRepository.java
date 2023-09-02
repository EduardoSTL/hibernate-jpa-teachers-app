package org.eduardo.repository;

import org.eduardo.entity.Teacher;

import java.util.List;

public interface CrudGenericRepository <T>{
    List<T> create();
    T findById(Integer id);
    T save(T entidad);
    void edit(Integer id);
    void delete(Integer id);
    List<T> findAll();
}
