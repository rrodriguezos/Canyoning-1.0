package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Cord;

@Repository
public interface CordRepository extends JpaRepository<Cord, Integer> {

}
