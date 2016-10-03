package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Organiser;

public interface UserRepository extends JpaRepository<Organiser, Integer> {

	@Query("select c from User c where c.userAccount.id=?1")
	Organiser findByUserAccountId(int userAccountId);


	@Query("select t.users from Trip t where t.id=?1") 
	Collection<Organiser> usersSusTrip(int tripId);

	// The users who have registered at least 80% the maximum number of trips that a user has registered.
	@Query("select u from User u where u.trips.size >= 0.8*(select max(u2.trips.size) from User u2)")
	Collection<Organiser> usersWhoRegisteredAtLeast80Maximum();

	@Query("select u from User u where u.lastLogin < ?1)")
	Collection<Organiser> usersWhoInactiveMoreThanYear(Date unAno);

	@Query("select c from User c where c.userAccount.username=?1")
	Organiser findByUserName(String username);
}
