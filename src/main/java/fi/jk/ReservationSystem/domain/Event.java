package fi.jk.ReservationSystem.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long eventId;
	
	@Size(min=2, max=30, message="Name length must be between 2 and 30 characters")
	private String name;
		
	@Pattern(regexp ="^[0-9]{1,2}[.]{1}[0-9]{1,2}[.]{1}[0-9]{4}", message="Give date in form days.months.years")
	private String date;
	
	@Size(max=100, message="Descriptions max length is 100 characters")
	private String description;
	
	@NotNull(message="Price cant be empty, if event is free of charge, type 0")
	private Double price;
		
	@Min(value=1, message="Duration has to be at least 1")
	private int duration;
		
	@Min(value=1, message="Capacity has to be at least 1")
	private int capacity;
	
	@ManyToOne
	@JoinColumn(name="venueId")
	private Venue venue;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="event")
	private List<Reservation> reservations;
	
	public Event () {}	
	

	public Event(String name, String date, String description, Double price, int duration, int capacity,
			Venue venue) {
		super();
		this.name = name;
		this.date = date;
		this.description = description;
		this.price = price;
		this.duration = duration;
		this.capacity = capacity;
		this.venue = venue;
	}	

	public Long getEventId() {
		return eventId;
	}


	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Double getPrice() {
		return price;
	}


	public void setPrice(Double price) {
		this.price = price;
	}


	public int getDuration() {
		return duration;
	}


	public void setDuration(int duration) {
		this.duration = duration;
	}


	public int getCapacity() {
		return capacity;
	}


	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}


	public Venue getVenue() {
		return venue;
	}


	public void setVenue(Venue venue) {
		this.venue = venue;
	}


	public List<Reservation> getReservations() {
		return reservations;
	}


	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

		

}
