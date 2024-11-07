package ticketingsystem.service;

import ticketingsystem.ticket.Ticket;
import ticketingsystem.ticket.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TicketPoolService {

    private final TicketPool ticketPool;

    @Autowired
    public TicketPoolService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    // Synchronized method to add tickets to the pool
    public synchronized void addTickets(List<Ticket> newTickets) {
        synchronized (ticketPool) {
            ticketPool.addTickets(Collections.synchronizedList(newTickets));
        }
    }

    // Synchronized method to remove a ticket from the pool
    public synchronized Ticket removeTicket() {
        synchronized (ticketPool) {
            return ticketPool.removeTicket();
        }
    }

    // Synchronized method to get the current number of available tickets
    public synchronized int getAvailableTicketsCount() {
        synchronized (ticketPool) {
            return ticketPool.getAvailableTicketsCount();
        }
    }
}
