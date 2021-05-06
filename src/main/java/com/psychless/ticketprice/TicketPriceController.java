package com.psychless.ticketprice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class TicketPriceController {

    @Autowired
    TicketPriceService ticketPriceService;

    @GetMapping("/getTicketPrice")
    public BigDecimal getTicketPrice(@RequestBody RouteInfo routeInfo) {
        return ticketPriceService.getTicketDraftPrice(routeInfo);
    }
}
