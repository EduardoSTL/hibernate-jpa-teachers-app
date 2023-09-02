package org.eduardo.service;

import jakarta.persistence.EntityManager;
import org.eduardo.entity.Subject;
import org.eduardo.exception.dataNotFoundException;
import org.eduardo.exception.dataRecoveryException;
import org.eduardo.repository.CrudGenericRepository;
import org.eduardo.repository.SubjectRepository;

import java.util.List;
import java.util.Optional;

public class SubjectServiceImpl implements GenericService<Subject> {
    private final EntityManager manager;

    private CrudGenericRepository<Subject> repository;

    public SubjectServiceImpl(EntityManager manager) {
        this.manager = manager;
        this.repository = new SubjectRepository(manager);
    }

    @Override
    public List<Subject> create() {
        System.out.println("--------- Crear Subject ---------");
        return repository.create();
    }

    @Override
    public Optional<Subject> findById(Integer id) {
        System.out.println("--------- Buscar Subject por id ---------");
        return Optional.ofNullable(repository.findById(id));
    }

    @Override
    public Iterable<Subject> findAll() {
        try {
            List<Subject> subjects = repository.findAll();
            if (subjects.isEmpty()) {
                throw new dataNotFoundException("No se encontraron registros de Subject");
            }
            return subjects;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
            throw new dataRecoveryException("Error al recuperar la lista de subjects", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public Subject save(Subject entidad) {
        try {
            System.out.println("--------- Guardar Subject ---------");
            manager.getTransaction().begin();
            Subject savedSubject = repository.save(entidad);
            manager.getTransaction().commit();
            return savedSubject;
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
            System.out.println("--------- Eliminar Subject ---------");
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
            System.out.println("--------- Editar Subject ---------");
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