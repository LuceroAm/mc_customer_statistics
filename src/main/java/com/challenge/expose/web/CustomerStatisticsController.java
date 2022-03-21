package com.challenge.expose.web;

import com.challenge.mccustomerstatistics.businness.CustomerStatisticsService;
import com.challenge.mccustomerstatistics.model.dto.request.CustomerStatisticsRequest;
import com.challenge.mccustomerstatistics.model.dto.response.CustomerStatisticsKpiResponse;
import com.challenge.mccustomerstatistics.model.dto.response.CustomerStatisticsResponse;
import com.challenge.mccustomerstatistics.util.Constants;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Constants.MAIN_PATH)
@Api(tags = "Microservice CustomerStatistics", description = "Esta API se encarga de la gestion de los Clientes")
@Slf4j
public class CustomerStatisticsController {

    @Autowired
    private CustomerStatisticsService customerStatisticsService;

    @PostMapping(value = Constants.SAVE_CLIENT)
    @ApiOperation(value = Constants.SAVE_VALUE, notes = Constants.SAVE_NOTE)
    public Completable createClient(@RequestBody CustomerStatisticsRequest request){
        return customerStatisticsService.createClient(request);
    }

    @GetMapping(Constants.LIST_CLIENT)
    @ApiOperation(value = Constants.GET_LIST_VALUE)
    public Observable<CustomerStatisticsResponse> listClients(){
        return  customerStatisticsService.listClients();
    }

    @GetMapping(Constants.KPI)
    @ApiOperation(value = Constants.GET_KPI_VALUE)
    public Maybe<CustomerStatisticsKpiResponse> kpiAgeClients(){
        return customerStatisticsService.kpiAgeClients();
    }


}
