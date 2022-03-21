package com.challenge.mccustomerstatistics.dao.impl;

import com.challenge.mccustomerstatistics.businness.impl.CustomerStatisticsServiceImpl;
import com.challenge.mccustomerstatistics.model.CustomerStatisticsEntity;
import com.challenge.mccustomerstatistics.model.dto.request.CustomerStatisticsRequest;
import com.challenge.mccustomerstatistics.model.dto.response.CustomerStatisticsKpiResponse;
import com.challenge.mccustomerstatistics.model.dto.response.CustomerStatisticsResponse;
import com.challenge.mccustomerstatistics.repository.CustomerStatisticsRepository;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerStatisticsDaoImplTest {

    @InjectMocks
    private CustomerStatisticsDaoImpl customerStatisticsDao;

    @Mock
    private CustomerStatisticsRepository customerStatisticsRepository;

    @Test
    void returnCustomerStatisticsServiceWhenCreateClientIsOk() {
        var testObserver = customerStatisticsDao
                .createClient(makeRequest().build()).test();
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
    }

    @Test
    void returnCustomerStatisticsServiceWhenListClientsIsOk() {
        ArrayList<CustomerStatisticsResponse> listCustomers = new ArrayList<>();
        listCustomers.add(makeResponse().build());
        listCustomers.add(makeResponse().name("Marcelo").build());
        var testObserver = customerStatisticsDao
                .listClients().test();
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
    }

    CustomerStatisticsRequest.CustomerStatisticsRequestBuilder makeRequest(){
        return CustomerStatisticsRequest.builder()
                .name("Lucero")
                .fatherLastName("Ampudia")
                .motherLastName("Trigoso")
                .age(26)
                .dateOfBirth("24-04-1996");
    }

    CustomerStatisticsResponse.CustomerStatisticsResponseBuilder makeResponse(){
        return CustomerStatisticsResponse.builder()
                .name("Lucero")
                .fatherLastName("Ampudia")
                .motherLastName("Trigoso")
                .age(26)
                .dateOfBirth("24-04-1996");
    }

    CustomerStatisticsEntity.CustomerStatisticsEntityBuilder makeEntity(){
        return CustomerStatisticsEntity.builder()
                .name("Lucero")
                .fatherLastName("Ampudia")
                .motherLastName("Trigoso")
                .age(26)
                .dateOfBirth("24-04-1996");
    }

    CustomerStatisticsKpiResponse.CustomerStatisticsKpiResponseBuilder makeKpiResponse(){
        return CustomerStatisticsKpiResponse.builder()
                .averageAges(BigDecimal.valueOf(24))
                .standardDeviation(BigDecimal.valueOf(2.64));
    }
}