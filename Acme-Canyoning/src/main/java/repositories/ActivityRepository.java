package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository; 

import domain.Activity; 

@Repository 
public interface ActivityRepository extends JpaRepository<Activity, Integer>{ 

	@Query("select d from DailyPlan d where d.trip.id = ?1")
	Collection<Activity> activityByCanyon(int canyonId);

	
	@Query("select a from Activity a where a.title like CONCAT(?1, '%') or a.description like CONCAT(?1, '%')")
	Collection<Activity> findActivityByKeyword(String text);


	
	
	@Query("select c.activity from Canyon c where c.name like CONCAT(?1, '%') or c.description like CONCAT(?1, '%')")
	Collection<Activity> findActivityByCanyonKeyword(String text);
} 
