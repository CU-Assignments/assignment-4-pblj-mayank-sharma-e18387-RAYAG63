import java.util.ArrayList;
import java.util.List;

class TicketBookingSystem {
    private List<String> availableSeats;

    public TicketBookingSystem(int numberOfSeats) {
        availableSeats = new ArrayList<>();
        for (int i = 1; i <= numberOfSeats; i++) {
            availableSeats.add("Seat" + i);
        }
    }

    public synchronized boolean bookSeat(String seat, String customerType) {
        if (availableSeats.contains(seat)) {
            System.out.println(customerType + " booking " + seat);
            availableSeats.remove(seat);
            return true;
        } else {
            System.out.println(seat + " is already booked.");
            return false;
        }
    }

    public void showAvailableSeats() {
        System.out.println("Available seats: " + availableSeats);
    }
}

class BookingThread extends Thread {
    private TicketBookingSystem bookingSystem;
    private String seat;
    private String customerType;

    public BookingThread(TicketBookingSystem bookingSystem, String seat, String customerType) {
        this.bookingSystem = bookingSystem;
        this.seat = seat;
        this.customerType = customerType;
    }

    @Override
    public void run() {
        bookingSystem.bookSeat(seat, customerType);
    }
}

public class TicketBookingDemo {
    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem(10);

        // Create threads for booking
        BookingThread vip1 = new BookingThread(bookingSystem, "Seat1", "VIP");
        BookingThread vip2 = new BookingThread(bookingSystem, "Seat2", "VIP");
        BookingThread regular1 = new BookingThread(bookingSystem, "Seat1", "Regular");
        BookingThread regular2 = new BookingThread(bookingSystem, "Seat3", "Regular");

        // Set thread priorities
        vip1.setPriority(Thread.MAX_PRIORITY);
        vip2.setPriority(Thread.MAX_PRIORITY);
        regular1.setPriority(Thread.MIN_PRIORITY);
        regular2.setPriority(Thread.MIN_PRIORITY);

        // Start threads
        vip1.start();
        vip2.start();
        regular1.start();
        regular2.start();

        // Wait for threads to finish
        try {
            vip1.join();
            vip2.join();
            regular1.join();
            regular2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Show remaining available seats
        bookingSystem.showAvailableSeats();
    }
}
