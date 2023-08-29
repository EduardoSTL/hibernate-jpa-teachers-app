package org.eduardo;

import jakarta.persistence.EntityManager;
import org.eduardo.entity.Group;
import org.eduardo.entity.Student;
import org.eduardo.entity.Subject;
import org.eduardo.util.JpaUtil;

public class OneToOne {
    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEntityManager();

        try{
            Group group1 = new Group(1, null, null, null);
            manager.persist(group1);
            Student student1 = new Student(1, "Juan", "Perez", null, null);
            student1.setGroup(group1);
            manager.persist(student1);
            manager.getTransaction().commit();
            System.out.println(student1);
        } catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
    }
}
