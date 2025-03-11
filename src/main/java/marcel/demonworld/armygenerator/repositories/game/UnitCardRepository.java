package marcel.demonworld.armygenerator.repositories.game;

import marcel.demonworld.armygenerator.entities.game.UnitCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UnitCardRepository extends JpaRepository<UnitCard, Integer> {

    @Query("SELECT UnitCard u FROM units u WHERE u.unitName = :passedName AND u.faction = :passedFaction")
    Optional<UnitCard> findByNameAndFaction(@Param("passedName") String name, @Param("passedFaction") String faction);
}
