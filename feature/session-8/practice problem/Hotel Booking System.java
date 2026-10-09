import java.time.*;
import java.util.*;

abstract class Room {
    int number;
    double rate;

    Room(int number, double rate) {
        this.number = number;
        this.rate = rate;
    }

    abstract double calculatePrice(long days);
}

class StandardRoom extends Room {
    StandardRoom(int number) {
        super(number, 100);
    }

    double calculatePrice(long days) {
        return rate * days;
    }
}

class DeluxeRoom extends Room {
    DeluxeRoom(int number) {
        super(number, 200);
    }

    double calculatePrice(long days) {
        return rate * days;
    }
}

class Suite extends Room {
    Suite(int number) {
        super(number, 300);
    }

    double calculatePrice(long days) {
        return rate * days;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Reservation {
    Room room;
    Customer customer;
    LocalDate start;
    LocalDate end;
    LocalDate deadline;
    boolean active = true;

    Reservation(Room room, Customer customer, LocalDate start,
                LocalDate end, LocalDate deadline) {
        this.room = room;
        this.customer = customer;
        this.start = start;
        this.end = end;
        this.deadline = deadline;
    }

    boolean overlaps(LocalDate s, LocalDate e) {
        return active && s.isBefore(end) && e.isAfter(start);
    }

    void cancel() {
        if (active && LocalDate.now().isBefore(deadline)) {
            active = false;
            System.out.println("Reservation for " + customer.name + ", Room "
                    + room.number + " cancelled successfully.");
        } else {
            System.out.println("Cancellation deadline passed or reservation inactive.");
        }
    }
}

class Hotel {
    List<Reservation> reservations = new ArrayList<>();

    boolean isAvailable(Room room, LocalDate start, LocalDate end) {
        for (Reservation r : reservations) {
            if (r.room == room && r.overlaps(start, end)) {
                return false;
            }
        }
        return true;
    }

    void book(Room room, Customer customer, LocalDate start,
              LocalDate end, LocalDate deadline) {
        if (!isAvailable(room, start, end)) {
            System.out.println("Room " + room.number + " is not available.");
            return;
        }

        Reservation r = new Reservation(room, customer, start, end, deadline);
        reservations.add(r);

        long days = java.time.temporal.ChronoUnit.DAYS.between(start, end);

        System.out.println("Reservation confirmed for " + customer.name
                + ", Room " + room.number + ".");
        System.out.println("Price: $" + room.calculatePrice(days));
    }
}

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();

        Room standard = new StandardRoom(101);
        Room deluxe = new DeluxeRoom(201);

        Customer a = new Customer("Customer A");
        Customer b = new Customer("Customer B");
        Customer c = new Customer("Customer C");

        LocalDate start1 = LocalDate.of(2027, 1, 1);
        LocalDate end1 = LocalDate.of(2027, 1, 5);
        LocalDate deadline = LocalDate.of(2026, 12, 25);

        System.out.println("Standard Room 101 is available: "
                + hotel.isAvailable(standard, start1, end1));

        hotel.book(standard, a, start1, end1, deadline);
        hotel.book(standard, b, LocalDate.of(2027, 1, 3),
                LocalDate.of(2027, 1, 7), deadline);

        hotel.reservations.get(0).cancel();

        hotel.book(deluxe, c, LocalDate.of(2027, 2, 10),
                LocalDate.of(2027, 2, 12), LocalDate.of(2027, 2, 1));
    }
}
