package fi.jk.ReservationSystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long eventId;
	private String name,date,description;
	private Double price;
	private int duration,capacity;
	
	public Event () {}

}
