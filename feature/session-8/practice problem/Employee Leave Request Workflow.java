abstract class Employee {
    String name;

    Employee(String name) {
        this.name = name;
    }

    abstract boolean canTakeLeave(int days);
}

class FullTimeEmployee extends Employee {
    FullTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 20;
    }
}

class PartTimeEmployee extends Employee {
    PartTimeEmployee(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 10;
    }
}

class Contractor extends Employee {
    Contractor(String name) {
        super(name);
    }

    boolean canTakeLeave(int days) {
        return days <= 5;
    }
}

class LeaveRequest {
    Employee employee;
    String dates;
    int days;
    private String status = "Pending";

    LeaveRequest(Employee employee, String dates, int days) {
        this.employee = employee;
        this.dates = dates;
        this.days = days;
        System.out.println("Leave request submitted for " + employee.name + " (" + dates + "). Status: " + status);
    }

    void review(String reviewer, boolean approved) {
        if (!status.equals("Pending")) {
            System.out.println("Request already reviewed");
            return;
        }

        if (approved && employee.canTakeLeave(days)) {
            status = "Approved";
            System.out.println(employee.name + "'s leave request (" + dates + ") approved.");
        } else {
            status = "Rejected";
            System.out.println(employee.name + "'s leave request (" + dates + ") rejected.");
        }

        System.out.println("Status: " + status);
    }

    void changeStatus(String newStatus) {
        if (!status.equals("Pending")) {
            System.out.println("Cannot change leave request status from " + status + " to " + newStatus);
        } else {
            status = newStatus;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Employee john = new FullTimeEmployee("John");
        LeaveRequest r1 = new LeaveRequest(john, "Jan 1-5", 5);
        r1.review("Alice", true);
        r1.changeStatus("Pending");

        Employee jane = new PartTimeEmployee("Jane");
        LeaveRequest r2 = new LeaveRequest(jane, "Feb 10-11", 2);
        r2.review("Bob", false);
    }
}
