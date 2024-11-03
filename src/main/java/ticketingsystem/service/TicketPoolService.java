package ticketingsystem.service;

import ticketingsystem.ticket.Ticket;
import ticketingsystem.ticket.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketPoolService {

    private final TicketPool ticketPool;

    @Autowired
    public TicketPoolService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    // Method to add tickets to the pool
    public void addTickets(List<Ticket> newTickets) {
        ticketPool.addTickets(newTickets);
    }

    // Method to remove a ticket from the pool
    public ticketingsystem.ticket.Ticket removeTicket() {
        return ticketPool.removeTicket();
    }

    // Method to get the current number of available tickets
    public int getAvailableTicketsCount() {
        return ticketPool.getAvailableTicketsCount();
    }
}
