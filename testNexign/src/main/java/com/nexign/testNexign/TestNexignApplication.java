package com.nexign.testNexign;

import com.nexign.testNexign.model.Subscriber;
import com.nexign.testNexign.model.CDR;
import com.nexign.testNexign.repository.CDRRepository;
import com.nexign.testNexign.repository.SubscriberRepository;
import com.nexign.testNexign.util.CDRGenerator;
import com.nexign.testNexign.util.SubscriberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.Instant;

@SpringBootApplication
public class TestNexignApplication implements CommandLineRunner {
	@Autowired
	private SubscriberRepository subscriberRepository;
	@Autowired
	private CDRRepository cdrRepository;
	@Autowired
	private SubscriberGenerator subscriberGenerator;
	@Autowired
	private CDRGenerator cdrGenerator;
	public static void main(String[] args) {
		SpringApplication.run(TestNexignApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		subscriberGenerator.generate(10L);
		cdrGenerator.generate(1000L);
		subscriberRepository.findAll().stream().map(Subscriber::getNumber).forEach(System.out::println);
		cdrRepository.findByReceiverNumberAndTimeInterval(subscriberRepository.findAll().get(0).getNumber(),Instant.parse("2025-02-10T12:00:00.00Z"),Instant.parse("2025-10-10T12:00:00.00Z")).forEach(System.out::println);
	}
}
