package fi.jk.ReservationSystem;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class ReservationSystemRestTest {
	@Autowired WebApplicationContext WAC;
	
	private MockMvc mMvc;
	
	@BeforeEach
	public void create() {
		this.mMvc = MockMvcBuilders.webAppContextSetup(WAC).build();
	}
	
	@Test
	public void isThisGood() throws Exception {
		mMvc.perform(get("/events")).andExpect(status().isOk());
	}
	
	@Test
	public void testJson() throws Exception {
		mMvc.perform(get("/eventapi"))
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	public void apiStatus() throws Exception {
		mMvc.perform(get("/api/events")).andExpect(status().isOk());
	}

}
