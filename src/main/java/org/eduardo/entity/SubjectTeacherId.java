package org.eduardo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class SubjectTeacherId implements Serializable {

    private Group group;
    private Teacher teacher;
    private Subject subject;

    /*@Column(name = "group_id")
    private Integer groupId;

    @Column(name = "teacher_id")
    private Integer teacherId;*/
}
