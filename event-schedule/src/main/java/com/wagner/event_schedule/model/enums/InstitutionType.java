package com.wagner.event_schedule.model.enums;

public enum InstitutionType {
    CONFEDERATION("Confederação"),
    SINGULAR("Singular"),
    CENTRAL("Central"),
    COOPERATIVE("Cooperativa");

    private final String description;

    InstitutionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
