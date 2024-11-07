package ticketingsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import ticketingsystem.config.Configuration;
import ticketingsystem.service.CustomerService;
import ticketingsystem.service.TicketPoolService;
import ticketingsystem.service.VendorService;

import java.util.Scanner;

@SpringBootApplication
public class TicketApplication {

	public static void main(String[] args) {
		SpringApplication.run(TicketApplication.class, args);
	}

	//Getting tickets form configuration

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

	// Configure Ticket form user

//	@Bean
//	CommandLineRunner commandLineRunner(
//			CustomerService customerService,
//			VendorService vendorService,
//			TicketPoolService ticketPoolService,
//			Configuration configuration) {
//		return args -> {
//			Scanner scanner = new Scanner(System.in);
//
//			// Prompt user for configuration values
//			System.out.print("Enter Total ticket : ");
//			String ticketTotal = scanner.nextLine();
//
//			System.out.print("Enter ticket release rate: ");
//			int ticketReleaseRate = scanner.nextInt();
//
//			System.out.print("Enter max ticket capacity: ");
//			int maxTicketCapacity = scanner.nextInt();
//
//			System.out.print("Enter number of vendors: ");
//			int vendorCount = scanner.nextInt();
//
//			System.out.print("Enter number of customers: ");
//			int customerCount = scanner.nextInt();
//
//			// Apply user inputs to the configuration instance
//			configuration.setTotalTickets(Integer.parseInt(ticketTotal));
//			configuration.setTicketReleaseRate(ticketReleaseRate);
//			configuration.setMaxTicketCapacity(maxTicketCapacity);
//			configuration.setVendorCount(vendorCount);
//			configuration.setCustomerCount(customerCount);
//
//			System.out.println("Configuration loaded: " + configuration);
//
//			// Initialize services based on the entered configuration
//			if (configuration.getVendorCount() > 0 && configuration.getTicketReleaseRate() > 0) {
//				vendorService.startVendorReleases(
//						configuration.getVendorCount(),
//						configuration.getTicketReleaseRate(),
//						1000); // example interval
//			}
//
//			if (configuration.getCustomerCount() > 0 && configuration.getCustomerRetrievalRate() > 0) {
//				customerService.startCustomerPurchases(
//						configuration.getCustomerCount(),
//						2000); // example interval
//			}
//		};
//	}



}
