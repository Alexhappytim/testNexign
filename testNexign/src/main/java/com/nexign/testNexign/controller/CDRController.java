package com.nexign.testNexign.controller;

import com.nexign.testNexign.service.CDRService;
import com.nexign.testNexign.service.UDRService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
@RestController
@RequestMapping("/api/cdr")
@AllArgsConstructor
public class CDRController {
    private CDRService cdrService;
    @PostMapping("/{phoneNumber}")
    public ResponseEntity<String> genCDRReport(@PathVariable("phoneNumber") String number, @RequestParam Instant startDate,@RequestParam Instant endDate){
        return cdrService.genCDRReport(number,startDate,endDate);
    }
}
