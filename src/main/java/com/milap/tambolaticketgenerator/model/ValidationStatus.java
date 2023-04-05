package com.milap.tambolaticketgenerator.model;

public enum ValidationStatus {
    SUCCESS(1),
    NUMBER_MISMATCH(2),
    INVALID_TICKET(3),
    INVALID_SESSION(4);

    private int status;

    ValidationStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
