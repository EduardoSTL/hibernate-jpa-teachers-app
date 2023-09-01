package org.eduardo.repository;

import jakarta.persistence.EntityManager;
import org.eduardo.entity.Mark;

import javax.swing.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MarkRepository implements CrudGenericRepository<Mark>{

    private final EntityManager manager;

    public MarkRepository(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<Mark> create() {
        return manager.createQuery("select m from Mark m", Mark.class).getResultList();
    }

    @Override
    public Mark findById(Integer id) {
        return manager.find(Mark.class, id);
    }

    @Override
    public void save(Mark mark) {
        if (mark.getId()!=null && mark.getId()>0){
            manager.merge(mark);
        } else {
            manager.persist(mark);
        }
    }

    @Override
    public void edit(Integer id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Mark mark = findById(id);
        if (mark.getId()!=null && mark.getId()>0){
            mark.setMark(Integer.valueOf(JOptionPane.showInputDialog("Digita el n° de mark: ", mark.getMark())));
            String fecha = JOptionPane.showInputDialog("Digita la fecha : ", mark.getDate());
            if (!fecha.isEmpty()){
                LocalDate fechaFormat = LocalDate.parse(fecha, formatter);
                mark.setDate(fechaFormat.atStartOfDay());
            }
            manager.merge(mark);
        }
    }

    @Override
    public void delete(Integer id) {
        Mark mark = findById(id);
        manager.remove(mark);
    }
}