package fi.jk.ReservationSystem.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Reservation {	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long reservationId;
	
	@ManyToOne
	@JoinColumn(name="eventId")
	private Event event;
	
	@ManyToOne
	@JoinColumn(name="userId")
	private User user;	
	
	public Reservation() {
		
	}

	public Reservation(Event event, User user) {
		super();
		this.event = event;
		this.user = user;
	}

	public Long getReservationId() {
		return reservationId;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	

}
