class LibraryMember {
    protected String memberId;
    protected int borrowLimit;
    protected int booksBorrowed;

    public LibraryMember(String memberId, int borrowLimit) {
        if (memberId == null || memberId.trim().length() < 4) {
            throw new IllegalArgumentException("Invalid member ID");
        }

        this.memberId = memberId;
        this.borrowLimit = borrowLimit;
    }

    public void borrowBook() {
        if (booksBorrowed < borrowLimit) {
            booksBorrowed++;
        }
    }

    public int getBooksBorrowed() {
        return booksBorrowed;
    }

    public void displayInfo() {
        System.out.print("General | Books: " + booksBorrowed);
    }
}

class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int borrowLimit, String course) {
        super(memberId, borrowLimit);
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public void displayInfo() {
        System.out.print("Student | Course: " + course
                + " | Books: " + booksBorrowed);
    }
}

public class WeeklyReport {

    static String batchPrint(LibraryMember[] members) {
        StringBuilder report = new StringBuilder();

        for (LibraryMember member : members) {
            member.displayInfo();

            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member;
                report.append("Course via downcast: ")
                      .append(student.getCourse())
                      .append(" | ");
            } else {
                report.append(" | ");
            }
        }

        return report.toString();
    }

    public static void main(String[] args) {
        LibraryMember[] members = {
            new LibraryMember("LB5", 3),
            new StudentMember("STU6", 3, "ECE")
        };

        System.out.println(batchPrint(members));
    }
}
