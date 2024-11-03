package ticketingsystem.service;

import jakarta.annotation.PreDestroy;
import ticketingsystem.ticket.TicketPool;
import ticketingsystem.ticket.Vendor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class VendorService {

    private final TicketPool ticketPool;
    private final ScheduledExecutorService executorService;

    @Autowired
    public VendorService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
        this.executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    }

    // Method to start vendor ticket release process
    public void startVendorReleases(int vendorCount, int ticketReleaseRate, int releaseInterval) {
        for (int i = 0; i < vendorCount; i++) {
            Vendor vendor = new Vendor(ticketPool, ticketReleaseRate, releaseInterval);
            executorService.scheduleAtFixedRate(vendor, 0, releaseInterval, TimeUnit.MILLISECONDS);
        }

        System.out.println("Vendor ticket release process started for " + vendorCount + " vendors.");
    }

    // Shut down the executor service when the application context is closing
    @PreDestroy
    public void shutdown() {
        System.out.println("Shutting down vendor executor service.");
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
