package edunity.model;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Records student attendance for courses.
 * Tracks attendance ID, student, course, date, presence status, and remarks.
 */

public class Attendance implements Serializable {
    private String recordId;
    private String studentId;
    private String courseId;
    private LocalDate attendanceDate;
    private boolean wasPresent;
    private String notes;

    public Attendance(String recordId, String studentId, String courseId, boolean wasPresent, String notes) {
        this.recordId = recordId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.attendanceDate = LocalDate.now();
        this.wasPresent = wasPresent;
        this.notes = notes;
    }

    public String getRecordId() { return recordId; }
    public String getStudentId() { return studentId; }
    public String getCourseId() { return courseId; }
    public LocalDate getAttendanceDate() { return attendanceDate; }
    public boolean isWasPresent() { return wasPresent; }
    public String getNotes() { return notes; }

    public void setRecordId(String recordId) { this.recordId = recordId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public void setAttendanceDate(LocalDate attendanceDate) { this.attendanceDate = attendanceDate; }
    public void setWasPresent(boolean wasPresent) { this.wasPresent = wasPresent; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String status = wasPresent ? "Present" : "Absent";
        return String.format("Record #%s | Student: %s | Course: %s | Date: %s | Status: %s | Notes: %s",
                recordId, studentId, courseId, attendanceDate.format(fmt), status, notes);
    }
}