import java.time.*;
import java.util.*;

abstract class Assignment {
    String title;
    int maxMarks;
    LocalDate dueDate;

    Assignment(String title, int maxMarks, LocalDate dueDate) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDate = dueDate;
    }

    abstract double applyPenalty(double marks, long lateDays);
}

class CodingAssignment extends Assignment {
    CodingAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    double applyPenalty(double marks, long lateDays) {
        return marks * Math.max(0, 1 - 0.10 * lateDays);
    }
}

class WrittenAssignment extends Assignment {
    WrittenAssignment(String title, int maxMarks, LocalDate dueDate) {
        super(title, maxMarks, dueDate);
    }

    double applyPenalty(double marks, long lateDays) {
        return marks * Math.max(0, 1 - 0.20 * lateDays);
    }
}

class Submission {
    String student;
    Assignment assignment;
    LocalDate date;
    private String status = "Submitted";

    Submission(String student, Assignment assignment, LocalDate date) {
        this.student = student;
        this.assignment = assignment;
        this.date = date;
        long days = Math.max(0, ChronoUnit.DAYS.between(
                assignment.dueDate, date));
        System.out.println(student + "'s submission for '" +
                assignment.title + "' received (" +
                (days == 0 ? "on time" : days + " days late") +
                "). Status: " + status + ".");
    }

    void grade(double marks) {
        if (!status.equals("Submitted")) {
            System.out.println("Cannot grade or resubmit: submission already graded.");
            return;
        }

        long days = Math.max(0, ChronoUnit.DAYS.between(
                assignment.dueDate, date));
        double finalMarks = assignment.applyPenalty(
                Math.min(marks, assignment.maxMarks), days);

        System.out.printf("%s graded: %.0f/%d", student,
                finalMarks, assignment.maxMarks);

        if (days > 0) {
            System.out.printf(" after %.0f%% late penalty", days *
                    (assignment instanceof CodingAssignment ? 10.0 : 20.0));
        }

        status = "Graded";
        System.out.println(". Status: " + status + ".");
    }

    void resubmit() {
        if (status.equals("Graded"))
            System.out.println("Cannot resubmit: '" + assignment.title +
                    "' has already been graded.");
        else
            System.out.println("Resubmission allowed.");
    }
}

public class AssignmentSubmissionPortal {
    public static void main(String[] args) {
        Assignment a1 = new CodingAssignment("Linked List Lab", 50,
                LocalDate.of(2026, 3, 10));
        Assignment a2 = new WrittenAssignment("Design Essay", 50,
                LocalDate.of(2026, 3, 12));

        Submission s1 = new Submission("Asha", a1,
                LocalDate.of(2026, 3, 10));
        Submission s2 = new Submission("Ravi", a2,
                LocalDate.of(2026, 3, 14));

        s1.grade(45);
        s2.grade(40);
        s1.resubmit();
    }
}
