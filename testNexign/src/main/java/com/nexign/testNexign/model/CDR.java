package com.nexign.testNexign.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "call_records")
public class CDR {
    public CDR(){}
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "call_type")
    private CallType callType;
    @ManyToOne
    @JoinColumn(name = "initiator", referencedColumnName = "id")
    private Subscriber initiator;
    @ManyToOne
    @JoinColumn(name = "receiver", referencedColumnName = "id")
    private Subscriber receiver;
    @Column(name = "start_date")
    private Instant startDate;
    @Column(name = "end_date")
    private Instant endDate;

    public CallType getCallType() {
        return callType;
    }

    public void setCallType(CallType callType) {
        this.callType = callType;
    }

    public Subscriber getInitiator() {
        return initiator;
    }

    public void setInitiator(Subscriber initiator) {
        this.initiator = initiator;
    }

    public Subscriber getReceiver() {
        return receiver;
    }

    public void setReceiver(Subscriber receiver) {
        this.receiver = receiver;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return callType.getCode() +
                ", " + initiator.getNumber() +
                ", " + receiver.getNumber() +
                ", " + startDate +
                ", " + endDate;
    }
}
