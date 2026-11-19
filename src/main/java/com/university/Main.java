package com.university;

import com.university.model.*;

/**
 * Demo that exercises every class and relationship in the diagram.
 */
public class Main {

    public static void main(String[] args) {
        Department ceng = new Department("CENG", "Computer Engineering");

        Instructor drYilmaz = new Instructor("I-001", "Ayse", "Yilmaz", "ayse.yilmaz@uni.edu.tr", "Dr.");
        Instructor profKaya = new Instructor("I-002", "Mehmet", "Kaya", "mehmet.kaya@uni.edu.tr", "Prof.");

        Course se = new Course("CENG301", "Software Engineering", 6, 40);
        Course db = new Course("CENG302", "Database Systems", 5, 40);
        se.setInstructor(drYilmaz);
        db.setInstructor(profKaya);
        ceng.addCourse(se);
        ceng.addCourse(db);

        Student ali = new Student("P-100", "Ali", "Demir", "ali.demir@uni.edu.tr", "2023001", 2023);
        ali.setAddress("Ataturk Cad. No:10", "Istanbul", "34000", "Turkey");
        ali.setDepartment(ceng);
        ali.setAdvisor(drYilmaz);

        Student zeynep = new Student("P-101", "Zeynep", "Arslan", "zeynep.arslan@uni.edu.tr", "2023002", 2023);
        zeynep.setDepartment(ceng);
        zeynep.setAdvisor(drYilmaz);

        Enrollment e1 = ali.enroll(se, "2026-Fall");
        Enrollment e2 = ali.enroll(db, "2026-Fall");
        Enrollment e3 = zeynep.enroll(se, "2026-Fall");

        drYilmaz.assignGrade(e1, Grade.AA);
        profKaya.assignGrade(e2, Grade.BB);
        drYilmaz.assignGrade(e3, Grade.CB);

        System.out.println("=== Department ===");
        System.out.println(ceng + " | courses: " + ceng.getCourses().size()
                + " | students: " + ceng.getStudents().size());

        System.out.println("\n=== Students ===");
        for (Student s : ceng.getStudents()) {
            System.out.println(s);
            System.out.println("  Address : " + (s.getAddress() == null ? "-" : s.getAddress()));
            System.out.println("  Advisor : " + s.getAdvisor().getFullName());
            for (Enrollment e : s.getEnrollments()) {
                System.out.println("  " + e);
            }
            System.out.printf("  GPA     : %.2f | completed credits: %d%n",
                    s.calculateGPA(), s.getCompletedCredits());
        }

        System.out.println("\n=== Course rosters ===");
        for (Course c : ceng.getCourses()) {
            System.out.println(c + " - " + c.getInstructor().getFullName()
                    + " -> " + c.getEnrolledStudents().size() + " student(s)");
        }

        System.out.println("\n=== Business rules ===");
        try {
            ali.enroll(se, "2026-Fall");
        } catch (IllegalStateException ex) {
            System.out.println("Blocked duplicate enrollment: " + ex.getMessage());
        }
        try {
            profKaya.assignGrade(e1, Grade.FF);
        } catch (IllegalStateException ex) {
            System.out.println("Blocked wrong instructor: " + ex.getMessage());
        }
    }
}
