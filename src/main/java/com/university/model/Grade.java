package com.university.model;

/**
 * UML: «enumeration» Grade
 * Letter grades with their 4.0-scale point values.
 */
public enum Grade {
    AA(4.0), BA(3.5), BB(3.0), CB(2.5), CC(2.0), DC(1.5), DD(1.0), FF(0.0);

    private final double points;

    Grade(double points) {
        this.points = points;
    }

    public double getPoints() {
        return points;
    }

    public boolean isPassing() {
        return this != FF;
    }
}
