package com.nexign.testNexign.controller;

import com.nexign.testNexign.model.UDR;
import com.nexign.testNexign.service.UDRService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * Контроллер для эндпоинта 3 задачи
 */
@RestController
@RequestMapping("/api/udr")
public class UDRController {

    private UDRService udrService;

    public UDRController(UDRService udrService) {
        this.udrService = udrService;
    }

    @GetMapping("/{phoneNumber}")
    public UDR getUDRForOneUser(@PathVariable("phoneNumber") String number, @RequestParam(required = false) Integer month){
        if (month != null && (month < 1 || month > 12)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Ошибка: месяц должен быть от 1 до 12.");
        }
        return udrService.getUDRForOneUser(number, month);
    }
    @GetMapping
    public List<UDR> getUDRForAll(@RequestParam Integer month){
        if (month != null && (month < 1 || month > 12)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Ошибка: месяц должен быть от 1 до 12.");
        }
        return udrService.getUDRForAll(month);
    }
}
