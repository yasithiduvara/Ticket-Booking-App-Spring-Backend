package ticketingsystem.ticket;

import ticketingsystem.service.TicketPoolService;

import java.util.concurrent.atomic.AtomicInteger;

public class Customer implements Runnable {
    private static final AtomicInteger customerIdCounter = new AtomicInteger(1);
    private final int customerId;
    private final TicketPool ticketPoolService;
    private final int purchaseInterval; // Interval between each ticket purchase attempt in milliseconds

    public Customer(TicketPool ticketPoolService, int purchaseInterval) {
        this.customerId = customerIdCounter.getAndIncrement();
        this.ticketPoolService = ticketPoolService;
        this.purchaseInterval = purchaseInterval;
    }

    // Run method to attempt ticket purchase at specified intervals
    @Override
    public void run() {
        while (true) {
            try {
                Ticket ticket = ticketPoolService.removeTicket();
                if (ticket != null) {
                    Logger.logInfo("Customer #" + customerId + " purchased " + ticket);
                } else {
                    Logger.logInfo("Customer #" + customerId + " attempted to purchase a ticket, but none were available.");
                }
                Thread.sleep(purchaseInterval);
            } catch (InterruptedException e) {
                Logger.logError("Customer #" + customerId + " interrupted.", e);
                Thread.currentThread().interrupt();
                break;
            } catch (Exception e) {
                Logger.logError("Customer #" + customerId + " encountered an error: " + e.getMessage(), e);
            }
        }
    }
}
