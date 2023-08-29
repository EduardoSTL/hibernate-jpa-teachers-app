package org.eduardo;

import org.eduardo.entity.Group;
import org.eduardo.entity.Mark;
import org.eduardo.entity.Subject;
import org.eduardo.entity.Teacher;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Test {
    public static void main(String[] args) {
        // Crear instancias de las entidades
        Teacher teacher1 = new Teacher(1, "Juan", "Perez",null);
        Teacher teacher2 = new Teacher(2, "Sergio", "Perez",null);

        Subject mathSubject = new Subject(1, "Matematicas", null);
        Subject scienceSubject = new Subject(1, "Ciencias", null);

        Group group1 = new Group(1, teacher1, null, mathSubject);
        Group group2 = new Group(2, teacher2, null, scienceSubject);

        Mark mathMark = new Mark(1, null, 21, mathSubject, null);
        Mark scienceMark = new Mark(1, null, 12, scienceSubject, null);

// Establecer relaciones
        teacher1.getGroups().add(group1);
        teacher2.getGroups().add(group2);

        group1.setTeacher(teacher1);
        group2.setTeacher(teacher2);

        group1.setSubject(mathSubject);
        group2.setSubject(scienceSubject);

        mathSubject.getMarks().add(mathMark);
        scienceSubject.getMarks().add(scienceMark);
    }
}