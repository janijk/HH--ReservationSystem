package fi.jk.ReservationSystem.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long userId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="user")
	private List<Reservation> reservations;
		
	@Column(name="username",nullable = false, unique = true)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;
	
	private String firstName, lastName, email;
	
	public User() {
		
	}

	public User(String username, String password, String firstName, String lastName,
			String email) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}
	
	

}
