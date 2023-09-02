package org.eduardo.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.eduardo.entity.Subject;

import javax.swing.*;
import java.util.List;

public class SubjectRepository implements CrudGenericRepository<Subject>{

    private final EntityManager manager;

    public SubjectRepository(EntityManager manager) {
        this.manager = manager;
    }
    @Override
    public List<Subject> create() {
        return manager.createQuery("select s from Subject s", Subject.class).getResultList();
    }

    @Override
    public Subject findById(Integer id) {
        return manager.find(Subject.class,id);
    }

    @Override
    public Subject save(Subject subject) {
        if (subject.getId()!= null && subject.getId()<0){
            manager.merge(subject);
        } else {
            manager.persist(subject);
        }
        return null;
    }

    @Override
    public void edit(Integer id) {
        Subject subject = findById(id);
        if (subject.getId()!= null && subject.getId()>0){
            subject.setTitulo(JOptionPane.showInputDialog("Digita el titulo: ", subject.getTitulo()));
            manager.merge(subject);
        }
    }

    @Override
    public void delete(Integer id) {
        Subject subject = findById(id);
        manager.remove(subject);
    }

    @Override
    public List<Subject> findAll() {
        TypedQuery<Subject> query = manager.createQuery("select s from Subject s", Subject.class);
        return query.getResultList();
    }
}
