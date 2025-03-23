package com.nexign.testNexign.util;

import com.nexign.testNexign.model.CDR;
import com.nexign.testNexign.model.CallType;
import com.nexign.testNexign.model.Subscriber;
import com.nexign.testNexign.repository.CDRRepository;
import com.nexign.testNexign.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.Year;
import java.time.ZoneId;
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
            cdr.setInitiator(firstSub);
            cdr.setReceiver(secondSub);


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
            long firstDate = ThreadLocalRandom.current().nextLong(startEpoch, endEpoch);
            long secondDate = ThreadLocalRandom.current().nextLong(firstDate, endEpoch + 1);


            cdr.setStartDate(Instant.ofEpochSecond(firstDate));
            cdr.setEndDate(Instant.ofEpochSecond(secondDate));
            generatedCDRs.add(cdr);
            cdrRepository.save(cdr);
        }
    }
}
