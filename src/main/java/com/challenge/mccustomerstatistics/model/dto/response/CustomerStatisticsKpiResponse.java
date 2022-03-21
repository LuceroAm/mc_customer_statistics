package com.challenge.mccustomerstatistics.model.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CustomerStatisticsKpiResponse {

    private BigDecimal averageAges;

    private BigDecimal standardDeviation;
}
