package edunity.storage;

import edunity.model.Attendance;
import edunity.model.Course;
import edunity.model.Payment;
import edunity.model.Student;

import java.io.*;
import java.util.*;

/**
 * Handles data persistence using file-based storage.
 * Saves and loads students, courses, payments, and attendance records.
 */

public class DataStorage {
    private static final String DATA_DIR = "data";
    private static final String STUDENTS_FILE = DATA_DIR + "/students.dat";
    private static final String COURSES_FILE = DATA_DIR + "/courses.dat";
    private static final String PAYMENTS_FILE = DATA_DIR + "/payments.dat";
    private static final String ATTENDANCE_FILE = DATA_DIR + "/attendance.dat";

    public DataStorage() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    // Generic save method
    private <T> void saveData(String filename, List<T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    // Generic load method
    @SuppressWarnings("unchecked")
    private <T> List<T> loadData(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<T>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Student operations
    public void saveStudents(List<Student> students) {
        saveData(STUDENTS_FILE, students);
    }

    public List<Student> loadStudents() {
        return loadData(STUDENTS_FILE);
    }

    // Course operations
    public void saveCourses(List<Course> courses) {
        saveData(COURSES_FILE, courses);
    }

    public List<Course> loadCourses() {
        return loadData(COURSES_FILE);
    }

    // Payment operations
    public void savePayments(List<Payment> payments) {
        saveData(PAYMENTS_FILE, payments);
    }

    public List<Payment> loadPayments() {
        return loadData(PAYMENTS_FILE);
    }

    // Attendance operations
    public void saveAttendance(List<Attendance> attendanceList) {
        saveData(ATTENDANCE_FILE, attendanceList);
    }

    public List<Attendance> loadAttendance() {
        return loadData(ATTENDANCE_FILE);
    }
}
