package com.psychless.ticketprice;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TicketPriceService {

    private final BigDecimal childDiscountMultiplier = new BigDecimal("0.5");
    private final BigDecimal baggageMultiplier = new BigDecimal("0.3");

    /**
     * Inputs RouteInfo -> Destination and list of passengers
     * Returns total price for passenger tickets and their luggage
     * Example:
     * Route "Vilnius" base price = 10 EUR
     * VAT = 21%
     * - Two passengers:
     * Adult carrying two bags
     * Child carrying one bag
     * = Result:
     * Adult (10 EUR + 21%) = 12.10 EUR
     * Two bags (2 x 10 EUR x 30% + 21%) = 7.26 EUR
     * Child (10 EUR x 50% + 21%) = 6.05 EUR
     * One bag (10 EUR x 30% + 21%) = 3.63 EUR
     * - Total price = 29.04 EUR
     */
    public BigDecimal getTicketDraftPrice(RouteInfo routeInfo) {
        final BigDecimal VAT = getCountryVAT(routeInfo.getDestination());
        final BigDecimal basePrice = getRoutePrice(routeInfo.getDestination());
        final BigDecimal baggagePrice = basePrice.multiply(baggageMultiplier);
        BigDecimal totalPrice = new BigDecimal(0);

        for (Passenger p : routeInfo.passengers) {
            BigDecimal pTicketPrice; // Passenger's ticket price
            BigDecimal pBaggagePrice; // Passenger's total baggage price

            pTicketPrice = basePrice.add(basePrice.multiply(VAT)); // Add VAT to ticket price
            if (p.getAge().equals(Passenger.PassengerAge.CHILD)) // If passenger is CHILD, add child discount
                pTicketPrice = pTicketPrice.multiply(childDiscountMultiplier);

            pBaggagePrice = baggagePrice.multiply(BigDecimal.valueOf(p.getBagCount())); // Calculate passenger's baggage price
            pBaggagePrice = pBaggagePrice.add(pBaggagePrice.multiply(VAT)); // Add VAT

            totalPrice = totalPrice.add(pTicketPrice);
            totalPrice = totalPrice.add(pBaggagePrice);
        }

        totalPrice = totalPrice.setScale(2, BigDecimal.ROUND_DOWN);
        return totalPrice;
    }

    private BigDecimal getCountryVAT(String destination) {
        // TODO: Add actual API call to retrieve current destination country's VAT
        final BigDecimal VAT = new BigDecimal("0.21");

        return VAT;
    }

    private BigDecimal getRoutePrice(String destination) {
        // TODO: Add actual API call to retrieve route price
        final BigDecimal basePrice = new BigDecimal("10.0");

        return basePrice;
    }
}
