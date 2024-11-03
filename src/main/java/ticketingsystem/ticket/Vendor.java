package ticketingsystem.ticket;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Vendor implements Runnable {
    private static final AtomicInteger vendorIdCounter = new AtomicInteger(1);
    private final int vendorId;
    private final TicketPool ticketPool;
    private final int ticketReleaseRate; // Number of tickets to add per release
    private final int releaseInterval; // Interval between ticket releases in milliseconds

    // Vendor constructor with all necessary parameters
    public Vendor(TicketPool ticketPool, int ticketReleaseRate, int releaseInterval) {
        this.vendorId = vendorIdCounter.getAndIncrement();
        this.ticketPool = ticketPool;
        this.ticketReleaseRate = ticketReleaseRate;
        this.releaseInterval = releaseInterval; // Use the constructor parameter
    }

    // Method to simulate ticket creation
    private List<Ticket> generateTickets() {
        // Generate a list of tickets; for simplicity, we'll create a single ticket per release
        return List.of(new Ticket(vendorId, "Event #" + vendorId, 50.0)); // Example ticket generation
    }

    // Run method to release tickets at specified intervals
    @Override
    public void run() {
        while (true) {
            try {
                List<Ticket> newTickets = generateTickets();
                ticketPool.addTickets(newTickets);
                Logger.logInfo("Vendor #" + vendorId + " released " + newTickets.size() + " tickets.");
                Thread.sleep(releaseInterval);
            } catch (InterruptedException e) {
                Logger.logError("Vendor #" + vendorId + " interrupted.", e);
                Thread.currentThread().interrupt(); // Restore interrupted status
                break; // Exit the loop if interrupted
            }
        }
    }
}
