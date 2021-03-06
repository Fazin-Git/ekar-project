package com.ekar.test.app.repository;

import com.ekar.test.app.entity.RequestResponseLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestResponseLogRepository extends JpaRepository<RequestResponseLog, Long> {
}
