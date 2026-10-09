import java.util.*;

interface PaymentMethod {
    boolean processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }
}

class PayPalPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return false;
    }
}

class BankTransferPayment implements PaymentMethod {
    public boolean processPayment(double amount) {
        return true;
    }
}

class Product {
    String name;
    double price;
    int quantity;

    Product(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    double getTotal() {
        return price * quantity;
    }
}

class Customer {
    String name;

    Customer(String name) {
        this.name = name;
    }
}

class Order {
    String id;
    Customer customer;
    List<Product> products = new ArrayList<>();
    String status = "Pending";

    Order(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
        System.out.println("Order created for " + customer.name + ".");
    }

    void addProduct(Product product) {
        products.add(product);
    }

    double getTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getTotal();
        }
        return total;
    }

    void pay(PaymentMethod method, String paymentName) {
        if (products.isEmpty()) {
            System.out.println("Cannot process payment for an empty order.");
            return;
        }

        System.out.println("Payment initiated via " + paymentName
                + " for Order " + id + ".");

        if (method.processPayment(getTotal())) {
            status = "Paid";
            System.out.println("Payment for Order " + id + " successful.");
        } else {
            System.out.println("Payment for Order " + id + " failed.");
        }

        System.out.println("Order status: " + status);
    }
}

public class Main {
    public static void main(String[] args) {
        Customer x = new Customer("Customer X");
        Order orderX = new Order("X", x);
        orderX.addProduct(new Product("Product A", 100, 2));
        orderX.addProduct(new Product("Product B", 50, 1));
        orderX.pay(new CreditCardPayment(), "Credit Card");

        Customer y = new Customer("Customer Y");
        Order orderY = new Order("Y", y);
        orderY.pay(new CreditCardPayment(), "Credit Card");

        Customer z = new Customer("Customer Z");
        Order orderZ = new Order("Z", z);
        orderZ.addProduct(new Product("Product C", 200, 1));
        orderZ.pay(new PayPalPayment(), "PayPal");
    }
}
