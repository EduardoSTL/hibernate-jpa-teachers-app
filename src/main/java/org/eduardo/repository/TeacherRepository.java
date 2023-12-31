package org.eduardo.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.eduardo.entity.Teacher;
import org.eduardo.util.EntityManagerUtil;

import javax.swing.*;
import java.util.List;

public class TeacherRepository implements CrudGenericRepository<Teacher>{

    private final EntityManager manager;

    public TeacherRepository() {
        this.manager = EntityManagerUtil.getEntityManager();
    }

    @Override
    public List<Teacher> create() {
        return manager.createQuery("select t from Teacher t", Teacher.class).getResultList();
    }

    @Override
    public Teacher findById(Integer id) {
        return manager.find(Teacher.class, id);
    }

    @Override
    public Teacher save(Teacher teacher) {
        if (teacher.getId()!=null && teacher.getId()>0){
            manager.merge(teacher);
        } else {
            manager.persist(teacher);
        }
        return teacher;
    }

    @Override
    public void edit(Integer id) {
        Teacher teacher = findById(id);
        if (teacher.getId()!= null && teacher.getId()>0){
            teacher.setNombre(JOptionPane.showInputDialog("Digita el primer nombre: ", teacher.getNombre()));
            teacher.setApellido(JOptionPane.showInputDialog("Digita el apellido: ", teacher.getApellido()));
            manager.merge(teacher);
        }
    }

    @Override
    public void delete(Integer id) {
        Teacher teacher = findById(id);
        manager.remove(teacher);
    }

    @Override
    public List<Teacher> findAll() {
        TypedQuery<Teacher> query = manager.createQuery("select t from Teacher t", Teacher.class);
        return query.getResultList();
    }
}