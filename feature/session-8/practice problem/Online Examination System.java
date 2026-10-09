import java.util.*;

abstract class Question {
    String id;
    int marks;

    Question(String id, int marks) {
        this.id = id;
        this.marks = marks;
    }

    abstract boolean evaluate(String answer);
}

class MultipleChoiceQuestion extends Question {
    String correctAnswer;

    MultipleChoiceQuestion(String id, int marks, String answer) {
        super(id, marks);
        correctAnswer = answer;
    }

    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    String correctAnswer;

    TrueFalseQuestion(String id, int marks, String answer) {
        super(id, marks);
        correctAnswer = answer;
    }

    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class ShortAnswerQuestion extends Question {
    String correctAnswer;

    ShortAnswerQuestion(String id, int marks, String answer) {
        super(id, marks);
        correctAnswer = answer;
    }

    boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Examination {
    String name;
    List<Question> questions = new ArrayList<>();

    Examination(String name) {
        this.name = name;
    }

    void addQuestion(Question q) {
        questions.add(q);
    }
}

class Attempt {
    String student;
    Examination exam;
    Map<Question, String> answers = new LinkedHashMap<>();
    boolean submitted = false;

    Attempt(String student, Examination exam) {
        this.student = student;
        this.exam = exam;
        System.out.println(exam.name + " started by " + student + ".");
    }

    void answer(Question q, String answer) {
        if (submitted) {
            System.out.println("Cannot change answers for a submitted examination.");
            return;
        }

        answers.put(q, answer);
        System.out.println("Answer recorded for " + q.id + ".");
    }

    void submit() {
        if (submitted) {
            System.out.println("Examination already submitted.");
            return;
        }

        submitted = true;
        int score = 0;
        int total = 0;

        System.out.println(exam.name + " submitted by " + student + ".");

        for (Question q : exam.questions) {
            total += q.marks;
            boolean correct = q.evaluate(answers.getOrDefault(q, ""));
            int earned = correct ? q.marks : 0;
            score += earned;

            System.out.println("Result: " + q.id + ": " +
                    (correct ? "Correct" : "Incorrect") +
                    " (" + earned + " points)");
        }

        System.out.println("Total score: " + score + "/" + total);
    }
}

public class Main {
    public static void main(String[] args) {
        Examination exam = new Examination("Exam A");
        Question q1 = new MultipleChoiceQuestion("Question 1", 5, "C");
        Question q2 = new TrueFalseQuestion("Question 2", 5, "False");

        exam.addQuestion(q1);
        exam.addQuestion(q2);

        Attempt a = new Attempt("Student 1", exam);
        a.answer(q1, "C");
        a.answer(q2, "True");
        a.submit();
        a.answer(q1, "A");
    }
}
