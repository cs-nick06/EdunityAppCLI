package edunity.model;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a student enrolled in the system.
 * Tracks basic info like name, contact details, enrollment date, and current grade.
 */

public class Student implements Serializable {
    private String id;
    private String fullName;
    private String phone;
    private String emailAddress;
    private LocalDate enrolledOn;
    private String currentGrade;

    public Student(String id, String fullName, String phone, String emailAddress, String currentGrade) {
        this.id = id;
        this.fullName = fullName;
        this.phone = phone;
        this.emailAddress = emailAddress;
        this.currentGrade = currentGrade;
        this.enrolledOn = LocalDate.now();
    }

    // Basic getters
    public String getId() { return id; }
    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }
    public String getEmailAddress() { return emailAddress; }
    public LocalDate getEnrolledOn() { return enrolledOn; }
    public String getCurrentGrade() { return currentGrade; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public void setPhone(String phone) { this.phone = phone; }
    public void setEmailAddress(String emailAddress) { this.emailAddress = emailAddress; }
    public void setEnrolledOn(LocalDate enrolledOn) { this.enrolledOn = enrolledOn; }
    public void setCurrentGrade(String currentGrade) { this.currentGrade = currentGrade; }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return String.format("ID: %s | Name: %s | Grade: %s | Phone: %s | Email: %s | Enrolled: %s",
                id, fullName, currentGrade, phone, emailAddress, enrolledOn.format(fmt));
    }
}
