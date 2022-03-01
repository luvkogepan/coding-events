package org.launchcode.codingevents.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chris Bay
 */
@Entity
public class EventCategory extends AbstractEntity{

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @OneToMany(mappedBy = "eventCategory")
    private List<Event> events = new ArrayList<>();

    public EventCategory() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
