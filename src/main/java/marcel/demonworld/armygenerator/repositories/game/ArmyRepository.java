package marcel.demonworld.armygenerator.repositories.game;

import marcel.demonworld.armygenerator.dto.game.UnitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ArmyRepository extends JpaRepository<UnitCard, Integer> {
}
