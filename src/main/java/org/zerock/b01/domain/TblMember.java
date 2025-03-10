package org.zerock.b01.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TblMember {

    @Id
    @Column(length = 50)
    private String mid;

    @Column(length = 50, nullable = false)
    private String mpw;

    @Column(length = 100, nullable = false)
    private String mname;

    @Column(length = 50)
    private String uuid;

    public void change(String title) {


    }
}
