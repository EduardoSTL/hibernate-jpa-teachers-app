package org.eduardo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "marks")
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mark_id")
    private Integer id;

    private LocalDateTime date;

    private Integer mark;


}
