package org.eduardo.repository;

import jakarta.persistence.EntityManager;
import org.eduardo.entity.Group;

import javax.swing.*;
import java.util.List;

public class GroupRepository implements CrudGenericRepository<Group> {

    private final EntityManager manager;

    public GroupRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Group> create() {
        return manager.createQuery("select g from Group g", Group.class).getResultList();
    }

    @Override
    public Group findById(Integer id) {
        return manager.find(Group.class, id);
    }

    @Override
    public void save(Group group) {
        try {
            manager.getTransaction().begin();
            manager.persist(group);
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
        Group group = findById(id);
        if (group.getId()!=null && group.getId()>0){
            group.setNombre(JOptionPane.showInputDialog("Digita el nombre: ",group.getNombre()));
        }
        manager.merge(group);
    }

    @Override
    public void delete(Integer id) {
        Group group = findById(id);
        manager.remove(group);
    }
}
