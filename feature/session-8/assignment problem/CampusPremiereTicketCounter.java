import java.util.*;

interface Seat {
    double getPrice();
    String getId();
}

class RegularSeat implements Seat {
    String id;

    RegularSeat(String id) {
        this.id = id;
    }

    public double getPrice() { return 150; }
    public String getId() { return id; }
}

class PremiumSeat implements Seat {
    String id;

    PremiumSeat(String id) {
        this.id = id;
    }

    public double getPrice() { return 250; }
    public String getId() { return id; }
}

class ReclinerSeat implements Seat {
    String id;

    ReclinerSeat(String id) {
        this.id = id;
    }

    public double getPrice() { return 400; }
    public String getId() { return id; }
}

class Show {
    String name;
    Set<String> booked = new HashSet<>();
    boolean started = false;

    Show(String name) {
        this.name = name;
    }
}

class Booking {
    String customer;
    Show show;
    List<Seat> seats;
    boolean cancelled = false;

    Booking(String customer, Show show, List<Seat> seats) {
        this.customer = customer;
        this.show = show;
        this.seats = seats;
    }

    double getTotal() {
        double total = 0;
        for (Seat s : seats)
            total += s.getPrice();
        return total;
    }

    void cancel() {
        if (cancelled) {
            System.out.println("Booking already cancelled.");
        } else if (show.started) {
            System.out.println("Cannot cancel after the show starts.");
        } else {
            for (Seat s : seats)
                show.booked.remove(s.getId());

            cancelled = true;
            System.out.println(customer + "'s booking cancelled. Seats " +
                    String.join(", ", seats.stream().map(Seat::getId).toList()) +
                    " released.");
        }
    }
}

public class CampusPremiereTicketCounter {
    static Booking book(String customer, Show show, Seat... seats) {
        if (seats.length == 0 || seats.length > 6) {
            System.out.println("Booking must contain 1 to 6 seats.");
            return null;
        }

        Set<String> requested = new HashSet<>();
        for (Seat s : seats) {
            if (show.booked.contains(s.getId()) ||
                    !requested.add(s.getId())) {
                System.out.println("Seat " + s.getId() +
                        " is already booked for this show.");
                return null;
            }
        }

        show.booked.addAll(requested);
        Booking b = new Booking(customer, show, Arrays.asList(seats));

        System.out.println("Booking confirmed for " + customer + ": " +
                String.join(", ", requested) + ".");
        System.out.printf("Total: ₹%.2f.%n", b.getTotal());
        return b;
    }

    public static void main(String[] args) {
        Show show = new Show("7 PM");

        Booking a = book("Asha", show,
                new RegularSeat("A1"),
                new RegularSeat("A2"),
                new PremiumSeat("F5"));

        book("Ravi", show, new RegularSeat("A2"));
        book("Ravi", show, new ReclinerSeat("R1"));

        if (a != null)
            a.cancel();

        book("Neha", show, new RegularSeat("A2"));
    }
}
