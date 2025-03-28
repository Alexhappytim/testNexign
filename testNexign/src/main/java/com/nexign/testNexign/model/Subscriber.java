package com.nexign.testNexign.model;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.Objects;

/**
 * Entity класс абонентов для базы данных
 */
@Entity
@Table(name = "subscribers")
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "number")
    private String number;

    public Subscriber(){}

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subscriber that = (Subscriber) o;
        return Objects.equals(id, that.id);
    }

    public String getNumber() {
        return number;
    }
}
