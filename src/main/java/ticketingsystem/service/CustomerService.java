package ticketingsystem.service;

import jakarta.annotation.PreDestroy;
import ticketingsystem.ticket.Customer;
import ticketingsystem.ticket.TicketPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
public class CustomerService {

    private final TicketPool ticketPool;
    private final ScheduledExecutorService executorService;

    @Autowired
    public CustomerService(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
        this.executorService = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    }

    // Method to start customer purchase process
    public void startCustomerPurchases(int customerCount, int purchaseInterval) {
        for (int i = 0; i < customerCount; i++) {
            Customer customer = new Customer(ticketPool, purchaseInterval);
            executorService.submit(customer);
        }

        System.out.println("Customer purchase process started for " + customerCount + " customers.");
    }

    // Shut down the executor service when the application context is closing
    @PreDestroy
    public void shutdown() {
        System.out.println("Shutting down customer executor service.");
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
