package com.university.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UML: Student —▷ Person   (generalization → "extends")
 */
public class Student extends Person {

    public static final int MAX_CREDITS_PER_TERM = 30;

    // UML attributes: - studentNumber : String, - enrollmentYear : int
    private final String studentNumber;
    private final int enrollmentYear;

    // UML association: Student "0..*" —— "1" Department  (majorsIn)
    private Department department;

    // UML association: Student "0..*" —— "0..1" Instructor (advisor)
    private Instructor advisor;

    // UML association class: Student "1" —— "0..*" Enrollment  → List
    private final List<Enrollment> enrollments = new ArrayList<>();

    public Student(String id, String firstName, String lastName, String email,
                   String studentNumber, int enrollmentYear) {
        super(id, firstName, lastName, email);
        this.studentNumber = studentNumber;
        this.enrollmentYear = enrollmentYear;
    }

    @Override
    public String getRole() {
        return "Student";
    }

    // UML operation: + enroll(course : Course, term : String) : Enrollment
    public Enrollment enroll(Course course, String term) {
        for (Enrollment e : enrollments) {
            if (e.getCourse().equals(course) && e.getTerm().equals(term)) {
                throw new IllegalStateException(getFullName() + " is already enrolled in "
                        + course.getCode() + " for " + term);
            }
        }
        if (getCreditsForTerm(term) + course.getCredits() > MAX_CREDITS_PER_TERM) {
            throw new IllegalStateException("Credit limit exceeded for " + term);
        }
        Enrollment enrollment = new Enrollment(this, course, term);
        course.addEnrollment(enrollment); // throws if full; keeps both ends consistent
        enrollments.add(enrollment);
        return enrollment;
    }

    // UML operation: + drop(course : Course, term : String) : boolean
    public boolean drop(Course course, String term) {
        for (Enrollment e : enrollments) {
            if (e.getCourse().equals(course) && e.getTerm().equals(term) && e.getGrade() == null) {
                enrollments.remove(e);
                course.removeEnrollment(e);
                return true;
            }
        }
        return false;
    }

    // UML operation: + calculateGPA() : double
    public double calculateGPA() {
        double totalPoints = 0;
        int totalCredits = 0;
        for (Enrollment e : enrollments) {
            if (e.getGrade() != null) {
                totalPoints += e.getGrade().getPoints() * e.getCourse().getCredits();
                totalCredits += e.getCourse().getCredits();
            }
        }
        return totalCredits == 0 ? 0.0 : totalPoints / totalCredits;
    }

    // UML operation: + getCompletedCredits() : int
    public int getCompletedCredits() {
        int sum = 0;
        for (Enrollment e : enrollments) {
            if (e.getGrade() != null && e.getGrade().isPassing()) {
                sum += e.getCourse().getCredits();
            }
        }
        return sum;
    }

    public int getCreditsForTerm(String term) {
        int sum = 0;
        for (Enrollment e : enrollments) {
            if (e.getTerm().equals(term)) {
                sum += e.getCourse().getCredits();
            }
        }
        return sum;
    }

    /** Sets both ends of the Student—Department association. */
    public void setDepartment(Department department) {
        if (this.department == department) return;
        if (this.department != null) this.department.removeStudent(this);
        this.department = department;
        if (department != null) department.addStudent(this);
    }

    public void setAdvisor(Instructor advisor) {
        if (this.advisor == advisor) return;
        if (this.advisor != null) this.advisor.removeAdvisee(this);
        this.advisor = advisor;
        if (advisor != null) advisor.addAdvisee(this);
    }

    public String getStudentNumber() { return studentNumber; }
    public int getEnrollmentYear() { return enrollmentYear; }
    public Department getDepartment() { return department; }
    public Instructor getAdvisor() { return advisor; }
    public List<Enrollment> getEnrollments() { return Collections.unmodifiableList(enrollments); }
}
