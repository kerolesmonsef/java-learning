package com.foo.learning.enums;

public enum Status {
    ACTIVE(1),
    DELETED(2),
    PENDING(3),
    INACTIVE(4);
    private int s;

    Status(int s) {
        this.s = s;
    }

    public int getValue(){
        return s;
    }
}
