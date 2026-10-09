class GymMember {
    String memberId;
    int monthlyFee;
    int sessionsAttended;

    public GymMember(String memberId, int monthlyFee) {
        this.memberId = memberId;
        this.monthlyFee = monthlyFee;
    }

    void attendSession() {
        sessionsAttended++;
    }

    int getSessionsAttended() {
        return sessionsAttended;
    }

    void displayInfo() {
    }
}

class PremiumMember extends GymMember {
    String trainerName;

    public PremiumMember(String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    void displayInfo() {
    }
}

public class MonthlyAttendanceAnnouncer {

    static String batchPrint(GymMember[] members) {
        StringBuilder result = new StringBuilder();

        for (GymMember member : members) {
            if (member instanceof PremiumMember) {
                PremiumMember p = (PremiumMember) member;

                result.append("Premium | Trainer: ")
                        .append(p.trainerName)
                        .append(" | Sessions: ")
                        .append(p.getSessionsAttended())
                        .append(" [Trainer via downcast: ")
                        .append(p.trainerName)
                        .append("] | ");
            } else {
                result.append("Standard | Sessions: ")
                        .append(member.getSessionsAttended())
                        .append(" | ");
            }

            member.displayInfo();
        }

        return result.toString();
    }

    public static void main(String[] args) {
        GymMember[] members = {
            new GymMember("MEM6", 1000),
            new PremiumMember("MEM7", 2000, "Coach Riya")
        };

        System.out.println(batchPrint(members));
    }
}
