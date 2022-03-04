package org.launchcode.codingevents.models;

import org.apache.catalina.User;
import org.aspectj.apache.bcel.generic.Tag;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Chris Bay
 */
@Entity
public class Event extends AbstractEntity{

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be at least 3 characters and no longer than 50 characters")
    private String name;

    //sets up relationship between events and event categories
    @ManyToOne
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;

    @ManyToMany
    private final List<Tag> tags = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private EventDetails eventDetails;

    public Event(String name) {
        this.name = name;
    }

    // no-arg constructor
    public Event () {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    @Override
    public String toString() {
        return this.name;
    }


}
