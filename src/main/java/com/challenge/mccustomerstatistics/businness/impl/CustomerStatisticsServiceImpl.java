package com.challenge.mccustomerstatistics.businness.impl;

import com.challenge.mccustomerstatistics.businness.CustomerStatisticsService;
import com.challenge.mccustomerstatistics.dao.CustomerStatisticsDao;
import com.challenge.mccustomerstatistics.model.dto.request.CustomerStatisticsRequest;
import com.challenge.mccustomerstatistics.model.dto.response.CustomerStatisticsKpiResponse;
import com.challenge.mccustomerstatistics.model.dto.response.CustomerStatisticsResponse;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerStatisticsServiceImpl  implements CustomerStatisticsService {

    @Autowired
    private CustomerStatisticsDao customerStatisticsDao;

    @Override
    public Completable createClient(CustomerStatisticsRequest request) {
        return customerStatisticsDao.createClient(request);
    }

    @Override
    public Observable<CustomerStatisticsResponse> listClients() {
        return customerStatisticsDao.listClients();
    }

    @Override
    public Maybe<CustomerStatisticsKpiResponse> kpiAgeClients() {
        return customerStatisticsDao.kpiAgeClients();
    }
}
