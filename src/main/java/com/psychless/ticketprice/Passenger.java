package com.psychless.ticketprice;

public class Passenger {
    private PassengerAge age;

    private int bagCount;

    public enum PassengerAge {
        ADULT,
        CHILD
    }

    public PassengerAge getAge() {
        return age;
    }

    public void setAge(PassengerAge age) {
        this.age = age;
    }

    public int getBagCount() {
        return bagCount;
    }

    public void setBagCount(int bagCount) {
        this.bagCount = bagCount;
    }
}
