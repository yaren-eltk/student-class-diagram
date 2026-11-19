package com.university.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UML: Course
 */
public class Course {

    // UML attributes: - code : String, - title : String, - credits : int, - capacity : int
    private final String code;
    private String title;
    private int credits;
    private int capacity;

    // UML association: Course "0..*" —— "1" Instructor
    private Instructor instructor;

    // UML association class: Course "1" —— "0..*" Enrollment
    private final List<Enrollment> enrollments = new ArrayList<>();

    public Course(String code, String title, int credits, int capacity) {
        if (credits <= 0) throw new IllegalArgumentException("credits must be positive");
        if (capacity <= 0) throw new IllegalArgumentException("capacity must be positive");
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.capacity = capacity;
    }

    // UML operation: + isFull() : boolean
    public boolean isFull() {
        return enrollments.size() >= capacity;
    }

    // UML operation: + getEnrolledStudents() : List<Student>
    public List<Student> getEnrolledStudents() {
        List<Student> result = new ArrayList<>();
        for (Enrollment e : enrollments) result.add(e.getStudent());
        return result;
    }

    void addEnrollment(Enrollment e) {
        if (isFull()) {
            throw new IllegalStateException(code + " is full (capacity " + capacity + ")");
        }
        enrollments.add(e);
    }

    void removeEnrollment(Enrollment e) {
        enrollments.remove(e);
    }

    /** Sets both ends of the Course—Instructor association. */
    public void setInstructor(Instructor instructor) {
        if (this.instructor == instructor) return;
        if (this.instructor != null) this.instructor.removeCourse(this);
        this.instructor = instructor;
        if (instructor != null) instructor.addCourse(this);
    }

    public String getCode() { return code; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getCredits() { return credits; }
    public int getCapacity() { return capacity; }
    public Instructor getInstructor() { return instructor; }
    public List<Enrollment> getEnrollments() { return Collections.unmodifiableList(enrollments); }

    @Override
    public String toString() {
        return code + " " + title + " (" + credits + " cr)";
    }
}
