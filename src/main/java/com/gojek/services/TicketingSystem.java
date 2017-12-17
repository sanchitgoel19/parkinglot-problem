package com.gojek.services;

import com.gojek.exceptions.TicketNotIssuedException;
import com.gojek.models.Ticket;

import java.util.TreeSet;

public class TicketingSystem {

    private final TreeSet<Integer> slots;

    public TicketingSystem(int slots) {
        this.slots = new TreeSet<>();
        for (int i = 1; i <= slots; i++) {
            this.slots.add(i);
        }
    }

    public Ticket issueTicket() {
        if (slots.isEmpty()) {
            throw new TicketNotIssuedException();
        } else {
            Ticket ticket = new Ticket(slots.first());
            slots.remove(slots.first());
            return ticket;
        }
    }

    public void markSlotFree(Ticket ticket) {
        Integer slot = ticket.getSlot();
        slots.add(slot);
    }
}
