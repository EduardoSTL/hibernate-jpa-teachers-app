package org.eduardo.service;

import jakarta.persistence.EntityManager;
import org.eduardo.entity.Group;
import org.eduardo.entity.Mark;
import org.eduardo.entity.Teacher;
import org.eduardo.exception.dataNotFoundException;
import org.eduardo.exception.dataRecoveryException;
import org.eduardo.repository.CrudGenericRepository;
import org.eduardo.repository.MarkRepository;

import java.util.List;
import java.util.Optional;

public class MarkServiceImpl implements GenericService<Mark> {
    private final EntityManager manager;

    private CrudGenericRepository<Mark> repository;

    public MarkServiceImpl(EntityManager manager) {
        this.manager = manager;
        this.repository = new MarkRepository(manager);
    }

    @Override
    public List<Mark> create() {
        System.out.println("--------- Crear Mark ---------");
        return repository.create();
    }

    @Override
    public Optional<Mark> findById(Integer id) {
        System.out.println("--------- Buscar Mark por id ---------");
        return Optional.ofNullable(repository.findById(id));
    }

    @Override
    public Iterable<Mark> findAll() {
        try {
            List<Mark> marks = repository.findAll();
            if (marks.isEmpty()) {
                throw new dataNotFoundException("No se encontraron registros de Marks");
            }
            return marks;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
            throw new dataRecoveryException("Error al recuperar la lista de marks", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public Mark save(Mark entidad) {
        try {
            System.out.println("--------- Guardar Mark ---------");
            manager.getTransaction().begin();
            Mark savedMark = repository.save(entidad);
            manager.getTransaction().commit();
            return savedMark;
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
            System.out.println("--------- Eliminar Mark ---------");
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
            System.out.println("--------- Editar Mark ---------");
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
