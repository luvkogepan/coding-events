package org.launchcode.codingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("events")
public class EventController {

//    private static List<String> events = new ArrayList<>();
    private HashMap<String, String> events = new HashMap<>();

//    @GetMapping
//    public String displayAllEvents(Model model) {
////        events.add("Code with Pride");
////        events.add("Strange Loop");
////        events.add("Apple WWDC");
////        events.add("SpringOne Platform");
//        model.addAttribute("events", events);
//        return "events/index";
//    }

    @GetMapping
    public String displayAllEvents(Model model) {
       events.put("WWDC", "Apple convention");
       events.put("Developer Week", "Developer conference");
       events.put("Cisco Live", "Cisco convention");
       model.addAttribute("events", events);
       return "events/index";
    }

    @GetMapping("create")
    public String renderCreateEventForm() {
        return "events/create";
    }
    //lives at same place as above getmapping request. ok because one is get and one is post
    @PostMapping("create")
    public String createEvent(@RequestParam String eventName) {
//        events.add(eventName);
        events.put("name", "description");
        //redirects to root path(displayAllEvents)
        return "redirect:";
    }
}
