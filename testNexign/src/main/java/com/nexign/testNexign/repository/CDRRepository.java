package com.nexign.testNexign.repository;

import com.nexign.testNexign.model.CDR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CDRRepository extends JpaRepository<CDR, Long>{
        //List<CDR> findTop10ByOrderByTimeDesc();
}
