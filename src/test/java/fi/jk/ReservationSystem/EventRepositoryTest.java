package fi.jk.ReservationSystem;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test; //if doesnt work change junit.api
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import fi.jk.ReservationSystem.domain.Event;
import fi.jk.ReservationSystem.domain.EventRepository;

@DataJpaTest
// @AutoConfigureTestDatabase(replace=Replace.NONE)
public class EventRepositoryTest { 
	@Autowired
	private EventRepository eRep;
	
	@Test		//Find event by its name works
	public void findByNameTest() {
		List <Event> events = eRep.findByName("Venemessut");
		assertThat(events.get(0).getName()).isEqualTo("Venemessut");
	}
	
	@Test		//Events can be created
	public void createEvent() {
		Event event = new Event("Messu","1.1.2022","kuvaus tapahtumasta",10.0,2,10,null);
		eRep.save(event);
		assertThat(event.getEventId()).isNotNull();
	}
	
	@Test		//Events can be deleted
	public void deleteEvent() {
		List <Event> events = eRep.findByName("Venemessut");
		Event evnt = events.get(0);
		eRep.delete(evnt);
		List <Event> isDeleted = eRep.findByName("Venemessut");
		assertThat(isDeleted).hasSize(0);		
	}
	
	@Test		//Events can be edited
	public void editEvent() {
		List <Event> events = eRep.findByName("Venemessut");
		Event evnt = events.get(0);
		evnt.setDate("3.3.2022");
		evnt.setName("Vene");
		assertThat(evnt.getDate()).isEqualTo("3.3.2022");
		assertThat(evnt.getName()).isEqualTo("Vene");
	}	

}
