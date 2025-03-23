package com.nexign.testNexign.model;

import lombok.Getter;

import java.time.Duration;


/**
 * Класс отчета UDR
 */
public class UDR {
    private String msisdn;
    private CallTime incomingCall;
    private CallTime outcomingCall;

    public UDR(String msisdn, CallTime incomingCall, CallTime outcomingCall) {
        this.msisdn = msisdn;
        this.incomingCall = incomingCall;
        this.outcomingCall = outcomingCall;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public CallTime getIncomingCall() {
        return incomingCall;
    }

    public void setIncomingCall(CallTime incomingCall) {
        this.incomingCall = incomingCall;
    }

    public CallTime getOutcomingCall() {
        return outcomingCall;
    }

    public void setOutcomingCall(CallTime outcomingCall) {
        this.outcomingCall = outcomingCall;
    }

    /**
     * Внутренний класс-оболочка для простого преобразования в json
     */

    public static class CallTime {
        private String totalTime;
        public CallTime(Duration duration) {
            this.totalTime = String.format("%02d:%02d:%02d", duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());
        }

        public String getTotalTime() {
            return totalTime;
        }
    }

}
