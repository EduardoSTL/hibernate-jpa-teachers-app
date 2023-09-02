package org.eduardo.service;

import jakarta.persistence.EntityManager;
import org.eduardo.entity.Teacher;
import org.eduardo.exception.dataNotFoundException;
import org.eduardo.exception.dataRecoveryException;
import org.eduardo.repository.CrudGenericRepository;
import org.eduardo.repository.TeacherRepository;

import java.util.List;
import java.util.Optional;

public class TeacherServiceImpl implements GenericService<Teacher>{

    private final EntityManager manager;

    private CrudGenericRepository<Teacher> repository;

    public TeacherServiceImpl(EntityManager manager) {
        this.manager = manager;
        this.repository = new TeacherRepository(manager);
    }

    @Override
    public List<Teacher> create() {
        System.out.println("--------- Crear Teacher ---------");
        return repository.create();
    }

    @Override
    public Optional<Teacher> findById(Integer id) {
        System.out.println("--------- Buscar Teacher por id ---------");
        return Optional.ofNullable(repository.findById(id));
    }

    @Override
    public Iterable<Teacher> findAll() {
        try {
            List<Teacher> teachers = repository.findAll();
            if (teachers.isEmpty()) {
                throw new dataNotFoundException("No se encontraron registros de Teacher");
            }
            return teachers;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
            throw new dataRecoveryException("Error al recuperar la lista de profesores.", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public Teacher save(Teacher entidad) {
        Teacher teacher = null; // Declara la variable teacher aquí para que esté en el ámbito correcto
        try {
            System.out.println("--------- Guardar Teacher ---------");
            manager.getTransaction().begin();
            repository.save(entidad); // Guarda la entidad y asigna el resultado a teacher
            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return teacher;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            System.out.println("--------- Eliminar Teacher ---------");
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
            System.out.println("--------- Editar Teacher ---------");
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
