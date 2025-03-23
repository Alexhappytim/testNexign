package com.nexign.testNexign.service;

import com.nexign.testNexign.model.CDR;
import com.nexign.testNexign.model.Subscriber;
import com.nexign.testNexign.model.UDR;
import com.nexign.testNexign.repository.CDRRepository;
import com.nexign.testNexign.repository.SubscriberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
@Service
@AllArgsConstructor
public class UDRService {
    private SubscriberRepository subscriberRepository;
    private CDRRepository cdrRepository;
    public UDR getUDRForOneUser(String number, Integer month) {
        if(month == null){
            List<CDR> incomingCalls = cdrRepository.findByReceiverNumber(number);
            List<CDR> outcomingCalls = cdrRepository.findByInitiatorNumber(number);
            return new UDR(number,
                    new UDR.CallTime(incomingCalls.stream().map(s -> {return Duration.between(s.getStartDate(),s.getEndDate());}).reduce(Duration.ZERO,Duration::plus)),
                    new UDR.CallTime(outcomingCalls.stream().map(s -> {return Duration.between(s.getStartDate(),s.getEndDate());}).reduce(Duration.ZERO,Duration::plus)));
        }
        List<CDR> incomingCalls = cdrRepository.findByReceiverNumberAndMonth(number,month);
        List<CDR> outcomingCalls = cdrRepository.findByInitiatorNumberAndMonth(number,month);
        return new UDR(number,
                new UDR.CallTime(incomingCalls.stream().map(s -> {return Duration.between(s.getStartDate(),s.getEndDate());}).reduce(Duration.ZERO,Duration::plus)),
                new UDR.CallTime(outcomingCalls.stream().map(s -> {return Duration.between(s.getStartDate(),s.getEndDate());}).reduce(Duration.ZERO,Duration::plus)));
    }

    public List<UDR> getUDRForAll(Integer month) {
        List<Subscriber> subList = subscriberRepository.findAll();
        List<UDR> udrList = new ArrayList<>();
        for(Subscriber s : subList){
            udrList.add(getUDRForOneUser(s.getNumber(),month));
        }
        return udrList;
    }
}
