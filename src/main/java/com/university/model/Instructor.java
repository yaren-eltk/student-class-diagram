package com.university.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UML: Instructor —▷ Person   (generalization → "extends")
 */
public class Instructor extends Person {

    // UML attribute: - title : String   (e.g. "Dr.", "Prof.")
    private String title;

    // UML association: Instructor "1" —— "0..*" Course (teaches)
    private final List<Course> courses = new ArrayList<>();

    // UML association: Instructor "0..1" —— "0..*" Student (advises)
    private final List<Student> advisees = new ArrayList<>();

    public Instructor(String id, String firstName, String lastName, String email, String title) {
        super(id, firstName, lastName, email);
        this.title = title;
    }

    @Override
    public String getRole() {
        return "Instructor";
    }

    @Override
    public String getFullName() {
        return title + " " + super.getFullName();
    }

    // UML operation: + assignGrade(e : Enrollment, g : Grade) : void
    public void assignGrade(Enrollment enrollment, Grade grade) {
        if (!courses.contains(enrollment.getCourse())) {
            throw new IllegalStateException(getFullName() + " does not teach "
                    + enrollment.getCourse().getCode());
        }
        enrollment.setGrade(grade);
    }

    void addCourse(Course course) {
        if (!courses.contains(course)) courses.add(course);
    }

    void removeCourse(Course course) {
        courses.remove(course);
    }

    void addAdvisee(Student s) {
        if (!advisees.contains(s)) advisees.add(s);
    }

    void removeAdvisee(Student s) {
        advisees.remove(s);
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public List<Course> getCourses() { return Collections.unmodifiableList(courses); }
    public List<Student> getAdvisees() { return Collections.unmodifiableList(advisees); }
}
