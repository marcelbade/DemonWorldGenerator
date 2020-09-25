package marcel.demonworld.armygenerator.repositories;

import marcel.demonworld.armygenerator.dto.statCardDTOs.UnitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArmyRepository extends JpaRepository<UnitCard, Integer> {
}
