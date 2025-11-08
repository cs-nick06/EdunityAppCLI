package edunity.service;
import edunity.model.*;
import edunity.storage.DataStorage;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages course-related operations.
 * Handles adding, updating, displaying, and finding courses.
 */
public class CourseService {
    private List<Course> courses;
    private DataStorage storage;
    private int nextId;

    public CourseService(DataStorage storage) {
        this.storage = storage;
        this.courses = storage.loadCourses();
        this.nextId = courses.isEmpty() ? 1 :
                courses.stream()
                        .mapToInt(c -> Integer.parseInt(c.getCourseId().substring(1)))
                        .max()
                        .orElse(0) + 1;
    }

    public void addCourse(String courseName, String subject, double fee, String schedule, String instructor) {
        String courseId = "C" + String.format("%03d", nextId++);
        Course course = new Course(courseId, courseName, subject, fee, schedule, instructor);
        courses.add(course);
        storage.saveCourses(courses);
        System.out.println("\nCourse added successfully! Course ID: " + courseId);
    }

    public void displayAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("\nNo courses available yet.");
            return;
        }

        System.out.println("\n=== All Courses ===");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public Course findCourseById(String courseId) {
        return courses.stream()
                .filter(c -> c.getCourseId().equalsIgnoreCase(courseId))
                .findFirst()
                .orElse(null);
    }

    public void searchCourses(String keyword) {
        List<Course> results = courses.stream()
                .filter(c -> c.getCourseName().toLowerCase().contains(keyword.toLowerCase()) ||
                        c.getSubjectArea().toLowerCase().contains(keyword.toLowerCase()) ||
                        c.getCourseId().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("\nNo courses found matching: " + keyword);
        } else {
            System.out.println("\n=== Search Results ===");
            for (Course course : results) {
                System.out.println(course);
            }
        }
    }

    public void updateCourse(String courseId, String courseName, String subject, Double fee, String schedule, String instructor) {
        Course course = findCourseById(courseId);
        if (course != null) {
            if (courseName != null && !courseName.trim().isEmpty()) course.setCourseName(courseName);
            if (subject != null && !subject.trim().isEmpty()) course.setSubjectArea(subject);
            if (fee != null) course.setTuitionFee(fee);
            if (schedule != null && !schedule.trim().isEmpty()) course.setClassSchedule(schedule);
            if (instructor != null && !instructor.trim().isEmpty()) course.setInstructorName(instructor);
            storage.saveCourses(courses);
            System.out.println("\nCourse updated successfully!");
        } else {
            System.out.println("\nCourse not found!");
        }
    }
}