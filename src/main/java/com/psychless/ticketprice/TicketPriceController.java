package com.psychless.ticketprice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TicketPriceController {

    @GetMapping("/getTicketPrice")
    public float getTicketPrice(@RequestBody List<Passenger> passengers) {
        float ticketPrice = 0;

        return ticketPrice;
    }
}
