package org.eduardo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@IdClass(SubjectTeacherId.class)
@Table(name = "Subject_teacher")
public class SubjectTeacher {
    @Id
    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Id
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;
}