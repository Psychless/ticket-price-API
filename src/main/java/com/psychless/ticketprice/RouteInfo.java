package com.psychless.ticketprice;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class RouteInfo {
    @NotEmpty(message = "Destination cannot be empty")
    String destination;
    List<Passenger> passengers;

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Passenger> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<Passenger> passengers) {
        this.passengers = passengers;
    }
}
