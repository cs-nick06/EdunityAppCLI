package edunity.service;
import edunity.model.*;
import edunity.storage.DataStorage;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages attendance tracking system.
 * Records and retrieves attendance information for students and courses.
 */

public class AttendanceService {
    private List<Attendance> attendanceRecords;
    private DataStorage storage;
    private int nextId;

    public AttendanceService(DataStorage storage) {
        this.storage = storage;
        this.attendanceRecords = storage.loadAttendance();
        this.nextId = attendanceRecords.isEmpty() ? 1 :
                attendanceRecords.stream()
                        .mapToInt(a -> Integer.parseInt(a.getRecordId().substring(1)))
                        .max()
                        .orElse(0) + 1;
    }

    public void markAttendance(String studentId, String courseId, boolean present, String remarks) {
        String attendanceId = "A" + String.format("%04d", nextId++);
        Attendance attendance = new Attendance(attendanceId, studentId, courseId, present, remarks);
        attendanceRecords.add(attendance);
        storage.saveAttendance(attendanceRecords);
        System.out.println("\nAttendance marked successfully! Record ID: " + attendanceId);
    }

    public void displayAllAttendance() {
        if (attendanceRecords.isEmpty()) {
            System.out.println("\nNo attendance records yet.");
            return;
        }

        System.out.println("\n=== All Attendance Records ===");
        for (Attendance record : attendanceRecords) {
            System.out.println(record);
        }
    }

    public void displayAttendanceByStudent(String studentId) {
        List<Attendance> studentRecords = attendanceRecords.stream()
                .filter(a -> a.getStudentId().equalsIgnoreCase(studentId))
                .collect(Collectors.toList());

        if (studentRecords.isEmpty()) {
            System.out.println("\nNo attendance records found for student: " + studentId);
        } else {
            System.out.println("\n=== Attendance for Student " + studentId + " ===");
            for (Attendance record : studentRecords) {
                System.out.println(record);
            }

            long presentCount = studentRecords.stream().filter(Attendance::isWasPresent).count();
            double attendanceRate = (presentCount * 100.0) / studentRecords.size();
            System.out.println(String.format("\nAttendance Rate: %.2f%% (%d/%d days)",
                    attendanceRate, presentCount, studentRecords.size()));
        }
    }

    public void displayAttendanceByCourse(String courseId) {
        List<Attendance> courseRecords = attendanceRecords.stream()
                .filter(a -> a.getCourseId().equalsIgnoreCase(courseId))
                .collect(Collectors.toList());

        if (courseRecords.isEmpty()) {
            System.out.println("\nNo attendance records found for course: " + courseId);
        } else {
            System.out.println("\n=== Attendance for Course " + courseId + " ===");
            for (Attendance record : courseRecords) {
                System.out.println(record);
            }
        }
    }
}
