package ticketingsystem.ticket;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Vendor implements Runnable {
    private static final AtomicInteger vendorIdCounter = new AtomicInteger(1);
    private final int vendorId;
    private final TicketPool ticketPool;
    private final int ticketReleaseRate;

    // Vendor constructor with all necessary parameters
    public Vendor(TicketPool ticketPool, int ticketReleaseRate) {
        this.vendorId = vendorIdCounter.getAndIncrement();
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
    }

    // Method to simulate ticket creation
    private List<Ticket> generateTickets() {
        // Generate a list of tickets; for simplicity, we'll create multiple tickets based on the rate
        return List.of(new Ticket(vendorId, "Event #" + vendorId, 50.0)); // Example ticket generation
    }

    // Run method to release tickets
    @Override
    public void run() {
        List<Ticket> newTickets = generateTickets();
        ticketPool.addTickets(newTickets);
        Logger.logInfo("Vendor #" + vendorId + " released " + newTickets.size() + " tickets.");
    }
}
