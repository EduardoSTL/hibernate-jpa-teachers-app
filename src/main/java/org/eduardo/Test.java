package org.eduardo;

import jakarta.persistence.EntityManager;
import org.eduardo.entity.Group;
import org.eduardo.entity.Mark;
import org.eduardo.entity.Subject;
import org.eduardo.entity.Teacher;
import org.eduardo.util.JpaUtil;

import java.util.List;

public class Test {
    public static void main(String[] args) {
        EntityManager manager = JpaUtil.getEntityManager();
        /*try {
            manager.getTransaction().begin();
            // Crear instancias de las entidades
            Teacher teacher1 = new Teacher(1, "Juan", "Perez",null);
            Teacher teacher2 = new Teacher(2, "Sergio", "Perez",null);

            Subject mathSubject = new Subject(1, "Matematicas", null);
            Subject scienceSubject = new Subject(1, "Ciencias", null);

            Group group1 = new Group(1, teacher1, null, mathSubject);
            Group group2 = new Group(2, teacher2, null, scienceSubject);

            Mark mathMark = new Mark(1, null, 21, mathSubject, null);
            Mark scienceMark = new Mark(1, null, 12, scienceSubject, null);

            teacher1.getGroups().add(group1);
            teacher2.getGroups().add(group2);

            group1.setTeachers(teacher1);

            group1.setSubject(mathSubject);
            group2.setSubject(scienceSubject);

            mathSubject.getMarks().add(mathMark);
            scienceSubject.getMarks().add(scienceMark);

            manager.persist(teacher1);
            manager.persist(teacher2);
            manager.persist(group1);
            manager.persist(group2);
            manager.persist(mathSubject);
            manager.persist(mathMark);
            manager.persist(scienceSubject);
            manager.persist(scienceMark);

            manager.getTransaction().commit();
            System.out.println("PRINT DATA: " + teacher1 + "\'" + teacher2 + "\'" + group1 + "\'" + group2 + "\'" + mathSubject
                    + "\'" + mathMark + "\'" + scienceSubject + "\'" + scienceMark);

            System.out.println("TCL transaction TEST");
            manager.getTransaction().begin();
            Teacher t = manager.find(Teacher.class, 1);
            manager.getTransaction().commit();
            System.out.println(t);
        } catch (Exception e){
            manager.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }*/
    }
}