package com.challenge.mccustomerstatistics.model.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@ApiModel
public class CustomerStatisticsRequest {

    @ApiModelProperty(value = "id", required = true , position = 1 , example = "1")
    private Long id;

    @ApiModelProperty(value = "name", required = true , position = 2 , example = "Lucero")
    private String name;

    @ApiModelProperty(value = "fatherLastName", required = true , position = 3 ,example = "Ampudia")
    private String fatherLastName;

    @ApiModelProperty(value = "motherLastName", required = true , position = 4, example = "Trigoso")
    private String motherLastName;

    @ApiModelProperty(value = "age", required = true , position = 5, example = "26")
    private int age;

    @ApiModelProperty(value = "dateOfBirth", required = true , position = 6, example = "24/04/1995")
    private String dateOfBirth;
}
