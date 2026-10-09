import java.util.*;

abstract class Vehicle {
    String name;
    double rate;
    boolean available = true;

    Vehicle(String name, double rate) {
        this.name = name;
        this.rate = rate;
    }

    abstract double calculateCharge(int days);
}

class Sedan extends Vehicle {
    Sedan(String name) {
        super(name, 50);
    }

    double calculateCharge(int days) {
        return rate * days;
    }
}

class SUV extends Vehicle {
    SUV(String name) {
        super(name, 80);
    }

    double calculateCharge(int days) {
        return rate * days;
    }
}

class Truck extends Vehicle {
    Truck(String name) {
        super(name, 100);
    }

    double calculateCharge(int days) {
        return rate * days;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Rental {
    Vehicle vehicle;
    Customer customer;
    int days;

    Rental(Vehicle vehicle, Customer customer, int days) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.days = days;
    }

    void rent() {
        if (vehicle.available) {
            vehicle.available = false;
            System.out.println(vehicle.name + " rented successfully by " + customer.name);
            System.out.println("Rental charge: $" + vehicle.calculateCharge(days));
        } else {
            System.out.println(vehicle.name + " is currently unavailable");
        }
    }

    void returnVehicle() {
        vehicle.available = true;
        System.out.println(vehicle.name + " returned by " + customer.name);
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle sedan = new Sedan("Sedan A");
        Vehicle suv = new SUV("SUV B");

        Customer c1 = new Customer("Customer 1");
        Customer c2 = new Customer("Customer 2");
        Customer c3 = new Customer("Customer 3");

        Rental r1 = new Rental(sedan, c1, 3);
        r1.rent();

        new Rental(sedan, c2, 2).rent();

        r1.returnVehicle();

        new Rental(suv, c3, 5).rent();
    }
}
