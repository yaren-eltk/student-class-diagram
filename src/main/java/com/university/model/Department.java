package com.university.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * UML: Department
 * Aggregation: Department ◇—— Course  (courses can exist independently of a department)
 * Association: Department "1" —— "0..*" Student
 */
public class Department {

    private final String code;
    private String name;

    private final List<Course> courses = new ArrayList<>();
    private final List<Student> students = new ArrayList<>();

    public Department(String code, String name) {
        this.code = code;
        this.name = name;
    }

    // UML operation: + addCourse(c : Course) : void   (aggregation: object is passed in)
    public void addCourse(Course course) {
        if (!courses.contains(course)) courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    void addStudent(Student s) {
        if (!students.contains(s)) students.add(s);
    }

    void removeStudent(Student s) {
        students.remove(s);
    }

    public String getCode() { return code; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Course> getCourses() { return Collections.unmodifiableList(courses); }
    public List<Student> getStudents() { return Collections.unmodifiableList(students); }

    @Override
    public String toString() {
        return code + " - " + name;
    }
}
