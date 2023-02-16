//package mk.ukim.finki.vtor_kolokvium;

import java.util.*;
import java.util.stream.IntStream;
class OperationNotAllowedException extends Exception {
    public OperationNotAllowedException(String message) {
        super(message);
    }
}
class Student{
    int yearsOfStudies;
    String id;
    int grades;
    List<Integer> all;
    Map<Integer,Map<String,Integer>> gradesByCourseByTerm;
    TreeSet<String> courses;

    public Student(int yearsOfStudies, String id) {
        this.all = new ArrayList<>();
        this.grades = 0;
        this.yearsOfStudies = yearsOfStudies;
        this.id = id;
        gradesByCourseByTerm = new TreeMap<>();
        IntStream.range(1,yearsOfStudies*2+1).forEach(i->gradesByCourseByTerm.put(i,new HashMap<>()));
        courses = new TreeSet<>();
    }

    public void addGrade(int term, String courseName, int grade) throws OperationNotAllowedException {
        if(!gradesByCourseByTerm.containsKey(term)){
            throw new OperationNotAllowedException(String.format("Term %d is not possible for student with ID %s",term,id));
        }
        if(gradesByCourseByTerm.get(term).size()==3){
            throw new OperationNotAllowedException(String.format("Student %s already has 3 grades in term %d",id,term));
        }
        gradesByCourseByTerm.get(term).put(courseName,grade);
        grades++;
        all.add(grade);
        courses.add(courseName);
    }

    public boolean graduate(){
        return yearsOfStudies==4?grades==24:grades==18;
    }

    public double avg(){
        return all.stream().mapToInt(i->i).average().orElse(5.0);
    }

    public String toLog() {
        return String.format("Student with ID %s graduated with average grade %.2f in %d years.",id,avg(),yearsOfStudies);
    }
    public int listSize(){
        return courses.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student: ").append(id).append("\n");
        for(int i=1;i<=yearsOfStudies*2;i++){
            sb.append("Term ").append(i).append("\n");
            int courses = gradesByCourseByTerm.get(i).size();
            sb.append("Courses: ").append(courses).append("\n");
            double average = gradesByCourseByTerm.get(i).values().stream().mapToInt(j->j).average().orElse(5.0);
            sb.append(String.format("Average grade for term: %.2f\n",average));
        }
        sb.append(String.format("Average grade: %.2f\n",avg()));
        sb.append("Courses attended: ");
        sb.append(String.join(",",courses));
        return sb.toString();
    }

    public String leale() {
        return String.format("Student: %s Courses passed: %d Average grade: %.2f",id,listSize(),avg());
    }
}
class Course{
    int grades;
    int students;
    String name;

    public Course(String name) {
        this.grades = 0;
        this.students = 0;
        this.name = name;
    }
    public void addGrade(int grade){
        this.grades+=grade;
        students++;
    }
    public double avg(){
        return (double) grades/students;
    }

    public int getGrades() {
        return grades;
    }

    public int getStudents() {
        return students;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s %d %.2f",name,students,avg());
    }
}
class Faculty {
    Map<String,Course> coursesPrint;
    Map<String,Student> studentMap;
    List<String> logs;
    public Faculty() {
        studentMap = new TreeMap<>(Comparator.reverseOrder());
        logs = new ArrayList<>();
        coursesPrint = new TreeMap<>();
    }

    void addStudent(String id, int yearsOfStudies) {
        Student student = new Student(yearsOfStudies,id);
        studentMap.putIfAbsent(id,student);
    }

    void addGradeToStudent(String studentId, int term, String courseName, int grade) throws OperationNotAllowedException {
        Student student = studentMap.get(studentId);
        student.addGrade(term, courseName, grade);
        if(student.graduate()){
            studentMap.remove(studentId);
            logs.add(student.toLog());
        }
        Course course = new Course(courseName);
        coursesPrint.putIfAbsent(courseName,course);
        coursesPrint.get(courseName).addGrade(grade);
    }

    String getFacultyLogs() {
        return String.join("\n",logs);
    }

    String getDetailedReportForStudent(String id) {
        return studentMap.get(id).toString();
    }

    void printFirstNStudents(int n) {
        Comparator<Student> comparator = Comparator.comparing(Student::listSize).thenComparing(Student::avg);
        studentMap.values().stream().sorted(comparator.reversed()).limit(n).forEach(i-> System.out.println(i.leale()));
    }

    void printCourses() {
        Comparator<Course> comparator = Comparator.comparing(Course::getStudents).thenComparing(Course::avg).thenComparing(Course::getName);
        coursesPrint.values().stream().sorted(comparator).forEach(System.out::println);
    }

}

public class FacultyTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();

        if (testCase == 1) {
            System.out.println("TESTING addStudent AND printFirstNStudents");
            Faculty faculty = new Faculty();
            for (int i = 0; i < 10; i++) {
                faculty.addStudent("student" + i, (i % 2 == 0) ? 3 : 4);
            }
            faculty.printFirstNStudents(10);

        } else if (testCase == 2) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            try {
                faculty.addGradeToStudent("123", 7, "NP", 10);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
            try {
                faculty.addGradeToStudent("1234", 9, "NP", 8);
            } catch (OperationNotAllowedException e) {
                System.out.println(e.getMessage());
            }
        } else if (testCase == 3) {
            System.out.println("TESTING addGrade and exception");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("123", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
            for (int i = 0; i < 4; i++) {
                try {
                    faculty.addGradeToStudent("1234", 1, "course" + i, 10);
                } catch (OperationNotAllowedException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else if (testCase == 4) {
            System.out.println("Testing addGrade for graduation");
            Faculty faculty = new Faculty();
            faculty.addStudent("123", 3);
            faculty.addStudent("1234", 4);
            int counter = 1;
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("123", i, "course" + counter, (i % 2 == 0) ? 7 : 8);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            counter = 1;
            for (int i = 1; i <= 8; i++) {
                for (int j = 1; j <= 3; j++) {
                    try {
                        faculty.addGradeToStudent("1234", i, "course" + counter, (j % 2 == 0) ? 7 : 10);
                    } catch (OperationNotAllowedException e) {
                        System.out.println(e.getMessage());
                    }
                    ++counter;
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("PRINT STUDENTS (there shouldn't be anything after this line!");
            faculty.printFirstNStudents(2);
        } else if (testCase == 5 || testCase == 6 || testCase == 7) {
            System.out.println("Testing addGrade and printFirstNStudents (not graduated student)");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), i % 5 + 6);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            if (testCase == 5)
                faculty.printFirstNStudents(10);
            else if (testCase == 6)
                faculty.printFirstNStudents(3);
            else
                faculty.printFirstNStudents(20);
        } else if (testCase == 8 || testCase == 9) {
            System.out.println("TESTING DETAILED REPORT");
            Faculty faculty = new Faculty();
            faculty.addStudent("student1", ((testCase == 8) ? 3 : 4));
            int grade = 6;
            int counterCounter = 1;
            for (int i = 1; i < ((testCase == 8) ? 6 : 8); i++) {
                for (int j = 1; j < 3; j++) {
                    try {
                        faculty.addGradeToStudent("student1", i, "course" + counterCounter, grade);
                    } catch (OperationNotAllowedException e) {
                        e.printStackTrace();
                    }
                    grade++;
                    if (grade == 10)
                        grade = 5;
                    ++counterCounter;
                }
            }
            System.out.println(faculty.getDetailedReportForStudent("student1"));
        } else if (testCase==10) {
            System.out.println("TESTING PRINT COURSES");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j < ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 3 : 2); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            faculty.printCourses();
        } else if (testCase==11) {
            System.out.println("INTEGRATION TEST");
            Faculty faculty = new Faculty();
            for (int i = 1; i <= 10; i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= ((j % 2 == 1) ? 2 : 3); k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }

            }

            for (int i=11;i<15;i++) {
                faculty.addStudent("student" + i, ((i % 2) == 1 ? 3 : 4));
                int courseCounter = 1;
                for (int j = 1; j <= ((i % 2 == 1) ? 6 : 8); j++) {
                    for (int k = 1; k <= 3; k++) {
                        int grade = sc.nextInt();
                        try {
                            faculty.addGradeToStudent("student" + i, j, ("course" + courseCounter), grade);
                        } catch (OperationNotAllowedException e) {
                            System.out.println(e.getMessage());
                        }
                        ++courseCounter;
                    }
                }
            }
            System.out.println("LOGS");
            System.out.println(faculty.getFacultyLogs());
            System.out.println("DETAILED REPORT FOR STUDENT");
            System.out.println(faculty.getDetailedReportForStudent("student2"));
            try {
                System.out.println(faculty.getDetailedReportForStudent("student11"));
                System.out.println("The graduated students should be deleted!!!");
            } catch (NullPointerException e) {
                System.out.println("The graduated students are really deleted");
            }
            System.out.println("FIRST N STUDENTS");
            faculty.printFirstNStudents(10);
            System.out.println("COURSES");
            faculty.printCourses();
        }
    }
}
