package org.eduardo.service;

import jakarta.persistence.EntityManager;
import org.eduardo.entity.Teacher;
import org.eduardo.exception.dataNotFoundException;
import org.eduardo.exception.dataRecoveryException;
import org.eduardo.repository.CrudGenericRepository;
import org.eduardo.repository.TeacherRepository;
import org.eduardo.util.EntityManagerUtil;

import java.util.List;
import java.util.Optional;

public class TeacherServiceImpl implements GenericService<Teacher>{

    private CrudGenericRepository<Teacher> repository;

    public TeacherServiceImpl() {
        this.repository = new TeacherRepository();
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
        List<Teacher> teachers;
        EntityManager manager = EntityManagerUtil.getEntityManager();
        try {
            manager.getTransaction().begin();
            teachers = repository.findAll();
            if (teachers.isEmpty()) {
                throw new dataNotFoundException("No se encontraron registros de Teachers");
            }
            manager.getTransaction().commit();
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
            throw new dataRecoveryException("Error al recuperar la lista de profesores.", e);
        } finally {
            EntityManagerUtil.closeEntityManager(manager);
        }
        return teachers;
    }

    @Override
    public Teacher save(Teacher entidad) {
        EntityManager manager = EntityManagerUtil.getEntityManager();
        try {
            System.out.println("--------- Guardar Teacher ---------");
            manager.getTransaction().begin();
            Teacher savedTeacher = repository.save(entidad);
            manager.getTransaction().commit();
            return savedTeacher;
        } catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
            return null;
        } finally {
            EntityManagerUtil.closeEntityManager(manager);
        }
    }

    @Override
    public void deleteById(Integer id) {
        EntityManager manager = EntityManagerUtil.getEntityManager();
        try {
            System.out.println("--------- Eliminar Teacher ---------");
            manager.getTransaction().begin();
            repository.delete(id);
            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            EntityManagerUtil.closeEntityManager(manager);
        }
    }

    @Override
    public void edit(Integer id) {
        EntityManager manager = EntityManagerUtil.getEntityManager();
        try {
            System.out.println("--------- Editar Teacher ---------");
            manager.getTransaction().begin();
            repository.edit(id);
            manager.getTransaction().commit();
        } catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            EntityManagerUtil.closeEntityManager(manager);
        }
    }
}
