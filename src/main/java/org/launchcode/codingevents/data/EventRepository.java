package org.launchcode.codingevents.data;

import org.launchcode.codingevents.models.Event;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Chris Bay
 */
public interface EventRepository extends CrudRepository<Event, Integer> {
}
