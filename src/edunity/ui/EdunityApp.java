package edunity.ui;

import edunity.storage.DataStorage;
import edunity.model.Course;
import edunity.model.Student;
import edunity.service.AttendanceService;
import edunity.service.CourseService;
import edunity.service.PaymentService;
import edunity.service.StudentService;
import java.util.*;

/**
 * Main application class for the Edunity Management System.
 * Provides a console-based menu interface for managing students, courses, payments, and attendance.
 */

public class EdunityApp {
    private StudentService studentService;
    private CourseService courseService;
    private PaymentService paymentService;
    private AttendanceService attendanceService;
    private Scanner scanner;

    public EdunityApp() {
        DataStorage storage = new DataStorage();
        this.studentService = new StudentService(storage);
        this.courseService = new CourseService(storage);
        this.paymentService = new PaymentService(storage);
        this.attendanceService = new AttendanceService(storage);
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("||========================================================||");
        System.out.println("||    TUITION MANAGEMENT SYSTEM - Welcome to Edunity!     ||");
        System.out.println("||========================================================||");

        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleStudentMenu();
                    break;
                case 2:
                    handleCourseMenu();
                    break;
                case 3:
                    handlePaymentMenu();
                    break;
                case 4:
                    handleAttendanceMenu();
                    break;
                case 5:
                    System.out.println("\nThank you for using Tuition Management System!");
                    running = false;
                    break;
                default:
                    System.out.println("\nInvalid choice! Please try again.");
            }
        }
        scanner.close();
    }

    private void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Payment Management");
        System.out.println("4. Attendance Management");
        System.out.println("5. Exit");
        System.out.println("=".repeat(50));
    }

    private void handleStudentMenu() {
        System.out.println("\n--- Student Management ---");
        System.out.println("1. Add New Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search Student");
        System.out.println("4. View Student Details");
        System.out.println("5. Update Student");
        System.out.println("6. Back to Main Menu");

        int choice = getIntInput("Enter your choice: ");

        switch (choice) {
            case 1:
                addNewStudent();
                break;
            case 2:
                studentService.displayAllStudents();
                break;
            case 3:
                System.out.print("Enter search keyword (name or ID): ");
                String keyword = scanner.nextLine();
                studentService.searchStudents(keyword);
                break;
            case 4:
                System.out.print("Enter student ID: ");
                String studentId = scanner.nextLine();
                studentService.displayStudentDetails(studentId);
                break;
            case 5:
                updateStudent();
                break;
            case 6:
                return;
            default:
                System.out.println("\nInvalid choice!");
        }
    }

    private void addNewStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter grade/class: ");
        String grade = scanner.nextLine();

        studentService.addStudent(name, phone, email, grade);
    }

    private void updateStudent() {
        System.out.print("Enter student ID to update: ");
        String studentId = scanner.nextLine();

        Student student = studentService.findStudentById(studentId);
        if (student == null) {
            System.out.println("\nStudent not found!");
            return;
        }

        System.out.println("\nCurrent details: " + student);
        System.out.println("\nEnter new details (press Enter to keep current value):");

        System.out.print("New name: ");
        String name = scanner.nextLine();
        System.out.print("New phone: ");
        String phone = scanner.nextLine();
        System.out.print("New email: ");
        String email = scanner.nextLine();
        System.out.print("New grade: ");
        String grade = scanner.nextLine();

        studentService.updateStudent(studentId, name, phone, email, grade);
    }

    private void handleCourseMenu() {
        System.out.println("\n--- Course Management ---");
        System.out.println("1. Add New Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Search Course");
        System.out.println("4. View Course Details");
        System.out.println("5. Update Course");
        System.out.println("6. Back to Main Menu");

        int choice = getIntInput("Enter your choice: ");

        switch (choice) {
            case 1:
                addNewCourse();
                break;
            case 2:
                courseService.displayAllCourses();
                break;
            case 3:
                System.out.print("Enter search keyword (name, subject, or ID): ");
                String keyword = scanner.nextLine();
                courseService.searchCourses(keyword);
                break;
            case 4:
                System.out.print("Enter course ID: ");
                String courseId = scanner.nextLine();
                courseService.displayCourseDetails(courseId);
                break;
            case 5:
                updateCourse();
                break;
            case 6:
                return;
            default:
                System.out.println("\nInvalid choice!");
        }
    }

    private void addNewCourse() {
        System.out.println("\n--- Add New Course ---");
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter subject: ");
        String subject = scanner.nextLine();
        double fee = getDoubleInput("Enter monthly fee: $");
        System.out.print("Enter schedule (e.g., Mon-Wed-Fri 4pm): ");
        String schedule = scanner.nextLine();
        System.out.print("Enter instructor name: ");
        String instructor = scanner.nextLine();

        courseService.addCourse(courseName, subject, fee, schedule, instructor);
    }

    private void updateCourse() {
        System.out.print("Enter course ID to update: ");
        String courseId = scanner.nextLine();

        Course course = courseService.findCourseById(courseId);
        if (course == null) {
            System.out.println("\nCourse not found!");
            return;
        }

        System.out.println("\nCurrent details: " + course);
        System.out.println("\nEnter new details (press Enter to keep current value):");

        System.out.print("New course name: ");
        String courseName = scanner.nextLine();
        System.out.print("New subject: ");
        String subject = scanner.nextLine();
        System.out.print("New monthly fee (0 to skip): $");
        String feeStr = scanner.nextLine();
        Double fee = feeStr.trim().isEmpty() ? null : Double.parseDouble(feeStr);
        System.out.print("New schedule: ");
        String schedule = scanner.nextLine();
        System.out.print("New instructor: ");
        String instructor = scanner.nextLine();

        courseService.updateCourse(courseId, courseName, subject, fee, schedule, instructor);
    }

    private void handlePaymentMenu() {
        System.out.println("\n--- Payment Management ---");
        System.out.println("1. Record New Payment");
        System.out.println("2. View All Payments");
        System.out.println("3. View Payments by Student");
        System.out.println("4. View Payments by Course");
        System.out.println("5. View Payment Details");
        System.out.println("6. Back to Main Menu");

        int choice = getIntInput("Enter your choice: ");

        switch (choice) {
            case 1:
                recordNewPayment();
                break;
            case 2:
                paymentService.displayAllPayments();
                break;
            case 3:
                System.out.print("Enter student ID: ");
                String studentId = scanner.nextLine();
                paymentService.displayPaymentsByStudent(studentId);
                break;
            case 4:
                System.out.print("Enter course ID: ");
                String courseId = scanner.nextLine();
                paymentService.displayPaymentsByCourse(courseId);
                break;
            case 5:
                System.out.print("Enter payment ID: ");
                String paymentId = scanner.nextLine();
                paymentService.displayPaymentDetails(paymentId);
                break;
            case 6:
                return;
            default:
                System.out.println("\nInvalid choice!");
        }
    }

    private void recordNewPayment() {
        System.out.println("\n--- Record New Payment ---");
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        double amount = getDoubleInput("Enter payment amount: $");
        System.out.print("Enter payment month (e.g., January 2024): ");
        String paymentMonth = scanner.nextLine();
        System.out.print("Enter payment method (Cash/Card/UPI/Bank Transfer): ");
        String paymentMethod = scanner.nextLine();

        paymentService.recordPayment(studentId, courseId, amount, paymentMonth, paymentMethod);
    }

    private void handleAttendanceMenu() {
        System.out.println("\n--- Attendance Management ---");
        System.out.println("1. Mark Attendance");
        System.out.println("2. View All Attendance");
        System.out.println("3. View Attendance by Student");
        System.out.println("4. View Attendance by Course");
        System.out.println("5. View Attendance Record Details");
        System.out.println("6. Back to Main Menu");

        int choice = getIntInput("Enter your choice: ");

        switch (choice) {
            case 1:
                markAttendance();
                break;
            case 2:
                attendanceService.displayAllAttendance();
                break;
            case 3:
                System.out.print("Enter student ID: ");
                String studentId = scanner.nextLine();
                attendanceService.displayAttendanceByStudent(studentId);
                break;
            case 4:
                System.out.print("Enter course ID: ");
                String courseId = scanner.nextLine();
                attendanceService.displayAttendanceByCourse(courseId);
                break;
            case 5:
                System.out.print("Enter record ID: ");
                String recordId = scanner.nextLine();
                attendanceService.displayAttendanceDetails(recordId);
                break;
            case 6:
                return;
            default:
                System.out.println("\nInvalid choice!");
        }
    }

    private void markAttendance() {
        System.out.println("\n--- Mark Attendance ---");
        System.out.print("Enter student ID: ");
        String studentId = scanner.nextLine();
        System.out.print("Enter course ID: ");
        String courseId = scanner.nextLine();
        System.out.print("Is student present? (yes/no): ");
        String presentStr = scanner.nextLine();
        boolean present = presentStr.equalsIgnoreCase("yes") || presentStr.equalsIgnoreCase("y");
        System.out.print("Enter remarks (optional): ");
        String remarks = scanner.nextLine();

        if (remarks.trim().isEmpty()) {
            remarks = present ? "Present" : "Absent";
        }

        attendanceService.markAttendance(studentId, courseId, present, remarks);
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
    }

    public static void main(String[] args) {
        EdunityApp system = new EdunityApp();
        system.start();
    }
}
