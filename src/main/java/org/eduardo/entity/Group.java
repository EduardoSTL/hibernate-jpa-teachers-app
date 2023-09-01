package org.eduardo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "´groups´")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Integer id;

    @Column(name = "name")
    private String nombre;

    /*@OneToMany(mappedBy = "group")
    private List<SubjectTeacher> subjectTeachers;

    @OneToMany(mappedBy = "group")
    private List<Student> students;*/

    /*@ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject ;*/
}