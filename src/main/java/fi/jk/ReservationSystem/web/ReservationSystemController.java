package fi.jk.ReservationSystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fi.jk.ReservationSystem.domain.Event;
import fi.jk.ReservationSystem.domain.EventRepository;
import fi.jk.ReservationSystem.domain.ReservationRepository;
import fi.jk.ReservationSystem.domain.UserRepository;
import fi.jk.ReservationSystem.domain.Venue;
import fi.jk.ReservationSystem.domain.VenueRepository;

@Controller
public class ReservationSystemController {
	@Autowired
	private EventRepository eRepository;
	@Autowired
	private VenueRepository vRepository;
	@Autowired
	private ReservationRepository rRepository;
	@Autowired
	private UserRepository uRepository;
	
	// List events
	@GetMapping(value="/events")
	public String showEvents(Model model) {
		model.addAttribute("events", eRepository.findAll());
		return "events";
	}
	
	// Show users reservations
	@GetMapping("/reservations")
	public String showReservations(Model model) {
		model.addAttribute("reservations", rRepository.findAll());
		return "reservations";
	}
	
	// Create new event
	@GetMapping("/addevent")
	public String addNewEvent(Model model) {
		model.addAttribute("event", new Event());
		model.addAttribute("venue", vRepository.findAll());
		return "addevents";
	}
	
	// Edit existing event
	@GetMapping("/editevent/{id}")
	public String editEvent(@PathVariable("id")Long eventsId, Model model) {
		model.addAttribute("event", eRepository.findById(eventsId));
		model.addAttribute("venue", vRepository.findAll());
		return "editevent";
	}
	
	// Create event
	@PostMapping("/create")
	public String createEvent(Event event) {
		eRepository.save(event);
		return "redirect:events";
	}
	
	// Delete event
	@GetMapping("/delete/{id}")
	public String deleteEvent(@PathVariable("id") Long eventId, Model model) {
		eRepository.deleteById(eventId);
	return "redirect:../events";
	}

}
