package org.eduardo.repository;

import java.util.List;

public interface CrudGenericRepository <T>{
    List<T> create();
    T findById(Integer id);
    void save(T t);
    void edit(Integer id);
    void delete(Integer id);
}
