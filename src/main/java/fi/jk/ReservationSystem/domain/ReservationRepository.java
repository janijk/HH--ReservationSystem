package fi.jk.ReservationSystem.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ReservationRepository extends CrudRepository<Reservation, Long> {	
	List<Reservation> findByUser(@Param("id") String id);

}
