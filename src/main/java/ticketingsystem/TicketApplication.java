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
			System.out.println("Configuration loaded: " + configuration);

			// Only start services if configuration values are valid
			if (configuration.getVendorCount() > 0 && configuration.getTicketReleaseRate() > 0) {
				vendorService.startVendorReleases(
						configuration.getVendorCount(),
						configuration.getTicketReleaseRate(),
						1000); // example interval
			}

			if (configuration.getCustomerCount() > 0 && configuration.getCustomerRetrievalRate() > 0) {
				customerService.startCustomerPurchases(
						configuration.getCustomerCount(),
						2000); // example interval
			}
		};
	}


}
