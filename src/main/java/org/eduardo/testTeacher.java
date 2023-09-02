package org.eduardo;

import jakarta.persistence.EntityManager;
import org.eduardo.entity.*;
import org.eduardo.service.*;
import org.eduardo.util.JpaUtil;


public class testTeacher {
    public static void main(String[] args) {
        EntityManager em = JpaUtil.getEntityManager();

        //Instanciar serviceImpl
        GenericService<Teacher> teacherService = new TeacherServiceImpl();

        // Crear un nuevo registro
        Teacher newTeacher = new Teacher();
        newTeacher.setNombre("uno");
        newTeacher.setApellido("dos");

        // Guardar el registro en la base de datos
        Teacher savedTeacher = teacherService.save(newTeacher);
        System.out.println("Teacher guardado con ID: " + savedTeacher.getId() + ", Nombre: " + savedTeacher.getNombre() +
        ", Apellido: " + savedTeacher.getApellido());

        // Buscar un teacher por ID
        Integer teacherId = 1;
        teacherService.findById(teacherId).ifPresent(teacher -> {
            System.out.println("Teacher encontrado por ID: " + teacher);
        });

        // Editar un Teacher por ID
        Integer teacherToEditId = 2; // Reemplaza con el ID del Teacher que deseas editar
        teacherService.edit(teacherToEditId);

        // Buscar todos los teachers
        Iterable<Teacher> teachers = teacherService.findAll();
        System.out.println("Lista de Teachers:");
        for (Teacher teacher : teachers) {
            System.out.println(teacher);
        }
        em.close();
    }
}

