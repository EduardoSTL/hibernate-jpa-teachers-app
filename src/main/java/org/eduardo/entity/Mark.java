package org.eduardo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id")
    private Integer id;

    @Column(name = "date_time")
    private LocalDateTime date;

    private Integer mark;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd,MM,yyyy");
        String dateFormat = (date != null) ? date.format(formatter) : null;
        return "{" +
                "id=" + id +
                ", date=" + dateFormat + '\'' +
                ", mark=" + mark + '\'' +
                /*", subject=" + subject + '\'' +
                ", student=" + student + '\'' +*/
                '}';
    }
}