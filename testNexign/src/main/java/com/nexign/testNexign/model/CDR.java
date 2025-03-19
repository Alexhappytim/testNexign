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
    @Column(name = "initiator_number")
    private String initiatorNumber;
    @Column(name = "receiver_number")
    private String receiverNumber;
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

    public String getInitiatorNumber() {
        return initiatorNumber;
    }

    public void setInitiatorNumber(String initiatorNumber) {
        this.initiatorNumber = initiatorNumber;
    }

    public String getReceiverNumber() {
        return receiverNumber;
    }

    public void setReceiverNumber(String receiverNumber) {
        this.receiverNumber = receiverNumber;
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
}
