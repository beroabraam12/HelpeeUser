package com.helpee.helpee.Class;

public class Order {
    private boolean isPaid;

    public Order(boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Order() {
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
