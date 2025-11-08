package edunity.model;

import java.io.Serializable;

/**
 * Represents a course offered in the system.
 * Includes course details like ID, name, subject, fees, schedule, and instructor.
 */

public class Course implements Serializable {
    private String courseId;
    private String courseName;
    private String subjectArea;
    private double tuitionFee;
    private String classSchedule;
    private String instructorName;

    public Course(String courseId, String courseName, String subjectArea, double tuitionFee, String classSchedule, String instructorName) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.subjectArea = subjectArea;
        this.tuitionFee = tuitionFee;
        this.classSchedule = classSchedule;
        this.instructorName = instructorName;
    }

    // Getters
    public String getCourseId() { return courseId; }
    public String getCourseName() { return courseName; }
    public String getSubjectArea() { return subjectArea; }
    public double getTuitionFee() { return tuitionFee; }
    public String getClassSchedule() { return classSchedule; }
    public String getInstructorName() { return instructorName; }

    // Setters
    public void setCourseId(String courseId) { this.courseId = courseId; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setSubjectArea(String subjectArea) { this.subjectArea = subjectArea; }
    public void setTuitionFee(double tuitionFee) { this.tuitionFee = tuitionFee; }
    public void setClassSchedule(String classSchedule) { this.classSchedule = classSchedule; }
    public void setInstructorName(String instructorName) { this.instructorName = instructorName; }

    @Override
    public String toString() {
        return String.format("[%s] %s - %s | Fee: $%.2f | Schedule: %s | Instructor: %s",
                courseId, courseName, subjectArea, tuitionFee, classSchedule, instructorName);
    }
}
