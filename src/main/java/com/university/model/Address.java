package com.university.model;

/**
 * UML: Address
 * Part side of the composition Person ◆—— Address.
 * Immutable value object; constructor is package-private so only Person creates it.
 */
public final class Address {

    private final String street;
    private final String city;
    private final String postalCode;
    private final String country;

    Address(String street, String city, String postalCode, String country) {
        this.street = street;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public String getStreet() { return street; }
    public String getCity() { return city; }
    public String getPostalCode() { return postalCode; }
    public String getCountry() { return country; }

    @Override
    public String toString() {
        return street + ", " + postalCode + " " + city + ", " + country;
    }
}
