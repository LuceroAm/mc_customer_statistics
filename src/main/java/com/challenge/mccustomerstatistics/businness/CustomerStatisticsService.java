package com.challenge.mccustomerstatistics.businness;

import com.challenge.mccustomerstatistics.model.dto.request.CustomerStatisticsRequest;
import com.challenge.mccustomerstatistics.model.dto.response.CustomerStatisticsKpiResponse;
import com.challenge.mccustomerstatistics.model.dto.response.CustomerStatisticsResponse;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

public interface CustomerStatisticsService {
    Completable createClient(CustomerStatisticsRequest request);

    Observable<CustomerStatisticsResponse> listClients();

    Maybe<CustomerStatisticsKpiResponse> kpiAgeClients();
}
