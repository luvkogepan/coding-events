package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;


/**
 * Created by Chris Bay
 */
@Entity
public class Event extends AbstractEntity{

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be at least 3 characters and no longer than 50 characters")
    private String name;

    @Size(max = 500, message = "Max description length is 500")
    private String description;

    @Email(message = "Invalid email")
    private String contactEmail;

    @NotNull
    private EventType type;

    public Event(String name, String description, String contactEmail) {
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;

    }

    // no-arg constructor
    public Event () {}

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

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return this.name;
    }


}
