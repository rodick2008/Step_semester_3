import java.util.*;

interface WashType {
    int getDuration();
    double getCharge();
    String getName();
}

class QuickWash implements WashType {
    public int getDuration() { return 30; }
    public double getCharge() { return 20; }
    public String getName() { return "Quick"; }
}

class NormalWash implements WashType {
    public int getDuration() { return 45; }
    public double getCharge() { return 30; }
    public String getName() { return "Normal"; }
}

class HeavyWash implements WashType {
    public int getDuration() { return 60; }
    public double getCharge() { return 45; }
    public String getName() { return "Heavy"; }
}

class WashingMachine {
    String id;
    private WashCycle currentCycle;

    WashingMachine(String id) {
        this.id = id;
    }

    boolean startWash(String student, WashType type) {
        if (currentCycle != null) {
            System.out.println("Machine " + id + " is currently busy.");
            return false;
        }

        currentCycle = new WashCycle(student, this, type);
        System.out.printf("%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
                type.getName(), id, student, type.getDuration(), type.getCharge());
        return true;
    }

    void completeWash() {
        if (currentCycle == null) {
            System.out.println(id + " is already free.");
            return;
        }

        currentCycle = null;
        System.out.println(id + " cycle completed. " + id + " is now free.");
    }
}

class WashCycle {
    String student;
    WashingMachine machine;
    WashType type;

    WashCycle(String student, WashingMachine machine, WashType type) {
        this.student = student;
        this.machine = machine;
        this.type = type;
    }
}

public class HostelLaundryQueue {
    public static void main(String[] args) {
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.startWash("Asha", new QuickWash());
        m1.startWash("Ravi", new HeavyWash());
        m2.startWash("Ravi", new HeavyWash());
        m1.completeWash();
        m1.startWash("Neha", new NormalWash());
    }
}
