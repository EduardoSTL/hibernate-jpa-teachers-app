package org.eduardo.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
    public Group save(Group group) {
        if (group.getId()!=null && group.getId()>0){
            manager.merge(group);
        } else {
            manager.persist(group);
        }
        return null;
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

    @Override
    public List<Group> findAll() {
        TypedQuery<Group> query = manager.createQuery("select g from Group g", Group.class);
        return query.getResultList();
    }
}
