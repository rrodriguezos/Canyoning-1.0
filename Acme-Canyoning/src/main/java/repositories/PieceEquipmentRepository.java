package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.PieceEquipment;

@Repository
public interface PieceEquipmentRepository extends
		JpaRepository<PieceEquipment, Integer> {
}
