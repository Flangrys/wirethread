package com.wirethread.network.packet;

public enum PacketBound {
    Clientbound(0),
    Serverbound(1);

    private final int bound;

    PacketBound(int bound) {
        this.bound = bound;
    }

    public int getBound() {
        return this.bound;
    }

    public PacketBound getOpposite() {
        return switch (this) {
            case Clientbound -> Serverbound;
            case Serverbound -> Clientbound;
        };
    }

    @Override
    public String toString() {
        return switch (this) {
            case Serverbound -> "serverbound";
            case Clientbound -> "clientbound";
        };
    }
}
