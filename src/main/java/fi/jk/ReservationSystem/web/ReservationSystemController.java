package fi.jk.ReservationSystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import fi.jk.ReservationSystem.domain.EventRepository;
import fi.jk.ReservationSystem.domain.VenueRepository;

@Controller
public class ReservationSystemController {
	@Autowired
	private EventRepository eRepository;
	@Autowired
	private VenueRepository vRepository;
	

}
