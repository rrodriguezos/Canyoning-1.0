package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Request;



@Repository 
public interface RequestRepository extends JpaRepository<Request, Integer> {

	
	
	@Query("select r from Request r where r.customer.id=?1 and r.requestState = 'PENDING' ")
	Collection<Request> requestPendingByCustomer(int customerId);
	
	@Query("select r from Request r where r.customer.id=?1 and r.requestState = 'REJECTED' ")
	Collection<Request> requestRejectByCustomer(int customerId);
	
	@Query("select r from Request r where r.customer.id=?1 and r.requestState = 'ACCEPTED' ")
	Collection<Request> requestAcceptedByCustomer(int customerId);

	@Query("select r from Request r where r.activity.id=?1 and r.requestState = 'ACCEPTED' ")
	Collection<Request> requestAcceptedByActivity(int activityId);

	@Query("select c.requests from Customer c where c.id = ?1")
	Collection<Request> requestByCustomer(int id);

//	@Query("select avg(o.activity.requests.size) from Organiser o where o.activity.requests.requestState = 'PENDING'")
//	Double averageCustomersInWaitingList();

	@Query("select r from Request r where r.activity.id=?1 and r.requestState = 'PENDING' ")
	Collection<Request> requestsPendingByActivity(int activityId);
	


}
