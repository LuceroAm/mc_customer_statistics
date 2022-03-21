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
        return null;
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

}
