package fi.jk.ReservationSystem.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fi.jk.ReservationSystem.domain.Event;
import fi.jk.ReservationSystem.domain.EventRepository;

@RestController
public class ReservationSystemRestController {
	@Autowired
	private EventRepository erep;
	
		//Fetch all events json
	@GetMapping("/eventapi")
	public List<Event> eventsRest(){
		return (List<Event>) erep.findAll();
	}
		//Fetch specific event json
	@GetMapping("/eventapi/{id}")
	public Optional<Event> findEvent(@PathVariable("id") Long eventId){
		return erep.findById(eventId);
	}
}
