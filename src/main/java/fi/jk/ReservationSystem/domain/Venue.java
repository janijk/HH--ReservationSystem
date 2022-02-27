package fi.jk.ReservationSystem.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Venue {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long venueId;
	private String name,location;
	private int capacity;	
	
	public Venue () {}
	
	

}
