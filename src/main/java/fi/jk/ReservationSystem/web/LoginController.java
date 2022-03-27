package fi.jk.ReservationSystem.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	// Login page
		@GetMapping("/login")
		public String logIn() {
			return "login";
		}

}
