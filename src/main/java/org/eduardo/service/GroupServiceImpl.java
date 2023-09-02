package org.eduardo.service;

import jakarta.persistence.EntityManager;
import org.eduardo.entity.Group;
import org.eduardo.entity.Teacher;
import org.eduardo.exception.dataNotFoundException;
import org.eduardo.exception.dataRecoveryException;
import org.eduardo.repository.CrudGenericRepository;
import org.eduardo.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

public class GroupServiceImpl implements GenericService<Group> {
    private final EntityManager manager;
    private CrudGenericRepository<Group> repository;

    public GroupServiceImpl(EntityManager manager) {
        this.manager = manager;
        this.repository = new GroupRepository(manager);
    }

    @Override
    public List<Group> create() {
        System.out.println("--------- Crear Grupo ---------");
        return repository.create();
    }

    @Override
    public Optional<Group> findById(Integer id) {
        System.out.println("--------- Buscar Grupo por id ---------");
        return Optional.ofNullable(repository.findById(id));
    }

    @Override
    public Iterable<Group> findAll() {
        try {
            List<Group> groups = repository.findAll();
            if (groups.isEmpty()) {
                throw new dataNotFoundException("No se encontraron registros de Grupo");
            }
            return groups;
        } catch (Exception e) {
            manager.getTransaction().rollback();
            e.printStackTrace();
            throw new dataRecoveryException("Error al recuperar la lista de grupos.", e);
        } finally {
            manager.close();
        }
    }

    @Override
    public Group save(Group entidad) {
        try {
            System.out.println("--------- Guardar Grupo ---------");
            manager.getTransaction().begin();
            Group savedGroup = repository.save(entidad);
            manager.getTransaction().commit();
            return savedGroup;
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
            System.out.println("--------- Eliminar Grupo ---------");
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
            System.out.println("--------- Editar Grupo ---------");
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
