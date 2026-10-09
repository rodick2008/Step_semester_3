class Employee {
    String employeeId;
    double salary;

    Employee(String employeeId, double salary) {
        this.employeeId = employeeId;
        this.salary = salary;
    }

    void raiseSalary(double salary) {
        this.salary += salary;
    }
}

public class PayrollBonusMain {
    public static void main(String[] args) {

        Employee[] employees = {
            new Employee("E-101", 40000),
            new Employee("E-102", 55000),
            new Employee("E-103", 62000),
            new Employee("E-104", 48000)
        };

        double bonus = 5000;

        for (int i = 0; i < employees.length; i++) {

            employees[i].raiseSalary(bonus);

            System.out.println(employees[i].employeeId
                    + " | Final Salary: Rs "
                    + employees[i].salary);
        }
    }
}
