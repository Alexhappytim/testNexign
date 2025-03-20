package com.nexign.testNexign.util;

import com.nexign.testNexign.model.CDR;
import com.nexign.testNexign.model.CallType;
import com.nexign.testNexign.model.Subscriber;
import com.nexign.testNexign.repository.CDRRepository;
import com.nexign.testNexign.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Year;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
@Service
@RequiredArgsConstructor
public class CDRGenerator {

    private final CDRRepository cdrRepository;

    private final SubscriberRepository subscriberRepository;

    public void generate(Long amountOfCDR) {
        List<CDR> generatedCDRs = cdrRepository.findAll();
        List<Subscriber> subscribers = subscriberRepository.findAll();
        for (long i = 0; i < amountOfCDR; i++) {
            CDR cdr = new CDR();
            cdr.setCallType(ThreadLocalRandom.current().nextInt(2) == 0 ? CallType.OUTGOING : CallType.INCOMING);
            Subscriber firstSub = subscribers.get(ThreadLocalRandom.current().nextInt(subscribers.size()));
            Subscriber secondSub;
            do {
                secondSub = subscribers.get(ThreadLocalRandom.current().nextInt(subscribers.size()));
            } while (secondSub.equals(firstSub));
            cdr.setInitiatorNumber(firstSub.getNumber());
            cdr.setReceiverNumber(secondSub.getNumber());
            System.out.println(i);


            Year currentYear = Year.now();
            Instant start = currentYear.atDay(1)
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant();
            Instant end = currentYear.plusYears(1)
                    .atDay(1)
                    .atStartOfDay(ZoneId.systemDefault())
                    .toInstant();

            long startEpoch = start.getEpochSecond();
            long endEpoch = end.getEpochSecond() - 1;
            long firstDate;
            long secondDate;
            do {
                firstDate = ThreadLocalRandom.current().nextLong(startEpoch, endEpoch + 1);
                secondDate = ThreadLocalRandom.current().nextLong(firstDate, endEpoch + 1);

            }while (checkIntersection(firstSub,secondSub,firstDate,secondDate,generatedCDRs));
            cdr.setStartDate(Instant.ofEpochSecond(firstDate));
            cdr.setEndDate(Instant.ofEpochSecond(secondDate));
            generatedCDRs.add(cdr);
            cdrRepository.save(cdr);
        }
    }

    private boolean checkIntersection(Subscriber firstSub, Subscriber secondSub, long firstDate, long secondDate, List<CDR> list) {
        return false;
//        for (CDR i : list) {
//            if (firstSub.getNumber().equals(i.getInitiatorNumber())
//                    || firstSub.getNumber().equals(i.getReceiverNumber())
//                    || secondSub.getNumber().equals(i.getInitiatorNumber())
//                    || secondSub.getNumber().equals(i.getReceiverNumber())) {
//                if ( (firstDate <= i.getEndDate().getEpochSecond()
//                        && firstDate >= i.getStartDate().getEpochSecond())
//                ||(secondDate <= i.getEndDate().getEpochSecond()
//                        && secondDate >= i.getStartDate().getEpochSecond()) ){
//                    return true;
//                }
//            }
//        }
//        return false;
    }
}
