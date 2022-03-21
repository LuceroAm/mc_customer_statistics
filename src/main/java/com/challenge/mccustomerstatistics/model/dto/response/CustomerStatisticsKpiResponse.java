package com.challenge.mccustomerstatistics.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerStatisticsKpiResponse {

    private String averageAges;

    private String standardDeviation;
}
