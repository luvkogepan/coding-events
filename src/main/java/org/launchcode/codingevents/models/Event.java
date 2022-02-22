package org.launchcode.codingevents.models;

import javax.validation.constraints.*;
import java.util.Objects;


/**
 * Created by Chris Bay
 */
public class Event {

    private int id;
    private static int nextId = 1;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be at least 3 characters and no longer than 50 characters")
    private String name;

    @Size(max = 500, message = "Max description length is 500")
    private String description;

    @NotBlank(message="Email is required")
    @Email(message = "Invalid email")
    private String contactEmail;

    @NotBlank(message="Location required")
    private String location;

    @Min(value=0, message="Number of attendees can not be negative")
    private int numAttendees;

    @AssertTrue(message="Must require registration")
    private boolean registrationRequired;

    @Positive(message="Price must be positive")
    private double price;

    public Event(String name, String description, String contactEmail, String location) {
        this();
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.location=location;

    }

    // no-arg constructor
    public Event () {
        this.id = nextId;
        nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNumAttendees() {
        return numAttendees;
    }

    public void setNumAttendees(int numAttendees) {
        this.numAttendees = numAttendees;
    }

    public boolean isRegistrationRequired() {
        return registrationRequired;
    }

    public void setRegistrationRequired(boolean registrationRequired) {
        this.registrationRequired = registrationRequired;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
