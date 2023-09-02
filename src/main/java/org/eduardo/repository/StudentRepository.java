package org.eduardo.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.eduardo.entity.Student;

import javax.swing.*;
import java.util.List;

public class StudentRepository implements CrudGenericRepository<Student>{

    private final EntityManager manager;

    public StudentRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Student> create() {
        return manager.createQuery("select s from Student s", Student.class).getResultList();
    }

    @Override
    public Student findById(Integer id) {
        return manager.find(Student.class, id);
    }

    @Override
    public void save(Student student) {
        if (student.getId()!=null && student.getId()>0){
            manager.merge(student);
        } else {
            manager.persist(student);
        }
    }

    @Override
    public void edit(Integer id) {
        Student student = findById(id);
        if (student.getId()!=null && student.getId()>0){
            student.setNombre(JOptionPane.showInputDialog("Digita el nombre: ",student.getNombre()));
            student.setApellido(JOptionPane.showInputDialog("Digita el apellido: ", student.getApellido()));
        }
        manager.merge(student);
    }

    @Override
    public void delete(Integer id) {
        Student student = findById(id);
        manager.remove(student);
    }

    @Override
    public List<Student> findAll() {
        TypedQuery<Student> query = manager.createQuery("select s from Student s", Student.class);
        return query.getResultList();
    }
}