package com.nexign.testNexign.repository;

import com.nexign.testNexign.model.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface SubscriberRepository extends JpaRepository<Subscriber, Long> {
    Subscriber findByNumber(String number);
}
