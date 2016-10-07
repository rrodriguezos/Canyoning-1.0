package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Canyon;

@Repository
public interface CanyonRepository extends JpaRepository<Canyon, Integer> {

	

	@Query("select a.canyon from Activity a where a.id = ?1")
	Canyon canyonByActivity(int activityId);

}
