package com.ekar.test.app.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Builder
@Table(name = "request_response_log")
public class RequestResponseLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "producer_thread_count")
    private Integer producerThreadCount;

    @Column(name = "consumer_thread_count")
    private Integer consumerThreadCount;

    @Column(name = "request_date_time")
    private LocalDateTime dateTime;
}
