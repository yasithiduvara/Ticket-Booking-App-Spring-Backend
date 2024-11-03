package ticketingsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ticketingsystem.config.Configuration;
import ticketingsystem.service.CustomerService;
import ticketingsystem.service.TicketPoolService;
import ticketingsystem.service.VendorService;

@SpringBootApplication
public class TicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(
			CustomerService customerService,
			VendorService vendorService,
			TicketPoolService ticketPoolService,
			Configuration configuration) {
		return args -> {
			// Start the vendor release process
			vendorService.startVendorReleases(
					configuration.getVendorCount(),
					configuration.getTicketReleaseRate(),
					configuration.getCustomerRetrievalRate());

			// Start the customer purchase process
			customerService.startCustomerPurchases(
					configuration.getCustomerCount(),
					configuration.getCustomerRetrievalRate());
		};
	}
}
