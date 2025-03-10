package org.zerock.b01.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer tno;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false)
    private LocalDate due_date;

    @Column(length = 500, nullable = false)
    private String writer;

    //    @ColumnDefault("0")
//    private Byte finished;
    @ColumnDefault("false")
    private Boolean finished;
}
