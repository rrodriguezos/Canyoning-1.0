package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Customer;

public interface ManagerRepository extends JpaRepository<Customer, Integer>{

	@Query("select m from Manager m where m.userAccount.id=?1")
	Customer findByUserAccountId(int userAccountId);
	
	@Query("select min(m.campaigns.size) from Manager m")
	Integer minimumNumberOfCampaignsPerManager();

	@Query("select max(m.campaigns.size) from Manager m")
	Integer maximumNumberOfCampaignsPerManager();

	@Query("select avg(m.campaigns.size) from Manager m")
	Double averageNumberOfCampaignsPerManager();

	@Query("select m from Manager m where m.campaigns.size = (select max(ms.campaigns.size) from Manager ms)")
	Collection<Customer> managersMoreCampaigns();
}
