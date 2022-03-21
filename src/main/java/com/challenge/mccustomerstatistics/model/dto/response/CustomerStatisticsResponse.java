package com.challenge.mccustomerstatistics.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerStatisticsResponse {

    private Long id;

    private String name;

    private String fatherLastName;

    private String motherLastName;

    private int age;

    private String dateOfBirth;
}
