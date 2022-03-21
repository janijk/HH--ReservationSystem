package fi.jk.ReservationSystem;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import fi.jk.ReservationSystem.web.ReservationSystemController;
import fi.jk.ReservationSystem.web.ReservationSystemRestController;

@SpringBootTest
class ReservationSystemApplicationTests {
	
	@Autowired
	private ReservationSystemController resSysCont;
	
	@Autowired
	private ReservationSystemRestController resSysRestCont;
	
				//Smoke testing
	@Test
	public void contLoad() throws Exception {
		assertThat(resSysCont).isNotNull();
	}
	
	@Test
	public void contLoadTwo() throws Exception {
		assertThat(resSysRestCont).isNotNull();
	}

}
