package marcel.demonworld.armygenerator.repositories.game;

import marcel.demonworld.armygenerator.entities.game.UnitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UnitCardRepository extends JpaRepository<UnitCard, Integer> {
}
