package stage1.graduation.model;

import stage1.graduation.util.Console;

import java.util.Comparator;
import java.util.Objects;

public class Student implements Comparable<Student> {

    private final int groupNumber;
    private final float averageMark;
    private final int recordBookNumber;

    private Student(StudentBuilder studentBuilder) {
        groupNumber = studentBuilder.groupNumber;
        averageMark = studentBuilder.averageMark;
        recordBookNumber = studentBuilder.recordBookNumber;
    }

    public int getGroupNumber() {
        return groupNumber;
    }

    public float getAverageMark() {
        return averageMark;
    }

    public int getRecordBookNumber() {
        return recordBookNumber;
    }

    public static Student inputStudent() {
        Student student;
        do {
            int recordBookNumber = askForStudentRecordBookNumber();
            int groupNumber = askForStudentGroupNumber();
            float averageScore = askForStudentAverageScore();
            try {
                student = new Student.StudentBuilder(recordBookNumber)
                        .setGroupNumber(groupNumber)
                        .setAverageMark(averageScore)
                        .build();
            } catch (RuntimeException e) {
                Console.println("");
                Console.println("Ошибка! " + e.getMessage());
                Console.println("Пожалуйста, повторите ввод:");
                continue;
            }
            break;
        } while (true);
        return student;
    }

    private static int askForStudentRecordBookNumber() {
        Console.print("Введите номер зачётной книжки студента > 0 и < 1_000_000: ");
        return Console.readInt();
    }

    private static int askForStudentGroupNumber() {
        Console.print("Введите номер группы студента > 0 и < 10_000: ");
        return Console.readInt();
    }

    private static float askForStudentAverageScore() {
        Console.print("Введите средний балл студента >= 0.0 и <= 5.0f: ");
        return Console.readFloat();
    }

    public static Student createStudentFromData(String[] data, int index) {
        Student student;
        try {
            int groupNumber = Integer.parseInt(data[0]);
            float averageScore = Float.parseFloat(data[1]);
            int recordBookNumber = Integer.parseInt(data[2]);
            student = new Student.StudentBuilder(recordBookNumber)
                    .setGroupNumber(groupNumber)
                    .setAverageMark(averageScore)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Ощибка при чтении " + index + " строки файла: " + e.getMessage());
        }
        return student;
    }

    public static Comparator<Student> compareByGroupNumber() {
        return Comparator.comparingInt(Student::getGroupNumber);
    }

    public static Comparator<Student> compareByAverageMark() {
        return Comparator.comparingDouble(Student::getAverageMark);
    }

    public static Comparator<Student> compareByRecordBookNumber() {
        return Comparator.comparingInt(Student::getRecordBookNumber);
    }

    public static Comparator<Student> compareByEvenRecordBookNumber() {
        return (o1, o2) -> {
            if (o1.recordBookNumber % 2 == 0 && o2.recordBookNumber % 2 == 0) {
                return Integer.compare(o1.recordBookNumber, o2.recordBookNumber);
            } else return 0;
        };
    }

    @Override
    public int compareTo(Student other) {
        int groupComparison = Integer.compare(this.groupNumber, other.groupNumber);
        if (groupComparison != 0) {
            return groupComparison;
        }

        int scoreComparison = Float.compare(this.averageMark, other.averageMark);
        if (scoreComparison != 0) {
            return scoreComparison;
        }

        return Integer.compare(this.recordBookNumber, other.recordBookNumber);
    }

    @Override
    public String toString() {
        return "Student{" +
                "groupNumber=" + groupNumber +
                ", averageMark=" + averageMark +
                ", recordBookNumber=" + recordBookNumber +
                '}';
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof Student)) {
            return false;
        }
        Student student = (Student) object;
        return groupNumber == student.groupNumber &&
                averageMark == student.averageMark &&
                recordBookNumber == student.recordBookNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupNumber, averageMark, recordBookNumber);
    }

    public static class StudentBuilder {

        private final int recordBookNumber;
        private int groupNumber;
        private float averageMark;

        public StudentBuilder(int recordBookNumber) {
            if (recordBookNumber <= 0 || recordBookNumber > 999_999) {
                throw new RuntimeException("Номер зачетной книжки должен быть больше 0 и меньше 1_000_000");
            }
            this.recordBookNumber = recordBookNumber;
        }

        public StudentBuilder setGroupNumber(int groupNumber) {
            if (groupNumber <= 0 || groupNumber > 9_999) {
                throw new RuntimeException("Номер группы должен быть больше 0 и меньше 10_000");
            }
            this.groupNumber = groupNumber;
            return this;
        }

        public StudentBuilder setAverageMark(float averageMark) {
            if (averageMark < 0.0f || averageMark > 5.0f) {
                throw new RuntimeException("Средний балл должен быть в промежутке от 0 до 5");
            }
            this.averageMark = averageMark;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}
