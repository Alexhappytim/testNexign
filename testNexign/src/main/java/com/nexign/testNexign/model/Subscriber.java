package com.nexign.testNexign.model;

import jakarta.persistence.*;

@Entity
@Table(name = "subscribers")
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    @Column(name = "number")
    private String number;

    public Subscriber(String number){
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
