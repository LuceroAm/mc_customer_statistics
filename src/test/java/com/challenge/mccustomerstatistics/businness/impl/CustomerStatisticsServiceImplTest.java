package com.challenge.mccustomerstatistics.businness.impl;

import com.challenge.mccustomerstatistics.dao.impl.CustomerStatisticsDaoImpl;
import com.challenge.mccustomerstatistics.model.dto.request.CustomerStatisticsRequest;
import com.challenge.mccustomerstatistics.model.dto.response.CustomerStatisticsKpiResponse;
import com.challenge.mccustomerstatistics.model.dto.response.CustomerStatisticsResponse;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerStatisticsServiceImplTest {

    @InjectMocks
    private CustomerStatisticsServiceImpl customerStatisticsService;

    @Mock
    private CustomerStatisticsDaoImpl customerStatisticsDao;

    @Test
    void returnCustomerStatisticsServiceWhenCreateClientIsOk() {
        when(customerStatisticsDao.createClient(makeRequest().build()))
                .thenReturn(Completable.complete());
        var testObserver = customerStatisticsService
                .createClient(makeRequest().build()).test();
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
    }

    @Test
    void returnCustomerStatisticsServiceWhenListClientsIsOk() {
        ArrayList<CustomerStatisticsResponse> listCustomers = new ArrayList<>();
        listCustomers.add(makeResponse().build());
        listCustomers.add(makeResponse().name("Marcelo").build());
        when(customerStatisticsDao.listClients())
                .thenReturn(Observable.fromIterable(listCustomers));
        var testObserver = customerStatisticsService
                .listClients().test();
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
    }

    @Test
    void returnCustomerStatisticsServiceWhenkpiAgeClientsIsOk() {
        ArrayList<CustomerStatisticsResponse> listCustomers = new ArrayList<>();
        listCustomers.add(makeResponse().build());
        listCustomers.add(makeResponse().name("Marcelo").age(21).build());
        listCustomers.add(makeResponse().name("Gary").age(25).build());
        when(customerStatisticsDao.kpiAgeClients())
                .thenReturn(Maybe.just(makeKpiResponse().build()));
        var testObserver = customerStatisticsService
                .kpiAgeClients().test();
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

    CustomerStatisticsKpiResponse.CustomerStatisticsKpiResponseBuilder makeKpiResponse(){
        return CustomerStatisticsKpiResponse.builder()
                .averageAges(BigDecimal.valueOf(24))
                .standardDeviation(BigDecimal.valueOf(2.64));
    }
}