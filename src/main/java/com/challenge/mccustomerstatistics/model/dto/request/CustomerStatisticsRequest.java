package com.challenge.mccustomerstatistics.model.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import java.util.Date;

@Data
@Builder
@ApiModel
public class CustomerStatisticsRequest {

    @ApiModelProperty(value = "id", required = true , position = 1)
    private Long id;

    @ApiModelProperty(value = "name", required = true , position = 2)
    private String name;

    @ApiModelProperty(value = "fatherLastName", required = true , position = 3)
    private String fatherLastName;

    @ApiModelProperty(value = "motherLastName", required = true , position = 4)
    private String motherLastName;

    @ApiModelProperty(value = "age", required = true , position = 5)
    private int age;

    @ApiModelProperty(value = "dateOfBirth", required = true , position = 6)
    private String dateOfBirth;
}
