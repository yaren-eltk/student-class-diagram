package com.university.model;

/**
 * UML: «abstract» Person
 * Generalization (inheritance) root for Student and Instructor.
 */
public abstract class Person {

    // UML attributes: - id : String, - firstName : String, - lastName : String, - email : String
    private final String id;
    private String firstName;
    private String lastName;
    private String email;

    // UML composition: Person ◆—— 1 Address (Address cannot exist without its Person)
    private Address address;

    protected Person(String id, String firstName, String lastName, String email) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be empty");
        }
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        setEmail(email);
    }

    // UML operation: + getFullName() : String
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // UML operation (abstract): + getRole() : String
    public abstract String getRole();

    /** Composition: the Address object is created and owned by this Person. */
    public void setAddress(String street, String city, String postalCode, String country) {
        this.address = new Address(street, city, postalCode, country);
    }

    public String getId() { return id; }
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public Address getAddress() { return address; }

    public void setEmail(String email) {
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("invalid email: " + email);
        }
        this.email = email;
    }

    @Override
    public String toString() {
        return getRole() + "[" + id + ", " + getFullName() + "]";
    }
}
