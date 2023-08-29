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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = false)
    @JoinTable(name = "subject/teacher", joinColumns = @JoinColumn(name="teacher_id")
            , inverseJoinColumns = {
            @JoinColumn(name = "group_id"),
            @JoinColumn(name = "subject_id")},
            uniqueConstraints = @UniqueConstraint(columnNames = {"teacher_id" ,"group_id", "subject_id"}))
    private List<Group> groups;
}