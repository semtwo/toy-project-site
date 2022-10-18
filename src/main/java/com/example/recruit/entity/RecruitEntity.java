package com.example.recruit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
//import java.sql.Date;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name ="recruitment")
public class RecruitEntity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
    @Column
    private String title;
    @Column
    private String dept;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fromDate;
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;
    @Column
    private String contents;

    @Column(name = "r_num")
    private int rnum;
    @Column
    private String author_id;

}
