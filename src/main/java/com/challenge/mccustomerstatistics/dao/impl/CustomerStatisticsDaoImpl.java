package com.challenge.mccustomerstatistics.dao.impl;

import com.challenge.mccustomerstatistics.dao.CustomerStatisticsDao;
import com.challenge.mccustomerstatistics.model.CustomerStatisticsEntity;
import com.challenge.mccustomerstatistics.model.dto.request.CustomerStatisticsRequest;
import com.challenge.mccustomerstatistics.model.dto.response.CustomerStatisticsKpiResponse;
import com.challenge.mccustomerstatistics.model.dto.response.CustomerStatisticsResponse;
import com.challenge.mccustomerstatistics.repository.CustomerStatisticsRepository;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@Repository
@Slf4j
@Data
public class CustomerStatisticsDaoImpl implements CustomerStatisticsDao {

    @Autowired
    private CustomerStatisticsRepository customerStatisticsRepository;

    @Override
    public Completable createClient(CustomerStatisticsRequest request) {
        return Single.fromCallable(()-> makeCustomerEntity(request))
                .map(customerStatisticsRepository::save)
                .toCompletable();
    }

    @Override
    public Observable<CustomerStatisticsResponse> listClients() {
        return Observable.fromIterable(customerStatisticsRepository.findAll())
                .map(this::listCustomer)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Maybe<CustomerStatisticsKpiResponse> kpiAgeClients() {
        return Observable.fromIterable(customerStatisticsRepository.findAll())
                .map(this::listCustomer)

                .toList()
                .map(list -> kpiResponse(list))
                .toMaybe();
    }


    private CustomerStatisticsEntity makeCustomerEntity(CustomerStatisticsRequest request)
    {
        return CustomerStatisticsEntity.builder()
                .name(request.getName())
                .fatherLastName(request.getFatherLastName())
                .motherLastName(request.getMotherLastName())
                .age(request.getAge())
                .dateOfBirth(request.getDateOfBirth())
                .build();

    }

    private CustomerStatisticsResponse listCustomer(CustomerStatisticsEntity entity)
    {
        return CustomerStatisticsResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .fatherLastName(entity.getFatherLastName())
                .motherLastName(entity.getMotherLastName())
                .age(entity.getAge())
                .dateOfBirth(entity.getDateOfBirth())
                .build();
    }

    private CustomerStatisticsKpiResponse kpiResponse(List<CustomerStatisticsResponse> listCustomers){
        return CustomerStatisticsKpiResponse.builder()
                .averageAges(calculateAverage(listCustomers))
                .standardDeviation(calculateStandardDeviation(listCustomers,calculateAverage(listCustomers)))
                .build();
    }

    private BigDecimal calculateAverage (List<CustomerStatisticsResponse> listCustomers){
        BigDecimal totalAges = new BigDecimal(0.0).setScale(2);
        for (CustomerStatisticsResponse item : listCustomers) {
            totalAges = totalAges.add(BigDecimal.valueOf(item.getAge()));
        }
        BigDecimal average = totalAges.divide(BigDecimal.valueOf(listCustomers.size()));
        return average;

    }

    private BigDecimal calculateStandardDeviation (List<CustomerStatisticsResponse> listCustomers , BigDecimal averageAges){
        BigDecimal summation = new BigDecimal(0.0).setScale(2);
        BigDecimal variance = new BigDecimal(0.0).setScale(2);
        BigDecimal deviation = new BigDecimal(0.0).setScale(2);
        for (CustomerStatisticsResponse item : listCustomers) {
            Double age = Double.valueOf(item.getAge());
            Double average = Double.valueOf(String.valueOf(averageAges));
            variance = variance.add(BigDecimal.valueOf(Math.pow(age - average, 2)));

        }
        Double denominador = Double.valueOf((listCustomers.size() - 1));
        variance = BigDecimal.valueOf(Double.valueOf(String.valueOf(variance)) / denominador);
        deviation = BigDecimal.valueOf(Math.sqrt(Double.valueOf(String.valueOf(variance))));
        return deviation;

    }


}
