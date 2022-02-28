package org.launchcode.codingevents.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by Chris Bay
 */
@Entity
public class EventCategory extends AbstractEntity{

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    public EventCategory() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
