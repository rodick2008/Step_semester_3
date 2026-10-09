interface MembershipPlan {
    int getMonths();
    double getDiscount();
    String getName();

    default double calculateFee() {
        return 1000 * getMonths() * (1 - getDiscount());
    }
}

class MonthlyPlan implements MembershipPlan {
    public int getMonths() { return 1; }
    public double getDiscount() { return 0; }
    public String getName() { return "Monthly"; }
}

class QuarterlyPlan implements MembershipPlan {
    public int getMonths() { return 3; }
    public double getDiscount() { return 0.10; }
    public String getName() { return "Quarterly"; }
}

class AnnualPlan implements MembershipPlan {
    public int getMonths() { return 12; }
    public double getDiscount() { return 0.25; }
    public String getName() { return "Annual"; }
}

class Membership {
    String member;
    private String status = "Active";
    MembershipPlan plan;

    Membership(String member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        System.out.printf("%s membership created for %s. Fee: ₹%.2f. Status: %s.%n",
                plan.getName(), member, plan.calculateFee(), status);
    }

    void checkIn() {
        if (status.equals("Active"))
            System.out.println(member + " checked in successfully.");
        else
            System.out.println("Check-in denied: " + member +
                    "'s membership is " + status + ".");
    }

    void freeze() {
        if (status.equals("Expired"))
            System.out.println("Cannot freeze an Expired membership.");
        else if (status.equals("Frozen"))
            System.out.println(member + "'s membership is already Frozen.");
        else {
            status = "Frozen";
            System.out.println(member + "'s membership frozen. Status: " + status + ".");
        }
    }

    void unfreeze() {
        if (status.equals("Expired"))
            System.out.println("Cannot unfreeze an Expired membership.");
        else if (status.equals("Frozen")) {
            status = "Active";
            System.out.println(member + "'s membership unfrozen. Status: " + status + ".");
        }
    }

    void expire() {
        status = "Expired";
        System.out.println(member + "'s membership expired. Status: " + status + ".");
    }
}

public class FitZoneMembershipDesk {
    public static void main(String[] args) {
        Membership a = new Membership("Asha", new QuarterlyPlan());
        Membership r = new Membership("Ravi", new MonthlyPlan());

        a.checkIn();
        a.freeze();
        a.checkIn();

        r.expire();
        r.freeze();
    }
}
