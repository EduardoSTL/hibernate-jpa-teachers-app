package org.eduardo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    private Integer id;

    @Column(name = "first_name")
    private String nombre;

    @Column(name = "last_name")
    private String apellido;

    @OneToMany(mappedBy = "teacher")
    private List<SubjectTeacher> subjectTeachers;
}