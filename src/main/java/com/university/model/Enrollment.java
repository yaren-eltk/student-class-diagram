package com.university.model;

import java.time.LocalDate;

/**
 * UML: Enrollment  (association class between Student and Course)
 * Holds the data that belongs to the link itself: term, date and grade.
 */
public class Enrollment {

    private final Student student;
    private final Course course;
    private final String term;
    private final LocalDate enrollmentDate;
    private Grade grade; // null until graded

    // package-private: created only through Student.enroll(...)
    Enrollment(Student student, Course course, String term) {
        this.student = student;
        this.course = course;
        this.term = term;
        this.enrollmentDate = LocalDate.now();
    }

    void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public String getTerm() { return term; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public Grade getGrade() { return grade; }

    @Override
    public String toString() {
        return student.getStudentNumber() + " -> " + course.getCode() + " [" + term + "] "
                + (grade == null ? "not graded" : grade);
    }
}
