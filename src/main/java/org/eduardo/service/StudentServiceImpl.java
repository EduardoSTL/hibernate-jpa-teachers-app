package org.eduardo.service;

import jakarta.persistence.EntityManager;
import org.eduardo.entity.Mark;
import org.eduardo.entity.Student;
import org.eduardo.entity.Teacher;
import org.eduardo.exception.dataNotFoundException;
import org.eduardo.exception.dataRecoveryException;
import org.eduardo.repository.CrudGenericRepository;
import org.eduardo.repository.StudentRepository;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements GenericService<Student>{

    private final EntityManager manager;

    private CrudGenericRepository<Student> repository;

    public StudentServiceImpl(EntityManager manager) {
        this.manager = manager;
        this.repository = new StudentRepository(manager);
    }

    @Override
    public List<Student> create() {
        System.out.println("--------- Crear Student ---------");
        return repository.create();
    }

    @Override
    public Optional<Student> findById(Integer id) {
        System.out.println("--------- Buscar Student por id ---------");
        return Optional.ofNullable(repository.findById(id));
    }

    @Override
    public Iterable<Student> findAll() {
        try {
            List<Student> students = repository.findAll();
            if (students.isEmpty()) {
                throw new dataNotFoundException("No se encontraron registros de Students");
            }
            return students;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
            throw new dataRecoveryException("Error al recuperar la lista de students", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public Student save(Student entidad) {
        try {
            System.out.println("--------- Guardar Student ---------");
            manager.getTransaction().begin();
            Student savedStudent = repository.save(entidad);
            manager.getTransaction().commit();
            return savedStudent;
        } catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            manager.close();
        }
    }

    @Override
    public void deleteById(Integer id) {
        try {
            System.out.println("--------- Eliminar Student ---------");
            manager.getTransaction().begin();
            repository.delete(id);
            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }

    @Override
    public void edit(Integer id) {
        try {
            System.out.println("--------- Editar Student ---------");
            manager.getTransaction().begin();
            repository.edit(id);
            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }
}