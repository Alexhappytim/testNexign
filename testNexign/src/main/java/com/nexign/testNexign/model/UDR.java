package com.nexign.testNexign.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Duration;
@Getter
@Setter
@AllArgsConstructor
public class UDR {
    private String msisdn;
    private CallTime incomingCall;
    private CallTime outcomingCall;

    @Getter
    public static class CallTime {
        private String totalTime;
        public CallTime(Duration duration) {
            this.totalTime = String.format("%02d:%02d:%02d", duration.toHours(), duration.toMinutesPart(), duration.toSecondsPart());
        }
    }

}
