package com.gojek.services;

import com.gojek.exceptions.TicketNotIssuedException;
import com.gojek.models.Ticket;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TicketIssuingServiceSpecs {

    @Test(expected = TicketNotIssuedException.class)
    public void issueTicketMethodShouldThrowTicketNotIssuedException() {
        TicketIssuingService ticketIssuingService = new TicketIssuingService(0);
        Ticket ticket = ticketIssuingService.issueTicket();
    }

    @Test
    public void shouldNotThrowException() {
        TicketIssuingService ticketIssuingService = new TicketIssuingService(5);
        Ticket ticket = ticketIssuingService.issueTicket();
        Integer slot = ticket.getSlot();
        assertTrue("Should assign a the lowest slot available", slot == 1);
        Ticket newTicket = ticketIssuingService.issueTicket();
        Integer newSlot = newTicket.getSlot();
        assertTrue("Should assign the new lowest slot availble", newSlot == 2);
    }
}
