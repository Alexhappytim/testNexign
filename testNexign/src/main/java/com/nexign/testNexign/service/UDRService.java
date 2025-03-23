package com.nexign.testNexign.service;

import com.nexign.testNexign.model.CDR;
import com.nexign.testNexign.model.Subscriber;
import com.nexign.testNexign.model.UDR;
import com.nexign.testNexign.repository.CDRRepository;
import com.nexign.testNexign.repository.SubscriberRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для бизнес-логики связанной с UDR
 */
@Service
public class UDRService {
    private SubscriberRepository subscriberRepository;
    private CDRRepository cdrRepository;

    public UDRService(SubscriberRepository subscriberRepository, CDRRepository cdrRepository) {
        this.subscriberRepository = subscriberRepository;
        this.cdrRepository = cdrRepository;
    }

    /**
     * Создает UDR отчет по конкретному абоненту за месяц(опционально)
     * @param number Номер телефона абонента
     * @param month Месяц по которому собирается отчет
     * @return UDR отчет
     */
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

    /**
     * Создает UDR отчеты по всем абонентам содержащимся в БД
     * @param month месяц по которому собирается отчет
     * @return список UDR отчетов
     */
    public List<UDR> getUDRForAll(Integer month) {
        List<Subscriber> subList = subscriberRepository.findAll();
        List<UDR> udrList = new ArrayList<>();
        for(Subscriber s : subList){
            udrList.add(getUDRForOneUser(s.getNumber(),month));
        }
        return udrList;
    }
}
