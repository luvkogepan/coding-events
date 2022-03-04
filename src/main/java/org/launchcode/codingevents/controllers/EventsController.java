package org.launchcode.codingevents.controllers;

import org.aspectj.apache.bcel.generic.Tag;
import org.launchcode.codingevents.data.EventCategoryRepository;
import org.launchcode.codingevents.data.EventRepository;
import org.launchcode.codingevents.data.TagRepository;
import org.launchcode.codingevents.models.Event;
import org.launchcode.codingevents.models.EventCategory;
import org.launchcode.codingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

/**
 * Created by Chris Bay
 */
@Controller
@RequestMapping(value = "events")
public class EventsController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private TagRepository tagRepository;

    // Lives at /events
    @GetMapping("")
    public String displayEvents(@RequestParam(required = false) Integer categoryId, Model model) {

        if (categoryId==null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
        } else {
            //http://localhost:8080/events?categoryId=2
            Optional<EventCategory> optCategory = eventCategoryRepository.findById(categoryId);
            if (optCategory.isPresent()) {
                EventCategory eventCategory = optCategory.get();
                model.addAttribute("events", eventCategory.getEvents());
                model.addAttribute("title", "Events in Category: " + eventCategory.getName());

            } else {
                model.addAttribute("title", "Invalid Category ID");
            }
        }

        return "events/index";
    }

    // lives at /events/create
    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create New Event");
        model.addAttribute("event", new Event());
        model.addAttribute("eventCategories", eventCategoryRepository.findAll());
        return "events/create";
    }

    // lives at /events/create
    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "events/create";
        }

        eventRepository.save(newEvent);
        return "redirect:";
    }

    @GetMapping("delete")
    public String renderDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Event");
        model.addAttribute("events", eventRepository.findAll());
        return "events/delete";
    }

    @PostMapping("delete")
    public String processDeleteEventsForm(@RequestParam(required = false) int[] eventIds) {

        if (eventIds != null) {
            for (int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventRepository.findById(eventId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: "+eventId);
        } else {
            Event event = result.get();
            model.addAttribute("title", event.getName()+ " Details");
            model.addAttribute("event", event);
        }
        return "events/detail";
    }

    //responds to /events/add-tag?eventId=13
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Tag to: "+event.getName());
        model.addAttribute("tags", tagRepository.findAll());
        model.addAttribute("event", event);
        model.addAttribute("eventTag", new EventTagDTO());
//        EventTagDTO eventTag = new EventTagDTO();
//        eventTag.setEvent(event);
//        model.addAttribute("eventTag", eventTag);
        return "events/add-tag.html";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag, Model model, Errors errors) {
        if (!errors.hasErrors()) {
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if (!event.getTags().contains(tag)) {
                event.addTag(tag);
                eventRepository.save(event);
            }
            return "redirect:detail?eventId="+event.getId();
        }
        return "redirect:add-tag";
    }

}
