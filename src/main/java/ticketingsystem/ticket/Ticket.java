package ticketingsystem.ticket;

public class Ticket {
    private final int id;
    private final String eventName;
    private final double price;

    // Constructor
    public Ticket(int id, String eventName, double price) {
        this.id = id;
        this.eventName = eventName;
        this.price = price;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public double getPrice() {
        return price;
    }

    // toString Method to display ticket information
    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", eventName='" + eventName + '\'' +
                ", price=" + price +
                '}';
    }
}
