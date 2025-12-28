package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import com.example.demo.entity.AssetLifecycleEvent;

public class AssetLifecycleEventRequest {

    @NotNull
    private EventType eventType;

    @NotNull
    private LocalDate eventDate;

    private String eventDescription;

    // getters and setters
    public EventType getEventType() {
        return eventType;
    }

    public void setEventType(EventType eventType) {
        this.eventType = eventType;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
}
