package com.nexign.testNexign.repository;

import com.nexign.testNexign.model.CDR;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CDRRepository extends JpaRepository<CDR, Long>{
        //List<CDR> findTop10ByOrderByTimeDesc();
}
