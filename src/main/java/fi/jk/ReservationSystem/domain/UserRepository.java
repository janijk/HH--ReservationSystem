package fi.jk.ReservationSystem.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);
	List <User> findListByUsername(String username);

}
