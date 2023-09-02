package org.eduardo.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class SubjectTeacherId implements Serializable {

    private Group group;
    private Teacher teacher;
    private Subject subject;
}
