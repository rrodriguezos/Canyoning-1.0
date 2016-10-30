package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Wetsuit;

@Repository
public interface WetsuitRepository extends JpaRepository<Wetsuit, Integer> {

}
