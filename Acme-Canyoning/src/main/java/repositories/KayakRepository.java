package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Kayak;

@Repository
public interface KayakRepository extends JpaRepository<Kayak, Integer> {

}
