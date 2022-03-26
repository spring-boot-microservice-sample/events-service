package com.example.br.events.event.enums;

import com.example.br.events.event.Event;

public enum EventType {
    VIRTUAL("virtual"),
    OFFLINE("offline");

    final String value;

    EventType(String value) {
        this.value = value;
    }
}
