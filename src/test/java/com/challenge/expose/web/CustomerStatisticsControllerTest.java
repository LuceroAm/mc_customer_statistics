package com.challenge.expose.web;

import com.challenge.mccustomerstatistics.businness.impl.CustomerStatisticsServiceImpl;
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
class CustomerStatisticsControllerTest {

    @InjectMocks
    private CustomerStatisticsController customerStatisticsController;

    @Mock
    private CustomerStatisticsServiceImpl customerStatisticsService;

    @Test
    void returnCustomerStatisticsControllerWhenCreateClientIsOk() {
        when(customerStatisticsService.createClient(makeRequest().build()))
                .thenReturn(Completable.complete());
        var testObserver = customerStatisticsController
                .createClient(makeRequest().build()).test();
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
    }

    @Test
    void returnCustomerStatisticsControllerWhenListClientsIsOk() {
        ArrayList<CustomerStatisticsResponse> listCustomers = new ArrayList<>();
        listCustomers.add(makeResponse().build());
        listCustomers.add(makeResponse().name("Marcelo").build());
        when(customerStatisticsService.listClients())
                .thenReturn(Observable.fromIterable(listCustomers));
        var testObserver = customerStatisticsController
                .listClients().test();
        testObserver.awaitTerminalEvent();
        testObserver.assertComplete();
    }

    @Test
    void returnCustomerStatisticsControllerWhenkpiAgeClientsIsOk() {
        ArrayList<CustomerStatisticsResponse> listCustomers = new ArrayList<>();
        listCustomers.add(makeResponse().build());
        listCustomers.add(makeResponse().name("Marcelo").age(21).build());
        listCustomers.add(makeResponse().name("Gary").age(25).build());
        when(customerStatisticsService.kpiAgeClients())
                .thenReturn(Maybe.just(makeKpiResponse().build()));
        var testObserver = customerStatisticsController
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