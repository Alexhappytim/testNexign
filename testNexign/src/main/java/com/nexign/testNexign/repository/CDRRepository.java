package com.nexign.testNexign.repository;

import com.nexign.testNexign.model.CDR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
@Repository
public interface CDRRepository extends JpaRepository<CDR, Long>{
    List<CDR> findByInitiatorNumber(String number);
    List<CDR> findByReceiverNumber(String number);
    @Query(value ="SELECT cdr.id,call_type,start_date,end_date,initiator,receiver FROM call_records AS cdr INNER JOIN subscribers ON initiator=subscribers.id WHERE MONTH(cdr.start_date) = :month AND subscribers.number = :number", nativeQuery = true)
    List<CDR> findByInitiatorNumberAndMonth(@Param("number") String number, @Param("month") Integer month);
    @Query(value = "SELECT cdr.id,call_type,start_date,end_date,initiator,receiver FROM call_records AS cdr INNER JOIN subscribers ON receiver=subscribers.id WHERE MONTH(cdr.start_date) = :month AND subscribers.number = :number",nativeQuery = true)
    List<CDR> findByReceiverNumberAndMonth(@Param("number") String number, @Param("month") Integer month);
    @Query(value="SELECT cdr.id,call_type,start_date,end_date,initiator,receiver FROM call_records AS cdr INNER JOIN subscribers ON receiver=subscribers.id WHERE subscribers.number = :number AND cdr.start_date>=:start_date AND cdr.end_date<=:end_date",nativeQuery = true)
    List<CDR> findByReceiverNumberAndTimeInterval(@Param("number") String number, @Param("start_date") Instant startDate, @Param("end_date") Instant endDate);
    @Query(value="SELECT cdr.id,call_type,start_date,end_date,initiator,receiver FROM call_records AS cdr INNER JOIN subscribers ON initiator=subscribers.id WHERE subscribers.number = :number AND cdr.start_date>=:start_date AND cdr.end_date<=:end_date",nativeQuery = true)
    List<CDR> findByInitiatorNumberAndTimeInterval(@Param("number") String number, @Param("start_date") Instant startDate, @Param("end_date") Instant endDate);
}
