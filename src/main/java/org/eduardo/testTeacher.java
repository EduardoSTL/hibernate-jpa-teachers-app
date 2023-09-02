package org.eduardo;

import jakarta.persistence.EntityManager;
import org.eduardo.entity.*;
import org.eduardo.service.*;
import org.eduardo.util.EntityManagerUtil;

public class testTeacher {
    public static void main(String[] args) {
        EntityManager entityManager = EntityManagerUtil.getEntityManager();

        // Inicializar Teacher service
        GenericService<Teacher> teacherService = new TeacherServiceImpl();

        try {
            Teacher newTeacher = new Teacher();
            newTeacher.setNombre("uno");
            newTeacher.setApellido("dos");

            // Guardar el registro
            Teacher savedTeacher = teacherService.save(newTeacher);
            System.out.println("Teacher guardado con ID: " + savedTeacher.getId() + ", Nombre: " + savedTeacher.getNombre() +
                    ", Apellido: " + savedTeacher.getApellido());

            // Buscar un registro por ID
            Integer teacherId = 1;
            teacherService.findById(teacherId).ifPresent(teacher -> {
                System.out.println("Teacher encontrado por ID: " + teacher);
            });

            // Editar un registro por ID
            Integer teacherToEditId = 2;
            teacherService.edit(teacherToEditId);

            // Eliminar un Teacher por ID
            Integer teacherToDeleteId = 5;
            teacherService.deleteById(teacherToDeleteId);
            System.out.println("Teacher con ID " + teacherToDeleteId + " eliminado correctamente.");


            // Buscar todos los teachers
            Iterable<Teacher> teachers = teacherService.findAll();
            System.out.println("Lista de Teachers:");
            for (Teacher teacher : teachers) {
                System.out.println(teacher);
            }
        } finally {
            EntityManagerUtil.closeEntityManager(entityManager);
        }
    }
}