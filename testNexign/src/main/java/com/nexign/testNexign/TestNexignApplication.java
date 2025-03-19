package com.nexign.testNexign;

import com.nexign.testNexign.model.Subscriber;
import com.nexign.testNexign.repository.CDRRepository;
import com.nexign.testNexign.repository.SubscriberRepository;
import com.nexign.testNexign.util.CDRGenerator;
import com.nexign.testNexign.util.SubscriberGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestNexignApplication {
	private static SubscriberRepository subscriberRepository;
	private static CDRRepository cdrRepository;
	public static void main(String[] args) {
		SpringApplication.run(TestNexignApplication.class, args);
		SubscriberGenerator subscriberGenerator = new SubscriberGenerator();
		CDRGenerator cdrGenerator = new CDRGenerator();
		subscriberGenerator.generate(10L);
		cdrGenerator.generate(1000L);
		System.out.println(subscriberRepository.findAll().toString());
		System.out.println(cdrRepository.findAll().toString());
	}

}
