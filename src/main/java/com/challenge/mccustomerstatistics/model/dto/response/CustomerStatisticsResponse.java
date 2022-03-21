package com.challenge.mccustomerstatistics.model.dto.response;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class CustomerStatisticsResponse {
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

    //    @Column(name = "Fecha_Nacimiento")
//      private Date Fec_Nacimiento;
}
