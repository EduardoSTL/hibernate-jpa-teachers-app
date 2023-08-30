package org.eduardo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Subject_teacher")
public class SubjectTeacher {

    @EmbeddedId
    private SubjectTeacherId id;

    @ManyToOne
    @MapsId("group_Id")
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @MapsId("teacher_Id")
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    //Backup
    /*@ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject ;*/
}
