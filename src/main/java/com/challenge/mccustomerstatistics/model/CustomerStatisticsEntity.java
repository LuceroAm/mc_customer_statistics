package com.challenge.mccustomerstatistics.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerStatisticsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "fatherLastName")
    private String fatherLastName;

    @Column(name = "motherLastName")
    private String motherLastName;

    @Column(name = "age")
    private int age;

    @Column(name = "dateOfBirth")
    private String dateOfBirth;

}
