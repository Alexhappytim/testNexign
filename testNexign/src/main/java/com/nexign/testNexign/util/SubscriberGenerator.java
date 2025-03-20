package com.nexign.testNexign.util;

import com.nexign.testNexign.model.Subscriber;
import com.nexign.testNexign.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class SubscriberGenerator {
    private static final int PHONE_NUMBER_LENGTH = 10;
    private final SubscriberRepository subscriberRepository;

    public void generate(Long amountToGenerate){
        Set<String> generatedNumbers = new HashSet<>();
        for(long i=0;i<amountToGenerate;i++){
            Subscriber sub = new Subscriber();
            String phoneNumber;
            do {
                StringBuilder sb = new StringBuilder("7");
                for (int j = 1; j < PHONE_NUMBER_LENGTH; j++) {
                    int digit = ThreadLocalRandom.current().nextInt(10);
                    sb.append(digit);
                }
                phoneNumber = sb.toString();
            } while (!generatedNumbers.add(phoneNumber));
            sub.setNumber(phoneNumber);
            subscriberRepository.save(sub);
        }
    }
}
