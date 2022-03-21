package com.challenge.mccustomerstatistics.repository;

import com.challenge.mccustomerstatistics.model.CustomerStatisticsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface CustomerStatisticsRepository extends JpaRepository<CustomerStatisticsEntity, Long> {

}
