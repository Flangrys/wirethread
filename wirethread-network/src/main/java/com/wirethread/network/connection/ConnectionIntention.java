package com.wirethread.network.connection;

public enum ConnectionIntention {
    STATUS,
    LOGIN,
    TRANSFER;

    public ConnectionIntention getFromId(int id) {
        return switch (id) {
            case 1 -> STATUS;
            case 2 -> LOGIN;
            case 3 -> TRANSFER;
            default -> throw new IllegalArgumentException("Invalid intent. Possibles options are [1-3]");
        };
    }

    public int id() {
        return this.ordinal() + 1;
    }
}