package com.ekar.test.app.repository;

import com.ekar.test.app.entity.CounterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CounterDetailsRepository extends JpaRepository<CounterDetails, Long> {
}
