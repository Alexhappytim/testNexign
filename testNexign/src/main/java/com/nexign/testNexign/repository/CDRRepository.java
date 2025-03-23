package com.nexign.testNexign.repository;

import com.nexign.testNexign.model.CDR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CDRRepository extends JpaRepository<CDR, Long>{
    List<CDR> findByInitiatorNumber(String number);
    List<CDR> findByReceiverNumber(String number);
    @Query(value ="SELECT cdr.id,call_type,start_date,end_date,initiator,receiver FROM call_records AS cdr INNER JOIN subscribers ON initiator=subscribers.id WHERE MONTH(cdr.start_date) = :month AND subscribers.number = :number", nativeQuery = true)
    List<CDR> findByInitiatorNumberAndMonth(@Param("number") String number, @Param("month") Integer month);
    @Query(value = "SELECT cdr.id,call_type,start_date,end_date,initiator,receiver FROM call_records AS cdr INNER JOIN subscribers ON receiver=subscribers.id WHERE MONTH(cdr.start_date) = :month AND subscribers.number = :number",nativeQuery = true)
    List<CDR> findByReceiverNumberAndMonth(@Param("number") String number, @Param("month") Integer month);


}
