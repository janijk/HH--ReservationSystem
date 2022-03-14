package fi.jk.ReservationSystem.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/addevent")
	public String addNewEvent(Model model) {
		model.addAttribute("event", new Event());
		model.addAttribute("venue", vRepository.findAll());
		return "addevents";
	}
	
	// Edit existing event
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/editevent/{id}")
	public String editEvent(@PathVariable("id")Long eventsId, Model model) {
		model.addAttribute("event", eRepository.findById(eventsId));
		model.addAttribute("venue", vRepository.findAll());
		return "editevent";
	}
	
	// Save event from "create new event window"
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping("/create")
	public String createEvent(@Valid Event event, BindingResult bres, Model model) {
		if (bres.hasErrors()) {
			model.addAttribute("venue", vRepository.findAll());
	        return "addevents";
	    } else {
	    	eRepository.save(event);
	        return "redirect:events";
	    }		
	}
	
	// Save event from "edit event window"
		@PreAuthorize("hasAuthority('ADMIN')")
		@PostMapping("/edit")
		public String editEvent(@Valid Event event, BindingResult bres, Model model) {
			if (bres.hasErrors()) {
				model.addAttribute("venue", vRepository.findAll());
		        return "editevent";
		    } else {
		    	eRepository.save(event);
		        return "redirect:events";
		    }		
		}
	
	// Make reservation
	@PostMapping("/book/{id}/{idtwo}")
	public String bookEvent(@PathVariable("id") Long eventId,@PathVariable("idtwo") Long userId,Model model) {
		model.addAttribute("event", eRepository.findById(eventId));
		model.addAttribute("user", uRepository.findById(userId));
		return "reservations";
	}
	
	// Delete event
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/delete/{id}")
	public String deleteEvent(@PathVariable("id") Long eventId) {
		eRepository.deleteById(eventId);
		return "redirect:../events";
	}
	
	// Cancel reservation
	@GetMapping("/remove/{id}")
	public String removeRes(@PathVariable("id") Long resId) {
		rRepository.deleteById(resId);
		return "redirect:/reservations";
	}

}
