package edunity.service;
import edunity.model.*;
import edunity.storage.DataStorage;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages student-related operations.
 * Handles adding, updating, displaying, and finding students.
 */

public class StudentService {
    private List<Student> students;
    private DataStorage storage;
    private int nextId;

    public StudentService(DataStorage storage) {
        this.storage = storage;
        this.students = storage.loadStudents();
        this.nextId = students.isEmpty() ? 1 :
                students.stream()
                        .mapToInt(s -> Integer.parseInt(s.getId().substring(1)))
                        .max()
                        .orElse(0) + 1;
    }

    public void addStudent(String name, String phoneNumber, String email, String grade) {
        String studentId = "S" + String.format("%03d", nextId++);
        Student student = new Student(studentId, name, phoneNumber, email, grade);
        students.add(student);
        storage.saveStudents(students);
        System.out.println("\nStudent added successfully! Student ID: " + studentId);
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("\nNo students registered yet.");
            return;
        }

        System.out.println("\n=== All Students ===");
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public Student findStudentById(String studentId) {
        return students.stream()
                .filter(s -> s.getId().equalsIgnoreCase(studentId))
                .findFirst()
                .orElse(null);
    }

    public void searchStudents(String keyword) {
        List<Student> results = students.stream()
                .filter(s -> s.getFullName().toLowerCase().contains(keyword.toLowerCase()) ||
                        s.getId().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("\nNo students found matching: " + keyword);
        } else {
            System.out.println("\n=== Search Results ===");
            for (Student student : results) {
                System.out.println(student);
            }
        }
    }

    public void updateStudent(String studentId, String name, String phone, String email, String grade) {
        Student student = findStudentById(studentId);
        if (student != null) {
            if (name != null && !name.trim().isEmpty()) student.setFullName(name);
            if (phone != null && !phone.trim().isEmpty()) student.setPhone(phone);
            if (email != null && !email.trim().isEmpty()) student.setEmailAddress(email);
            if (grade != null && !grade.trim().isEmpty()) student.setCurrentGrade(grade);
            storage.saveStudents(students);
            System.out.println("\nStudent updated successfully!");
        } else {
            System.out.println("\nStudent not found!");
        }
    }
}
