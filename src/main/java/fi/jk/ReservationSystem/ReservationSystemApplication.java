package fi.jk.ReservationSystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.jk.ReservationSystem.domain.Event;
import fi.jk.ReservationSystem.domain.EventRepository;
import fi.jk.ReservationSystem.domain.Reservation;
import fi.jk.ReservationSystem.domain.ReservationRepository;
import fi.jk.ReservationSystem.domain.User;
import fi.jk.ReservationSystem.domain.UserRepository;
import fi.jk.ReservationSystem.domain.Venue;
import fi.jk.ReservationSystem.domain.VenueRepository;

@SpringBootApplication
public class ReservationSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationSystemApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(EventRepository erep, ReservationRepository rrep, UserRepository urep, VenueRepository vrep) {
		return (args) -> {
			vrep.save(new Venue("Stadikka","Helsinki",300));
			vrep.save(new Venue("Hong Kong Stadium","Hong Kong",1000));
			
			urep.save(new User("jaka","123lol","jani","karvo","jk@jk.com"));
			
			erep.save(new Event("Venemessut","12.12.2022","bulit messut stadikal",100.00,4,120,vrep.findByName("Stadikka").get(0)));
			erep.save(new Event("E-sport kisa","1.1.2022","MM-kisat",100.00,4,120,vrep.findByName("Stadikka").get(0)));
			erep.save(new Event("Olympialaisten etkot","8.9.2030","Suomen jotkut olympialaiset sitten näin monen vuoden jne ja tulee uskomaton meininki"
									,500.00,30,400,vrep.findByName("Stadikka").get(0)));
			
			rrep.save(new Reservation(erep.findByName("Venemessut").get(0),urep.findByUsername("jaka").get(0)));
			rrep.save(new Reservation(erep.findByName("E-sport kisa").get(0),urep.findByUsername("jaka").get(0)));
								
			
		};
	}

}
