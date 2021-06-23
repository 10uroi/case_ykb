package com.ykb.vacation.enums;

public enum ApprovalType {
    DENIED(0), ACCEPT(1), WAITING(2);

    private final int value;

    private ApprovalType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
