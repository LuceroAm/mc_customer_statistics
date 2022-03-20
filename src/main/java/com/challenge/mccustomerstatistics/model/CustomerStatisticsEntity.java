package com.challenge.mccustomerstatistics.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table(name = "customer")
@AllArgsConstructor
@NoArgsConstructor
public class CustomerStatisticsEntity {

    @Column(name = "id")
    private Long id;

    @Column(name = "Nombre")
    private String name;

    @Column(name = "Apellido_Paterno")
    private String ape_pat;

    @Column(name = "Apellido_Materno")
    private String ape_mat;

    @Column(name = "Edad")
    private int age;

    @Column(name = "Fecha_Nacimiento")
    private Date Fec_Nacimiento;

}
