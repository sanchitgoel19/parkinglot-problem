package com.gojek.services;

import com.gojek.exceptions.TicketNotIssuedException;
import com.gojek.exceptions.VehicleNotFoundException;
import com.gojek.models.Ticket;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TicketingSystemSpecs {

    @Test(expected = TicketNotIssuedException.class)
    public void issueTicketMethodShouldThrowTicketNotIssuedException() {
        TicketingSystem ticketingSystem = new TicketingSystem(0);
        Ticket ticket = ticketingSystem.issueTicket();
    }

    @Test
    public void shouldNotThrowException() {
        TicketingSystem ticketingSystem = new TicketingSystem(5);
        Ticket ticket = ticketingSystem.issueTicket();
        Integer slot = ticket.getSlot();
        assertTrue("Should assign a the lowest slot available", slot == 1);
        Ticket newTicket = ticketingSystem.issueTicket();
        Integer newSlot = newTicket.getSlot();
        assertTrue("Should assign the new lowest slot availble", newSlot == 2);
    }

    @Test
    public void shouldFreeTheSlot() {
        TicketingSystem ticketingSystem = new TicketingSystem(5);
        Ticket ticket = ticketingSystem.issueTicket();
        ticketingSystem.markSlotFree(ticket);
    }
}
