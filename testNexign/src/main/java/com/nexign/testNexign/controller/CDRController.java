package com.nexign.testNexign.controller;

import com.nexign.testNexign.service.CDRService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;

/**
 * Контроллер для эндпоитов для 3 задачи
 */
@RestController
@RequestMapping("/api/cdr")
public class CDRController {
    private CDRService cdrService;

    public CDRController(CDRService cdrService) {
        this.cdrService = cdrService;
    }

    @PostMapping("/{phoneNumber}")
    public ResponseEntity<String> genCDRReport(@PathVariable("phoneNumber") String number, @RequestParam Instant startDate,@RequestParam Instant endDate){
        return cdrService.genCDRReport(number,startDate,endDate);
    }
}
