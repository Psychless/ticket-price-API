package com.psychless.ticketprice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TicketPriceServiceTest {
    @Autowired
    TicketPriceService tps;
    RouteInfo routeInfo;

    @BeforeEach
    void setUp() {
        routeInfo = new RouteInfo();
        routeInfo.setDestination("Vilnius");
        routeInfo.setPassengers(new ArrayList<Passenger>());
    }

    // All tests expect base price = 10EUR and VAT = 21%

    @Test
    void testGetTicketDraftPrice_adultTicketPrice() {
        Passenger p = new Passenger();
        p.setAge(Passenger.PassengerAge.ADULT);
        p.setBagCount(0);
        routeInfo.passengers.add(p);

        assertEquals(new BigDecimal("12.10"), tps.getTicketDraftPrice(routeInfo));
    }

    @Test
    void testGetTicketDraftPrice_childrenTicketPrice() {
        Passenger p = new Passenger();
        p.setAge(Passenger.PassengerAge.CHILD);
        p.setBagCount(0);
        routeInfo.passengers.add(p);

        assertEquals(new BigDecimal("6.05"), tps.getTicketDraftPrice(routeInfo));
    }

    @Test
    void testGetTicketDraftPrice_1Adult1Bag() {
        Passenger p = new Passenger();
        p.setAge(Passenger.PassengerAge.ADULT);
        p.setBagCount(1);
        routeInfo.passengers.add(p);

        assertEquals(new BigDecimal("15.73"), tps.getTicketDraftPrice(routeInfo));
    }

    @Test
    void testGetTicketDraftPrice_passengerBagVariety1() {
        Passenger p1 = new Passenger();
        p1.setAge(Passenger.PassengerAge.ADULT);
        p1.setBagCount(2);
        routeInfo.passengers.add(p1);
        
        Passenger p2 = new Passenger();
        p2.setAge(Passenger.PassengerAge.CHILD);
        p2.setBagCount(1);
        routeInfo.passengers.add(p2);

        assertEquals(new BigDecimal("29.04"), tps.getTicketDraftPrice(routeInfo));
    }

    @Test
    void testGetTicketDraftPrice_passengerBagVariety2() {
        Passenger p1 = new Passenger();
        p1.setAge(Passenger.PassengerAge.ADULT);
        p1.setBagCount(1);
        routeInfo.passengers.add(p1);

        Passenger p2 = new Passenger();
        p2.setAge(Passenger.PassengerAge.CHILD);
        p2.setBagCount(1);
        routeInfo.passengers.add(p2);

        Passenger p3 = new Passenger();
        p3.setAge(Passenger.PassengerAge.ADULT);
        p3.setBagCount(1);
        routeInfo.passengers.add(p3);

        assertEquals(new BigDecimal("41.14"), tps.getTicketDraftPrice(routeInfo));
    }

    @Test
    void testGetTicketDraftPrice_passengerBagVariety3() {
        Passenger p1 = new Passenger();
        p1.setAge(Passenger.PassengerAge.ADULT);
        p1.setBagCount(1);
        routeInfo.passengers.add(p1);

        Passenger p2 = new Passenger();
        p2.setAge(Passenger.PassengerAge.CHILD);
        p2.setBagCount(0);
        routeInfo.passengers.add(p2);

        assertEquals(new BigDecimal("21.78"), tps.getTicketDraftPrice(routeInfo));
    }

    @Test
    void testGetTicketDraftPrice_passengerBagVariety4() {
        Passenger p1 = new Passenger();
        p1.setAge(Passenger.PassengerAge.ADULT);
        p1.setBagCount(2);
        routeInfo.passengers.add(p1);

        Passenger p2 = new Passenger();
        p2.setAge(Passenger.PassengerAge.ADULT);
        p2.setBagCount(2);
        routeInfo.passengers.add(p2);

        assertEquals(new BigDecimal("38.72"), tps.getTicketDraftPrice(routeInfo));
    }

    @Test
    void testGetTicketDraftPrice_passengerBagVariety5() {
        Passenger p1 = new Passenger();
        p1.setAge(Passenger.PassengerAge.ADULT);
        p1.setBagCount(1);
        routeInfo.passengers.add(p1);

        Passenger p2 = new Passenger();
        p2.setAge(Passenger.PassengerAge.CHILD);
        p2.setBagCount(1);
        routeInfo.passengers.add(p2);

        Passenger p3 = new Passenger();
        p3.setAge(Passenger.PassengerAge.CHILD);
        p3.setBagCount(2);
        routeInfo.passengers.add(p3);

        assertEquals(new BigDecimal("38.72"), tps.getTicketDraftPrice(routeInfo));
    }
}